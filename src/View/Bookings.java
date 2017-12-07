package View;

import Modules.V1_Bookings;
import Modules.V1_Database;
import javafx.beans.property.adapter.JavaBeanBooleanProperty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Bookings implements ActionListener{

    private JFrame reservationWindow;
    private Container basePane;
    private JTextArea bookingsField;
    private JTextField phone;
    private JButton deleteButton;
    private JTextField BID;
    private JButton searchButton;
    private JTextField phoneNumber;
    private JButton backButton;

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
        reservationWindow.setResizable(false);

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
        northPanel.setLayout(new GridLayout(1, 4));

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        northPanel.add(backButton);

        JLabel phoneLabel = new JLabel("Phone number: ");
        phoneLabel.setHorizontalAlignment(JLabel.RIGHT  );
        northPanel.add(phoneLabel);

        phoneNumber = new JTextField();
        phoneNumber.setSize(150, 27);
        northPanel.add(phoneNumber);

        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        northPanel.add(searchButton);

    }

    private void makeCenterPane() {

        JPanel centerPanel = new JPanel();
        basePane.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new GridLayout(2,1));

        JPanel centerNorth = new JPanel();
        centerPanel.add(centerNorth);
        centerNorth.setLayout(new GridLayout(1,6));

        JPanel centerSouth = new JPanel();
        bookingsField = new JTextArea();
        bookingsField.setEditable(false);
        centerSouth.setLayout(new GridLayout(1,1));
        centerPanel.add(centerSouth);
        centerSouth.add(bookingsField);


        JTextField phoneNr = new JTextField("Phone number");
        JTextField bookingID = new JTextField("BID");
        JTextField showingID = new JTextField("Showing");
        JTextField row = new JTextField("Row");
        JTextField firstSeat = new JTextField("First seat");
        JTextField lastSeat = new JTextField("Last seat");

        centerNorth.add(phoneNr);
        phoneNr.setHorizontalAlignment(JTextField.CENTER);
        centerNorth.add(bookingID);
        bookingID.setHorizontalAlignment(JTextField.CENTER);
        centerNorth.add(showingID);
        showingID.setHorizontalAlignment(JTextField.CENTER);
        centerNorth.add(row);
        row.setHorizontalAlignment(JTextField.CENTER);
        centerNorth.add(firstSeat);
        firstSeat.setHorizontalAlignment(JTextField.CENTER);
        centerNorth.add(lastSeat);
        lastSeat.setHorizontalAlignment(JTextField.CENTER);

    }

    private void makeSouthPane() {

        JPanel southPanel = new JPanel();
        basePane.add(southPanel, BorderLayout.SOUTH);
        southPanel.setLayout(new GridLayout(1,5));

        JLabel phoneNr = new JLabel("Phone number:");
        phoneNr.setHorizontalAlignment(JLabel.RIGHT);
        JLabel bookingID = new JLabel("Booking ID:");
        bookingID.setHorizontalAlignment(JLabel.RIGHT);
        phone = new JTextField("");
        BID = new JTextField("");
        deleteButton = new JButton("Delete");

        phone.setEditable(false);

        southPanel.add(phoneNr);
        southPanel.add(phone);
        southPanel.add(bookingID);
        southPanel.add(BID);
        southPanel.add(deleteButton);

        deleteButton.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == deleteButton){
            deleteBooking();
        }
        if(e.getSource() == searchButton) {
            searchBooking();
        }

    }

    private void deleteBooking() {
        V1_Database.DeleteBooking(phone.getText(), Integer.parseInt(BID.getText()));

        JOptionPane.showMessageDialog(null, "Booking deleted");
        searchBooking();
    }

    private void searchBooking(){
        V1_Database.LoadBookings();

        bookingsField.setText("");

        String PNR = phoneNumber.getText();

        for(V1_Bookings booking: V1_Database.getBookings()) {
            if(booking.getPhone().equals(PNR)) {
                String collectedStrings = bookingsField.getText();

                bookingsField.setText(collectedStrings + "Phone: " + booking.getPhone() + "    Booking ID: " + booking.getID() +
                        "    Showing ID: " + booking.getShowing_ID() + "    Row: " + booking.getRow() + "    First Seat: " + booking.getFirstSeat() + "    Last Seat: " + booking.getLastSeat() + "\n");
                System.out.println(booking);

            }
        }

        phone.setText(phoneNumber.getText());
    }
    }



