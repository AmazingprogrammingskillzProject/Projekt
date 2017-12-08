package View;

import Controller.ButtonController;
import Modules.V1_Bookings;
import Modules.V1_Database;
import javafx.beans.property.adapter.JavaBeanBooleanProperty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.NumberFormat;

import static View.Main.getMainWindow;


public class Bookings implements ActionListener{

    private static JFrame reservationWindow;
    private static Container basePane;
    private static JTextArea bookingsField;
    private static JTextField phone;
    private static JButton deleteButton;
    private static JTextField BID;
    private static JButton searchButton;
    private static JFormattedTextField phoneNumber;
    private static JButton backButton;



    public Bookings()
    {
        makeFrame();
    }

    public JFrame getReservationWindow() {
        return reservationWindow;
    }

    public Container getBasePane() {
        return basePane;
    }

    public static JTextArea getBookingsField() {
        return bookingsField;
    }

    public static JTextField getPhone() {
        return phone;
    }

    public static JButton getDeleteButton() {
        return deleteButton;
    }

    public static JTextField getBID() {
        return BID;
    }

    public static JButton getSearchButton() {
        return searchButton;
    }

    public static JTextField getPhoneNumber() {
        return phoneNumber;
    }

    public static JButton getBackButton() {
        return backButton;
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
        reservationWindow.setSize(840, 480);

    }

    private void makeNorthPane() {

        JPanel northPanel = new JPanel();
        basePane.add(northPanel, BorderLayout.NORTH);
        northPanel.setLayout(new GridLayout(1, 4));

        backButton = new JButton("Go back");

        northPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reservationWindow.setVisible(false);
                getMainWindow().setVisible(true);
            }
        });


        JLabel phoneLabel = new JLabel("Phone number: ");
        phoneLabel.setHorizontalAlignment(JLabel.RIGHT  );
        northPanel.add(phoneLabel);

        Format phoneNumberFormat = NumberFormat.getNumberInstance();

        phoneNumber = new JFormattedTextField(phoneNumberFormat);
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
        centerNorth.setLayout(new GridLayout(1,8));

        JPanel centerSouth = new JPanel();
        bookingsField = new JTextArea(8,8);
        bookingsField.setEditable(false);
        JScrollPane scroller = new JScrollPane(bookingsField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        centerSouth.setLayout(new GridLayout(1,1));
        centerSouth.add(scroller);
        centerSouth.setVisible(true);
        centerPanel.add(centerSouth);
       // centerSouth.add(bookingsField);


        JTextField phoneNr = new JTextField("Phone number");
        JTextField bookingID = new JTextField("Booking ID");
        JTextField movieName = new JTextField("Movie Name");
        JTextField date = new JTextField("Date");
        JTextField time = new JTextField("Time");
        JTextField row = new JTextField("Row");
        JTextField firstSeat = new JTextField("First seat");
        JTextField lastSeat = new JTextField("Last seat");

        centerNorth.add(phoneNr);
        phoneNr.setHorizontalAlignment(JTextField.CENTER);
        centerNorth.add(bookingID);
        bookingID.setHorizontalAlignment(JTextField.CENTER);
        centerNorth.add(movieName);
        movieName.setHorizontalAlignment(JTextField.CENTER);
        centerNorth.add(date);
        date.setHorizontalAlignment(JTextField.CENTER);
        centerNorth.add(time);
        time.setHorizontalAlignment(JTextField.CENTER);
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
        BID.setEnabled(false);
        BID.setBackground(Color.gray);

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
            ButtonController.deleteBooking();
        }
        if(e.getSource() == searchButton) {
            ButtonController.searchBooking();
        }
    }
    }



