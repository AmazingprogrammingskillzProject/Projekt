// COMPILE:
// javac -cp mysql.jar Database.java

// RUN:
// java -cp mysql.jar:. Database

package Modules;

import Controller.DatabaseController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;



public class Database
{
    //  Database credentials
    static final String MYDB = "Gr_AR_Biograf";
    static final String USER = "Gruppe_AR";
    static final String PASS = "giraf1234";

    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:mysql://mydb.itu.dk/" + MYDB;


    public static String getUSER() {
        return USER;
    }

    public static String getPASS() {
        return PASS;
    }

    public static String getDbUrl() {
        return DB_URL;
    }

    private static ArrayList<Cinema> Cinemas;
    private static ArrayList<Booking> Bookings;
    private static ArrayList<Movie> Movies;
    private static ArrayList<SeatBooking> SeatBookings;
    private static ArrayList<Showing> Showings;

    public static ArrayList<Cinema> getCinemas() {
        return Cinemas;
    }
    public static ArrayList<Showing> getShowings() {
        return Showings;
    }
    public static ArrayList<Movie> getMovies()
    {
        return Movies;
    }
    public static ArrayList<SeatBooking> getSeatBookings() {
        return SeatBookings;
    }
    public static ArrayList<Booking> getBookings() {
        return Bookings;
    }

    public static String getMovieName(int m)
    {
        return Movies.get(m).getName();
    }
    public static Movie getmovie(int m)
    {
        return Movies.get(m);
    }

    public static void main(String[] args) {
        LoadEntireDB();
        for(Showing cinema: Showings) {

            System.out.println(cinema);
        }
        ReturnCode rc = DatabaseController.CreateBooking("+4520112852", 7, 5, 3, 5);
//        ReturnCode rc = DeleteBooking("+4520112852", 10);
//        ReturnCode rc = InsertIntoShowings(1, 1, "2017-12-13","18:00:00");

        System.out.println(rc);

        for(Movie movie: Movies)
        {
            System.out.println(movie);
        }
    }

    public static void LoadEntireDB()
    {
        LoadMovies();
        LoadBookings();
        LoadCinema();
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

            sql = "SELECT * FROM Bookings";
            rs = statement.executeQuery(sql);
            while(rs.next())
            {
                int id = rs.getInt("ID");
                String phone = rs.getString("Phone");
                int showing_ID = rs.getInt("Showing_ID");
                int row = rs.getInt("Row");
                int firstSeat = rs.getInt("FirstSeat");
                int lastSeat = rs.getInt("LastSeat");



                Booking booking = new Booking(id, phone, showing_ID, row, firstSeat, lastSeat);
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

            sql = "SELECT * FROM Cinemas";
            rs = statement.executeQuery(sql);
            while(rs.next())
            {
                int id = rs.getInt("ID");
                int number = rs.getInt("Number");
                int rows = rs.getInt("Rows");
                int seats = rs.getInt("Seats");

                Cinema cinema = new Cinema(id, number, rows, seats);
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

            sql = "SELECT * FROM Movies";
            rs = statement.executeQuery(sql);
            while(rs.next())
            {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String length = rs.getString("Length");
                String description = rs.getString("Description");
                Movie movie = new Movie(id, name, length, description);
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

            sql = "SELECT * FROM SeatBookings";
            rs = statement.executeQuery(sql);
            while(rs.next())
            {
                int booking_ID = rs.getInt("Booking_ID");
                int showing_ID = rs.getInt("Showing_ID");
                int row = rs.getInt("Row");
                int seat = rs.getInt("Seat");
                SeatBooking seatBooking = new SeatBooking(booking_ID,showing_ID,row,seat);
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

            sql = "SELECT * FROM Showings";
            rs = statement.executeQuery(sql);
            while(rs.next())
            {
                int id = rs.getInt("ID");
                int cinema_ID = rs.getInt("Cinema_ID");
                int movie_ID = rs.getInt("Movie_ID");
                String date = rs.getString("Date");
                String time = rs.getString("Time");

                Showing showing = new Showing(id,cinema_ID,movie_ID, date,time);
                Showings.add(showing);
            }

            for(Showing showing: Showings){
                showing.toString();
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

            for(Cinema c : Cinemas) {
                if(cinema_ID == c.getID()) {
                    cinemaExists = true;
                }
            }

            if (!cinemaExists) {
                return ReturnCode.CINEMA_ID_DOES_NOT_EXIST;
            }

            for(Movie m : Movies) {
                if (movie_ID == m.getID()) {
                    movieExists = true;
                }
            }

            if(!movieExists) {
                return ReturnCode.MOVIE_ID_DOES_NOT_EXIST;
            }

            for(Showing s : Showings) {
                if(cinema_ID == s.getCinema_ID() && date.equals(s.getDate())) {
                    int showTime = Integer.parseInt(s.getTime().replace(":",""));
                    int newShowTime = Integer.parseInt(time.replace(":",""));

                    // Math.abs fjerner "-" så tallet altid bliver positivt
                    if(Math.abs(showTime - newShowTime) < 20000)  {
                        return ReturnCode.SHOWING_EXISTS;
                    }
                }
            }

            sql = "INSERT INTO Showings(`Cinema_ID`, `Movie_ID`, `Date`, `Time`) VALUES ("+cinema_ID+", "+movie_ID+", '"+date+"', '"+time+"')";
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

