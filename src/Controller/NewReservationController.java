package Controller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



import static View.NewReservation.drawButtons;


public class NewReservationController {


    @FXML
    private Label dateshow;

    @FXML
    private AnchorPane moviepane;
    @FXML
    private Label day7;

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


    public void initialize() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
        String dateText = sdf.format(d);
        dateshow.setText(dateText);

        String[] daysOfTheWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "nextday"};

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK); //day = 1 = sunday)

        drawButtons(moviepane);


        switch (day) {
            case 1:
                today.setText(daysOfTheWeek[0]);
                day2.setText(daysOfTheWeek[1]);
                day3.setText(daysOfTheWeek[2]);
                day4.setText(daysOfTheWeek[3]);
                day5.setText(daysOfTheWeek[4]);
                day6.setText(daysOfTheWeek[5]);
                day7.setText(daysOfTheWeek[6]);
                break;
            case 2:
                today.setText(daysOfTheWeek[1]);
                day2.setText(daysOfTheWeek[2]);
                day3.setText(daysOfTheWeek[3]);
                day4.setText(daysOfTheWeek[4]);
                day5.setText(daysOfTheWeek[5]);
                day6.setText(daysOfTheWeek[6]);
                day7.setText(daysOfTheWeek[0]);
                break;
            case 3:
                today.setText(daysOfTheWeek[2]);
                day2.setText(daysOfTheWeek[3]);
                day3.setText(daysOfTheWeek[4]);
                day4.setText(daysOfTheWeek[5]);
                day5.setText(daysOfTheWeek[6]);
                day6.setText(daysOfTheWeek[0]);
                day7.setText(daysOfTheWeek[1]);
                break;
            case 4:
                today.setText(daysOfTheWeek[3]);
                day2.setText(daysOfTheWeek[4]);
                day3.setText(daysOfTheWeek[5]);
                day4.setText(daysOfTheWeek[6]);
                day5.setText(daysOfTheWeek[0]);
                day6.setText(daysOfTheWeek[1]);
                day7.setText(daysOfTheWeek[2]);
                break;
            case 5:
                today.setText(daysOfTheWeek[4]);
                day2.setText(daysOfTheWeek[5]);
                day3.setText(daysOfTheWeek[6]);
                day4.setText(daysOfTheWeek[0]);
                day5.setText(daysOfTheWeek[1]);
                day6.setText(daysOfTheWeek[2]);
                day7.setText(daysOfTheWeek[3]);
                break;
            case 6:
                today.setText(daysOfTheWeek[5]);
                day2.setText(daysOfTheWeek[6]);
                day3.setText(daysOfTheWeek[0]);
                day4.setText(daysOfTheWeek[1]);
                day5.setText(daysOfTheWeek[2]);
                day6.setText(daysOfTheWeek[3]);
                day7.setText(daysOfTheWeek[4]);
                break;
            case 7:
                today.setText(daysOfTheWeek[6]);
                day2.setText(daysOfTheWeek[0]);
                day3.setText(daysOfTheWeek[1]);
                day4.setText(daysOfTheWeek[2]);
                day5.setText(daysOfTheWeek[3]);
                day6.setText(daysOfTheWeek[4]);
                day7.setText(daysOfTheWeek[5]);
                break;


        }

    }
}