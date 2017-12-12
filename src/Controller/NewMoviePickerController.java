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
    public static ArrayList<String>getDates() //gets all possbile dates and sorts it
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
        Collections.sort(dates);
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

    public static ArrayList<String> getMoviesName() //gets all the movies the cinema offers and sorts it.
    {
        ArrayList<String>movienames = new ArrayList<>();
        for(Showing s: getShowings())
        {
            if(!(movienames.contains(getMovieName(s.getMovie_ID()-1))))
            movienames.add(getMovieName(s.getMovie_ID()-1));
        }

        Collections.sort(movienames);
        return movienames;
    }

    public static String[] sortedGetMoviesByDateAndTime(String date, String time) //sorts the ArrayList getMoviesByDateAndTime()
    {
        if(getTimeBox2().getSelectedItem()!=null)
        {
            setPickedTime2(getTimeBox2().getSelectedItem().toString());
        }
        List<String> sortedList = getMoviesByDateAndTime(date,time).subList(0, getMoviesByDateAndTime(date,time).size());
        Collections.sort(sortedList);
        sortedList.removeAll(Collections.singleton(null));

        return sortedList.toArray(new String[getMoviesByDateAndTime(getPickedDate2(),getPickedTime2()).size()]);
    }



    public static ArrayList<String> getMoviesByDateAndTime(String date, String time) //This gets movies depended on date and time
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

    public static String[] sortedGetTimesByDate(String date) //This one sorts the ArrayList getTimesByDate()
    {
        if (getDateBox2().getSelectedItem()!=null)
        {
            setPickedDate2(getDateBox2().getSelectedItem().toString());
        }
        List<String> sortedList = getTimesByDate(date).subList(0, getTimesByDate(date).size());
        Collections.sort(sortedList);
        sortedList.removeAll(Collections.singleton(null));
        return sortedList.toArray(new String[getTimesByDate(getPickedDate2()).size()]);
    }


    public static ArrayList<String> getTimesByDate(String date) //This gets all possible times
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





    public static String[] sortedGetDatesByMovie(String moviename) //this sorts the ArrayList getDatesByMovie()
    {
        if(getMoviesBox1().getSelectedItem()!=null)
        {
            setPickedMovie1(getMoviesBox1().getSelectedItem().toString());
        }
        List<String> sortedList = getDatesByMovie(moviename).subList(0, getDatesByMovie(moviename).size());
        Collections.sort(sortedList);
        sortedList.removeAll(Collections.singleton(null));

        return sortedList.toArray(new String[getDatesByMovie(getPickedMovie1()).size()]);
    }

    public static ArrayList<String> getDatesByMovie(String moviename) //gets the date depended on the picked movie
    {
        ArrayList<String>dates = new ArrayList<>();

            for(Showing s: getShowings())
            {
                if(getMovieIDbyName(moviename) == s.getMovie_ID()&&!(dates.contains(s.getDate())))
                {
                    dates.add(s.getDate());
                }
            }
        dates.removeAll(Collections.singleton(null));

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
    public static String[] sortedGetDatesByMovieAndTime(String movieName, String time) //sorts the ArrayList getDatesByMovieAndTime()
    {
        if(getTimeBox3().getSelectedItem()!=null)
        {
            setPickedTime3(getTimeBox3().getSelectedItem().toString());
        }
        List<String> sortedList = getDatesByMovieAndTime(movieName,time).subList(0,getDatesByMovieAndTime(movieName,time).size());
        Collections.sort(sortedList);
        sortedList.removeAll(Collections.singleton(null));
        return sortedList.toArray(new String[getDatesByMovieAndTime(getPickedMovie3(),getPickedTime3()).size()]);
    }

         public static ArrayList<String> getDatesByMovieAndTime(String movieName, String time) //gets the dates depended on the picked movie and time
    {
        ArrayList<String> dates = new ArrayList<>();

            for(Showing s: getShowings()) {
                if (getMovieIDbyName(movieName) == s.getMovie_ID() && s.getTime().equals(time))
                {
                    dates.add(s.getDate());
                }
            }
        dates.removeAll(Collections.singleton(null));

        return dates;
    }


    public static String[] sortedGetTimesByDateAndMovie(String movieName, String date)  //gets all possible times when movie is chosen
    {
        if(getDateBox1().getSelectedItem()!=null)
        {
            setPickedDate1(getDateBox1().getSelectedItem().toString());
        }
        List<String> sortedList = getTimesByDateAndMovie(movieName,date).subList(0, getTimesByDateAndMovie(movieName,date).size());
        Collections.sort(sortedList);
        sortedList.removeAll(Collections.singleton(null));

        return sortedList.toArray(new String[getTimesByDateAndMovie(getPickedMovie1(),getPickedDate1()).size()]);
    }

    public static ArrayList<String> getTimesByDateAndMovie(String movieName, String date)  //gets the times depended on the picked date and movie
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


    public static ArrayList<String> getTimesByMovie(String moviename) //gets the times depended on the picked movie
    {
        ArrayList<String>times = new ArrayList<>();


            for(Showing s: getShowings())
            {
                if(getMovieIDbyName(moviename) == s.getMovie_ID()&&!(times.contains(s.getTime())))
                {
                    times.add(s.getTime());
                }
            }
        times.removeAll(Collections.singleton(null));

        return times;
    }

    public static String[] sortedGetTimesByMovie(String moviename) //sorts the ArrayList getTimesByMovie()
    {

        if(getMoviesBox3().getSelectedItem()!=null)
        {
            setPickedMovie3(getMoviesBox3().getSelectedItem().toString());

        }

        List<String> sortedList = getTimesByMovie(moviename).subList(0, getTimesByMovie(moviename).size());
        Collections.sort(sortedList);
        sortedList.removeAll(Collections.singleton(null));

        return sortedList.toArray(new String[getTimesByMovie(getPickedMovie3()).size()]);
    }
}
