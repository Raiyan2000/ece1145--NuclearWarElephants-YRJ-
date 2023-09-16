package hotciv.standard;

import hotciv.framework.*;

public class TileImpl implements Tile{


    /** return the tile type as a string. The set of
     * valid strings are defined by the graphics
     * engine, as they correspond to named image files.
     * @return the tile type as string
     */

    private String tile_type;

    public TileImpl(String name){
        tile_type = name;
    }

    public String getTypeString() {
        return tile_type;
    }
    public void setTypeString(String type){
        tile_type = type;
    }
}
