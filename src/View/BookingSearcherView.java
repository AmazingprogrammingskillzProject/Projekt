package View;

import Controller.ButtonController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Controller.DatabaseController.LoadBookingFromController;
import static Controller.DatabaseController.checkBookingID;


// Denne klasse er vores Vooking vindue.
// Dette er vinduet hvorfra man kan søge efter bookings, og slette dem hvis nødvendigt.
public class BookingSearcherView implements ActionListener{

    private static JFrame reservationWindow;
    private static Container basePane;
    private static JTextArea bookingsField;
    private static JTextField phone;
    private static JButton deleteButton;
    private static JTextField BID;
    private static JButton searchButton;
    private static JTextField phoneNumber;
    private static JButton backButton;



    public BookingSearcherView()
    {
        makeFrame();
    }

    public static JTextArea getBookingsField() {
        return bookingsField;
    }

    public static JTextField getPhone() {
        return phone;
    }

    public static JTextField getBID() {
        return BID;
    }

    public static JTextField getPhoneNumber() {
        return phoneNumber;
    }

    /*
    Oprettelsen af vinduet og alle de tilhørende komponenter er delt op i flere metoder.
    Detter gør det nemmere at finde frem til en specifik komponent hvis der skal laves rettelser.
    Metoderne er navngivet efter hvilken region de har i et BorderLayout
     */


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
        reservationWindow.setLocationRelativeTo(null);

    }

    private void makeNorthPane() {

        JPanel northPanel = new JPanel();
        basePane.add(northPanel, BorderLayout.NORTH);
        northPanel.setLayout(new GridLayout(1, 4));

        backButton = new JButton("Go back");

        northPanel.add(backButton);
        backButton.addActionListener(e -> {
            reservationWindow.setVisible(false);
            MainView.getMainWindow().setVisible(true);
        });


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

        phoneNr.setEditable(false);
        bookingID.setEditable(false);
        movieName.setEditable(false);
        date.setEditable(false);
        time.setEditable(false);
        row.setEditable(false);
        firstSeat.setEditable(false);
        lastSeat.setEditable(false);

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

        if(e.getSource() == deleteButton)
        {
            LoadBookingFromController();

            if(!(BID.getText().matches("[0-9]+")))
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid booking ID");
                return;
            }

            Boolean BIDFound = false;

            BIDFound = checkBookingID(BID);

            System.out.println(BIDFound);

            if(!BIDFound)
            {
                JOptionPane.showMessageDialog(null, "Booking not found");
                return;
            }

                ButtonController.deleteBooking();

        }
        if(e.getSource() == searchButton)
        {
            if(!(phoneNumber.getText().matches("[0-9]+"))){
                JOptionPane.showMessageDialog(null, "Please enter a valid phone number");
            }

            else
                {
            ButtonController.searchBooking();
            }
    }
    }
}




