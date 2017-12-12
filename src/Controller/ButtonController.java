package Controller;

import Modules.Booking;
import Modules.Database;
import Modules.Movie;
import Modules.Showing;
import View.BookingSearcherView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Exception.ShowingsNotFoundException;

import static View.BookingSearcherView.getBID;


// Den her klasse er brugt til at håndtere de to knapper vi har i BookingSeacherView
public class ButtonController {


    // En del af det der slette bookings
    public static void deleteBooking()
    {

            DatabaseController.DeleteBooking(BookingSearcherView.getPhone().getText(), Integer.parseInt(getBID().getText()));

            searchBooking();
    }

    // Søger bookings frem på det givne telefonnummer, og skriver disse ud i et TextField
    public static void  searchBooking()
    {

        Database.LoadBookings();
        Database.LoadMovies();
        Database.LoadShowings();

        ArrayList<Booking> bookings = Database.getBookings();
        ArrayList<Showing> showings = Database.getShowings();
        ArrayList<Movie> movies = Database.getMovies();

        BookingSearcherView.getBookingsField().setText("");

        boolean bookingFound = false;

        String PNR = BookingSearcherView.getPhoneNumber().getText();

        // Tjekker om det er et telefonnummer der er skrevet ind i textFielded
        // er det ikke er, får brugeren det at vide via en pop op besked
        if(PNR.length() < 8 || PNR.length() > 8 || !(PNR.matches("[0-9]+")))
        {
            JOptionPane.showMessageDialog(null, "Error: Make sure the phone number is of 8 digits");

        }
        else {
            for (Booking booking : bookings) {
                if (booking.getPhone().equals(PNR)) {

                    bookingFound = true;
                    getBID().setEnabled(true);
                    getBID().setBackground(Color.white);

                    String movieName = null;
                    Movie V1movie = null;


                    String collectedStrings = BookingSearcherView.getBookingsField().getText();

                    // Med følgende for loops findes film navnet.
                    for (Showing show : showings) {
                        if (booking.getShowing_ID() == show.getID()) {
                            for (Movie movie : movies) {
                                if (show.getMovie_ID() == movie.getID()) {
                                    V1movie = movie;
                                    movieName = V1movie.getName();

                                    // Her skrives alt booking informationen ind i et textField
                                    BookingSearcherView.getBookingsField().setText(collectedStrings + "Phone: " + booking.getPhone() + "    Booking ID: " + booking.getID() +
                                            "    Movie name: " + movieName +  "    Date: " + show.getDate() + "   Time: " + show.getTime() +
                                            "    Row: " + booking.getRow() + "    First Seat: " + booking.getFirstSeat() + "    Last Seat: " + booking.getLastSeat() + "\n");

                                }
                            }
                        }
                    }
                }

                    BookingSearcherView.getPhone().setText(BookingSearcherView.getPhoneNumber().getText());

            }

            // Hvis der ikke er en booking på telefonnummeret, få brugeren besked om dette.
            if (!bookingFound){
                JOptionPane.showMessageDialog(null, "No Booking found");

            }
        }
    }
}
