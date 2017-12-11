package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import static Controller.NewMoviePickerController.*;



import static View.Main.getMainWindow;

public class NewMoviePicker implements ActionListener {
    private static JFrame window;
    private JPanel panel;

    private JButton goBack;

//1 represents the first option, 2 the second and 3 the third
    private static JComboBox moviesBox1;
    private static JComboBox dateBox1;
    private static JComboBox timeBox1;

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Double width = screenSize.getWidth();
    private Double height = screenSize.getHeight();

    private JLabel pickAmovie1;

    private static String pickedMovie1;
    private static String pickedDate1;
    private static String pickedTime1;

    private static int pickedShowID1;
    private static int pickedCinemaID1;

    private static JComboBox dateBox2;
    private static JComboBox timeBox2;
    private static JComboBox movieBox2;

    private JButton findSeats2;

    private JLabel pickAmovie2;
    private JLabel pickAdate2;
    private JLabel pickAtime2;

    private static String pickedDate2;
    private static String pickedTime2;
    private static String pickedMovie2;

    private static int pickedShowID2;
    private static int pickedCinemaID2;

    private JLabel pickAMovie3;
    private static JComboBox moviesBox3;
    private static JComboBox dateBox3;
    private static JComboBox timeBox3;

    private JButton findSeat3;

    private JLabel pickAmovie3;

    private static String pickedMovie3;
    private static String pickedDate3;
    private static String pickedTime3;

    private static int pickedShowID3;
    private static int pickedCinemaNr3;

    public NewMoviePicker()
    {
        makePicker1();

    }

    public static JComboBox getMoviesBox1() {
        return moviesBox1;
    }

    public static JComboBox getDateBox1() {
        return dateBox1;
    }

    public static JComboBox getTimeBox1() {
        return timeBox1;
    }

    public static JFrame getWindow() {
        return window;
    }

    public static String getPickedMovie1() {
        return pickedMovie1;
    }

    public static String getPickedDate1() {
        return pickedDate1;
    }

    public static String getPickedTime1() {
        return pickedTime1;
    }

    public static void setPickedMovie1(String pickedMovie1) {
        NewMoviePicker.pickedMovie1 = pickedMovie1;
    }

    public static void setPickedDate1(String pickedDate1) {
        NewMoviePicker.pickedDate1 = pickedDate1;
    }

    public static void setPickedTime1(String pickedTime1) {
        NewMoviePicker.pickedTime1 = pickedTime1;
    }

    public static void setPickedShowID1(int pickedShowID) {
        pickedShowID1 = pickedShowID;
    }

    public static void setPickedCinemaID1(int pickedCinemaID) {
        pickedCinemaID1 = pickedCinemaID;
    }

    public static JComboBox getDateBox2() {
        return dateBox2;
    }

    public static JComboBox getTimeBox2() {
        return timeBox2;
    }

    public static JComboBox getMovieBox2() {
        return movieBox2;
    }

    public static void setPickedDate2(String pickedDate) {
        pickedDate2 = pickedDate;
    }

    public static void setPickedTime2(String pickedTime) {
        pickedTime2 = pickedTime;
    }

    public static void setPickedMovie2(String pickedMovie) {
        pickedMovie2 = pickedMovie;
    }

    public static void setPickedShowID2(int pickedShowID) {
        pickedShowID2 = pickedShowID;
    }

    public static void setPickedCinemaID2(int pickedCinemaID) {
        pickedCinemaID2 = pickedCinemaID;
    }

    public static String getPickedDate2() {
        return pickedDate2;
    }

    public static String getPickedTime2() {
        return pickedTime2;
    }

    public static String getPickedMovie2() {
        return pickedMovie2;
    }

    public static String getPickedMovie3() {
        return pickedMovie3;
    }

    public static String getPickedDate3() {
        return pickedDate3;
    }

    public static String getPickedTime3() {
        return pickedTime3;
    }

    public static JComboBox getMoviesBox3() {
        return moviesBox3;
    }

    public static JComboBox getDateBox3() {
        return dateBox3;
    }

    public static JComboBox getTimeBox3() {
        return timeBox3;
    }

    public static void setPickedMovie3(String pickedMovie) {
        pickedMovie3 = pickedMovie;
    }

    public static void setPickedDate3(String pickedDate) {
        pickedDate3 = pickedDate;
    }

    public static void setPickedTime3(String pickedTime) {
        pickedTime3 = pickedTime;
    }

    public static void setPickedShowID3(int pickedShowID) {
        pickedShowID3 = pickedShowID;
    }

    public static void setPickedCinemaID3(int pickedCinemaNr) {
        pickedCinemaNr3 = pickedCinemaNr;
    }

