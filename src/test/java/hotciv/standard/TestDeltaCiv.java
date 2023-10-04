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
public class TestDeltaCiv {
    private Game game;

    /** Fixture for alphaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new DeltaCivWorld(), new UnitActionAlpha(), new CenturyRoundAges());
    }

    // FRS p. 455 states that 'Red is the first player to take a turn'.
    @Test
    public void RedHasCityAtTileEightTwelve() {
        Position city_pos = new Position(8,12);
        assertThat(game.getCityAt(city_pos), is(notNullValue()));
        assertThat(game.getCityAt(city_pos).getOwner(), is(Player.RED));
    }

    @Test
    public void BlueHasCityAtTileFourFive() {
        Position city_pos = new Position(4,5);
        assertThat(game.getCityAt(city_pos), is(notNullValue()));
        assertThat(game.getCityAt(city_pos).getOwner(), is(Player.BLUE));
    }

    @Test
    public void TileZeroZeroIsOceans() {
        Position ocean_pos = new Position(0,0);
        assertThat(game.getTileAt(ocean_pos).getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void TileZeroFiveIsMountains() {
        Position mountain_pos = new Position(0,5);
        assertThat(game.getTileAt(mountain_pos).getTypeString(), is(GameConstants.MOUNTAINS));
    }

    @Test
    public void TileOneThreeIsHills() {
        Position hill_pos = new Position(1,3);
        assertThat(game.getTileAt(hill_pos).getTypeString(), is(GameConstants.HILLS));
    }





}

