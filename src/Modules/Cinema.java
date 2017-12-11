package Modules;

public class Cinema {

    private int ID;
    private int Number;
    private int Rows;
    private int Seats;

    public int getID() {
        return ID;
    }

    public int getNumber() {
        return Number;
    }

    public int getRows() {
        return Rows;
    }

    public int getSeats() {
        return Seats;
    }

    public Cinema(int ID, int number, int rows, int seats) {
        this.ID = ID;
        Number = number;
        Rows = rows;
        Seats = seats;
    }

    public Cinema() {
        this(-1, -1, 8, 12);
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "ID = " + ID +
                ", Number = " + Number +
                ", Rows = " + Rows +
                ", Seats = " + Seats +
                '}';
    }
}
