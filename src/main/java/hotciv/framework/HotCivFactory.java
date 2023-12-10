package hotciv.framework;

import hotciv.standard.*;
public interface HotCivFactory {
    public WorldLayout createWorldLayout();

    public UnitActionStrategy createUnitActionStrategy();

    public WorldAgeStrategy createWorldAgeStrategy();

    public WinStrategy createWinStrategy();

    public AttackStrategy createAttackStrategy();

    public ProductionStrategy createProductionStrategy();

    public CityPopulationStrategy createPopulationStrategy();
}
