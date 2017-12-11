package Modules;

import java.lang.ref.SoftReference;
import java.util.ArrayList;



public class Movie {

    private int ID;
    private String Name;
    private String Length;
    private String Description;

    public int getID() {
        return ID;
    }

    public Movie(int ID, String name, String length, String description)
    {
        this.ID = ID;
        Name = name;
        Length = length;
        Description = description;
    }

    public String getName() {
        return Name;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "ID = " + ID +
                ", Name = '" + Name + '\'' +
                ", Length = '" + Length + '\'' +
                ", Description = '" + Description + '\'' +
                '}';
    }
}
