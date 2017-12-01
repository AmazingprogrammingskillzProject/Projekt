package Modules;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Show
{
    private int fid;
    private int sal;
    private String movieName;
    private String time;
    private String date;
    private static List<Show> shows;

    static final String MYDB = "Gr_AR_Biograf";
    static final String USER = "Gruppe_AR";
    static final String PASS = "giraf1234";

    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:mysql://mydb.itu.dk/" + MYDB;
    private static int FID = 10;

    public static void main(String[] args)
    {
        connectDB();
        print();
    }


    public static void print()
    {
        for(Show s: shows)
        {
            System.out.println("FID: "+s.fid+"   Sal:  "+s.sal+"  FilmNavn:   "+s.movieName+"           Tid:  "+s.time+"  Dato:  "+s.date);
        }
    }





    public Show(int fid, int sal, String movieName, String time, String date)
    {
        this.fid = fid;
        this.sal = sal;
        this.movieName = movieName;
        this.time = time;
        this.date = date;

    }

    public static void connectDB()
    {
        shows = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        String sql = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM Forestillinger");

            while(rs.next())
            {
                int id = rs.getInt("FID");
                int sal = rs.getInt("Sal");
                String name = rs.getString("Film");
                String timestamp = rs.getString("Tidspunkt");
                String date = rs.getString("Dato");
                Show show = new Show(id,sal,name,timestamp,date);
                shows.add(show);

                System.out.println("ok");
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


