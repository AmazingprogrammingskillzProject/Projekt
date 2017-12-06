/*
package View;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.Screen;




public class Sal extends Application {

    private int seats = 12;
    private int rows = 8;
    Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds(); //gets screen resolution data
    private Rectangle[][] rectangles = new Rectangle[seats][rows];




    public static void main(String[] args) {
        Application.launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Group root1 = new Group();
        Parent root = FXMLLoader.load(getClass().getResource("sal.fxml"));
        Scene scene = new Scene(*/
/*//*
root1, visualBounds.getWidth()/3, visualBounds.getHeight()/2, Color.WHITE);


        for (int i = 0; i< seats; i++)
        {
/*            Rectangle r = new Rectangle();
            r.setFill(Color.GREEN);
            r.setX(scene.getWidth()/5+i*30);
            r.setY(scene.getHeight()/5);
            r.setWidth(visualBounds.getHeight()/80);
            r.setHeight(visualBounds.getHeight()/80);
            root1.getChildren().add(r);*//*


            for (int q = 0; q<rows; q++)
            {
                Rectangle s = new Rectangle();
                s.setFill(Color.GREEN);
                s.setX(scene.getWidth()/6+i*30);
                s.setY(scene.getHeight()/6+q*30);
                s.setWidth(visualBounds.getHeight()/60);
                s.setHeight(visualBounds.getHeight()/60);
                root.getChildren().add(s);
                rectangles[i][q] = s ;

                final int row = q+1 ;
                final int seat = i+1 ;
                s.setOnMouseClicked(event ->{
                    s.setFill(Color.RED);
                    System.out.println(row+","+seat);
                });

            }
        }



        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
*/
