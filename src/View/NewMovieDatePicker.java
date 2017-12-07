package View;


import Modules.V1_Showings;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import static Controller.NewMoviePickerController.*;

import static Modules.V1_Database.*;

public class NewMovieDatePicker implements ActionListener {
    private JFrame window2;

    private JLabel pickAMovie;
    private JComboBox moviesBox;
    private JComboBox dateBox;
    private JComboBox timeBox;

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Double width = screenSize.getWidth();
    private Double height = screenSize.getHeight();

    private JLabel pickAmovie;

    private static String pickedMovie;
    private static String pickedDate;
    private static String pickedTime;




    public static void main(String[] args)
    {
        LoadEntireDB();
        NewMovieDatePicker newDateTimeReservation = new NewMovieDatePicker();
        newDateTimeReservation.makeFrame();


    }

    public NewMovieDatePicker()
    {

    }


    public void makeFrame()
    {
        window2 = new JFrame("Make a new Reservation");
        window2.setVisible(true);
        window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window2.setSize(((int)(width/3)), ((int)(height/2)));
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,4,5,10));


        String[] movies = getMoviesName().toArray(new String[getMoviesName().size()]);


        moviesBox = new JComboBox(movies);
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        pickAMovie = new JLabel("Pick first a movie");
        panel.add(pickAMovie);

        JLabel pickAday = new JLabel("Pick a date after");
        panel.add(pickAday);

        JLabel pickAtime = new JLabel("Pick  a time");
        panel.add(pickAtime);

        JPanel emptyCell = new JPanel();
        panel.add(emptyCell);


        panel.add(moviesBox);


        moviesBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateBox.removeAllItems();




                for(int i =0; i<getMovieDates(pickedMovie).length; i++)
                {
                    String[] DB = getMovieDates(pickedMovie);

                    dateBox.addItem(DB[i]);

                }




            }
        });

        dateBox = new JComboBox();
        dateBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeBox.removeAllItems();
                for(int i =0; i<getMovieTimes(pickedMovie, pickedDate).length; i++)
                {
                    String [] DB2 = getMovieTimes(pickedMovie, pickedDate);
                    timeBox.addItem(DB2[i]);
                }
            }
        });

        panel.add(dateBox);
        timeBox = new JComboBox();
        timeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPickedTime(e);
            }
        });
        panel.add(dateBox);
        panel.add(timeBox);
        JButton findSeat = new JButton("Find Seats");
        panel.add(findSeat);


        window2.add(panel);




        window2.pack();


    }

        public ArrayList<String> getDatesByMovie(String moviename)
    {
        ArrayList<String>dates = new ArrayList<>();
        for(V1_Showings s: getShowings())
        {
            if(getMovieIDbyName(moviename) == s.getMovie_ID())
            {
                dates.add(s.getDate());
            }
        }
        return dates;
    }
    public ArrayList<String> getTimesByDateAndMovie(String moviename, String date)
    {
        ArrayList<String> times = new ArrayList<>();
        for(V1_Showings s: getShowings())
        {
            if(getMovieIDbyName(moviename)==s.getMovie_ID()&&s.getDate().equals(pickedDate))
            {
                times.add(s.getTime());
            }
        }
        return times;

    }


    public String[] getMovieDates(String moviename)
    {
        ArrayList<String>dates = new ArrayList<>();
        if(moviesBox.getSelectedItem()!=null)
        {
            pickedMovie = moviesBox.getSelectedItem().toString();

            for(V1_Showings s: getShowings())
            {
                if(getMovieIDbyName(moviename) == s.getMovie_ID())
                {
                    dates.add(s.getDate());
                }
            }

        }
        return dates.toArray(new String[getDatesByMovie(pickedMovie).size()]);
    }

    public String[] getMovieTimes(String movieName, String date)
    {
        ArrayList<String> times = new ArrayList<>();

        if(dateBox.getSelectedItem()!=null)
        {
            pickedDate = dateBox.getSelectedItem().toString();
            for(V1_Showings s: getShowings()) {
                if (getMovieIDbyName(movieName) == s.getMovie_ID() && s.getDate().equals(date))
                {
                    times.add(s.getTime());
                }
            }
        }
        String[] test = times.toArray(new String[getTimesByDateAndMovie(pickedMovie, pickedDate).size()]);
        return test;
    }

    public void setPickedTime(ActionEvent evt)
    {
        if(timeBox.getSelectedItem()!=null)
        {
            pickedTime = timeBox.getSelectedItem().toString();
        }
    }






    public void actionPerformed(ActionEvent e)
    {


    }

}