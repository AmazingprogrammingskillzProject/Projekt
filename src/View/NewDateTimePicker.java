package View;

import Modules.V1_Movies;
import Modules.V1_Showings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Controller.NewMoviePickerController.getDates;
import static Modules.V1_Database.*;

public class NewDateTimePicker implements ActionListener
{
    private JFrame window1;
    private JComboBox dateBox;
    private JComboBox timeBox;
    private JComboBox movieBox;

    private JButton findSeats;

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Double width = screenSize.getWidth();
    private Double height = screenSize.getHeight();

    private JLabel pickAmovie;
    private JLabel pickAdate;
    private JLabel pickAtime;

    private static String pickedDate;
    private static String pickedTime;
    private static String pickedMovie;

    public static void main(String[] args)
    {
        LoadEntireDB();
        NewDateTimePicker newDateTimePicker = new NewDateTimePicker();
        newDateTimePicker.makeFrame();

    }

    public NewDateTimePicker()
    {
    }

    public void makeFrame()
    {
        window1 = new JFrame("Make New Reservation");
        window1.setVisible(true);
        window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window1.setSize(((int)(width/3)), ((int)(height/2)));
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,4,5,10));


        String[] dates = getDates().toArray(new String[getDates().size()]);
        dateBox = new JComboBox(dates);
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);


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

                for(int i =0; i<getDatesTime(pickedDate).length; i++)
                {
                    String[] timeBoxItems = getDatesTime(pickedDate);
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

        findSeats = new JButton("FindSeat");
        panel.add(findSeats);

        window1.add(panel);
        window1.pack();


    }

    public ArrayList<String> getTimesByDate(String date)
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

    }

    public String[] getDatesTime(String date)
    {
        ArrayList<String> times = new ArrayList<>();

        if (dateBox.getSelectedItem()!=null)
        {
            pickedDate = dateBox.getSelectedItem().toString();

            for(V1_Showings s : getShowings())
            {
                if(s.getDate().equals(date))
                {
                    times.add(s.getTime());
                }
            }
        }
        return times.toArray(new String[getTimesByDate(pickedDate).size()]);
    }

    public ArrayList<String> getMovieByTimeAndDate(String date, String time)
    {
        ArrayList<String> movie = new ArrayList<>();

        for(V1_Showings s: getShowings())
        {
            if(s.getTime().equals(time)&&s.getDate().equals(date))
            {
                movie.add(getMovie((s.getMovie_ID()-1)).getName());
            }
        }
        return movie;
    }

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
                    movies.add(getMovie((s.getMovie_ID()-1)).getName());
                }
            }
        }
        return movies.toArray(new String[getMovieByTimeAndDate(pickedDate,pickedTime).size()]);
    }



    public void actionPerformed(ActionEvent actionEvent) {

    }

}
