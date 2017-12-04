package View;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import View.NewReservation;



public class NewReservation extends Application {



    Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds(); //gets screen resolution data
    private double offset_x; //Start x point on mouse click
    private double offset_y; //Start y point on mouse click
    public static void main(String[] args)
    {

        launch(args);


    }


    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("reservation.fxml"));
        stage.initStyle(StageStyle.TRANSPARENT);
        root.setOnMousePressed(event -> {
            offset_x = event.getSceneX();
            offset_y = event.getSceneY();
        });
        //Sets the new coords for the stage
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - offset_x);
            stage.setY(event.getScreenY() - offset_y);
        });
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();


        //when mouse is pressed anywhere on the scene it stores the position




    }
}
