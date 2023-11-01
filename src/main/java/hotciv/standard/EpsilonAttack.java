package hotciv.standard;

import hotciv.framework.*;
import java.util.Random;

public class EpsilonAttack implements AttackStrategy
{
    public boolean Attack(Position from, Position to,Game game_board)
    {
        //get combined defensive strength
        int defenseTerrainPoints = getTerrainFactor(game_board,to);
        int defenseFriendlyUnits = getFriendlyUnitsFactor(game_board,to);

        //obtains total defensive points
        int totalDefensiveStrength = defenseFriendlyUnits * defenseTerrainPoints;

        //get combined offensive strength
        int offensiveTerrainPoints = getTerrainFactor(game_board,from);
        int offenseFriendlyUnits = getFriendlyUnitsFactor(game_board,from);

        //obtains total offensive points
        int totalOffenseStrength = offenseFriendlyUnits * offensiveTerrainPoints;

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
        return 0;
    }
}



