package Modules;

public class V1_Cinema {

    private int ID;
    private int Number;
    private int Rows;
    private int Seats;

    public int getID() {
        return ID;
    }

    public V1_Cinema(int ID, int number, int rows, int seats) {
        this.ID = ID;
        Number = number;
        Rows = rows;
        Seats = seats;
    }

    @Override
    public String toString() {
        return "V1_Cinema{" +
                "ID = " + ID +
                ", Number = " + Number +
                ", Rows = " + Rows +
                ", Seats = " + Seats +
                '}';
    }
}
