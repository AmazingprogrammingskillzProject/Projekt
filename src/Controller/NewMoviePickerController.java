package Controller;

import Modules.V1_Movies;
import Modules.V1_Showings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import static Modules.V1_Database.*;


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

    public static ArrayList<String> getMoviesName()
    {
        ArrayList<String>movienames = new ArrayList<>();
        for(V1_Movies m: getMovies())
        {
            movienames.add(m.getName());
        }
        return movienames;
    }









}
