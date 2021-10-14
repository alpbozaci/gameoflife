package ch.bozaci.gameoflife;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class GameOfLifeItem extends Rectangle
{
    private boolean nextState;

    public GameOfLifeItem(double x, double y, double delta)
    {
        this.setId("(" + x + "/" + y + ")");
        this.setX(x * delta);
        this.setY(y * delta);
        this.setWidth(delta);
        this.setHeight(delta);
        this.setArcWidth(0);
        this.setArcHeight(0);
        this.setFill(Color.WHITESMOKE);
        this.setStroke(Color.GREY);
    }

    public boolean isAlive()
    {
        return this.getFill().equals(Color.BURLYWOOD);
    }

    public boolean isDead()
    {
        return this.getFill().equals(Color.WHITESMOKE);
    }

    public void setAlive()
    {
        this.setFill(Color.BURLYWOOD);
    }

    public void setDead()
    {
        this.setFill(Color.WHITESMOKE);
    }

    public boolean isNextState()
    {
        return nextState;
    }

    public void setNextState(boolean nextState)
    {
        this.nextState = nextState;
    }

}
