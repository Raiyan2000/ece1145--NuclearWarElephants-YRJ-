package hotciv.framework;

import hotciv.standard.TileImpl;

public interface ProductionStrategy {

    public int getProductionIncrease(int populationSize, TileImpl[][] board, Position p);
    public int getFoodIncrease(int populationSize, TileImpl[][] board, Position p);
}
