package hotciv.standard;

import hotciv.framework.*;

public class AlphaCivWorld implements WorldLayout {

    public void createWorld(TileImpl[][] board) {
        String[] layout =
                new String[] {
                        "ohoooooooooooooo",
                        ".ooooooooooooooo",
                        "ooMooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "ooooooooohoooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                };
        String line;
        for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            line = layout[r];
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                char tileChar = line.charAt(c);
                String type = "error";
                if ( tileChar == '.' ) { board[r][c] = new TileImpl(GameConstants.OCEANS);}
                if ( tileChar == 'o' ) { board[r][c] = new TileImpl(GameConstants.PLAINS);}
                if ( tileChar == 'M' ) { board[r][c] = new TileImpl(GameConstants.MOUNTAINS);}
                if ( tileChar == 'f' ) { board[r][c] = new TileImpl(GameConstants.FOREST);}
                if ( tileChar == 'h' ) { board[r][c] = new TileImpl(GameConstants.HILLS); }
            }
        }
        //Red has one archer and settler
        UnitImpl archer = new UnitImpl(GameConstants.ARCHER, Player.RED);
        UnitImpl settler = new UnitImpl(GameConstants.SETTLER, Player.RED);

        //Blue has one Legion
        UnitImpl legion = new UnitImpl(GameConstants.LEGION, Player.BLUE);

        //Set units in world tiles
        board[2][0].setUnitType(archer);
        board[3][2].setUnitType(legion);
        board[4][3].setUnitType(settler);

        //Set city owners and city objects
        ((TileImpl)board[1][1]).setCityOwner(Player.RED);
        ((TileImpl)board[4][1]).setCityOwner(Player.BLUE);
    }
}
