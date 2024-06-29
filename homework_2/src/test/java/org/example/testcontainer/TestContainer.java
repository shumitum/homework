package org.example.testcontainer;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.example.properties.AppProperties;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class TestContainer {

    private static final String USER = "name";
    private static final String PASSWORD = "password";

    @Container
    private static PostgreSQLContainer<?> postgres;

    private TestContainer() {
    }

    public static PostgreSQLContainer<?> getPostgresContainer() {
        if (postgres == null) {
            postgres = new PostgreSQLContainer<>("postgres:14-alpine")
                    .withDatabaseName("db_name")
                    .withUsername(USER)
                    .withPassword(PASSWORD);
            postgres.setPortBindings(List.of("6545:5432"));
            postgres.start();
            createTables();
        }
        return postgres;
    }

    private static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(postgres.getJdbcUrl(), USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Got SQL Exception " + e.getMessage());
        }
        return connection;
    }

    private static void createTables() {
        try (Connection connection = getConnection()) {
            String liquibaseSchemaName = AppProperties.getProperty("liquibase.schema");
            createSchema(liquibaseSchemaName, connection);
            Database database =
                    DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            database.setLiquibaseSchemaName(liquibaseSchemaName);
            Liquibase liquibase =
                    new Liquibase(AppProperties.getProperty("liquibase.changelogPath"), new ClassLoaderResourceAccessor(), database);
            liquibase.update();
        } catch (LiquibaseException exc) {
            System.out.println("SQL Exception in migration " + exc.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createSchema(String schemaName, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("CREATE SCHEMA IF NOT EXISTS " + schemaName)) {
            preparedStatement.executeUpdate();
        }
    }

    @Test
    void connectionEstablished() {
        PostgreSQLContainer<?> postgresContainer = TestContainer.getPostgresContainer();

        assertThat(postgresContainer.isCreated()).isTrue();
        assertThat(postgresContainer.isRunning()).isTrue();
    }
}
