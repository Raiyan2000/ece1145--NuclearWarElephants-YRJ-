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

  //current year variable
  private int age;
  //Initialized  World board as an array
  private TileImpl[][] world_board = new TileImpl[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];

  public GameImpl(){

    //game age starts at 4000
    age = 4000;

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

    //Set units in world tiles
    world_board[2][0].setUnitType(archer);
    world_board[3][2].setUnitType(legion);
    world_board[4][3].setUnitType(settler);

    //Set city owners
    world_board[1][1].setCityOwner(player_red);
    world_board[4][1].setCityOwner(player_blue);


  }

  public void setCurrentPlayerInTurn(Player name){
    current_player_turn = name;
  }

  public void test_setUnitPosition(Position pos, Unit troop){
    world_board[pos.getRow()][pos.getColumn()].setUnitType(troop);
  }

  public Tile getTileAt( Position p ) {
    return world_board[p.getRow()][p.getColumn()];
  }
  public Unit getUnitAt( Position p ) {
    return world_board[p.getRow()][p.getColumn()].getUnit();
  }
  public City getCityAt( Position p ) {
    return world_board[p.getRow()][p.getColumn()].getCity();
  }
  public Player getPlayerInTurn() {
    return current_player_turn;
  }
  public Player getWinner() { if (this.getAge() == 3000) {return Player.RED; } else { return null; } }
  public int getAge() { return age; }

  public void setAge(int x) { age = x; }

  public boolean moveUnit( Position from, Position to )
  {
    //check to make sure unit exists at the starting position
    Unit initialUnit = world_board[from.getRow()][from.getColumn()].getUnit();

    if(initialUnit != null)
    {
      //get terrain type of tile to be moved to
      String landType = world_board[to.getRow()][to.getColumn()].getTypeString();

      //check if there is a defending unit at the new position
      if(world_board[to.getRow()][to.getColumn()].getUnit() != null) {
        //there is a defending unit, but attacking wins, so replace defending unit w/ attacking unit
        world_board[to.getRow()][to.getColumn()].setUnitType(initialUnit);

        //remove attacking unit from previous tile
        world_board[from.getRow()][from.getColumn()].setUnitType(null);

        return true;
      }
      else if(landType.equals(GameConstants.MOUNTAINS) || landType.equals(GameConstants.OCEANS))
      {
        return false; //cannot move to this type of terrain
      }
      else //case where terrain can be moved to and it is empty
      {
        //move unit to new location
        world_board[to.getRow()][to.getColumn()].setUnitType(initialUnit);

        //remove unit from old location
        world_board[from.getRow()][from.getColumn()].setUnitType(null);

        return true;
      }
    }
    else
    {
      return false; //move was unsuccessful
    }
  }
  public void endOfTurn() {
    if(current_player_turn == Player.RED) {
      current_player_turn = Player.BLUE;
    } else if(current_player_turn == Player.BLUE) {
      //round is over at this point
      //increment year by 100 after each round
      age-=100;
      //Round is over loop for cities
      for(int i = 0; i < GameConstants.WORLDSIZE; i++)
      {
        for(int j = 0; j < GameConstants.WORLDSIZE; j++)
        {
          if(world_board[i][j].getCity() != null)
          {
            //Increment production of cities by 6
            world_board[i][j].getCity().incrementProductionPerRound();

            //Check if city has enough treasury to produce specified unit
            if(world_board[i][j].getCity().getTreasury() > world_board[i][j].getCity().getProductionCost() && world_board[i][j].getCity().getWorkforceFocus() == GameConstants.productionFocus)
            {
              // Create new unit
              Unit new_unit = new UnitImpl(world_board[i][j].getCity().getProduction(), world_board[i][j].getCity().getOwner());

              //Place unit in proper tile depending on other units placement
              if(world_board[i][j].getUnit() == null)
              {
                world_board[i][j].setUnitType(new_unit);
              }
              else {
                world_board[i][j+1].setUnitType(new_unit);
              }

              //Deduct unit cost from Treasury of city
              world_board[i][j].getCity().setTreasury(world_board[i][j].getCity().getTreasury() - new_unit.getUnitCost());
            }
          }

        }
      }


      current_player_turn = Player.RED;
    }
  }
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) {}

}
