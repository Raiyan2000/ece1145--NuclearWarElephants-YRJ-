package hotciv.standard;

import hotciv.framework.Position;
import hotciv.framework.ProductionStrategy;

public class NullProductionStrategy implements ProductionStrategy {

    public int getProductionIncrease(int populationSize, TileImpl[][] board, Position p)
    {
        return 0;
    }
    public int getFoodIncrease(int populationSize, TileImpl[][] board, Position p)
    {
        return 0;
    }
}
