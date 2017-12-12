package Controller;

import Enums.ReturnCode;
import Modules.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

// Denne klasse sørger for selv oprettelsen og sletningen af bookings.
public class DatabaseController {

    // Metoden der opretter bookings i databasen, samt tilhørende seatBookings
    public static ReturnCode CreateBooking(String phone, int showing_ID, int row, int firstSeat, int lastSeat)
    {
        Connection connection = null;
        Statement statement = null;
        String sql = null;
        ResultSet rs = null;

        boolean isBooked = false;

        Database.LoadSeatBookings();

        // Her tjekker vi om de valgte sæder allerede er booket.
        for(SeatBooking sb: Database.getSeatBookings()) {
            if (sb.getShowing_ID() == showing_ID && sb.getRow() == row) {
                if(sb.getSeat() >= firstSeat && sb.getSeat() <= lastSeat) {
                    isBooked = true;
                }
            }
        }


        if (isBooked) {
            return ReturnCode.IS_BOOKED;
        }

        try {
            connection = DriverManager.getConnection(Database.getDbUrl(), Database.getUSER(), Database.getPASS());
            statement = connection.createStatement();

            sql = "INSERT INTO Bookings(`Phone`, `Showing_ID`, `Row`, `FirstSeat`, `LastSeat`) VALUES ('"+phone+"', "+showing_ID+", "+row+", "+firstSeat+", "+lastSeat+")";
            statement.executeUpdate(sql);

            sql = "SELECT * FROM Bookings WHERE `Phone` = '"+phone+"' AND `Showing_ID` = "+showing_ID+" AND `Row` = "+row+" AND `FirstSeat` = "+firstSeat+" AND `LastSeat` = "+lastSeat;
            rs = statement.executeQuery(sql);

            rs.next();
            int booking_ID = rs.getInt("ID");

            for(int i = firstSeat; i <= lastSeat; i++){
                sql = "INSERT INTO SeatBookings(`Booking_ID`, `Showing_ID`, `Row`, `Seat`) VALUES ("+booking_ID+", "+showing_ID+", "+row+", "+i+")";
                statement.executeUpdate(sql);
            }

            connection.close();
        }

        catch(Exception e)
        { // handle errors:
            e.printStackTrace();
        }
        finally
        {
            try
            {
                rs.close();
                connection.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        Database.LoadSeatBookings();
        Database.LoadBookings();

        return ReturnCode.SUCCESS;
    }

    // Metodes der sletter bookings fra databasen
    public static ReturnCode DeleteBooking(String phone, int booking_ID)
    {
        System.out.println("phone: "+ phone+ "      id:   " + booking_ID);
            Connection connection = null;
            Statement statement = null;
            String sql = null;

            try {
                connection = DriverManager.getConnection(Database.getDbUrl(), Database.getUSER(), Database.getPASS());
                statement = connection.createStatement();

                sql = "DELETE FROM Bookings WHERE `Phone` = '"+phone+"' AND `ID` = "+booking_ID;
                statement.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Booking deleted");

                // Her sørges der for at seatbookings kun slettes hvis de hører til det rigtige booking ID
                for(Booking b : Database.getBookings()) {
                    if (b.getPhone().equals((phone)) && b.getID() == booking_ID)
                    {
                        sql = "DELETE FROM SeatBookings WHERE `Booking_ID` = " + booking_ID;
                    }
                }


                statement.executeUpdate(sql);

                connection.close();
            }

            catch(Exception e)
            { // handle errors:
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    connection.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }

            Database.LoadSeatBookings();
            Database.LoadBookings();

        return ReturnCode.SUCCESS;

    }

    public static ArrayList<Cinema> getCinemas() {
        return Database.getCinemas();
    }
}
