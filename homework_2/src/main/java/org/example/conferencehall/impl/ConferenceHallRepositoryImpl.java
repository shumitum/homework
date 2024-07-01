package org.example.conferencehall.impl;

import lombok.Getter;
import org.example.conferencehall.model.ConferenceHall;
import org.example.crud.CrudRepository;
import org.example.database.DbConnection;
import org.example.out.Output;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Getter
public class ConferenceHallRepositoryImpl implements CrudRepository<ConferenceHall> {

    @Override
    public void save(ConferenceHall conferenceHall) {
        if (conferenceHall != null) {
            String newConferenceHall = "INSERT INTO data.conference_halls (conference_hall_name) VALUES (?)";
            try (Connection connection = DbConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(newConferenceHall)) {
                connection.setAutoCommit(false);
                preparedStatement.setString(1, conferenceHall.getName());
                preparedStatement.executeUpdate();
                connection.commit();
                Output.printMessage(String.format("Конференц-зал \"%s\" создан", conferenceHall.getName()));
            } catch (SQLException e) {
                Output.printMessage("Got SQL Exception in transaction " + e.getMessage());
            }
        } else {
            Output.printMessage("ConferenceHall is null or have existing id, place wasn't saved");
        }
    }

    @Override
    public void update(ConferenceHall conferenceHall) {
        if (existsById(conferenceHall.getConferenceHallId())) {
            String updConferenceHall = "UPDATE data.conference_halls SET conference_hall_name = ?  WHERE conference_hall_id = ?";
            try (Connection connection = DbConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(updConferenceHall)) {
                connection.setAutoCommit(false);
                preparedStatement.setString(1, conferenceHall.getName());
                preparedStatement.setInt(2, conferenceHall.getConferenceHallId());
                preparedStatement.executeUpdate();
                connection.commit();
                Output.printMessage("Данные конференц-зала обновлены - " + conferenceHall);
            } catch (SQLException e) {
                Output.printMessage("Got SQL Exception in transaction " + e.getMessage());
            }
        } else {
            throw new NoSuchElementException("ConferenceHall not found, place wasn't updated");
        }
    }

    @Override
    public void delete(Integer id) {
        if (existsById(id)) {
            String deleteConferenceHall = "DELETE FROM data.conference_halls WHERE conference_hall_id = ?";
            try (Connection connection = DbConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(deleteConferenceHall)) {
                connection.setAutoCommit(false);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                connection.commit();
                Output.printMessage("ConferenceHall was deleted");
            } catch (SQLException e) {
                Output.printMessage("Got SQL Exception in transaction " + e.getMessage());
            }
        } else {
            throw new NoSuchElementException("ConferenceHall not found, place wasn't deleted");
        }
    }

    @Override
    public List<ConferenceHall> findAll() {
        List<ConferenceHall> conferenceHalls = new ArrayList<>();
        String findAllConferenceHall = "SELECT * FROM data.conference_halls";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findAllConferenceHall)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ConferenceHall conferenceHall = ConferenceHall.builder()
                        .conferenceHallId(resultSet.getInt("conference_hall_id"))
                        .name(resultSet.getString("conference_hall_name"))
                        .build();
                conferenceHalls.add(conferenceHall);
            }
        } catch (SQLException e) {
            Output.printMessage("Got SQL Exception in transaction " + e.getMessage());
        }
        return conferenceHalls;
    }

    @Override
    public boolean existsById(Integer id) {
        String retrieveConferenceHallId = "SELECT conference_hall_id FROM data.conference_halls WHERE conference_hall_id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(retrieveConferenceHallId)) {
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
