package Controller;

import Modules.V1_Bookings;
import Modules.V1_Database;
import Modules.V1_Showings;
import View.Bookings;

import javax.swing.*;

public class ButtonController {

    public static void deleteBooking() {
        V1_DatabaseController.DeleteBooking(Bookings.getPhone().getText(), Integer.parseInt(Bookings.getBID().getText()));

        JOptionPane.showMessageDialog(null, "Booking deleted");
        searchBooking();
    }

    public static void searchBooking(){
        V1_Database.LoadBookings();

        Bookings.getBookingsField().setText("");

        String PNR = Bookings.getPhoneNumber().getText();

        for(V1_Bookings booking: V1_Database.getBookings()) {
            if(booking.getPhone().equals(PNR)) {
                String collectedStrings = Bookings.getBookingsField().getText();

                Bookings.getBookingsField().setText(collectedStrings + "Phone: " + booking.getPhone() + "    Booking ID: " + booking.getID() +
                        "    Showing ID: " +  V1_Database.getShowings() + "    Row: " + booking.getRow() + "    First Seat: " + booking.getFirstSeat() + "    Last Seat: " + booking.getLastSeat() + "\n");
                System.out.println(booking);

            }
        }

        Bookings.getPhone().setText(Bookings.getPhoneNumber().getText());
    }



}
