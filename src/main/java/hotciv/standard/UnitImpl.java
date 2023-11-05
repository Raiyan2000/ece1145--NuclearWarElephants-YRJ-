package hotciv.standard;

import hotciv.framework.*;
public class UnitImpl implements Unit {

    private String unit_type;
    private Player owner;

    private int unit_cost;
    private int archer_state;

    private int defensive_stats;
    private int attack_stats;
    private int moveCount;

    private String unitMovementType;

    public UnitImpl(String troop_type, Player name) {
        unit_type = troop_type;
        owner = name;

        if(troop_type.equals(GameConstants.ARCHER))
        {
            unit_cost = 10;
            defensive_stats = 3;
            attack_stats = 2;
            moveCount = 1;
            unitMovementType = GameConstants.GROUND;
        }
        else if(troop_type.equals(GameConstants.LEGION))
        {
            unit_cost = 15;
            attack_stats = 4;
            defensive_stats = 2;
            moveCount = 1;
            unitMovementType = GameConstants.GROUND;
        }
        else if(troop_type.equals(GameConstants.UFO))
        {
            unit_cost = 60;
            defensive_stats = 8;
            attack_stats = 1;
            moveCount = 2;
            unitMovementType = GameConstants.FLYING;
        }
        else if(troop_type.equals(GameConstants.SETTLER))
        {
            unit_cost = 30;
            defensive_stats = 3;
            attack_stats = 0;
            moveCount = 1;
            unitMovementType = GameConstants.GROUND;
        }
        else
        {
            unit_cost = 30;
        }

    }

    public void setUnitCost(int cost) {
        unit_cost = cost;
    }

    public int getUnitCost() {
        return unit_cost;
    }

    public String getTypeString(){
        return unit_type;
    }


    public Player getOwner() {
        return owner;
    }


    public int getMoveCount()
    {
        return moveCount;
    }


    public int getDefensiveStrength()
    {
        return defensive_stats;
    }

    public void setDefensiveStrength(int defense)
    {
        defensive_stats = defense;
    }

    public int getAttackingStrength()
    {
        return 0;
    }

    public void setAttackingStrength(int offense)
    {
        attack_stats = offense;
    }

    public int getArcherState()
    {
        return archer_state;
    }

    public void setArcherState(int fortify)
    {
        archer_state = fortify;
    }

    public void setMoveCount(int moves)
    {
        moveCount = moves;
    }

    public void setUnitMovementType(String movement)
    {
        unitMovementType = movement;
    }

    public String getUnitMovementType()
    {
        return unitMovementType;
    }
}
