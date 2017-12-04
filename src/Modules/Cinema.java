package Modules;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cinema
{
    private int cinemanumber;
    private int FID;
    private int seats = 12;
    private int rows = 8;
    private int seat;
    private int row;
    private boolean reserved;
    private List<Cinema> seatinfo = new ArrayList<>();

    private boolean[][] reservedseats = new boolean[seats][rows];

    public void addSeatInfo(Cinema c)
    {
        seatinfo.add(c);
    }





    public Cinema(int cinemanumber, int FID, int seat, int row, boolean reserved) //Constructor
    {
        this.cinemanumber = cinemanumber;
        this.FID = FID;
        this.seat = seat;
        this.row = row;
        this.reserved = reserved;
    }





    public boolean sortReserved(int seat, int row)
    {
        boolean[][] i = new boolean[seats][rows];
        for (Cinema s: seatinfo)
        {

            i[s.getSeat()-1][s.getRow()-1] = s.getReserved();

        }

        return i[seat][row];
    }


    public void addReservedseats(Cinema c)
    {
        for(int r = 0; r<rows; r++)
        {
            for(int s = 0; s<seats; s++)
            {
                reservedseats[s][r] = sortReserved(s, r);
            }
        }
    }

    public boolean getReserved()
    {
        return reserved;
    }


    public int getCinemanumber() {
        return cinemanumber;
    }

    public int getFID() {
        return FID;
    }

    public int getSeat() {
        return seat;
    }



    public int getRow() {
        return row;
    }

}
