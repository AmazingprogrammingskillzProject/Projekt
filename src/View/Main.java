package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Modules.V1_Database.LoadEntireDB;

public class Main
{

    private static JFrame mainWindow;
    private JButton bookAnewReservation;
    private JButton searchOrCancelReservation;

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Double width = screenSize.getWidth();
    private Double height = screenSize.getHeight();

    public static void main(String[] args)
    {
        LoadEntireDB();
        Main client = new Main();
        client.makeFrame();
    }

    public static JFrame getMainWindow()
    {
        return mainWindow;
    }


    public void makeFrame()
    {

        mainWindow = new JFrame("Make a new Reservation");
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.setSize(640,480);
        mainWindow.setSize(640,480);

        bookAnewReservation = new JButton("Book a new reservation");
        panel.add(bookAnewReservation);

        bookAnewReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.setVisible(false);
                ChooseBookingMode ChooseBookingMode = new ChooseBookingMode();


            }
        });

        searchOrCancelReservation = new JButton("Search or cancel reservation");
        panel.add(searchOrCancelReservation);
        searchOrCancelReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.setVisible(false);
                Bookings bookings = new Bookings();

            }
        });

        mainWindow.add(panel);
        mainWindow.pack();
        mainWindow.setSize(((int)(width/2)), ((int)(height/2)));
        mainWindow.setLocationRelativeTo(null);

    }




}
