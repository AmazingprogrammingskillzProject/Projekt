package Controller;

import Modules.V1_Bookings;
import Modules.V1_Database;
import Modules.V1_Movies;
import Modules.V1_Showings;
import View.Bookings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static View.Bookings.getBID;
import static View.Bookings.getPhoneNumber;

public class ButtonController {

    public static void deleteBooking() {

            V1_DatabaseController.DeleteBooking(Bookings.getPhone().getText(), Integer.parseInt(getBID().getText()));

            searchBooking();
    }

    public static void  searchBooking() {

        V1_Database.LoadBookings();
        V1_Database.LoadMovies();
        V1_Database.LoadShowings();

        ArrayList<V1_Bookings> bookings = V1_Database.getBookings();
        ArrayList<V1_Showings> showings = V1_Database.getShowings();
        ArrayList<V1_Movies> movies = V1_Database.getMovies();

        Bookings.getBookingsField().setText("");

        boolean bookingFound = false;

        String PNR = Bookings.getPhoneNumber().getText();

        if(PNR.length() < 8 || PNR.length() > 8 || !(PNR.matches("[0-9]+")))
        {
            JOptionPane.showMessageDialog(null, "Error: Make sure the phone number is of 8 digits");

        } else {
            for (V1_Bookings booking : bookings) {
                if (booking.getPhone().equals(PNR)) {

                    bookingFound = true;
                    getBID().setEnabled(true);
                    getBID().setBackground(Color.white);

                    String movieName = null;
                    V1_Movies V1movie = null;


                    String collectedStrings = Bookings.getBookingsField().getText();

                    // Med følgende for loops findes film navnet.
                    for (V1_Showings show : showings) {
                        if (booking.getShowing_ID() == show.getID()) {
                            for (V1_Movies movie : movies) {
                                if (show.getMovie_ID() == movie.getID()) {
                                    V1movie = movie;
                                    movieName = V1movie.getName();

                                    Bookings.getBookingsField().setText(collectedStrings + "Phone: " + booking.getPhone() + "    Booking ID: " + booking.getID() +
                                            "    Movie name: " + movieName +  "    Date: " + show.getDate() + "   Time: " + show.getTime() +
                                            "    Row: " + booking.getRow() + "    First Seat: " + booking.getFirstSeat() + "    Last Seat: " + booking.getLastSeat() + "\n");

                                }
                            }
                        }
                    }
                }

                    Bookings.getPhone().setText(Bookings.getPhoneNumber().getText());

            }

            if (!bookingFound){
                JOptionPane.showMessageDialog(null, "No Booking found");

            }
        }
    }
}
