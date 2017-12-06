package Modules;

public class V1_Showings {

    private int ID;
    private int Cinema_ID;
    private int Movie_ID;
    private String Date;
    private String Time;

    public int getCinema_ID() {
        return Cinema_ID;
    }

    public String getDate() {
        return Date;
    }

    public String getTime() {
        return Time;
    }

    public V1_Showings(int ID, int cinema_ID, int movie_ID, String date, String time) {
        this.ID = ID;
        Cinema_ID = cinema_ID;
        Movie_ID = movie_ID;
        Date = date;
        Time = time;
    }

    @Override
    public String toString() {
        return "V1_Showings{" +
                "ID = " + ID +
                ", Cinema_ID = " + Cinema_ID +
                ", Movie_ID = " + Movie_ID +
                ", Date = '" + Date + '\'' +
                ", Time = '" + Time + '\'' +
                '}';
    }
}