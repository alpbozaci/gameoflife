package ch.bozaci.gameoflife;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


public class GameOfLife extends Application
{
    private static final Integer SCREEN_WIDTH = 1500;
    private static final Integer SCREEN_HEIGHT = 1000;
    private static final Integer DELTA = 20;
    
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        Group root = new Group();
        drawFields(root);

        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
    
    private void drawFields(Group root)
    {
        for (int i = 0; i < SCREEN_HEIGHT; i += DELTA)
        {
            Line line = new Line();
            
            line.setStartX(0);
            line.setStartY(i);
            line.setEndX(SCREEN_WIDTH);
            line.setEndY(i);
            
            System.out.println("line y: " + i);
            
            root.getChildren().add(line);
        }
    }
}