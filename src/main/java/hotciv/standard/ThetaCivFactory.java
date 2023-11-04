package hotciv.standard;

import hotciv.framework.*;

public class ThetaCivFactory implements HotCivFactory {

    public WorldLayout createWorldLayout()
    {
        return new AlphaCivWorld();
    }

    public UnitActionStrategy createUnitActionStrategy()
    {
        return new UnitActionGamma();
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
