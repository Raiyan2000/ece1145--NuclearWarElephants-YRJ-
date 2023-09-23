package hotciv.standard;

import hotciv.framework.*;
public class UnitImpl implements Unit {

    private String unit_type;
    private Player owner;

    private int unit_cost;

    public UnitImpl(String troop_type, Player name) {
        unit_type = troop_type;
        owner = name;

        if(troop_type == GameConstants.ARCHER)
        {
            unit_cost = 10;
        }
        else if(troop_type == GameConstants.LEGION)
        {
            unit_cost = 15;
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
        return 0;
    }


    public int getDefensiveStrength()
    {
        return 0;
    }

    public int getAttackingStrength()
    {
        return 0;
    }
}
