package Controller;

import Modules.Movie;
import Modules.Showing;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static Modules.Database.*;

import static View.NewMoviePickerView.*;



public class NewMoviePickerController
{
    public static ArrayList<String>getDates() //gets all possbile dates
    {
        ArrayList<String> dates = new ArrayList<>();
        for(Showing s: getShowings())
        {
            if(!(dates.contains(s.getDate())))
            {
                dates.add(s.getDate());
            }
        }

        dates.removeAll(Collections.singleton(null));
        return dates;


    }
    public static int getMovieIDbyName(String movieName) //returns a movie ID when a name is inputed
    {
        int ID = 0;
        for(Movie m: getMovies())
        {
            if(m.getName().equals(movieName))
            {
                ID = m.getID();
            }
        }
        return ID;
    }

    public static ArrayList<String> getMoviesName() //gets all the movies the cinema offers
    {
        ArrayList<String>movienames = new ArrayList<>();
        for(Showing s: getShowings())
        {
            if(!(movienames.contains(getMovieName(s.getMovie_ID()-1))))
            movienames.add(getMovieName(s.getMovie_ID()-1));
        }
        return movienames;
    }

    public static String[] getMoviesByDateAndTime(String date, String time) //gets all possible movies when a date and a time is picked
    {
        ArrayList<String> movies = new ArrayList<>();

        if(getTimeBox2().getSelectedItem()!=null)
        {
            setPickedTime2(getTimeBox2().getSelectedItem().toString());

            for(Showing s: getShowings())
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


        return sortedList.toArray(new String[loadGetMoviesByDateAndTime(getPickedDate2(),getPickedTime2()).size()]);
    }
    //This one loads "getMoviesByDateAndTime". All the loads methods loads the get method. If these aren't loaded, it will require to choose an item
    // two times before it pick  object
    public static ArrayList<String> loadGetMoviesByDateAndTime(String date, String time) //This one loads "getMoviesByDateAndTime".
    {
        ArrayList<String> movies = new ArrayList<>();

            for(Showing s: getShowings())
            {
                if(s.getDate().equals(date)&&s.getTime().equals(time))
                {
                    movies.add(getmovie((s.getMovie_ID()-1)).getName());
                }
            }


        movies.removeAll(Collections.singleton(null));

        return movies;
    }

    public static String[] getTimesByDate(String date) //gets all possible Times when date is chosen
    {
        ArrayList<String> times = new ArrayList<>();

        if (getDateBox2().getSelectedItem()!=null)
        {
            setPickedDate2(getDateBox2().getSelectedItem().toString());

            for(Showing s : getShowings())
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


        return sortedList.toArray(new String[loadGetTimesByDate(getPickedDate2()).size()]);
    }
    public static ArrayList<String> loadGetTimesByDate(String date)
    {
        ArrayList<String> times = new ArrayList<>();

            for(Showing s : getShowings())
            {
                if(s.getDate().equals(date)&&!(times.contains(s.getTime())))
                {
                    times.add(s.getTime());
                }
            }
        times.removeAll(Collections.singleton(null));


        return times;
    }


    public static void setPickedTime(ActionEvent evt) //This sets the picked time
    {
        if(getTimeBox1().getSelectedItem()!=null) {
            setPickedTime1(getTimeBox1().getSelectedItem().toString());

            for (Showing s : getShowings())
            {
                if (getMovieIDbyName(getPickedMovie1()) == s.getMovie_ID()&&s.getDate().equals(getPickedDate1())&&s.getTime().equals(getPickedTime1()))
                {
                    setPickedShowID1(s.getID());
                    setPickedCinemaID1(s.getCinema_ID());
                }

            }
        }
    }

    public static String[] getTimesByMovie(String movieName, String date)  //gets all possible times when movie is chosen
    {
        ArrayList<String> times = new ArrayList<>();

        if(getDateBox1().getSelectedItem()!=null)
        {
            setPickedDate1(getDateBox1().getSelectedItem().toString());
            for(Showing s: getShowings()) {
                if (getMovieIDbyName(movieName) == s.getMovie_ID() && s.getDate().equals(date))
                {
                    times.add(s.getTime());
                }
            }
        }
        List<String> sortedList = times.subList(0, times.size());
        Collections.sort(sortedList);
        sortedList.removeAll(Collections.singleton(null));




        return sortedList.toArray(new String[loadGetTimesByMovieAndDate(getPickedMovie1(),getPickedDate1()).size()]);
    }

    public static ArrayList<String> loadGetTimesByMovieAndDate(String movieName, String date)
    {
        ArrayList<String> times = new ArrayList<>();

            for(Showing s: getShowings()) {
                if (getMovieIDbyName(movieName) == s.getMovie_ID() && s.getDate().equals(date))
                {
                    times.add(s.getTime());
                }
            }


        times.removeAll(Collections.singleton(null));
        return times;
    }

    public static String[] getDatesByMovie(String moviename)
    {
        ArrayList<String>dates = new ArrayList<>();
        if(getMoviesBox1().getSelectedItem()!=null)
        {
            setPickedMovie1(getMoviesBox1().getSelectedItem().toString());

            for(Showing s: getShowings())
            {
                if(getMovieIDbyName(moviename) == s.getMovie_ID()&&!(dates.contains(s.getDate())))
                {
                    dates.add(s.getDate());
                }
            }

        }
        List<String> sortedList = dates.subList(0, dates.size());
        Collections.sort(sortedList);
        sortedList.removeAll(Collections.singleton(null));

        return sortedList.toArray(new String[loadGetDatesByMovie(getPickedMovie1()).size()]);
    }
    public static ArrayList<String> loadGetDatesByMovie(String moviename)
    {
        ArrayList<String>dates = new ArrayList<>();

            for(Showing s: getShowings())
            {
                if(getMovieIDbyName(moviename) == s.getMovie_ID()&&!(dates.contains(s.getDate())))
                {
                    dates.add(s.getDate());
                }
            }



        return dates;
    }



    public static void setPickedMovie(ActionEvent evt)
    {
        if(getMovieBox2().getSelectedItem()!=null)
        {
            setPickedMovie2((getMovieBox2().getSelectedItem().toString()));

            for(Showing s : getShowings())
            {
                if(s.getTime().equals(getPickedTime2())&&s.getDate().equals(getPickedDate2())&&getMovieIDbyName(getPickedMovie2())==s.getMovie_ID())
                {
                    setPickedShowID2(s.getID());
                    setPickedCinemaID2(s.getCinema_ID());
                }
            }
        }
    }

    public static void setPickedDate(ActionEvent evt)
    {
        if(getDateBox3().getSelectedItem()!=null)
        {
            setPickedDate3(getDateBox3().getSelectedItem().toString());
            for(Showing s: getShowings())
            {
                if(getMovieIDbyName(getPickedMovie3())==s.getMovie_ID()&&s.getTime().equals(getPickedTime3())&&s.getDate().equals(getPickedDate3()))
                {
                    setPickedDate3(s.getDate());
                    setPickedShowID3( s.getID());
                    setPickedCinemaID3(s.getCinema_ID());

                }
            }
        }
    }
    public static String[] getDatesByMovieAndTime(String movieName, String time)
    {
        ArrayList<String> dates = new ArrayList<>();

        if(getTimeBox3().getSelectedItem()!=null)
        {
            setPickedTime3(getTimeBox3().getSelectedItem().toString());
            for(Showing s: getShowings()) {
                if (getMovieIDbyName(movieName) == s.getMovie_ID() && s.getTime().equals(time))
                {
                    dates.add(s.getDate());
                }
            }
        }
        List<String> sortedList = dates.subList(0,dates.size());
        Collections.sort(sortedList);
        sortedList.removeAll(Collections.singleton(null));




        return sortedList.toArray(new String[loadGetDatesByMovieAndTime(getPickedMovie3(),getPickedTime3()).size()]);
    }
    public static ArrayList<String> loadGetDatesByMovieAndTime(String movieName, String time)
    {
        ArrayList<String> dates = new ArrayList<>();

            for(Showing s: getShowings()) {
                if (getMovieIDbyName(movieName) == s.getMovie_ID() && s.getTime().equals(time))
                {
                    dates.add(s.getDate());
                }
            }




        return dates;
    }

    public static String[] getTimesByMovie(String moviename)
    {
        ArrayList<String>times = new ArrayList<>();
        if(getMoviesBox3().getSelectedItem()!=null)
        {
            setPickedMovie3(getMoviesBox3().getSelectedItem().toString());

            for(Showing s: getShowings())
            {
                if(getMovieIDbyName(moviename) == s.getMovie_ID()&&!(times.contains(s.getTime())))
                {
                    times.add(s.getTime());
                }
            }

        }

        List<String> sortedList = times.subList(0, times.size());
        Collections.sort(sortedList);
        sortedList.removeAll(Collections.singleton(null));

        return sortedList.toArray(new String[loadGetTimesByMovie(getPickedMovie3()).size()]);
    }
    public static ArrayList<String> loadGetTimesByMovie(String moviename)
    {
        ArrayList<String>times = new ArrayList<>();


            for(Showing s: getShowings())
            {
                if(getMovieIDbyName(moviename) == s.getMovie_ID()&&!(times.contains(s.getTime())))
                {
                    times.add(s.getTime());
                }
            }


        return times;
    }









}
