package hotciv.standard;

import hotciv.framework.HotCivFactory;
import hotciv.framework.UnitActionStrategy;
import hotciv.framework.WorldAgeStrategy;
import hotciv.framework.WorldLayout;
import hotciv.framework.WinStrategy;

public class AlphaCivFactory implements HotCivFactory {
    public WorldLayout createWorldLayout()
    {
        return new AlphaCivWorld();
    }

    public UnitActionStrategy createUnitActionStrategy()
    {
        return new UnitActionAlpha();
    }

    public WorldAgeStrategy createWorldAgeStrategy()
    {
        return new CenturyRoundAges();
    }

    public WinStrategy createWinStrategy() {
        return new Age3000Winner();
    }
}
