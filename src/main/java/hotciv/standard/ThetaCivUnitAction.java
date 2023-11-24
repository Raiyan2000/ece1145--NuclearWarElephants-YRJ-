package hotciv.standard;

import hotciv.framework.*;

public class ThetaCivUnitAction implements UnitActionStrategy
{
    public void ArcherUnitAction(Unit archer_unit)
    {
        //get current defensive strength
        int currStrength = archer_unit.getDefensiveStrength();

        if (archer_unit.getArcherState() == 0) {
            archer_unit.setArcherState(1); //fortify the tile

            //double the defensive strength
            archer_unit.setDefensiveStrength(2 * currStrength);
            //demobilize unit
            archer_unit.setMoveCount(0);
        } else {
            archer_unit.setArcherState(0); //un-fortify if already fortified

            //half the defensive strength
            archer_unit.setDefensiveStrength(currStrength / 2);
            //mobilize unit
            archer_unit.setMoveCount(1);
        }
    }

    public void SettlerUnitAction(TileImpl[][] board, Position p, Unit Settler_unit) {
        //creates a new city
        board[p.getRow()][p.getColumn()].setCityOwner(Settler_unit.getOwner());

        board[p.getRow()][p.getColumn()].setUnitType(null); //removes settler

        //sets city population to 1
        board[p.getRow()][p.getColumn()].getCity().setPopulation(1);
    }

    public void ufoUnitAction(TileImpl[][] board, Position p, Unit ufo)
    {
        //check if city exists
        if(board[p.getRow()][p.getColumn()].getCity() != null)
        {
            //obtain current city population
            int cityPopulation = board[p.getRow()][p.getColumn()].getCity().getPopulation();

            if(cityPopulation != 0)
            {
                //subtract one from city population
                board[p.getRow()][p.getColumn()].getCity().setPopulation(cityPopulation - 1);
            }
            else
            {
                //erases city from map if population reaches zero
                board[p.getRow()][p.getColumn()].removeCity();
            }
        }
        else
        {
            //check terrain type
            String terrainType = board[p.getRow()][p.getColumn()].getTypeString();

            if(terrainType.equals(GameConstants.FOREST))
            {
                //set terrain type to plains
                ((TileImpl)(board[p.getRow()][p.getColumn()])).setTypeString(GameConstants.PLAINS);
            }
        }
    }
}
