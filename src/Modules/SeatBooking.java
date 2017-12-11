package Modules;

public class SeatBooking {

    private int Booking_ID;
    private int Showing_ID;
    private int Row;
    private int Seat;


    public int getShowing_ID() {
        return Showing_ID;
    }

    public int getRow() {
        return Row;
    }

    public int getSeat() {
        return Seat;
    }

    public SeatBooking(int booking_ID, int showing_ID, int row, int seat) {
        Booking_ID = booking_ID;
        Showing_ID = showing_ID;
        Row = row;
        Seat = seat;
    }

    @Override
    public String toString() {
        return "SeatBooking{" +
                "Booking_ID = " + Booking_ID +
                ", Showing_ID = " + Showing_ID +
                ", Row = " + Row +
                ", Seat = " + Seat +
                '}';
    }
}
