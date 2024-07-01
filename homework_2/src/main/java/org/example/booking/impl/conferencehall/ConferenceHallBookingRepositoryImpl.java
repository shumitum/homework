package org.example.booking.impl.conferencehall;

import lombok.Getter;
import org.example.booking.BookingRepository;
import org.example.booking.model.Booking;
import org.example.booking.model.PlaceType;
import org.example.booking.model.Slot;
import org.example.database.DbConnection;
import org.example.out.Output;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ConferenceHallBookingRepositoryImpl implements BookingRepository {

    @Override
    public void save(Booking booking) {
        if (booking != null) {
            String newBooking = "INSERT INTO data.bookings (booking_date, slot, place_type, booker_name, place_id) VALUES (?,?,?,?,?)";
            try (Connection connection = DbConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(newBooking)) {
                connection.setAutoCommit(false);
                preparedStatement.setDate(1, Date.valueOf(booking.getBookingDate()));
                preparedStatement.setString(2, booking.getSlot().toString());
                preparedStatement.setString(3, booking.getPlaceType().toString());
                preparedStatement.setString(4, booking.getBookerName());
                preparedStatement.setInt(5, booking.getPlaceId());
                preparedStatement.executeUpdate();
                connection.commit();
                Output.printMessage("Бронирование конференц-зала создано");
            } catch (SQLException e) {
                Output.printMessage("Got SQL Exception in transaction " + e.getMessage());
            }
        } else {
            Output.printMessage("Бронирование конференц-зала is null, бронирование wasn't saved");
        }
    }

    @Override
    public void deleteBooking(Integer bookingId, String userName) {
        if (existsByIdAndName(bookingId, userName)) {
            String deleteBooking = "DELETE FROM data.bookings WHERE booking_id = ? AND place_type = ?";
            try (Connection connection = DbConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(deleteBooking)) {
                connection.setAutoCommit(false);
                preparedStatement.setInt(1, bookingId);
                preparedStatement.setString(2, PlaceType.CONFERENCE_HALL.toString());
                preparedStatement.executeUpdate();
                connection.commit();
                Output.printMessage(String.format("Бронирование конференц-зала с ID=%s  отменено", bookingId));
            } catch (SQLException e) {
                Output.printMessage("Got SQL Exception in transaction " + e.getMessage());
            }
        } else {
            Output.printMessage("Бронирования с указанным ID для пользователя не существует");
        }
    }

    @Override
    public List<Booking> findBookingByUserName(String userName) {
        List<Booking> bookings = new ArrayList<>();
        String findBookingByUserName = "SELECT * FROM data.bookings WHERE booker_name = ? AND place_type = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findBookingByUserName)) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, PlaceType.CONFERENCE_HALL.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            bookings = resultSetToBookingList(resultSet);
        } catch (SQLException e) {
            Output.printMessage("Got SQL Exception in transaction " + e.getMessage());
        }
        return bookings;
    }

    @Override
    public List<Booking> findBookingByDate(LocalDate date) {
        List<Booking> bookings = new ArrayList<>();
        String findBookingByDate = "SELECT * FROM data.bookings WHERE booking_date = ? AND place_type = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findBookingByDate)) {
            preparedStatement.setDate(1, Date.valueOf(date));
            preparedStatement.setString(2, PlaceType.CONFERENCE_HALL.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            bookings = resultSetToBookingList(resultSet);
        } catch (SQLException e) {
            Output.printMessage("Got SQL Exception in transaction " + e.getMessage());
        }
        return bookings;
    }

    @Override
    public List<Booking> findAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String findAllBooking = "SELECT * FROM data.bookings WHERE place_type = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findAllBooking)) {
            preparedStatement.setString(1, PlaceType.CONFERENCE_HALL.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            bookings = resultSetToBookingList(resultSet);
        } catch (SQLException e) {
            Output.printMessage("Got SQL Exception in transaction " + e.getMessage());
        }
        return bookings;
    }

    private boolean existsByIdAndName(Integer id, String userName) {
        String retrieveBookingId = "SELECT booking_id FROM data.bookings WHERE booking_id = ?" +
                " AND booker_name = ? AND place_type = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(retrieveBookingId)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3, PlaceType.CONFERENCE_HALL.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            Output.printMessage("Got SQL Exception in transaction " + e.getMessage());
        }
        return false;
    }

    private List<Booking> resultSetToBookingList(ResultSet resultSet) {
        List<Booking> bookings = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Booking booking = Booking.builder()
                        .bookingId(resultSet.getInt("booking_id"))
                        .bookingDate(resultSet.getDate("booking_date").toLocalDate())
                        .slot(Slot.valueOf(resultSet.getString("slot")))
                        .placeType(PlaceType.valueOf(resultSet.getString("place_type")))
                        .bookerName(resultSet.getString("booker_name"))
                        .placeId(resultSet.getInt("place_id"))
                        .build();
                bookings.add(booking);
            }
        } catch (SQLException e) {
            Output.printMessage("Got SQL Exception in transaction " + e.getMessage());
        }
        return bookings;
    }
}
