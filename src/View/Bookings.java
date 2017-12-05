package View;

import Modules.V1_Bookings;
import Modules.V1_Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Bookings implements ActionListener{

    private JFrame reservationWindow;
    private Container basePane;

  //  private String PNR;

    private int numberOfReservations;


    public static void main(String[] args) {
        Bookings bookings = new Bookings();
        V1_Database.LoadBookings();
        V1_Database.getBookings();
        System.out.println();

    }

    public Bookings() {
            makeFrame();
            V1_Database.LoadBookings();
        }

    private void makeFrame () {
        reservationWindow = new JFrame("Reservation");
        reservationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reservationWindow.setVisible(true);

        basePane = reservationWindow.getContentPane();
        basePane.setLayout(new BorderLayout(6,6));
        basePane.setVisible(true);

        makeNorthPane();
        makeCenterPane();
        makeSouthPane();

        reservationWindow.pack();
        reservationWindow.setSize(640, 480);

    }

    private void makeNorthPane() {
        JPanel northPanel = new JPanel();
        basePane.add(northPanel, BorderLayout.NORTH);
        northPanel.setLayout(new GridLayout(1, 3));

        JLabel phoneLabel = new JLabel("Phone number: ");
        northPanel.add(phoneLabel);

        JTextField phoneNumber = new JTextField();
        phoneNumber.setSize(150, 27);
        northPanel.add(phoneNumber);

        JButton search = new JButton("Search");
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent search) {
                String PNR = phoneNumber.getText();

                for(V1_Bookings booking: V1_Database.getBookings()) {
                    if(booking.getPhone().equals(PNR)) {
                        System.out.println(booking);
                    }
                }

            }
        });
        northPanel.add(search);

    }

    private void makeCenterPane() {

        JPanel centerPanel = new JPanel();
        basePane.add(centerPanel, BorderLayout.CENTER);

        centerPanel.setLayout(new FlowLayout());


        JTextField phoneNr = new JTextField("Phone number");
        JTextField bookingID = new JTextField("BID");
        JTextField showingID = new JTextField("Showing");
        JTextField row = new JTextField("Row");
        JTextField firstSeat = new JTextField("First seat");
        JTextField lastSeat = new JTextField("Last seat");

        centerPanel.add(phoneNr);
        phoneNr.setHorizontalAlignment(JTextField.CENTER);
        centerPanel.add(bookingID);
        bookingID.setHorizontalAlignment(JTextField.CENTER);
        centerPanel.add(showingID);
        showingID.setHorizontalAlignment(JTextField.CENTER);
        centerPanel.add(row);
        row.setHorizontalAlignment(JTextField.CENTER);
        centerPanel.add(firstSeat);
        firstSeat.setHorizontalAlignment(JTextField.CENTER);
        centerPanel.add(lastSeat);
        lastSeat.setHorizontalAlignment(JTextField.CENTER);

    }

    private void makeSouthPane() {

        JPanel southPanel = new JPanel();
        basePane.add(southPanel, BorderLayout.SOUTH);
        southPanel.setLayout(new GridLayout(1,5));

        JLabel phoneNr = new JLabel("Phone number:");
        JLabel bookingID = new JLabel("Booking ID:");
        JTextField phone = new JTextField("");
        JTextField BID = new JTextField("");
        JButton delete = new JButton("Delete");

        southPanel.add(phoneNr);
        southPanel.add(phone);
        southPanel.add(bookingID);
        southPanel.add(BID);
        southPanel.add(delete);

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                V1_Database.DeleteBooking(phone.getText(), Integer.parseInt(BID.getText()));
            }
        });

    }

   public void actionPerformed(ActionEvent search) {

    }
}


