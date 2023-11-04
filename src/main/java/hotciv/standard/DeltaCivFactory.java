package hotciv.standard;

import hotciv.framework.*;

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

    //change this when it's time
    public WinStrategy createWinStrategy() {
        return new Age3000Winner();
    }

    public AttackStrategy createAttackStrategy(){return new AttackerWinsAttack();}
}
