package hotciv.standard;

import hotciv.framework.*;

import java.util.Iterator;

public class AttackerWinsAttack implements AttackStrategy {

    public boolean Attack(Position from, Position to, Game game_board) {
        return true;
    }

    public int getTerrainFactor(Game game_board, Position position)
    {
        return 0;
    }

    public int getFriendlyUnitsFactor(Game game_board, Position position)
    {
        return 0;
    }
}
