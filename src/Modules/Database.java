// COMPILE:
// javac -cp mysql.jar Database.java

// RUN:
// java -cp mysql.jar:. Database

package Modules;

import java.sql.*; // STEP 1: Import required packages
import java.util.Date;




public class Database
{
    //  Database credentials
    static final String MYDB = "Gr_AR_Biograf";
    static final String USER = "Gruppe_AR";
    static final String PASS = "giraf1234";

    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:mysql://mydb.itu.dk/" + MYDB;

    public static void main(String[] args) {
        connectDB();

    }
    public static void connectDB()
    {

        Connection connection = null;
        Statement statement = null;
        String sql = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM Shows");

            while(rs.next())
            {
                int id = rs.getInt("FID");
                int sal = rs.getInt("CinemaNr");
                String name = rs.getString("Movie");
                String timestamp = rs.getString("Time");
                Date date = rs.getDate("Date");
                Show show = new Show(id,sal,name,timestamp,date);
                show.addShow(show);

                System.out.println("ok");
            }

            rs = st.executeQuery("SELECT * FROM Bookings");

            while(rs.next())
            {
                String phone = rs.getString("Phone");
                String movie = rs.getString("Movie");
                String date = rs.getString("Date");
                String time = rs.getString("Time");
                int sal = rs.getInt("CinemaNr");
                int row = rs.getInt("Row");
                int seat = rs.getInt("Seat");

                Booking booking = new Booking(phone, movie, date, time, sal, row, seat);
                booking.addBooking(booking);
            }

            rs = st.executeQuery("SELECT * FROM SeatReservations");
            while(rs.next())
            {
                int cinemanumber = rs.getInt("CinemaNr");
                int fid = rs.getInt("FID");
                int seat = rs.getInt("Seat");
                int row = rs.getInt("Row");
                boolean reserved = rs.getBoolean("Reserved");
                Cinema cinema = new Cinema(cinemanumber, fid, seat, row, reserved);
                cinema.addSeatInfo(cinema);

            }




            //STEP 5: Extract data from result set

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

}