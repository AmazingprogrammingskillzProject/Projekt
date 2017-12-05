package Modules;

public class V1_Bookings {

    private int ID;
    private String Phone;
    private int Showing_ID;
    private int Row;
    private int FirstSeat;
    private int LastSeat;

    public V1_Bookings(int ID, String phones, int showing_ID, int row, int firstSeat, int lastSeat) {
        this.ID = ID;
        Phone = phones;
        Showing_ID = showing_ID;
        Row = row;
        FirstSeat = firstSeat;
        LastSeat = lastSeat;
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