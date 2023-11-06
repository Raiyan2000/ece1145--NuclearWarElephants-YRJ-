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

//This is the hotfix for Release 2.1

public class GameImpl implements Game {
  //Created Player Red and Blue
  private Player player_red = Player.RED;
  private Player player_blue = Player.BLUE;
  private Player current_player_turn;

  private HotCivFactory factory;

  private WorldLayout world_layout;

  //current year variable
  private int age;

  private WorldAgeStrategy ageStrategy;

  private Game game_board;

  private String game_type;
  private UnitActionStrategy UnitMovement;

  private WinStrategy winStrategy;

  private AttackStrategy attackStrategyObject;

  private int RedAttackWins;

  private int BlueAttackWins;

  //Initialized  World board as an array
  private TileImpl[][] world_board = new TileImpl[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];

  public GameImpl(HotCivFactory myFactory){

    //game age starts at 4000
    age = -4000;

    this.factory = myFactory;

    ageStrategy = factory.createWorldAgeStrategy();

    //setup the WinStrategy
    winStrategy = factory.createWinStrategy();

    // First turn: Player Red
    current_player_turn = Player.RED;

    // Set World Layout
    world_layout = factory.createWorldLayout();
    world_layout.createWorld(world_board);

    UnitMovement = factory.createUnitActionStrategy();

    winStrategy = factory.createWinStrategy();

    attackStrategyObject = factory.createAttackStrategy();
  }

  public String getGameType() {
    return game_type;
  }

  public void setCurrentPlayerInTurn(Player name){
    current_player_turn = name;
  }

  public void test_setUnitPosition(Position pos, Unit troop){
    world_board[pos.getRow()][pos.getColumn()].setUnitType(troop);
  }

  public void checkAdjacentCityTiles(int x, int y) {
    return;
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

  public Player getWinner() { return winStrategy.isWinner(age, world_board); }
  public int getAge() { return age; }

  public void setAge(int x) { age = x; }

  public boolean moveUnit( Position from, Position to )
  {
    //check to make sure unit exists at the starting position
    Unit initialUnit = world_board[from.getRow()][from.getColumn()].getUnit();

    //check terrain type of the location the unit wants to be moved to
    String landTypeNewSpot = world_board[to.getRow()][to.getColumn()].getTypeString();

    //check if unit exists and if terrain is traversable
    if (initialUnit != (null) && !landTypeNewSpot.equals(GameConstants.MOUNTAINS) && !landTypeNewSpot.equals(GameConstants.OCEANS))
    {
      //check initial unit movement type
      String movementType = initialUnit.getUnitMovementType();

      //check to see if there is a defending unit and a need to attack
      Unit defendingUnit = world_board[to.getRow()][to.getColumn()].getUnit();

      if(defendingUnit != null)
      {
        boolean successfulAttack = attackStrategyObject.Attack(from,to,game_board);

        if(successfulAttack)
        {
          //update the board
          world_board[to.getRow()][to.getColumn()].setUnitType(initialUnit);
          world_board[from.getRow()][from.getColumn()].setUnitType(null);

          Player attackUnitOwner = initialUnit.getOwner();

          //change owner of city in event of successful conquest
          if(world_board[to.getRow()][to.getColumn()].getCity() != null)
          {
            world_board[to.getRow()][to.getColumn()].getCity().setOwnerCity(attackUnitOwner);
          }

          winStrategy.setAttackWinCount(attackUnitOwner);
          return true;
        }
        else
        {
          //attack failed, so remove initial attacking unit from board
          world_board[from.getRow()][from.getColumn()].setUnitType(null);
          return false;
        }
      }
      else
      {
        //no defending unit, so just place new unit on new tile and remove old unit
        world_board[to.getRow()][to.getColumn()].setUnitType(initialUnit);
        world_board[from.getRow()][from.getColumn()].setUnitType(null);

        if(movementType.equals(GameConstants.GROUND))
        {
          //change city owner if successful conquest
          if (world_board[to.getRow()][to.getColumn()].getCity() != null) {
            world_board[to.getRow()][to.getColumn()].getCity().setOwnerCity(initialUnit.getOwner());
          }
        }

        return true;
      }
    }

    return false; //exit function if any of the above conditions are met
  }

  public int GetNumAttackWins(Player p)
  {
    if(p == Player.BLUE)
    {
      return BlueAttackWins;
    }
    else
    {
      return RedAttackWins;
    }
  }

  public void placeNewUnit(Unit new_unit, int row, int column)
  {
    if(world_board[row][column].getUnit() == null)
    {
      world_board[row][column].setUnitType(new_unit);
    }
    else {
      world_board[row][column+1].setUnitType(new_unit);
    }
  }
  public void endOfRound()
  {
    City current_city;
    boolean sufficient_treasury;
    boolean production_focused;

    //Round is over loop for cities
    for(int row = 0; row < GameConstants.WORLDSIZE; row++)
    {
      for(int column = 0; column < GameConstants.WORLDSIZE; column++)
      {
        if(world_board[row][column].getCity() != null)
        {
          //Get the current city
          current_city = world_board[row][column].getCity();

          //Check if there is sufficient treasury to produce the unit
          sufficient_treasury = current_city.getTreasury() > current_city.getProductionCost();

          //Check for production focus
          production_focused = current_city.getWorkforceFocus() == GameConstants.productionFocus;

          //Increment production of cities by 6
          current_city.incrementProductionPerRound();

          //Check if city has enough treasury to produce specified unit
          if(sufficient_treasury && production_focused)
          {
            // Create new unit
            Unit new_unit = new UnitImpl(current_city.getProduction(), current_city.getOwner());

            //Placing new created unit at proper tile
            placeNewUnit(new_unit, row, column);

            //Remaining Treasury after unit cost
            int remaining_treasury = current_city.getTreasury() - new_unit.getUnitCost();

            //Deduct unit cost from Treasury of city
            current_city.setTreasury(remaining_treasury);
          }
        }
      }
    }
  }
  public void endOfTurn() {
    if (current_player_turn == Player.RED) {

      current_player_turn = Player.BLUE;

    } else if (current_player_turn == Player.BLUE) {

      //increment year by 100 after each round
      age = ageStrategy.agePostRound(age);

      //end of round functionality
      this.endOfRound();

      current_player_turn = Player.RED;
    }
  }

  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType )
  {
    world_board[p.getRow()][p.getColumn()].getCity().setProduction(unitType);
  }
  public void performUnitActionAt( Position p )
  {
    //obtains unit present at the current tile
    Unit currUnit = world_board[p.getRow()][p.getColumn()].getUnit();

    if(currUnit.getTypeString().equals(GameConstants.SETTLER))
    {
      UnitMovement.SettlerUnitAction(world_board,p,currUnit);
    }

    if(currUnit.getTypeString().equals(GameConstants.ARCHER))
    {
      UnitMovement.ArcherUnitAction(currUnit);
    }

    if(currUnit.getTypeString().equals(GameConstants.UFO))
    {
      UnitMovement.ufoUnitAction(world_board,p,currUnit);
    }
  }


}

