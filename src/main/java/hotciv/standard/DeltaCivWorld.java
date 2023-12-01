package hotciv.standard;

import hotciv.framework.*;

public class DeltaCivWorld implements WorldLayout {

    public void createWorld(TileImpl[][] board) {
        String[] layout =
                new String[] {
                        "...ooMooooo.....",
                        "..ohhoooofffoo..",
                        ".oooooMooo...oo.",
                        ".ooMMMoooo..oooo",
                        "...ofooohhoooo..",
                        ".ofoofooooohhoo.",
                        "...ooo..........",
                        ".ooooo.ooohooM..",
                        ".ooooo.oohooof..",
                        "offfoooo.offoooo",
                        "oooooooo...ooooo",
                        ".ooMMMoooo......",
                        "..ooooooffoooo..",
                        "....ooooooooo...",
                        "..ooohhoo.......",
                        ".....ooooooooo..",
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

        //Set city owners and city objects
        ((TileImpl)board[8][12]).setCityOwner(Player.RED);
        ((TileImpl)board[4][5]).setCityOwner(Player.BLUE);
    }
}
