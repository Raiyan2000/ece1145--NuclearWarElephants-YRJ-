package hotciv.standard;

import hotciv.framework.*;

public class ThetaCivFactory implements HotCivFactory {

    public WorldLayout createWorldLayout()
    {
        return new AlphaCivWorld();
    }

    public UnitActionStrategy createUnitActionStrategy()
    {
        return new ThetaCivUnitAction();
    }

    public WorldAgeStrategy createWorldAgeStrategy()
    {
        return new CenturyRoundAges();
    }
    
    public WinStrategy createWinStrategy() {
        return new Age3000Winner();
    }
    public AttackStrategy createAttackStrategy(){return new AttackerWinsAttack();}
}