    public void makePicker1() //Creates JFrame and the first pick-option to choose
    {
        window = new JFrame("Make a new Reservation");
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(new GridLayout(10,7));
        goBack = new JButton("Go back");
        panel.add(goBack);
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                getMainWindow().setVisible(true);
            }
        });

        makeSixEmptyCells();

        String[] movies = getMoviesName().toArray(new String[getMoviesName().size()]);
        moviesBox1 = new JComboBox(movies);

        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        pickAmovie1 = new JLabel("Pick a movie first");
        panel.add(pickAmovie1);

        makeOneEmptyCells();

        JLabel pickAday = new JLabel("Pick a date");
        panel.add(pickAday);
        makeOneEmptyCells();

        JLabel pickAtime = new JLabel("Pick  a time");
        panel.add(pickAtime);

        makeTwoEmptyCells();

        panel.add(moviesBox1);
        makeOneEmptyCells();

        moviesBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateBox1.removeAllItems();

                for(int i = 0; i< getDatesByMovie(pickedMovie1).length; i++)
                {
                    String[] DB = getDatesByMovie(pickedMovie1);

                    dateBox1.addItem(DB[i]);
                }
            }
        });


        dateBox1 = new JComboBox();
        dateBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeBox1.removeAllItems();
                for(int i = 0; i< getTimesByMovie(pickedMovie1, pickedDate1).length; i++)
                {
                    String [] DB2 = getTimesByMovie(pickedMovie1, pickedDate1);
                    timeBox1.addItem(DB2[i]);
                }
            }
        });

        panel.add(dateBox1);
        makeOneEmptyCells();


        timeBox1 = new JComboBox();
        timeBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPickedTime(e);
            }
        });

        panel.add(timeBox1);
        makeOneEmptyCells();

        JButton findSeat = new JButton("Find Seats");
        panel.add(findSeat);
        findSeat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                Cinema cinema = new Cinema(pickedShowID1, pickedCinemaID1);

            }
        });

        makeSevenEmptyCells();
        makePicker2();
        makePicker3();

        window.add(panel);
        window.pack();
        window.setSize((int)(width/2),(int)(height/2));
        window.setLocationRelativeTo(null);
        window.setResizable(false);

    }


    public void makePicker2() //Creates the second pick-option to choose
    {

        String[] dates = getDates().toArray(new String[getDates().size()]);
        dateBox2 = new JComboBox(dates);
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        pickAdate2 = new JLabel("Pick a date first");
        panel.add(pickAdate2);
        makeOneEmptyCells();

        pickAtime2 = new JLabel("Pick a time");
        panel.add(pickAtime2);
        makeOneEmptyCells();

        pickAmovie2 = new JLabel("Pick a movie");
        panel.add(pickAmovie2);

        makeTwoEmptyCells();
        panel.add(dateBox2);

        dateBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeBox2.removeAllItems();

                for(int i = 0; i< getTimesByDate(pickedDate2).length; i++)
                {
                    String[] timeBoxItems = getTimesByDate(pickedDate2);
                    timeBox2.addItem(timeBoxItems[i]);
                }
            }
        });
        makeOneEmptyCells();
        timeBox2 = new JComboBox();
        panel.add(timeBox2);

        timeBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movieBox2.removeAllItems();

                for(int i = 0; i< getMoviesByDateAndTime(pickedDate2, pickedTime2).length; i++)
                {
                    String[] movieBoxItems = getMoviesByDateAndTime(pickedDate2, pickedTime2);
                    movieBox2.addItem(movieBoxItems[i]);

                }
            }
        });

        makeOneEmptyCells();
        movieBox2 = new JComboBox();
        panel.add(movieBox2);

        movieBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPickedMovie(e);
            }
        });

        makeOneEmptyCells();

        findSeats2 = new JButton("Find Seats");
        panel.add(findSeats2);
        findSeats2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                Cinema cinema = new Cinema(pickedShowID2, pickedCinemaID2);
            }
        });

        makeSevenEmptyCells();

    }

    public void makePicker3() //Creates the third pick-option to choose
    {


        String[] movies = getMoviesName().toArray(new String[getMoviesName().size()]);



        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        pickAMovie3 = new JLabel("Pick a movie first");
        panel.add(pickAMovie3);
        makeOneEmptyCells();

        JLabel pickAtime = new JLabel("Pick  a time");
        panel.add(pickAtime);
        makeOneEmptyCells();

        JLabel pickAday = new JLabel("Pick a date");
        panel.add(pickAday);
        makeTwoEmptyCells();



        moviesBox3 = new JComboBox(movies);
        panel.add(moviesBox3);


        moviesBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeBox3.removeAllItems();

                for(int i = 0; i< getTimesByMovie(pickedMovie3).length; i++)
                {
                    String[] timeBoxItems = getTimesByMovie(pickedMovie3);

                    timeBox3.addItem(timeBoxItems[i]);

                }
            }
        });

        makeOneEmptyCells();
        timeBox3 = new JComboBox();
        panel.add(timeBox3);
        timeBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateBox3.removeAllItems();
                for(int i = 0; i<getDatesByMovieAndTime(pickedMovie3, pickedTime3).length; i++)
                {
                    String [] dateBoxItems = getDatesByMovieAndTime(pickedMovie3, pickedTime3);
                    dateBox3.addItem(dateBoxItems[i]);
                }
            }
        });

        makeOneEmptyCells();
        dateBox3 = new JComboBox();
        dateBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setPickedDate(e);
            }
        });

        panel.add(dateBox3);
        makeOneEmptyCells();
        findSeat3 = new JButton("Find Seats");
        panel.add(findSeat3);
        findSeat3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                Cinema cinema = new Cinema(pickedShowID3, pickedCinemaNr3);
            }
        });
        makeSevenEmptyCells();
    }

    public void makeSevenEmptyCells()
    {
        for(int i = 0; i<7;i++)
        {
            JPanel p = new JPanel();
            panel.add(p);
        }
    }

    public void makeSixEmptyCells()
    {
        for(int i = 0; i<6;i++)
        {
            JPanel p = new JPanel();
            panel.add(p);
        }
    }



    public void makeTwoEmptyCells()
    {
        for(int i = 0; i<2;i++)
        {
            JPanel p = new JPanel();
            panel.add(p);
        }
    }
    public void makeOneEmptyCells()
    {
        JPanel p = new JPanel();
        panel.add(p);
    }

    public void actionPerformed(ActionEvent e)
    {



    }

}