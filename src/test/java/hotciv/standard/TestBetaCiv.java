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
public class TestBetaCiv {
    private Game game;

    /* Test List
    - If a player conquers all cities they are the winner
    - Between 4000BC and 100BC, the game ages 100 years each round
    - After birth of christ, the sequence is -100, -1, +1, +50
    - Between 50 AD and 1750 AD 50 years pass per round
    - Between 1750 and 1900 25 years pass per round
    - Between 1900 and 1970 5 years pass per round
    - After 1970 1 year passes per round

     */

    /** Fixture for alphaciv testing. */

    @Before
    public void setUp() {
        game = new GameImpl(new BetaCivFactory());
    }

    // FRS p. 455 states that 'Red is the first player to take a turn'.
    @Test
    public void CityConquerWinner() {
        Position city_pos = new Position(1,1);
        assertThat(game.getCityAt(city_pos), is(notNullValue()));
        assertThat(game.getCityAt(city_pos).getOwner(), is(Player.RED));
    }

    @Test
    public void Age4000BC() {
        ((GameImpl)(game)).setAge(-4000);
        //Mimic end of round
        ((GameImpl)(game)).setCurrentPlayerInTurn(Player.BLUE);
        game.endOfTurn();

        assertThat(game.getAge(), is(-3900));
    }

    @Test
    public void Age100BC() {
        //set age to 100
        ((GameImpl)(game)).setAge(-100);
        //Mimic end of round
        ((GameImpl)(game)).setCurrentPlayerInTurn(Player.BLUE);
        game.endOfTurn();

        assertThat(game.getAge(), is(-1));
    }

    @Test
    public void AgeN1BC() {
        //set age to -1
        ((GameImpl)(game)).setAge(-1);
        //Mimic end of round
        ((GameImpl)(game)).setCurrentPlayerInTurn(Player.BLUE);
        game.endOfTurn();

        assertThat(game.getAge(), is(1));
    }

    @Test
    public void Age1BC() {
        //set age to 1
        ((GameImpl)(game)).setAge(1);
        //Mimic end of round
        ((GameImpl)(game)).setCurrentPlayerInTurn(Player.BLUE);
        game.endOfTurn();

        assertThat(game.getAge(), is(50));
    }

    @Test
    public void Age50AD() {
        //set the age to 50 AD
        //using negative numbers to represent BC
        ((GameImpl)(game)).setAge(50);
        //Mimic end of round
        ((GameImpl)(game)).setCurrentPlayerInTurn(Player.BLUE);
        game.endOfTurn();

        assertThat(game.getAge(), is(100));
    }

    @Test
    public void Age1750AD() {
        //set the age to 1750 AD
        //using negative numbers to represent BC
        ((GameImpl)(game)).setAge(1750);
        //Mimic end of round
        ((GameImpl)(game)).setCurrentPlayerInTurn(Player.BLUE);
        game.endOfTurn();

        assertThat(game.getAge(), is(1775));
    }

    @Test
    public void Age1900AD() {
        //set the age to 1900 AD
        //using negative numbers to represent BC
        ((GameImpl)(game)).setAge(1900);
        //Mimic end of round
        ((GameImpl)(game)).setCurrentPlayerInTurn(Player.BLUE);
        game.endOfTurn();

        assertThat(game.getAge(), is(1905));
    }

    @Test
    public void Age1970AD() {
        //set the age to 1970 AD
        //using negative numbers to represent BC
        ((GameImpl)(game)).setAge(1970);
        //Mimic end of round
        ((GameImpl)(game)).setCurrentPlayerInTurn(Player.BLUE);
        game.endOfTurn();

        assertThat(game.getAge(), is(1971));
    }


}