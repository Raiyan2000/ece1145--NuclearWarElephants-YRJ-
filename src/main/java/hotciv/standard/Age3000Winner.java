package hotciv.standard;

import hotciv.framework.*;
public class Age3000Winner implements WinStrategy {

    public Player isWinner(int age, TileImpl[][] world_board) {
        if (age == 3000) {return Player.RED; } else { return null; }
    }

}
