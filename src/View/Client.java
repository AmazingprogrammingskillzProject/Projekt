/*
package View;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.awt.*;
import static Modules.Show.connectDB;


public class Client extends Application {

    private static Dimension  screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //gets screen resolution data

    private double offset_x; //Start x point on mouse click
    private double offset_y; //Start y point on mouse click


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("design.fxml"));

        Scene scene = new Scene(root, (screenSize.getWidth()/2), screenSize.getHeight()/2);

        //when mouse is pressed anywhere on the scene it stores the position
        scene.setOnMousePressed(event -> {
            offset_x = event.getSceneX();
            offset_y = event.getSceneY();
        });
        //Sets the new coords for the stage
        scene.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - offset_x);
            primaryStage.setY(event.getScreenY() - offset_y);
        });

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args)
    {

        connectDB();
        launch(args);


    }
}
*/
