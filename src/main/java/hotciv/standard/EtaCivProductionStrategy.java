package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.ProductionStrategy;

import java.util.*;

public class EtaCivProductionStrategy implements ProductionStrategy {
    private HashMap<String,Integer> tileProdAmounts;
    private HashMap<String,Integer> tileFoodAmounts;

    public EtaCivProductionStrategy() {
        tileProdAmounts = new HashMap<String,Integer>();
        tileProdAmounts.put(GameConstants.MOUNTAINS, 1);
        tileProdAmounts.put(GameConstants.OCEANS, 0);
        tileProdAmounts.put(GameConstants.PLAINS, 0);
        tileProdAmounts.put(GameConstants.HILLS, 2);
        tileProdAmounts.put(GameConstants.FOREST, 3);

        tileFoodAmounts = new HashMap<String,Integer>();
        tileFoodAmounts.put(GameConstants.MOUNTAINS, 0);
        tileFoodAmounts.put(GameConstants.OCEANS, 1);
        tileFoodAmounts.put(GameConstants.PLAINS, 3);
        tileFoodAmounts.put(GameConstants.HILLS, 0);
        tileFoodAmounts.put(GameConstants.FOREST, 0);
    }
    public int getProductionIncrease(int populationSize, TileImpl[][] board, Position p)
    {
        int max_production = 0;
        ArrayList<Integer> production_list = new ArrayList<Integer>();

        int row = p.getRow();
        int col = p.getColumn();

        if(populationSize == 0)
        {
            return 0;
        }

        if(populationSize == 1)
        {
            return 1;
        }
        max_production += 1;
        int temp_size = populationSize - 1;

        // Checking for all the possible adjacent positions
        if (isValidPos(row - 1, col - 1)) {
           production_list.add(tileProdAmounts.get(board[row-1][col-1].getTypeString()));
        }
        if (isValidPos(row - 1, col)) {
            production_list.add(tileProdAmounts.get(board[row-1][col].getTypeString()));
        }
        if (isValidPos(row - 1, col + 1)) {
            production_list.add(tileProdAmounts.get(board[row-1][col+1].getTypeString()));
        }
        if (isValidPos(row, col - 1)) {
            production_list.add(tileProdAmounts.get(board[row][col-1].getTypeString()));
        }
        if (isValidPos(row, col + 1)) {
            production_list.add(tileProdAmounts.get(board[row][col+1].getTypeString()));
        }
        if (isValidPos(row + 1, col - 1)) {
            production_list.add(tileProdAmounts.get(board[row+1][col-1].getTypeString()));
        }
        if (isValidPos(row + 1, col)) {
            production_list.add(tileProdAmounts.get(board[row+1][col].getTypeString()));
        }
        if (isValidPos(row + 1, col + 1)) {
            production_list.add(tileProdAmounts.get(board[row+1][col+1].getTypeString()));
        }

        Collections.sort(production_list, Collections.reverseOrder());

        for(int i = 0; i < temp_size; i++)
        {
            max_production += production_list.get(i);
        }

        return max_production;
    }
    public int getFoodIncrease(int populationSize, TileImpl[][] board, Position p)
    {
        int max_food = 0;
        ArrayList<Integer> production_list = new ArrayList<Integer>();

        int row = p.getRow();
        int col = p.getColumn();

        if(populationSize == 0)
        {
            return 0;
        }

        if(populationSize == 1)
        {
            return 1;
        }
        max_food += 1;
        int temp_size = populationSize - 1;

        // Checking for all the possible adjacent positions
        if (isValidPos(row - 1, col - 1)) {
            production_list.add(tileFoodAmounts.get(board[row-1][col-1].getTypeString()));
        }
        if (isValidPos(row - 1, col)) {
            production_list.add(tileFoodAmounts.get(board[row-1][col].getTypeString()));
        }
        if (isValidPos(row - 1, col + 1)) {
            production_list.add(tileFoodAmounts.get(board[row-1][col+1].getTypeString()));
        }
        if (isValidPos(row, col - 1)) {
            production_list.add(tileFoodAmounts.get(board[row][col-1].getTypeString()));
        }
        if (isValidPos(row, col + 1)) {
            production_list.add(tileFoodAmounts.get(board[row][col+1].getTypeString()));
        }
        if (isValidPos(row + 1, col - 1)) {
            production_list.add(tileFoodAmounts.get(board[row+1][col-1].getTypeString()));
        }
        if (isValidPos(row + 1, col)) {
            production_list.add(tileFoodAmounts.get(board[row+1][col].getTypeString()));
        }
        if (isValidPos(row + 1, col + 1)) {
            production_list.add(tileFoodAmounts.get(board[row+1][col+1].getTypeString()));
        }

        Collections.sort(production_list, Collections.reverseOrder());

        for(int i = 0; i < temp_size; i++)
        {
            max_food += production_list.get(i);
        }

        return max_food;
    }

    static boolean isValidPos(int row, int col)
    {
        if (row < 0 || col < 0 || row > (GameConstants.WORLDSIZE - 1) || col > (GameConstants.WORLDSIZE - 1)) {
            return false;
        }
        return true;
    }

}
