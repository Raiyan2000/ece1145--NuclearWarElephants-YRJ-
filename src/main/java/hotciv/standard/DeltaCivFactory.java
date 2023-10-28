package hotciv.standard;

import hotciv.framework.HotCivFactory;
import hotciv.framework.UnitActionStrategy;
import hotciv.framework.WorldAgeStrategy;
import hotciv.framework.WorldLayout;

public class DeltaCivFactory implements HotCivFactory {

    public WorldLayout createWorldLayout()
    {
        return new DeltaCivWorld();
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
