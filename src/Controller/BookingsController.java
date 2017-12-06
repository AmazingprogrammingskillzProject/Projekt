package Controller;

import Modules.V1_Bookings;


import Modules.V1_Database;
import com.jfoenix.controls.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import static Modules.V1_Database.getBookings;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookingsController implements Initializable {

    @FXML
    private JFXTextField seachnumber;

    @FXML
    private TableView<V1_Bookings> tablevew;

    @FXML
    private JFXButton searchbutt;

    @FXML
    private TableColumn<V1_Bookings, String> phone;

    @FXML
    private TableColumn<V1_Bookings, Integer> BID, showid, row, firstseat, lastseat;

    @FXML
    private JFXTextField seachnumber2;

    @FXML
    private JFXTextField bookingID;

    @FXML
    private JFXButton cancel;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        searchbutt.setOnMouseClicked(event -> {
            ArrayList<V1_Bookings> bookingsFound = new ArrayList<>();
            for(V1_Bookings b: getBookings())
            {

                if (b.getPhone().equals(seachnumber.getText()))
                {
                    bookingsFound.add(b);

                }
            }
            ObservableList<V1_Bookings> bookingsfoundO = FXCollections.observableArrayList(bookingsFound);


            phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
            BID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            showid.setCellValueFactory(new PropertyValueFactory<>("movieName"));
            row.setCellValueFactory(new PropertyValueFactory<>("Row"));
            firstseat.setCellValueFactory(new PropertyValueFactory<>("FirstSeat"));
            lastseat.setCellValueFactory(new PropertyValueFactory<>("LastSeat"));
            tablevew.setItems(bookingsfoundO);
        });
        cancel.setRipplerFill(Color.RED);
        if(!(seachnumber2.getText().equals("")&&bookingID.getText().equals("")))
        {
            cancel.setOnMouseClicked(event -> {
                V1_Database.DeleteBooking(seachnumber2.getText(), Integer.parseInt(bookingID.getText()));

            });
        }








    }
}

