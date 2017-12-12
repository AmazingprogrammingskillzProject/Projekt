package Controller;

import Enums.ReturnCode;
import Modules.*;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static Modules.Database.getMovieName;
import static Modules.Database.getShowings;
import static View.NewMoviePickerView.getWindow;

// Denne klasse sørger for selv oprettelsen og sletningen af bookings.
public class DatabaseController
{


    private static Cinema selectedCinema = null;

    public static Cinema getSelectedCinema() {
        return selectedCinema;
    }

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
    public static void setShowingInfo(JLabel showingInfo, int cinemaNR, int showID)
    {
        for(Showing s: getShowings())
        {
            if(s.getCinema_ID()==cinemaNR&&s.getID()==showID)
            {
                showingInfo.setText(getMovieName(s.getMovie_ID()-1)+" - "+s.getDate()+" - "+s.getTime());
            }
        }
    }

    public static void setCinemaNumber(int cinemaNumber)
    {


        for(Cinema c : getCinemas()){
            if(c.getNumber() == cinemaNumber){
                selectedCinema = c;
                break;
            }
        }
        if(selectedCinema == null) {
            JOptionPane.showMessageDialog(null, "A cinema with given number was not found");
            getWindow().setVisible(true);
            return;
        }
    }
    public static void setSeatRow(int r, int s, int showID, JButton button)
    {
        for(SeatBooking booked : Database.getSeatBookings()) {

            if(booked.getShowing_ID() == showID) {
                if(r == booked.getRow() && s == booked.getSeat()) {
                    button.setBackground(Color.RED);
                }
            }
        }

    }

    public static ArrayList<Cinema> getCinemas() {
        return Database.getCinemas();
    }

    public static void LoadEntireDB()
    {
        Database.LoadMovies();
        Database.LoadBookings();
        Database.LoadCinema();
        Database.LoadSeatBookings();
        Database.LoadShowings();
    }
    public static void LoadBookingFromController()
    {
        Database.LoadBookings();
    }
    public static boolean checkBookingID(JTextField BID)
    {
        boolean BIDfound = false;
        for(Booking b: Database.getBookings())
        {
            if((Integer.parseInt(BID.getText()) == b.getID()))
            {
                BIDfound = true;
            }
        }
        return BIDfound;
    }
}
