package hotciv.standard;

import hotciv.framework.HotCivFactory;
import hotciv.framework.UnitActionStrategy;
import hotciv.framework.WorldAgeStrategy;
import hotciv.framework.WorldLayout;

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

}
