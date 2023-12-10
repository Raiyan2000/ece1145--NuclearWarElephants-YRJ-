package hotciv.standard;

import hotciv.framework.*;

public class EtaCivFactory implements HotCivFactory {

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

    public AttackStrategy createAttackStrategy(){return new AttackerWinsAttack();}

    public ProductionStrategy createProductionStrategy()
    {
        return new EtaCivProductionStrategy();
    }

    public CityPopulationStrategy createPopulationStrategy(){return new FoodIncreasePopulationStrategy();}
}
