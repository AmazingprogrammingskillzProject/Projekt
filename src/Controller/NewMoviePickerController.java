package Controller;

import Modules.V1_Movies;
import Modules.V1_Showings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Modules.V1_Database.*;
import static View.NewDateTimePicker.*;


public class NewMoviePickerController
{
    public static ArrayList<String>getDates()
    {
        ArrayList<String> dates = new ArrayList<>();
        for(V1_Showings s: getShowings())
        {
            if(!(dates.contains(s.getDate())))
            {
                dates.add(s.getDate());
            }
        }
        return dates;
    }
    public static int getMovieIDbyName(String movieName)
    {
        int ID = 0;
        for(V1_Movies m: getMovies())
        {
            if(m.getName().equals(movieName))
            {
                ID = m.getID();
            }
        }
        return ID;
    }

    public static ArrayList<String> getMoviesName()
    {
        ArrayList<String>movienames = new ArrayList<>();
        for(V1_Movies m: getMovies())
        {
            movienames.add(m.getName());
        }
        return movienames;
    }

    public static String[] getDateMovie(String date, String time)
    {
        ArrayList<String> movies = new ArrayList<>();

        if(getTimeBox().getSelectedItem()!=null)
        {
            setPickedTime(getTimeBox().getSelectedItem().toString());

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

    public static String[] getTimesByDate(String date)
    {
        ArrayList<String> times = new ArrayList<>();

        if (getDateBox().getSelectedItem()!=null)
        {
            setPickedDate(getDateBox().getSelectedItem().toString());

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









}
