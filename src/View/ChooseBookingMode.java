package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseBookingMode
{
    private static JFrame chooseBookingModeWindow;
    private JButton dateTime;
    private JButton movieDate;
    private JButton movieTime;
    private JButton goBack;

    private JLabel pickByDateAndTime;
    private JLabel pickByMovieAndDate;
    private JLabel pickByMovieAndTime;

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Double width = screenSize.getWidth();
    private Double height = screenSize.getHeight();

    public ChooseBookingMode()
    {
        makeFrame();
    }
    public static JFrame getChooseBookingModeWindow()
    {
        return chooseBookingModeWindow;
    }

    public void makeFrame()
    {
        chooseBookingModeWindow = new JFrame("Make a new Reservation");
        chooseBookingModeWindow.setVisible(true);
        chooseBookingModeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chooseBookingModeWindow.setSize(((int)(width/3)), ((int)(height/4)));
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,3));

        goBack = new JButton("Go Back");
        panel.add(goBack);
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseBookingModeWindow.setVisible(false);
                Main.getMainWindow().setVisible(true);
            }
        });

        panel.add(goBack);

        JPanel emptyCell1 = new JPanel();
        panel.add(emptyCell1);

        JPanel emptyCell2 = new JPanel();
        panel.add(emptyCell2);


        pickByMovieAndDate = new JLabel("Pick a show by movie and date");
        panel.add(pickByMovieAndDate);

        pickByMovieAndTime = new JLabel("Pick a show by movioe and time");
        panel.add(pickByMovieAndTime);

        pickByDateAndTime = new JLabel("Pick a show by date and time ");
        panel.add(pickByDateAndTime);

        movieDate = new JButton("Pick a movie");
        panel.add(movieDate);

        movieDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseBookingModeWindow.setVisible(false);
                NewMovieDatePicker newMovieDatePicker = new NewMovieDatePicker();
            }
        });

        movieTime = new JButton("Pick a time");
        panel.add(movieTime);
        movieTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseBookingModeWindow.setVisible(false);
                NewMovieTimePicker movieTimePicker = new NewMovieTimePicker();
            }
        });

        dateTime = new JButton("Pick a date");
        panel.add(dateTime);
        dateTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseBookingModeWindow.setVisible(false);
                NewDateTimePicker newDateTimePicker = new NewDateTimePicker();

            }
        });
        chooseBookingModeWindow.add(panel);
        chooseBookingModeWindow.pack();




    }
}
