package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeCell;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import Modules.V1_Database.*;
import javafx.scene.paint.Color;

import static Modules.V1_Database.getNumbersOfMovies;
import static View.NewReservation.drawButtons;


public class NewReservationController
{


    @FXML
    private Label dateshow;

    @FXML
    private AnchorPane moviepane;

    @FXML
    private Label day6;

    @FXML
    private Label day5;

    @FXML
    private Label day4;

    @FXML
    private Label day3;

    @FXML
    private Label day2;

    @FXML
    private Label today;



    public void initialize()
    {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
        String dateText = sdf.format(d);
        dateshow.setText(dateText);

        String[] daysOfTheWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday","Friday","Saturday"};

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        today.setText(daysOfTheWeek[day-1]);
        day2.setText(daysOfTheWeek[day]);
        day3.setText(daysOfTheWeek[day+1]);
        day4.setText(daysOfTheWeek[day+2]);
        day5.setText(daysOfTheWeek[day+3]);
        day6.setText(daysOfTheWeek[day+3]);
        drawButtons(moviepane);






    }
}
