package org.example.workplace.impl;

import lombok.Getter;
import org.example.crud.CrudRepository;
import org.example.database.DbConnection;
import org.example.out.Output;
import org.example.workplace.model.Workplace;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Getter
public class WorkPlaceRepositoryImpl implements CrudRepository<Workplace> {

    @Override
    public void save(Workplace workplace) {
        if (workplace != null) {
            String newWorkplace = "INSERT INTO data.workplaces (floor) VALUES (?)";
            try (Connection connection = DbConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(newWorkplace)) {
                connection.setAutoCommit(false);
                preparedStatement.setInt(1, workplace.getFloor());
                preparedStatement.executeUpdate();
                connection.commit();
                Output.printMessage("Рабочее место создано");
            } catch (SQLException e) {
                Output.printMessage("Got SQL Exception in transaction " + e.getMessage());
            }
        } else {
            Output.printMessage("Рабочее место is null or have existing id, Рабочее место wasn't saved");
        }
    }

    @Override
    public void update(Workplace workplace) {
        if (existsById(workplace.getWorkplaceId())) {
            String newWorkplace = "UPDATE data.workplaces SET floor = ?  WHERE workplace_id = ?";
            try (Connection connection = DbConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(newWorkplace)) {
                connection.setAutoCommit(false);
                preparedStatement.setInt(1, workplace.getFloor());
                preparedStatement.setInt(2, workplace.getWorkplaceId());
                preparedStatement.executeUpdate();
                connection.commit();
                Output.printMessage("Данные рабочего места обновлены - " + workplace);
            } catch (SQLException e) {
                Output.printMessage("Got SQL Exception in transaction " + e.getMessage());
            }
        } else {
            throw new NoSuchElementException("Рабочее место not found, Рабочее место wasn't updated");
        }
    }

    @Override
    public void delete(Integer id) {
        if (existsById(id)) {
            String deleteWorkplace = "DELETE FROM data.workplaces WHERE workplace_id = ?";
            try (Connection connection = DbConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(deleteWorkplace)) {
                connection.setAutoCommit(false);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                connection.commit();
                System.out.println("Рабочее место was deleted");
            } catch (SQLException e) {
                Output.printMessage("Got SQL Exception in transaction " + e.getMessage());
            }
        } else {
            throw new NoSuchElementException("Рабочее место not found, Рабочее место wasn't deleted");
        }
    }

    @Override
    public List<Workplace> findAll() {
        List<Workplace> workplaces = new ArrayList<>();
        String findAllWorkplaces = "SELECT workplace_id, floor FROM data.workplaces";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findAllWorkplaces)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Workplace workplace = Workplace.builder()
                        .workplaceId(resultSet.getInt("workplace_id"))
                        .floor(resultSet.getInt("floor"))
                        .build();
                workplaces.add(workplace);
            }
        } catch (SQLException e) {
            Output.printMessage("Got SQL Exception in transaction " + e.getMessage());
        }
        return workplaces;
    }

    @Override
    public boolean existsById(Integer id) {
        String retrieveWorkplaceId = "SELECT workplace_id FROM data.workplaces WHERE workplace_id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(retrieveWorkplaceId)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            Output.printMessage("Got SQL Exception in transaction " + e.getMessage());
        }
        return false;
    }
}