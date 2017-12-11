package Exception;

import javax.swing.*;

public class NoInternetConnectException extends Exception
{
    public NoInternetConnectException()
    {
        super("No Internet");
        JOptionPane error = new JOptionPane();
        error.showMessageDialog(null, "No Internet Connection found\n The Program will close now");
        System.exit(0);
    }
}
