package hotciv.standard;

import hotciv.framework.*;

public class SemiCivFactory implements HotCivFactory {

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
        return new DynamicRoundAges();
    }

    //change this when it's time
    public WinStrategy createWinStrategy() {
        return new ThreeAttackWinner();
    }

    public AttackStrategy createAttackStrategy(){return new EpsilonAttack();}
}
