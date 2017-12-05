package View;

import Modules.V1_Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Bookings2 extends Application {
    private double offset_x; //Start x point on mouse click
    private double offset_y;

    public static void main(String[] args)
    {
        V1_Database.LoadBookings();
        V1_Database.getBookings();

        launch(args);
    }

    @Override
    public void start(Stage stage)  throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Bookings2.fxml"));
        Scene scene = new Scene(root);
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

        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();


    }
}
