package View;

import Modules.V1_Showings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Controller.NewMoviePickerController.getDates;
import static Controller.NewMoviePickerController.getMovieIDbyName;
import static Modules.V1_Database.*;
import static View.ChooseBookingMode.getChooseBookingModeWindow;

public class NewDateTimePicker implements ActionListener
{
    private JFrame window1;
    private JComboBox dateBox;
    private JComboBox timeBox;
    private JComboBox movieBox;

    private JButton findSeats;
    private JButton goBack;

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Double width = screenSize.getWidth();
    private Double height = screenSize.getHeight();

    private JLabel pickAmovie;
    private JLabel pickAdate;
    private JLabel pickAtime;

    private static String pickedDate;
    private static String pickedTime;
    private static String pickedMovie;
    private int pickedShowID;
    private int pickedCinemaID;


    public NewDateTimePicker()
    {
        makeFrame();
    }

    public JFrame getWindow1() {
        return window1;
    }

    public static String getPickedDate() {
        return pickedDate;
    }

    public static String getPickedTime() {
        return pickedTime;
    }

    public static String getPickedMovie() {
        return pickedMovie;
    }

    public void makeFrame()
    {
        window1 = new JFrame("Make New Reservation");
        window1.setVisible(true);
        window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window1.setSize(((int)(width/3)), ((int)(height/2)));
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,5,5,10));


        String[] dates = getDates().toArray(new String[getDates().size()]);
        dateBox = new JComboBox(dates);
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        goBack = new JButton("Go back");
        panel.add(goBack);
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window1.setVisible(false);
                getChooseBookingModeWindow().setVisible(true);
            }
        });

        JPanel emptyCell1 = new JPanel();
        panel.add(emptyCell1);

        JPanel emptyCell2 = new JPanel();
        panel.add(emptyCell2);

        JPanel emptyCell3 = new JPanel();
        panel.add(emptyCell3);


        pickAdate = new JLabel("Pick a date first!");
        panel.add(pickAdate);

        pickAtime = new JLabel("  Pick a time");
        panel.add(pickAtime);

        pickAmovie = new JLabel("Pick a movie");
        panel.add(pickAmovie);

        JPanel emptyCell = new JPanel();
        panel.add(emptyCell);
        panel.add(dateBox);

        dateBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeBox.removeAllItems();

                for(int i = 0; i< getTimesByDate(pickedDate).length; i++)
                {
                    String[] timeBoxItems = getTimesByDate(pickedDate);
                    timeBox.addItem(timeBoxItems[i]);
                }
            }
        });

        timeBox = new JComboBox();
        panel.add(timeBox);

        timeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movieBox.removeAllItems();

                for(int i = 0; i< getDateMovie(pickedDate, pickedTime).length;i++)
                {
                    String[] movieBoxItems = getDateMovie(pickedDate,pickedTime);
                    movieBox.addItem(movieBoxItems[i]);

                }
            }
        });

        movieBox = new JComboBox();
        panel.add(movieBox);

        movieBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPickedMovie(e);
            }
        });

        findSeats = new JButton("FindSeat");
        panel.add(findSeats);
        findSeats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window1.setVisible(false);
                Cinema cinema = new Cinema(pickedShowID, pickedCinemaID);

            }
        });

        window1.add(panel);
        window1.pack();


    }

/*    public ArrayList<String> getTimesByDate(String date)
    {
        ArrayList<String> times = new ArrayList<>();

        if(dateBox.getSelectedItem()!=null)
        {
            for(V1_Showings s: getShowings())
            {
                if(date.equals(s.getDate()))
                {
                    times.add(s.getTime());
                }
            }
        }
        return times;

    }*/

    public String[] getTimesByDate(String date)
    {
        ArrayList<String> times = new ArrayList<>();

        if (dateBox.getSelectedItem()!=null)
        {
            pickedDate = dateBox.getSelectedItem().toString();

            for(V1_Showings s : getShowings())
            {
                if(s.getDate().equals(date)&&!(times.contains(s.getTime())))
                {
                    times.add(s.getTime());
                }
            }
        }
        List<String> sortedList = times.subList(0, times.size());
        Collections.sort(sortedList);
        sortedList.removeAll(Collections.singleton(null));

        return sortedList.toArray(new String[sortedList.size()]);
    }

/*    public ArrayList<String> getMovieByTimeAndDate(String date, String time)
    {
        ArrayList<String> movie = new ArrayList<>();

        for(V1_Showings s: getShowings())
        {
            if(s.getTime().equals(time)&&s.getDate().equals(date))
            {
                movie.add(getmovie((s.getMovie_ID()-1)).getName());
            }
        }
        return movie;
    }*/

    public String[] getDateMovie(String date, String time)
    {
        ArrayList<String> movies = new ArrayList<>();

        if(timeBox.getSelectedItem()!=null)
        {
            pickedTime = timeBox.getSelectedItem().toString();

            for(V1_Showings s: getShowings())
            {
                if(s.getDate().equals(date)&&s.getTime().equals(time))
                {
                    movies.add(getmovie((s.getMovie_ID()-1)).getName());
                }
            }
        }

        List<String> sortedList = movies.subList(0, movies.size());
        Collections.sort(sortedList);
        sortedList.removeAll(Collections.singleton(null));

        return sortedList.toArray(new String[sortedList.size()]);
    }

    public void setPickedMovie(ActionEvent evt)
    {
        if(movieBox.getSelectedItem()!=null)
        {
            pickedMovie = movieBox.getSelectedItem().toString();

            for(V1_Showings s : getShowings())
            {
                if(s.getTime().equals(pickedTime)&&s.getDate().equals(pickedDate)&&getMovieIDbyName(pickedMovie)==s.getMovie_ID())
                {
                    pickedShowID = s.getID();
                    pickedCinemaID = s.getCinema_ID();
                }
            }
        }
    }

    public void actionPerformed(ActionEvent actionEvent) {

    }

}
