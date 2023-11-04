package hotciv.standard;

import hotciv.framework.*;

import java.util.Iterator;

public class AttackerWinsAttack implements AttackStrategy {

    public boolean Attack(Position from, Position to, Game game_board) {
        return true;
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

    public int getFriendlyUnitsFactor(Game game_board, Position position)
    {
        Iterator<Position> neighborhood = Utility.get8neighborhoodIterator(position);
        Position p;
        //get owner of attacking unit
        Player player = game_board.getUnitAt(position).getOwner();
        int support = 0;
        while (neighborhood.hasNext()) {
            p = neighborhood.next();
            if (game_board.getUnitAt(p) != null &&
                    game_board.getUnitAt(p).getOwner() == player) {
                support++;
            }
        }
        return support;
    }
}
