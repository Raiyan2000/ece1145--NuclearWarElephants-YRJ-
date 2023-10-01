package hotciv.framework;

import hotciv.standard.*;

public interface UnitActionStrategy
{
    void ArcherUnitAction(Unit u);

    void SettlerUnitAction(TileImpl[][] board,Position p, Unit u);
}
