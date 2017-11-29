/*


package View;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;

import static Modules.Show.connectDB;


public class Client extends Application {

    public static Dimension  screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    @FXML
    private AnchorPane windows;



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


    }
}
*/
