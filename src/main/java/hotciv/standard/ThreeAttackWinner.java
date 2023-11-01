package hotciv.standard;

import hotciv.framework.*;
public class ThreeAttackWinner implements WinStrategy {

    private int BlueAttackWins = 0;
    private int RedAttackWins = 0;
    private int YellowAttackWins = 0;
    private int GreenAttackWins = 0;

    public Player isWinner(int age, TileImpl[][] world_board)
    {
        if(GreenAttackWins == 3)
        {
           return Player.GREEN;
        }
        else if(YellowAttackWins == 3)
        {
            return Player.YELLOW;
        }
        else if(RedAttackWins == 3)
        {
            return Player.RED;
        }
        else if(BlueAttackWins == 3)
        {
            return Player.BLUE;
        }
        else
        {
            return null;
        }
    }

    public void setAttackWinCount(Player p)
    {
        if(p == Player.BLUE)
        {
            BlueAttackWins++;
        }
        else if(p == Player.RED)
        {
            RedAttackWins++;
        }
        else if(p == Player.GREEN)
        {
            GreenAttackWins++;
        }
        else
        {
            YellowAttackWins++;
        }
    }



}