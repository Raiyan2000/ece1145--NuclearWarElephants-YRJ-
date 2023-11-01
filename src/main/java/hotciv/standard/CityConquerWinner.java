package hotciv.standard;

import hotciv.framework.*;
public class CityConquerWinner implements WinStrategy {

    public Player isWinner(int age, TileImpl[][] world_board) {

        //not finished
        int blueCounter = 0;
        int redCounter = 0;

        //count up the cities
        for(int row = 0; row < GameConstants.WORLDSIZE; row++)
        {
            for(int column = 0; column < GameConstants.WORLDSIZE; column++)
            {
                if(world_board[row][column].getCity() != null)
                {
                    if (world_board[row][column].getCity().getOwner() == Player.RED) {
                        redCounter++;
                    }

                    if (world_board[row][column].getCity().getOwner() == Player.BLUE) {
                        blueCounter++;
                    }
                }
            }
        }

        //determine if there is a winner
        if (redCounter != 0 && blueCounter == 0) {
            return Player.RED;
        }

        else if (redCounter == 0 && blueCounter != 0) {
            return Player.BLUE;
        }

        //no winner
        else {
            return null;
        }

    }

    public void setAttackWinCount(Player p)
    {
        return;
    }

}