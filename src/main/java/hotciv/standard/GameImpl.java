package hotciv.standard;

import hotciv.framework.*;

/** Skeleton implementation of HotCiv.
 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/

public class GameImpl implements Game {
  //Created Player Red and Blue
  private Player player_red = Player.RED;
  private Player player_blue = Player.BLUE;
  private Player current_player_turn;

  //Initialized  World board as an array
  private TileImpl[][] world_board = new TileImpl[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];

  public GameImpl(){

    //Sets the World board to plains
    for(int i = 0; i < GameConstants.WORLDSIZE; i++)
    {
      for(int j = 0; j < GameConstants.WORLDSIZE; j++)
      {
        world_board[i][j] = new TileImpl(GameConstants.PLAINS);

      }
    }
    //Sets certain parts to be ocean, hills and mountains
    world_board[1][0].setTypeString(GameConstants.OCEANS);
    world_board[0][1].setTypeString(GameConstants.HILLS);
    world_board[2][2].setTypeString(GameConstants.MOUNTAINS);

    // First turn: Player Red
    current_player_turn = player_red;

    //Red has one archer and settler
    UnitImpl archer = new UnitImpl(GameConstants.ARCHER, player_red);
    UnitImpl settler = new UnitImpl(GameConstants.SETTLER, player_red);

    //Blue has one Legion
    UnitImpl legion = new UnitImpl(GameConstants.LEGION, player_blue);

    //Set units int world tiles
    world_board[2][0].setUnitType(archer);
    world_board[3][2].setUnitType(legion);
    world_board[4][3].setUnitType(settler);






  }

  public Tile getTileAt( Position p ) {
    return world_board[p.getRow()][p.getColumn()];
  }
  public Unit getUnitAt( Position p ) {
    return world_board[p.getRow()][p.getColumn()].getUnit();
  }
  public City getCityAt( Position p ) { return null; }
  public Player getPlayerInTurn() {
    return current_player_turn;
  }
  public Player getWinner() { return null; }
  public int getAge() { return 0; }
  public boolean moveUnit( Position from, Position to ) {
    return false;
  }
  public void endOfTurn() {}
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) {}
}
