// COMPILE:
// javac -cp mysql.jar Database.java

// RUN:
// java -cp mysql.jar:. Database

package Modules;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;


public class V1_Database
{
    //  Database credentials
    static final String MYDB = "Gr_AR_Biograf";
    static final String USER = "Gruppe_AR";
    static final String PASS = "giraf1234";

    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:mysql://mydb.itu.dk/" + MYDB;


    static ArrayList<V1_Cinema> Cinemas;
    static ArrayList<V1_Bookings> Bookings;
    static ArrayList<V1_Movies> Movies;
    static ArrayList<V1_SeatBookings> SeatBookings;
    static ArrayList<V1_Showings> Showings;

    public static void main(String[] args) {
        LoadEntireDB();
        for(V1_Showings cinema: Showings) {

            System.out.println(cinema);
        }
//        ReturnCode rc = CreateBooking("+4520112852", 1, 7, 6, 7);
//        ReturnCode rc = DeleteBooking("+4520112852", 10);
        ReturnCode rc = InsertIntoShowings(1, 1, "2017-12-13","18:00:00");

        System.out.println(rc);

    }

    public static void LoadEntireDB()
    {
        LoadBookings();
        LoadCinema();
        LoadMovies();
        LoadSeatBookings();
        LoadShowings();
    }

    public static void LoadBookings()
    {
        Connection connection = null;
        Statement statement = null;
        String sql = null;
        ResultSet rs = null;

        Bookings = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();

            sql = "SELECT * FROM `V1_Bookings`";
            rs = statement.executeQuery(sql);
            while(rs.next())
            {
                int id = rs.getInt("ID");
                String phone = rs.getString("Phone");
                int showing_ID = rs.getInt("Showing_ID");
                int row = rs.getInt("Row");
                int firstSeat = rs.getInt("FirstSeat");
                int lastSeat = rs.getInt("LastSeat");

                V1_Bookings booking = new V1_Bookings(id, phone, showing_ID, row, firstSeat, lastSeat);
                Bookings.add(booking);
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
    }

    public static void LoadCinema()
    {
        Connection connection = null;
        Statement statement = null;
        String sql = null;
        ResultSet rs = null;

        Cinemas = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();

            sql = "SELECT * FROM `V1_Cinema`";
            rs = statement.executeQuery(sql);
            while(rs.next())
            {
                int id = rs.getInt("ID");
                int number = rs.getInt("Number");
                int rows = rs.getInt("Rows");
                int seats = rs.getInt("Seats");

                V1_Cinema cinema = new V1_Cinema(id, number, rows, seats);
                Cinemas.add(cinema);
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
    }

    public static void LoadMovies()
    {
        Connection connection = null;
        Statement statement = null;
        String sql = null;
        ResultSet rs = null;

        Movies = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();

            sql = "SELECT * FROM `V1_Movies`";
            rs = statement.executeQuery(sql);
            while(rs.next())
            {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String length = rs.getString("Length");
                String description = rs.getString("Description");
                V1_Movies movie = new V1_Movies(id, name, length, description);
                Movies.add(movie);
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
    }

    public static void LoadSeatBookings()
    {
        Connection connection = null;
        Statement statement = null;
        String sql = null;
        ResultSet rs = null;

        SeatBookings = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();

            sql = "SELECT * FROM `V1_SeatBookings`";
            rs = statement.executeQuery(sql);
            while(rs.next())
            {
                int booking_ID = rs.getInt("Booking_ID");
                int showing_ID = rs.getInt("Showing_ID");
                int row = rs.getInt("Row");
                int seat = rs.getInt("Seat");
                V1_SeatBookings seatBooking = new V1_SeatBookings(booking_ID,showing_ID,row,seat);
                SeatBookings.add(seatBooking);
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
    }

    public static void LoadShowings()
    {
        Connection connection = null;
        Statement statement = null;
        String sql = null;
        ResultSet rs = null;

        Showings = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();

            sql = "SELECT * FROM `V1_Showings`";
            rs = statement.executeQuery(sql);
            while(rs.next())
            {
                int id = rs.getInt("ID");
                int cinema_ID = rs.getInt("Cinema_ID");
                int movie_ID = rs.getInt("Movie_ID");
                String date = rs.getString("Date");
                String time = rs.getString("Time");
                V1_Showings showing = new V1_Showings(id,cinema_ID,movie_ID,date,time);
                Showings.add(showing);
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
    }

    public static ReturnCode CreateBooking(String phone, int showing_ID, int row, int firstSeat, int lastSeat)
    {
        Connection connection = null;
        Statement statement = null;
        String sql = null;
        ResultSet rs = null;

        boolean isBooked = false;

        LoadSeatBookings();

        for(V1_SeatBookings sb: SeatBookings) {
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
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
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

        LoadSeatBookings();
        LoadBookings();

        return ReturnCode.SUCCESS;
    }

    public static ReturnCode DeleteBooking(String phone, int booking_ID)
    {
        Connection connection = null;
        Statement statement = null;
        String sql = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
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

        LoadSeatBookings();
        LoadBookings();

        return ReturnCode.SUCCESS;
    }

    public static ReturnCode InsertIntoShowings(int cinema_ID, int movie_ID, String date, String time)
    {
        Connection connection = null;
        Statement statement = null;
        String sql = null;

        LoadMovies();
        LoadCinema();
        LoadShowings();

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();

            boolean cinemaExists = false;
            boolean movieExists = false;

            for(V1_Cinema c : Cinemas) {
                if(cinema_ID == c.getID()) {
                    cinemaExists = true;
                }
            }

            if (!cinemaExists) {
                return ReturnCode.CINEMA_ID_DOES_NOT_EXIST;
            }

            for(V1_Movies m : Movies) {
                if (movie_ID == m.getID()) {
                    movieExists = true;
                }
            }

            if(!movieExists) {
                return ReturnCode.MOVIE_ID_DOES_NOT_EXIST;
            }

            for(V1_Showings s : Showings) {
                if(cinema_ID == s.getCinema_ID() && date.equals(s.getDate())) {
                    int showTime = Integer.parseInt(s.getTime().replace(":",""));
                    int newShowTime = Integer.parseInt(time.replace(":",""));

                    // Math.abs fjerner "-" så tallet altid bliver positivt
                    if(Math.abs(showTime - newShowTime) < 20000)  {
                        return ReturnCode.SHOWING_EXISTS;
                    }
                }
            }

            sql = "INSERT INTO `V1_Showings`(`Cinema_ID`, `Movie_ID`, `Date`, `Time`) VALUES ("+cinema_ID+", "+movie_ID+", '"+date+"', '"+time+"')";
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

        LoadShowings();

        return ReturnCode.SUCCESS;
    }


}