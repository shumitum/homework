package org.example.database;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.example.out.Output;
import org.example.properties.AppProperties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbPreparation {
    private DbPreparation() {
    }

    public static void createTables() {
        try (Connection connection = DbConnection.getConnection()) {
            String liquibaseSchemaName = AppProperties.getProperty("liquibase.schema");
            createSchema(liquibaseSchemaName, connection);
            Database database =
                    DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            database.setLiquibaseSchemaName(liquibaseSchemaName);
            Liquibase liquibase =
                    new Liquibase(AppProperties.getProperty("liquibase.changelogPath"), new ClassLoaderResourceAccessor(), database);
            liquibase.update();
        } catch (LiquibaseException exc) {
            Output.printMessage("SQL Exception in migration " + exc.getMessage());
        } catch (SQLException e) {
            Output.printMessage(e.getMessage());
        }
    }

    private static void createSchema(String schemaName, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("CREATE SCHEMA IF NOT EXISTS " + schemaName)) {
            preparedStatement.executeUpdate();
        }
    }
}
