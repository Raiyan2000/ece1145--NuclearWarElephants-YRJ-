package hotciv.standard;

import hotciv.framework.*;
import java.util.Random;
import java.util.Iterator;

public class EpsilonAttack implements AttackStrategy
{
    public boolean Attack(Position from, Position to,Game game_board)
    {
        //get combined defensive strength
        int defenseTerrainPoints = getTerrainFactor(game_board,to);
        int defenseFriendlyUnits = getFriendlyUnitsFactor(game_board,to);
        Unit defendingUnit = game_board.getUnitAt(to);
        int defenseStat = defendingUnit.getDefensiveStrength();

        //obtains total defensive points
        int totalDefensiveStrength = (defenseStat + defenseFriendlyUnits) * defenseTerrainPoints;

        //get combined offensive strength
        int offensiveTerrainPoints = getTerrainFactor(game_board,from);
        int offenseFriendlyUnits = getFriendlyUnitsFactor(game_board,from);
        Unit attackingUnit = game_board.getUnitAt(from);
        int attackStat = defendingUnit.getAttackingStrength();

        //obtains total offensive points
        int totalOffenseStrength = (attackStat + offenseFriendlyUnits) * offensiveTerrainPoints;

        //random number values calculated into attack results
        Random rand = new Random();
        int offenseRandom = rand.nextInt(5)+1;
        int defenseRandom = rand.nextInt(5)+1;

        //total score values
        int total_score_offense = totalOffenseStrength * offenseRandom;
        int total_score_defense = totalDefensiveStrength * defenseRandom;

        //returns whether attack was successful
        return total_score_defense < total_score_offense;
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



