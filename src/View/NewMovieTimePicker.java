package View;


import Modules.V1_Showings;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import static Controller.NewMoviePickerController.*;

import static Modules.V1_Database.*;
import static View.ChooseBookingMode.getChooseBookingModeWindow;

public class NewMovieTimePicker implements ActionListener {
    private JFrame windows3;

    private JLabel pickAMovie;
    private JComboBox moviesBox;
    private JComboBox dateBox;
    private JComboBox timeBox;

    private JButton findSeat;
    private JButton goBack;

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Double width = screenSize.getWidth();
    private Double height = screenSize.getHeight();

    private JLabel pickAmovie;

    private static String pickedMovie;
    private static String pickedDate;
    private static String pickedTime;

    public NewMovieTimePicker()
    {
        makeFrame();
    }

    public JFrame getWindows3() {
        return windows3;
    }

    public static String getPickedMovie() {
        return pickedMovie;
    }

    public static String getPickedDate() {
        return pickedDate;
    }

    public static String getPickedTime() {
        return pickedTime;
    }

    public void makeFrame()
    {
        windows3 = new JFrame("Make a new Reservation");
        windows3.setVisible(true);
        windows3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windows3.setSize(((int)(width/0.2)), ((int)(height/0.1)));
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,5));
        goBack = new JButton("Go back");
        panel.add(goBack);
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windows3.setVisible(false);
                getChooseBookingModeWindow().setVisible(true);
            }
        });

        JPanel emptyCell1 = new JPanel();
        panel.add(emptyCell1);

        JPanel emptyCell2 = new JPanel();
        panel.add(emptyCell2);

        JPanel emptyCell3 = new JPanel();
        panel.add(emptyCell3);

        String[] movies = getMoviesName().toArray(new String[getMoviesName().size()]);


        moviesBox = new JComboBox(movies);
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        pickAMovie = new JLabel("Pick first a movie");
        panel.add(pickAMovie);

        JLabel pickAtime = new JLabel("Pick  a time");
        panel.add(pickAtime);

        JLabel pickAday = new JLabel("Pick a date after");
        panel.add(pickAday);

        JPanel emptyCell = new JPanel();
        panel.add(emptyCell);


        panel.add(moviesBox);


        moviesBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeBox.removeAllItems();




                for(int i =0; i<getTimesByMovie(pickedMovie).length; i++)
                {
                    String[] timeBoxItems = getTimesByMovie(pickedMovie);

                    timeBox.addItem(timeBoxItems[i]);

                }




            }
        });


        timeBox = new JComboBox();
        panel.add(timeBox);
        timeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateBox.removeAllItems();
                for(int i =0; i<getDatesByMovieAndTime(pickedMovie, pickedTime).length; i++)
                {
                    String [] dateBoxItems = getDatesByMovieAndTime(pickedMovie, pickedTime);
                    dateBox.addItem(dateBoxItems[i]);
                }
            }
        });


        dateBox = new JComboBox();
        dateBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setPickedDate(e);
            }
        });

        panel.add(dateBox);

        findSeat = new JButton("Find Seats");
        panel.add(findSeat);



        windows3.add(panel);




        windows3.pack();


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
            if(getMovieIDbyName(moviename)==s.getMovie_ID()&&s.getDate().equals(date))
            {
                times.add(s.getTime());
            }
        }
        return times;

    }


    public String[] getTimesByMovie(String moviename)
    {
        ArrayList<String>times = new ArrayList<>();
        if(moviesBox.getSelectedItem()!=null)
        {
            pickedMovie = moviesBox.getSelectedItem().toString();

            for(V1_Showings s: getShowings())
            {
                if(getMovieIDbyName(moviename) == s.getMovie_ID())
                {
                    times.add(s.getTime());
                }
            }

        }
        return times.toArray(new String[getTimesByDateAndMovie(pickedMovie, pickedDate).size()]);
    }

    public String[] getDatesByMovieAndTime(String movieName, String time)
    {
        ArrayList<String> dates = new ArrayList<>();

        if(timeBox.getSelectedItem()!=null)
        {
            pickedTime = timeBox.getSelectedItem().toString();
            for(V1_Showings s: getShowings()) {
                if (getMovieIDbyName(movieName) == s.getMovie_ID() && s.getTime().equals(time))
                {
                    dates.add(s.getDate());
                }
            }
        }

        return dates.toArray(new String[getDatesByMovie(pickedMovie).size()]);
    }



    //sets picked time
    public void setPickedDate(ActionEvent evt)
    {
        if(dateBox.getSelectedItem()!=null)
        {
            pickedDate = dateBox.getSelectedItem().toString();
        }
    }






    public void actionPerformed(ActionEvent e)
    {


    }

}