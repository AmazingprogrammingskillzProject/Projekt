package Controller;

import Modules.V1_Showings;

import java.util.ArrayList;
import java.util.List;

import static Modules.V1_Database.getShowings;


public class NewBookingController
{
    public static ArrayList<String>getDates()
    {
        ArrayList<String> dates = new ArrayList<>();
        for(V1_Showings s: getShowings())
        {
            if(!(dates.contains(s.getDate())))
            {
                dates.add(s.getDate());
            }
        }
        return dates;
    }


}
