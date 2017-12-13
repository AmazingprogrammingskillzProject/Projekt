package View;

import Controller.DatabaseController;
import Enums.ReturnCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Controller.DatabaseController.*;
import static View.NewMoviePickerView.getWindow;


public class CinemaView implements ActionListener{

    private JFrame cinemaWindow;
    private Container basePane;
    private JButton orderButton;
    private JButton backButton;
    private JComboBox rowBox;
    private JComboBox firstSeatbox;
    private JComboBox lastSeatBox;
    private JTextField phoneField;
    private JComboBox ticketBox;

    // Fields initialisere originalt med en -1 værdi, så det er nemt at finde fejl.
    private int showID = -1;
    private int cinemaNR = -1;

    private int cRows = -1;
    private int cSeats = -1;

    private int selectedRow = -1;
    private int selectedFSeat = -1;
    private int selectedLSeat = -1;
    private int numberOfTickets = -1;

    private ArrayList<JButton> seatButtonArray;

    private Integer[] rowArray = {1, 2, 3, 4, 5, 6, 7, 8};
    private Integer[] seatArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private Integer[] ticketArray;


    public CinemaView(int showID) {
        this.showID = showID;
        cRows = 8;
        cSeats = 12;
        makeFrame();
    }

    public CinemaView(int showID, int cinemaNumber){
        this.showID = showID;
        this.cinemaNR = cinemaNumber;

        setCinemaNumber(cinemaNumber);


        // In case a cinema is not found, inform user about problem and return


        this.cRows = getSelectedCinema().getRows();
        this.cSeats = getSelectedCinema().getSeats();

        ticketArray = new Integer[cSeats];
        rowArray = new Integer[cRows];
        seatArray = new  Integer[cSeats];

        for(int i = 0; i < cRows; i++){
            rowArray[i] = i + 1;
        }
        for(int i = 0; i < cSeats; i++){
            seatArray[i] = i + 1;
            ticketArray[i] = i + 1;
        }
        makeFrame();
    }


    private void makeFrame() {

        cinemaWindow = new JFrame("Cinema");
        cinemaWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cinemaWindow.setVisible(true);
        cinemaWindow.setResizable(false);

        basePane = cinemaWindow.getContentPane();
        basePane.setLayout(new BorderLayout(6,6));
        basePane.setVisible(true);


//        JTextField west = new JTextField("                 ");
//        basePane.add(west, BorderLayout.WEST);
//
        makeNorthPane();
        makeCenterPane();
        makeSouthPane();
        makeWestPane();

        cinemaWindow.pack();
        cinemaWindow.setSize(1024,768);
        cinemaWindow.setLocationRelativeTo(null);


    }

    private void makeNorthPane() {

        JPanel northPanel = new JPanel();
        basePane.add(northPanel, BorderLayout.NORTH);
        northPanel.setLayout(new GridLayout(1,3));

        backButton = new JButton("back");
        northPanel.add(backButton);

        backButton.addActionListener(this);


        JLabel cinemaNumber = new JLabel("Cinema " + cinemaNR);
        cinemaNumber.setHorizontalAlignment(JLabel.CENTER);
        northPanel.add(cinemaNumber);

        JLabel showingInfo = new JLabel();
        setShowingInfo(showingInfo, cinemaNR, showID);
        showingInfo.setHorizontalAlignment(JLabel.CENTER);
        northPanel.add(showingInfo);

    }

    private void makeCenterPane() {


        LoadEntireDB();

        JPanel centerPanel = new JPanel();
        basePane.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new GridLayout(cRows, cSeats, 240/cSeats, 160/cRows));

        seatButtonArray = new ArrayList<>();

