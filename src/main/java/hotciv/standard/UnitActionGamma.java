package hotciv.standard;

import hotciv.framework.*;

public class UnitActionGamma implements UnitActionStrategy
{
    public void ArcherUnitAction(Unit archer_unit)
    {
        //get current defensive strength
        int currStrength = archer_unit.getDefensiveStrength();

        if (((UnitImpl)archer_unit).getArcherState() == 0) {
            ((UnitImpl)archer_unit).setArcherState(1); //fortify the tile

            //double the defensive strength
            ((UnitImpl)archer_unit).setDefensiveStrength(2 * currStrength);
            //demobilize unit
            ((UnitImpl)archer_unit).setMoveCount(0);
        } else {
            ((UnitImpl)archer_unit).setArcherState(0); //un-fortify if already fortified

            //half the defensive strength
            ((UnitImpl)archer_unit).setDefensiveStrength(currStrength / 2);
            //mobilize unit
            ((UnitImpl)archer_unit).setMoveCount(1);
        }
    }

    public void SettlerUnitAction(TileImpl[][] board, Position p, Unit Settler_unit) {
        //creates a new city
        ((TileImpl)board[p.getRow()][p.getColumn()]).setCityOwner(Settler_unit.getOwner());

        board[p.getRow()][p.getColumn()].setUnitType(null); //removes settler

        //sets city population to 1
        ((CityImpl)board[p.getRow()][p.getColumn()].getCity()).setPopulation(1);
    }

    public void ufoUnitAction(TileImpl[][] board, Position p, Unit ufo) {
        return;
    }
}
