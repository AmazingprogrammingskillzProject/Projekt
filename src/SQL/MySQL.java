/*
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
    private static int FID = 10;
    public static void main(String[]args)
    {
        Connection connection = null;
        Statement statement = null;
        String sql = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = (Statement) connection.createStatement();
            //STEP 5: Extract data from result set
            for(int i=0; i< FID; i++) {

                for(int r=0;r<=8;r++)
                {
                    for(int s = 0; s<=12; s++)
                    {


                    }
                }

            }
            connection.close();
        }





        catch(Exception e) { // handle errors:
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                connection.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }}

}*/
