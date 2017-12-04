package Controller;

import com.jfoenix.controls.JFXTreeCell;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewReservationController
{
    @FXML
    private Label dateshow;

    @FXML
    private JFXTreeCell thisday;



    public void initialize()
    {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
        String dateText = sdf.format(d);
        dateshow.setText(dateText);

        String[] daysOfTheWeek = {"Sunday", "Monday", "Tuesday,", "Wednesday", "Thursday","Friday","Saturday"};

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        thisday.setText("hi");

    }
}
