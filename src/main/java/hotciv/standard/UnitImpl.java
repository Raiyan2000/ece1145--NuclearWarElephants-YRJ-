package hotciv.standard;

import hotciv.framework.*;
public class UnitImpl implements Unit {

    private String unit_type;
    private Player owner;

    public UnitImpl(String troop_type, Player name) {
        unit_type = troop_type;
        owner = name;
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
