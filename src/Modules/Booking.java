package Modules;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private String phone;
    private String movie;
    private String date;
    private String time;
    private int sal;
    private int row;
    private int seat;
    private List<Reservation> reservations= new ArrayList<>();


    public Reservation(String phone, String movie, String date, String time, int sal, int row, int seat)
    {
        this.phone = phone;
        this.movie = movie;
        this.date = date;
        this.time = time;
        this.sal = sal;
        this.row = row;
        this.seat = seat;
    }

    public void addReservation(Reservation r)
    {
        reservations.add(r);
    }


    //public Reservation(String phone, String)



}
