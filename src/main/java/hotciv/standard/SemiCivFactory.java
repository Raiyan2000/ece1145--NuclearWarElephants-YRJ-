package hotciv.standard;

import hotciv.framework.*;

public class SemiCivFactory implements HotCivFactory {

    public WorldLayout createWorldLayout()
    {
        return new DeltaCivWorld();
    }

    public UnitActionStrategy createUnitActionStrategy()
    {
        return new ThetaCivUnitAction();
    }

    public WorldAgeStrategy createWorldAgeStrategy()
    {
        return new DynamicRoundAges();
    }

    //change this when it's time
    public WinStrategy createWinStrategy() {
        return new ThreeAttackWinner();
    }

    public AttackStrategy createAttackStrategy(){return new AttackerWinsAttack();}

    public ProductionStrategy createProductionStrategy()
    {
        return new NullProductionStrategy();
    }

    public CityPopulationStrategy createPopulationStrategy(){return new NullPopulationStrategy();}
}
