package Modules;

public class Booking {

    private int ID;
    private String Phone;
    private int Showing_ID;
    private int Row;
    private int FirstSeat;
    private int LastSeat;


    // Bruges til oprettelse af Booking objekter
    public Booking(int ID, String phones, int showing_ID, int row, int firstSeat, int lastSeat) {
        this.ID = ID;
        Phone = phones;
        Showing_ID = showing_ID;
        Row = row;
        FirstSeat = firstSeat;
        LastSeat = lastSeat;


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
        return "Booking{" +
                "ID = " + ID +
                ", Phone = '" + Phone + '\'' +
                ", Showing_ID = " + Showing_ID +
                ", Row = " + Row +
                ", FirstSeat = " + FirstSeat +
                ", LastSeat = " + LastSeat +
                '}';
    }
}