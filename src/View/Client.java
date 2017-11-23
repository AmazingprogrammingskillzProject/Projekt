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

import static SQL.MySQL.connectDB;

public class Client extends Application {

    private static Dimension  screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    @Override

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("design.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, (screenSize.getWidth()/2), screenSize.getHeight()/2));

        primaryStage.show();

    }
    public static void main(String[] args)
    {

        connectDB();
        launch(args);


    }
}
