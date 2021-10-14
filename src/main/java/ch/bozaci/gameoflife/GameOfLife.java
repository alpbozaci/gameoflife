package ch.bozaci.gameoflife;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class GameOfLife extends Application
{
    private static final Integer DELTA = 20;
    private static final Integer SCREEN_WIDTH = 2000;
    private static final Integer SCREEN_HEIGHT = 1200;

    private static final Integer X_MATRIX_MAX = 100;
    private static final Integer Y_MATRIX_Max = 50;

    private GameOfLifeItem[][] matrix;

    private Timeline timeline;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        timeline = new Timeline(new KeyFrame(Duration.ZERO, new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                calculateNextMatrix();
            }
        }), new KeyFrame(Duration.millis(100)));

        timeline.setCycleCount(Timeline.INDEFINITE);

        BorderPane borderPane = new BorderPane();
        Group matrixGrp = new Group();
        createMatrix();
        drawMatrix(matrixGrp);
        borderPane.setCenter(matrixGrp);

        // Group buttonGrp = new Group();
        FlowPane buttonGrp = new FlowPane();
        buttonGrp.getChildren().add(createNextButton());
        buttonGrp.getChildren().add(createStartButton());
        buttonGrp.getChildren().add(createStopButton());
        borderPane.setBottom(buttonGrp);

        Scene scene = new Scene(borderPane, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    private void createMatrix()
    {
        matrix = new GameOfLifeItem[X_MATRIX_MAX][Y_MATRIX_Max];

        for (int y = 0; y < Y_MATRIX_Max; y++)
        {
            for (int x = 0; x < X_MATRIX_MAX; x++)
            {
                GameOfLifeItem item = createItem(x, y);
                matrix[x][y] = item;
            }
        }
    }

    private void drawMatrix(Group group)
    {
        for (int y = 0; y < Y_MATRIX_Max; y++)
        {
            for (int x = 0; x < X_MATRIX_MAX; x++)
            {
                GameOfLifeItem rectangle = matrix[x][y];
                group.getChildren().add(rectangle);
            }
        }
    }

    private void redrawMatrix()
    {
        for (int y = 0; y < Y_MATRIX_Max; y++)
        {
            for (int x = 0; x < X_MATRIX_MAX; x++)
            {
                GameOfLifeItem item = matrix[x][y];
                if (item.isNextState())
                {
                    item.setAlive();
                }
                else
                {
                    item.setDead();
                }
            }
        }
    }

    private GameOfLifeItem createItem(double x, double y)
    {
        GameOfLifeItem item = new GameOfLifeItem(x, y, DELTA);

        item.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                System.out.println("clicked: " + item.getId());

                if (item.isDead())
                {
                    item.setAlive();
                }
                else
                {
                    item.setDead();
                }
            }
        });

        return item;
    }

    private Button createNextButton()
    {
        Button button = new Button("next");
        button.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                System.out.println("next");
                calculateNextMatrix();
            }
        });

        return button;
    }

    private Button createStartButton()
    {
        Button button = new Button("start");
        button.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                System.out.println("start");
                timeline.play();
            }
        });

        return button;
    }

    private Button createStopButton()
    {
        Button button = new Button("stop");
        button.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                System.out.println("stop");
                timeline.stop();
            }
        });

        return button;
    }

    private void calculateNextMatrix()
    {
        for (int y = 1; y < Y_MATRIX_Max - 1; y++)
        {
            for (int x = 1; x < X_MATRIX_MAX - 1; x++)
            {
                GameOfLifeService.calculateNextState(matrix, x, y);
            }
        }
        redrawMatrix();
    }

}