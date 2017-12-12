package Exception;

import javax.swing.*;


public class ShowingsNotFoundException extends Exception
{
    public ShowingsNotFoundException()
    {
        super("Showings not found!");
        JOptionPane error = new JOptionPane();
        error.showMessageDialog(null, "Showings not found on selected movie.\n Please select another movie");
        return;

    }
}
