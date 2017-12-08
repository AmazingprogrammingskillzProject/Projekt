package Controller;

import Modules.ReturnCode;
import Modules.V1_Cinema;
import Modules.V1_Database;
import Modules.V1_SeatBookings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class V1_DatabaseController {

    public static ReturnCode CreateBooking(String phone, int showing_ID, int row, int firstSeat, int lastSeat)
    {
        Connection connection = null;
        Statement statement = null;
        String sql = null;
        ResultSet rs = null;

        boolean isBooked = false;

        V1_Database.LoadSeatBookings();

        for(V1_SeatBookings sb: V1_Database.getSeatBookings()) {
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
            connection = DriverManager.getConnection(V1_Database.getDbUrl(), V1_Database.getUSER(), V1_Database.getPASS());
            statement = connection.createStatement();

            sql = "INSERT INTO `V1_Bookings`(`Phone`, `Showing_ID`, `Row`, `FirstSeat`, `LastSeat`) VALUES ('"+phone+"', "+showing_ID+", "+row+", "+firstSeat+", "+lastSeat+")";
            statement.executeUpdate(sql);

            sql = "SELECT * FROM `V1_Bookings` WHERE `Phone` = '"+phone+"' AND `Showing_ID` = "+showing_ID+" AND `Row` = "+row+" AND `FirstSeat` = "+firstSeat+" AND `LastSeat` = "+lastSeat;
            rs = statement.executeQuery(sql);

            rs.next();
            int booking_ID = rs.getInt("ID");

            for(int i = firstSeat; i <= lastSeat; i++){
                sql = "INSERT INTO `V1_SeatBookings`(`Booking_ID`, `Showing_ID`, `Row`, `Seat`) VALUES ("+booking_ID+", "+showing_ID+", "+row+", "+i+")";
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

        V1_Database.LoadSeatBookings();
        V1_Database.LoadBookings();

        return ReturnCode.SUCCESS;
    }

    public static ReturnCode DeleteBooking(String phone, int booking_ID)
    {
        Connection connection = null;
        Statement statement = null;
        String sql = null;

        try {
            connection = DriverManager.getConnection(V1_Database.getDbUrl(), V1_Database.getUSER(), V1_Database.getPASS());
            statement = connection.createStatement();

            sql = "DELETE FROM `V1_Bookings` WHERE `Phone` = '"+phone+"' AND `ID` = "+booking_ID;
            statement.executeUpdate(sql);

            sql = "DELETE FROM `V1_SeatBookings` WHERE `Booking_ID` = "+booking_ID;
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

        V1_Database.LoadSeatBookings();
        V1_Database.LoadBookings();

        return ReturnCode.SUCCESS;
    }
    public static ArrayList<V1_Cinema> getCinemas() {
        return V1_Database.getCinemas();
    }
}