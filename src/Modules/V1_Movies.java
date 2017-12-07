package Modules;

import java.lang.ref.SoftReference;
import java.util.ArrayList;



public class V1_Movies {

    private int ID;
    private String Name;
    private String Length;
    private String Description;

    public int getID() {
        return ID;
    }

    public V1_Movies(int ID, String name, String length, String description) {
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
        return "V1_Movies{" +
                "ID = " + ID +
                ", Name = '" + Name + '\'' +
                ", Length = '" + Length + '\'' +
                ", Description = '" + Description + '\'' +
                '}';
    }
}
