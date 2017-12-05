package View;

import Modules.V1_Showings;
import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.scene.shape.Line;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static Modules.V1_Database.*;


public class NewReservation extends Application {
    private static int buttoncollumns = 7;
    private static int buttonrow = 3;


    static Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds(); //gets screen resolution data
    private double offset_x; //Start x point on mouse click
    private double offset_y; //Start y point on mouse click

    private static JFXButton[][][] buttons = new JFXButton[6][buttonrow][buttoncollumns];


    static Date de = new Date();
    static SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd");
    static String dateText = sdff.format(de);
    static String todayString = dateText;



    public static void main(String[] args)
    {

        LoadEntireDB();


        launch(args);



    }


    @Override
    public void start(Stage stage) throws Exception
    {
        System.out.println(todayString);
        Parent root = FXMLLoader.load(getClass().getResource("reservation2.fxml"));
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


        //when mouse is pressed anywhere on the scene it stores the position

    }



    public static void drawButtons(AnchorPane anchorPane)
    {

        for(int m = 0; m <getNumbersOfMovies();m++)
        {
            for (int c = 0; c<buttonrow; c++)
            {
                for(int r = 0; r<buttoncollumns; r++)
                {
                    JFXButton b = new JFXButton();

                    b.setStyle("-fx-background-color: Green");
                    b.setRipplerFill(Color.BLACK);

                    b.setTextFill(Color.WHITE);
                    b.setLayoutX(visualBounds.getWidth()/6+r*100);
                    b.setLayoutY((visualBounds.getHeight()/19+c*50)+m*200);
                    b.setScaleX(visualBounds.getWidth()/2500);
                    b.setScaleY(visualBounds.getHeight()/1900);
                    buttons[m][c][r] = b;

                    final int movie = m;
                    final int coloumn = c;
                    final int row = r;



                    b.setOnMouseClicked(event -> {
                        System.out.println(getMovieName(movie)+": "+row+", "+coloumn);

                    });


                    anchorPane.getChildren().add(b);

                }
            }
            Line line1 = new Line();
            line1.setStartX(visualBounds.getWidth()/256);
            line1.setStartY(visualBounds.getHeight()/5.625+m*200);
            line1.setEndX(visualBounds.getWidth()/2.56);
            line1.setEndY(visualBounds.getHeight()/5.625+m*200);
            line1.setOpacity(0.5);



            anchorPane.getChildren().add(line1);


            Label moviename = new Label();
            moviename.setText(getMovieName(m));
            moviename.setStyle("-fx-font: 20 system; -fx-font-weight: bold; ");
            moviename.setLayoutX(50);
            moviename.setLayoutY(120+m*200);
            anchorPane.getChildren().add(moviename);

        }
        loadShows();

    }
    public static void loadShows()
    {
        for(int m = 0; m<getNumbersOfMovies();m++)
        {
            for(int c = 0; c<buttoncollumns; c++)
            {
                for(int r = 0; r<buttonrow; r++)
                {
                    for(V1_Showings s: getShowings())
                    {
                        if(s.getDate().equals(todayString)&&s.getMovie_ID()==m+1)
                        {
                            buttons[m][r][c].setText(s.getTime());
                        }
                        else
                        {
                        }

                    }

                }
            }
        }
    }

}
