package hotciv.standard;

import hotciv.framework.*;

public class TileImpl implements Tile{


    /** return the tile type as a string. The set of
     * valid strings are defined by the graphics
     * engine, as they correspond to named image files.
     * @return the tile type as string
     */

    private String tile_type;

    private Unit unit;

    private City city;

    public TileImpl(String name){
        tile_type = name;
        unit = null;
        city = null;
    }

    public void setUnitType(Unit type)
    {
        unit = type;
    }

    public void setCityOwner(Player name){
        City temp_city = new CityImpl(name, null);
        city = temp_city;
    }

    public Unit getUnit(){
        return unit;
    }

    public City getCity(){
        return city;
    }

    public String getTypeString() {
        return tile_type;
    }


    public void setTypeString(String type){
        tile_type = type;
    }
}