        for (int r = 1; r <= cRows; r++){
            for(int s = 1; s <= cSeats; s++) {

                final int bttRow = r;
                final int bttSeat = s;

                String bttLabel = "" + r + ", " + s;

                JButton button = new JButton(bttLabel);
                button.setFont(new Font("Times New Roman", Font.PLAIN, 10));
                button.setBackground(Color.GREEN);
                button.setOpaque(true);

                // gemmer række og sæde for knappen der blev trykket på
                button.addActionListener(e -> {

                    numberOfTickets = (int) ticketBox.getItemAt(ticketBox.getSelectedIndex());



                    selectedRow = bttRow;
                    selectedFSeat = bttSeat;
                    selectedLSeat = bttSeat + numberOfTickets -1;
                    if(selectedLSeat > seatArray.length){
                        JOptionPane.showMessageDialog(null, "Error selecting seats");
                        selectedLSeat = bttSeat;
                    }
                    selectSeat();

                });

                setSeatRow(r,s,showID,button);

                seatButtonArray.add(button);
                centerPanel.add(button);


            }
        }

    }

    // Kaldes på tryk af sæde knap
    private void selectSeat() {

        for(JButton JB : seatButtonArray){
            if(JB.getBackground() == Color.YELLOW) {
                JB.setBackground(Color.GREEN);
            }
        }


        rowBox.setSelectedIndex(selectedRow -1);
        firstSeatbox.setSelectedIndex(selectedFSeat -1);
        lastSeatBox.setSelectedIndex(selectedLSeat -1);




        JOptionPane.showMessageDialog(null, "Row: " + selectedRow + " Seat: " + selectedFSeat + " - " + selectedLSeat);

        for(int i = 0; i < seatButtonArray.size(); i ++)
            if(i >= selectedFSeat - (cSeats + 1) + cSeats  * selectedRow && i <= selectedLSeat - (cSeats + 1) + cSeats * selectedRow){
                if(seatButtonArray.get(i).getBackground() == Color.RED){
                    JOptionPane.showMessageDialog(null, "Some picked seats already booked");
                    return;
                } else {
                    seatButtonArray.get(i).setBackground(Color.YELLOW);
                }
            }
        }

    private void makeSouthPane() {

        JPanel southPanel = new JPanel();
        basePane.add(southPanel, BorderLayout.SOUTH);
        southPanel.setLayout(new GridLayout(2,1));

        JTextField southText = new JTextField("Order tickets");
        southText.setHorizontalAlignment(JTextField.CENTER);
        southText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        southPanel.add(southText);

        JPanel southOrderPanel = new JPanel();
        southOrderPanel.setLayout(new GridLayout(1,9));
        southPanel.add(southOrderPanel);

        JLabel rowsLabel = new JLabel("Rows: ");
        rowsLabel.setHorizontalAlignment(JLabel.RIGHT);
        southOrderPanel.add(rowsLabel);

        rowBox = new JComboBox();
        for(int i = 0; i < rowArray.length; i++){
            rowBox.addItem(rowArray[i]);
        }
        southOrderPanel.add(rowBox);
        rowBox.setEnabled(false);

        JLabel FSLabel = new JLabel("First seat:");
        FSLabel.setHorizontalAlignment(JLabel.RIGHT);
        southOrderPanel.add(FSLabel);

        firstSeatbox = new JComboBox();
        for(int i = 0; i < seatArray.length; i++){
            firstSeatbox.addItem(seatArray[i]);
        }
        southOrderPanel.add(firstSeatbox);
        firstSeatbox.setEnabled(false);


        JLabel LSLabel = new JLabel("Last seat:");
        LSLabel.setHorizontalAlignment(LSLabel.RIGHT);
        southOrderPanel.add(LSLabel);

        lastSeatBox = new JComboBox();
        for(int i = 0; i < seatArray.length; i++){
            lastSeatBox.addItem(seatArray[i]);
        }
        southOrderPanel.add(lastSeatBox);
        lastSeatBox.setEnabled(false);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setHorizontalAlignment(JLabel.RIGHT);
        southOrderPanel.add(phoneLabel);

        phoneField = new JTextField();
        southOrderPanel.add(phoneField);


        orderButton = new JButton("Order");
        orderButton.addActionListener(this);
        southOrderPanel.add(orderButton);


    }

    private void makeWestPane() {

        JPanel westPane = new JPanel();
        basePane.add(westPane, BorderLayout.WEST);

        JLabel ticketLabel = new JLabel("Tickets: ");
        westPane.add(ticketLabel);


        ticketBox = new JComboBox();
        for(int i = 0; i < ticketArray.length; i++){
            ticketBox.addItem(seatArray[i]);
        }
        westPane.add(ticketBox);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == orderButton){
            makeBooking();
        }
        if(e.getSource() == backButton){
            goBack();
        }
    }

    private void makeBooking() {
        System.out.println(phoneField);


        if (phoneField.getText().matches("[0-9]+") && phoneField.getText().length() == 8) {

                String phone = phoneField.getText();
                int row = (int) rowBox.getItemAt(rowBox.getSelectedIndex());

                int fseat = (int) firstSeatbox.getItemAt(firstSeatbox.getSelectedIndex());
                int lseat = (int) lastSeatBox.getItemAt(lastSeatBox.getSelectedIndex());

                System.out.println(phone + " " + showID + " " + row + " " + fseat + " " + lseat);

                ReturnCode rtc = DatabaseController.CreateBooking(phone, showID, row, fseat, lseat);
                switch (rtc) {
                    case SUCCESS:
                        JOptionPane.showMessageDialog(null, "Booking created");

                        for (int i = 0; i < seatButtonArray.size(); i++)
                            if (i >= selectedFSeat - (cSeats + 1) + cSeats * selectedRow && i <= selectedLSeat - (cSeats + 1) + cSeats * selectedRow) {
                                seatButtonArray.get(i).setBackground(Color.RED);
                            }

                        break;

                    case IS_BOOKED:
                        JOptionPane.showMessageDialog(null, "Some selected seats are already booked");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Unexpected error happened: " + rtc);
                        break;
                }

            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid phone number");
            }
        }
    private void goBack(){
        cinemaWindow.setVisible(false);
        getWindow().setVisible(true);
    }


}
