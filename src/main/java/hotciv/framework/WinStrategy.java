package hotciv.framework;


import hotciv.standard.*;
public interface WinStrategy {

    //returns player who won
    Player isWinner(int age, TileImpl[][] world_board);

    public void setAttackWinCount(Player p);

}
