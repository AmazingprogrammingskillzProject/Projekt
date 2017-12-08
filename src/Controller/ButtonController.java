package Controller;

import Modules.V1_Bookings;
import Modules.V1_Database;
import Modules.V1_Movies;
import Modules.V1_Showings;
import View.Bookings;

import javax.swing.*;
import java.util.ArrayList;

public class ButtonController {

    public static void deleteBooking() {
        V1_DatabaseController.DeleteBooking(Bookings.getPhone().getText(), Integer.parseInt(Bookings.getBID().getText()));

        JOptionPane.showMessageDialog(null, "Booking deleted");
        searchBooking();
    }

    public static void searchBooking() {

        V1_Database.LoadBookings();
        V1_Database.LoadMovies();
        V1_Database.LoadShowings();

        ArrayList<V1_Bookings> bookings = V1_Database.getBookings();
        ArrayList<V1_Showings> showings = V1_Database.getShowings();
        ArrayList<V1_Movies> movies = V1_Database.getMovies();

        boolean bookingFound = false;

        Bookings.getBookingsField().setText("");

        String PNR = Bookings.getPhoneNumber().getText();

        for (V1_Bookings booking : bookings) {
            if (booking.getPhone().equals(PNR)) {

                bookingFound = true;
               // System.out.println("booking ID: " + booking.getID());

                String movieName = null;
                V1_Movies V1movie = null;
                String collectedStrings = Bookings.getBookingsField().getText();


                // Med følgende for loops findes film navnet.
                for (V1_Showings show : showings) {
                  //  System.out.println("Show ID: " + show.getID() + "      Movie ID: " + show.getMovie_ID());
                    if (booking.getShowing_ID() == show.getID()) {
                        for (V1_Movies movie : movies) {
                         //   System.out.println("Movie ID: " + movie.getID());
                            if (show.getMovie_ID() == movie.getID()) {
                               // System.out.println("Movie ID: " + movie.getID() + " Movie name: " + movie.getName());
                                V1movie = movie;
                                movieName = V1movie.getName();

                                Bookings.getBookingsField().setText(collectedStrings + "Phone: " + booking.getPhone() + "    Booking ID: " + booking.getID() +
                                       /* "    Showing ID: " + booking.getShowing_ID() + */"    Movie name: " + movieName +  "    Date: " + show.getDate() + "   Time: " + show.getTime() +
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
