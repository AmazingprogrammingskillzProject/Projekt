package Exception;

import javax.swing.*;

public class NoInternetConnectionException extends Exception
{
    public NoInternetConnectionException()
    {
        super("No Internet");
        JOptionPane error = new JOptionPane();
        error.showMessageDialog(null, "No Internet Connection found\n The Program will close now");
        System.exit(0);
    }
}
