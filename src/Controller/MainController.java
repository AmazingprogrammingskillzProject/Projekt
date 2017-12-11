package Controller;

import View.MainView;

import java.io.IOException;
import java.net.*;
import Exception.NoInternetConnectionException;

import static Modules.Database.LoadEntireDB;


public class MainController
{

    public static void main(String[] args) throws NoInternetConnectionException {

        if(netIsAvailable()==true)
        {
            LoadEntireDB();
            MainView client = new MainView();
            client.makeFrame();

        }
        else
        {
            throw new NoInternetConnectionException();

        }

    }

    private static boolean netIsAvailable() {
        try
        {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }

}
