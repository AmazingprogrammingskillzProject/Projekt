// COMPILE:
// javac -cp mysql.jar MySQL.java 

// RUN:
// java -cp mysql.jar:. MySQL

package SQL;

import java.sql.*; // STEP 1: Import required packages

public class MySQL {
    //  Database credentials
    static final String MYDB = "Gr_AR_Biograf";
    static final String USER = "Gruppe_AR";
    static final String PASS = "giraf1234";

    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:mysql://mydb.itu.dk/" + MYDB;
    public static void connectDB()
    {
        Connection connection = null;
        Statement statement = null;
        String sql = null;
        ResultSet rs = null;

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver()); // STEP 2: Register JDBC driver
            connection = DriverManager.getConnection(DB_URL, USER, PASS); // STEP 3: Open a connection
            statement = connection.createStatement(); // STEP 4: Execute a query

            sql = "SELECT * FROM FIlm"; // implicit semi-colon!
            rs = statement.executeQuery(sql);
            //STEP 5: Extract data from result set
            while (rs.next()) { //Retrieve by column name
                // int id  = rs.getInt("id");
                String FilmID = rs.getString("FilmID");
                String name = rs.getString("Name");
                //Display values
                System.out.println("Name: '" + name + "', FilmID: '" + FilmID + "'");
            }
        } catch(Exception e) { // handle errors:
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                connection.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }}

}