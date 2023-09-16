package hotciv.standard;

import hotciv.framework.*;
public class UnitImpl implements Unit {

    private String unit_type;
    private Player owner;

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
