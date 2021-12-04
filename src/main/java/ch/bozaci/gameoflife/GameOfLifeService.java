package ch.bozaci.gameoflife;

import java.util.List;


public class GameOfLifeService
{

    public GameOfLifeService()
    {
    }

    public static void calculateNextState(GameOfLifeItem[][] matrix, int x, int y)
    {
        GameOfLifeItem itemNW = matrix[x - 1][y - 1];
        GameOfLifeItem itemN_ = matrix[x + 0][y - 1];
        GameOfLifeItem itemNE = matrix[x + 1][y - 1];

        GameOfLifeItem itemW_ = matrix[x - 1][y];
        GameOfLifeItem item__ = matrix[x + 0][y];
        GameOfLifeItem itemE_ = matrix[x + 1][y];

        GameOfLifeItem itemSW = matrix[x - 1][y + 1];
        GameOfLifeItem itemS_ = matrix[x + 0][y + 1];
        GameOfLifeItem itemSE = matrix[x + 1][y + 1];

        List<GameOfLifeItem> list = List.of(itemNW, itemN_, itemNE, itemW_, itemE_, itemSW, itemS_, itemSE);

        long countOfAliveItems = list.stream().filter(item -> item.isAlive()).count();

        //System.out.println("x = " + x + ", y = " + y + ", count = " + countOfAliveItems);
        
        if (item__.isAlive())
        {
            if (countOfAliveItems < 2 || countOfAliveItems > 3)
            {
                //System.out.println("item is dead");
                item__.setNextState(false); // will die
            }
            if (countOfAliveItems == 2 || countOfAliveItems == 3)
            {
                //System.out.println("item remains alive");
                item__.setNextState(true);
            }
        }

        if (item__.isDead())
        {
            if (countOfAliveItems == 3)
            {
                //System.out.println("item is born");
                item__.setNextState(true);
            }
            else
            {
                //System.out.println("item remains dead");
                item__.setNextState(false);
            }
        }
    }
    
    public static void reset(GameOfLifeItem[][] matrix, int x, int y)
    {
        GameOfLifeItem item = matrix[x][y];
        item.setNextState(false);
    }
}
