package Controller;

import View.Main;

import static Modules.V1_Database.LoadEntireDB;

public class MainController {
    public static void main(String[] args)
    {
        LoadEntireDB();
        Main client = new Main();
        client.makeFrame();
    }

}
