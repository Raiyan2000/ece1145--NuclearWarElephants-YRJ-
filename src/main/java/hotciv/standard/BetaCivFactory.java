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

    public AttackStrategy createAttackStrategy(){return new AttackerWinsAttack();}

    public ProductionStrategy createProductionStrategy()
    {
        return new NullProductionStrategy();
    }

    public CityPopulationStrategy createPopulationStrategy(){return new NullPopulationStrategy();}
}
