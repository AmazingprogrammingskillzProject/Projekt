package Exception;

import javax.swing.*;


public class ShowingsNotFoundException extends RuntimeException
{
    public ShowingsNotFoundException()
    {
        super("Showings not found!");
        JOptionPane error = new JOptionPane();
        error.showMessageDialog(null, "Showings not found on selected movie.\n Please select another movie");
        return;

    }
}
