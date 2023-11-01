package hotciv.standard;

import hotciv.framework.*;

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

    public AttackStrategy createAttackStrategy(){return null;}
}
