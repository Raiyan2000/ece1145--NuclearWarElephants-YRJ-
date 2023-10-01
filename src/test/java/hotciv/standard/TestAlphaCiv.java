package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

/** Skeleton class for AlphaCiv test cases

    Updated Oct 2015 for using Hamcrest matchers

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
public class TestAlphaCiv {
  private Game game;

  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl(new AlphaCivWorld());
  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game, is(notNullValue()));
    // TODO: reenable the assert below to get started...
    // assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  /** REMOVE ME. Not a test of HotCiv, just an example of what
      matchers the hamcrest library has... */
  @Test
  public void shouldDefinetelyBeRemoved() {
    // Matching null and not null values
    // 'is' require an exact match
    String s = null;
    assertThat(s, is(nullValue()));
    s = "Ok";
    assertThat(s, is(notNullValue()));
    assertThat(s, is("Ok"));

    // If you only validate substrings, use containsString
    assertThat("This is a dummy test", containsString("dummy"));

    // Match contents of Lists
    List<String> l = new ArrayList<String>();
    l.add("Bimse");
    l.add("Bumse");
    // Note - ordering is ignored when matching using hasItems
    assertThat(l, hasItems(new String[] {"Bumse","Bimse"}));

    // Matchers may be combined, like is-not
    assertThat(l.get(0), is(not("Bumse")));
  }

  @Test
  public void tileOneZeroIsOcean()
  {
    Position ocean_tile_pos = new Position(1,0);
    assertThat(game.getTileAt(ocean_tile_pos).getTypeString(), is(GameConstants.OCEANS));
  }

  @Test
  public void tileZeroOneIsHills()
  {
    Position hills_tile_pos = new Position(0,1);
    assertThat(game.getTileAt(hills_tile_pos).getTypeString(), is(GameConstants.HILLS));
  }

  @Test
  public void tileTwoTwoIsMountains()
  {
    Position mountain_tile_pos = new Position(2,2);
    assertThat(game.getTileAt(mountain_tile_pos).getTypeString(), is(GameConstants.MOUNTAINS));
  }

  @Test
  public void RedHasOneArcher()
  {
    Position archer_pos = new Position(2, 0);
    assertThat(game.getUnitAt(archer_pos).getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void RedHasOneSettler()
  {
    Position archer_pos = new Position(4, 3);
    assertThat(game.getUnitAt(archer_pos).getTypeString(), is(GameConstants.SETTLER));
  }

  @Test
  public void BlueHasOneLegion()
  {
    Position archer_pos = new Position(3, 2);
    assertThat(game.getUnitAt(archer_pos).getTypeString(), is(GameConstants.LEGION));
  }

  @Test
  public void RedHasCityOnTileOneOne()
  {
    Position city_pos = new Position(1, 1);
    assertThat(game.getCityAt(city_pos), is(notNullValue()));
    assertThat(game.getCityAt(city_pos).getOwner(), is(Player.RED));
  }
  @Test
  public void BlueHasCityOnTileFourOne()
  {
    Position city_pos = new Position(4,1);
    assertThat(game.getCityAt(city_pos), is(notNullValue()));
    assertThat(game.getCityAt(city_pos).getOwner(), is(Player.BLUE));
  }
  @Test
  public void CityHasPopulationSizeOne()
  {
    Position city_pos = new Position(4,1);
    Position city_pos_two = new Position(1,1);

    assertThat(game.getCityAt(city_pos).getSize(), is(1));
    assertThat(game.getCityAt(city_pos_two).getSize(), is(1));
  }

  @Test
  public void CityProduceSixProductionPerRound()
  {
    Position city_pos = new Position(4,1);
    Position city_pos_two = new Position(1,1);

    assertThat(game.getCityAt(city_pos).getTreasury(), is(0));
    assertThat(game.getCityAt(city_pos_two).getTreasury(), is(0));

    //Mimic end of round
    game.setCurrentPlayerInTurn(Player.BLUE);
    game.endOfTurn();

    assertThat(game.getCityAt(city_pos).getTreasury(), is(6));
    assertThat(game.getCityAt(city_pos_two).getTreasury(), is(6));
  }

  @Test
  public void playerSelectProduceOfCity()
  {

  }


  
  @Test
  public void AttackUnitMovesToNewTile()
  {
    Position attackPosition = new Position(2,0);
    Position endPosition = new Position(2,1);
    //assertThat(game.getUnitAt(attackPosition),is("archer"));
    assertThat(game.moveUnit(attackPosition,endPosition), is(true));
  }

  @Test
  public void yearStarts4000()
  {
    assertThat(game.getAge(), is(4000));
  }

  @Test
  public void yearIncrements100()
  {
    //Mimic end of round
    game.setCurrentPlayerInTurn(Player.BLUE);
    game.endOfTurn();

    assertThat(game.getAge(), is(3900));
  }

  @Test
  public void redWinsAge3000()
  {
    //set game to year 3000BC
    game.setAge(3000);

    assertThat(game.getAge(), is(3000));


    assertThat(game.getWinner(), is(Player.RED));
  }

  @Test
  public void cityProducesProperUnit()
  {
    Position city_of_red = new Position(1,1);

    game.getCityAt(city_of_red).setTreasury(100);

    game.getCityAt(city_of_red).setWorkFocus(GameConstants.productionFocus);
    game.getCityAt(city_of_red).setProductionType(GameConstants.ARCHER);
    game.getCityAt(city_of_red).calculateProductionCost();

    //Mimic end of round
    game.setCurrentPlayerInTurn(Player.BLUE);
    game.endOfTurn();

    assertThat(game.getUnitAt(city_of_red).getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void cityProducesUnitAtProperTile()
  {
    Position city_of_red = new Position(1,1);
    Position pos_of_newUnit = new Position(1, 2);

    game.getCityAt(city_of_red).setTreasury(100);

    game.getCityAt(city_of_red).setWorkFocus(GameConstants.productionFocus);
    game.getCityAt(city_of_red).setProductionType(GameConstants.ARCHER);
    game.getCityAt(city_of_red).calculateProductionCost();

    Unit temp = new UnitImpl(GameConstants.LEGION, Player.RED);

    game.test_setUnitPosition(city_of_red, temp);

    //Mimic end of round
    game.setCurrentPlayerInTurn(Player.BLUE);
    game.endOfTurn();

    assertThat(game.getUnitAt(pos_of_newUnit).getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void unitCostDeductedFromTreasury()
  {
    Position city_of_red = new Position(1,1);

    game.getCityAt(city_of_red).setTreasury(100);

    game.getCityAt(city_of_red).setWorkFocus(GameConstants.productionFocus);
    game.getCityAt(city_of_red).setProductionType(GameConstants.ARCHER);
    game.getCityAt(city_of_red).calculateProductionCost();

    assertThat(game.getCityAt(city_of_red).getTreasury(), is(100));

    //Mimic end of round
    game.setCurrentPlayerInTurn(Player.BLUE);
    game.endOfTurn();

    assertThat(game.getCityAt(city_of_red).getTreasury(), is(96));
  }

}

