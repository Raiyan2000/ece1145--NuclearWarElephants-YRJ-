package hotciv.standard;

import hotciv.framework.*;

public class EpsilonAttack implements AttackStrategy
{
    public boolean Attack(Position from, Position to,Game game_board)
    {
        //get combined defensive strength
        int defenseTerrainPoints = getTerrainFactor(game_board,to);

        //get combined offensive strength
        int offensiveTerrainPoints = getTerrainFactor(game_board,from);


        return false;
    }

    public int getTerrainFactor(Game game_board, Position position)
    {
        // cities overrule underlying terrain
        if ( game_board.getCityAt(position) != null ) { return 3; }
        Tile t = game_board.getTileAt(position);
        if ( t.getTypeString() == GameConstants.FOREST ||
                t.getTypeString() == GameConstants.HILLS ) {
            return 2;
        }
        return 1;
    }
}



