package Modules;

import java.util.ArrayList;
import java.util.List;

public class Booking {
    private String phone;
    private String movie;
    private String date;
    private String time;
    private int sal;
    private int row;
    private int seat;
    private List<Booking> bookings = new ArrayList<>();






    public Booking(String phone, String movie, String date, String time, int sal, int row, int seat)
    {
        this.phone = phone;
        this.movie = movie;
        this.date = date;
        this.time = time;
        this.sal = sal;
        this.row = row;
        this.seat = seat;
    }

    public void addBooking(Booking b)
    {
        bookings.add(b);
    }


    //public Booking(String phone, String)



}
