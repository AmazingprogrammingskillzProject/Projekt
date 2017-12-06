package Modules;

public class V1_Bookings {

    private int ID;
    private String Phone;
    private int Showing_ID;
    private int Row;
    private int FirstSeat;
    private int LastSeat;
    private String movieName;


    public V1_Bookings(int ID, String phones, int showing_ID, String movieName,  int row, int firstSeat, int lastSeat) {
        this.ID = ID;
        Phone = phones;
        Showing_ID = showing_ID;
        Row = row;
        FirstSeat = firstSeat;
        LastSeat = lastSeat;
        this.movieName = movieName;

    }

    public int getShowing_ID() {
        return Showing_ID;
    }

    public int getFirstSeat() {
        return FirstSeat;
    }

    public int getID() {
        return ID;
    }

    public int getLastSeat() {
        return LastSeat;
    }

    public int getRow() {
        return Row;
    }

    public String getPhone() {
        return Phone;
    }

    @Override
    public String toString() {
        return "V1_Bookings{" +
                "ID = " + ID +
                ", Phone = '" + Phone + '\'' +
                ", Showing_ID = " + Showing_ID +
                ", Row = " + Row +
                ", FirstSeat = " + FirstSeat +
                ", LastSeat = " + LastSeat +
                '}';
    }
}