package Modules;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static Modules.Database.connectDB;


public class Show
{
    private int fid;
    private int sal;
    private String movieName;
    private String time;
    private Date date;
    private static List<Show> shows = new ArrayList<>();

    public static void main(String[] args) {
        connectDB();
        print();
    }

    public static void print()
    {
        for(Show s: shows)
        {
            System.out.println("FID: "+s.fid+"   Sal:  "+s.sal+"  FilmNavn:   "+s.movieName+"           Tid:  "+s.time+"  Dato:  "+s.date);
        }
    }

    public void addShow(Show s)
    {
        shows.add(s);
    }


    public Show(int fid, int sal, String movieName, String time, Date date)
    {
        this.fid = fid;
        this.sal = sal;
        this.movieName = movieName;
        this.time = time;
        this.date = date;

    }

}


