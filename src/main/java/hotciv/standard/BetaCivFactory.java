package hotciv.standard;

import hotciv.framework.*;

public class BetaCivFactory implements HotCivFactory {

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
        return new DynamicRoundAges();
    }

    //change this when it's time
    public WinStrategy createWinStrategy() {
        return new CityConquerWinner();
    }
}
