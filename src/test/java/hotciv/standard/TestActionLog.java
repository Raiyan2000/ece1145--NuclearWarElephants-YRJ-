package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;
public class TestActionLog {
    private Game game;

    /* Test List


     */


    @Before
    public void setUp() {
        game = new GameImpl(new AlphaCivFactory());

        game = new ActionLogDecorator(game);
    }

    // FRS p. 455 states that 'Red is the first player to take a turn'.
    @Test
    public void shouldBeRedAsStartingPlayer() {
        // TODO: reenable the assert below to get started...
        assertThat(game.getPlayerInTurn(), is(Player.RED));
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
    public void workForceFocusLog()
    {
        Position city_of_red = new Position(1,1);

        game.changeWorkForceFocusInCityAt(city_of_red, "foodFocus");

        assertThat(game, is(notNullValue()));
    }

    @Test
    public void cityProductionChangeLog()
    {
        Position city_of_red = new Position(1,1);
        Position pos_of_newUnit = new Position(1, 2);

        ((CityImpl)(game.getCityAt(city_of_red))).setTreasury(100);

        ((CityImpl)(game.getCityAt(city_of_red))).setWorkFocus(GameConstants.productionFocus);
        ((CityImpl)(game.getCityAt(city_of_red))).setProductionType(GameConstants.ARCHER);
        ((CityImpl)(game.getCityAt(city_of_red))).calculateProductionCost();


        Unit temp = new UnitImpl(GameConstants.LEGION, Player.RED);

        game.changeProductionInCityAt(city_of_red, GameConstants.LEGION);

        assertThat(game, is(notNullValue()));
    }

    @Test
    public void yearIncrements100()
    {
        //Mimic end of round
        ((ActionLogDecorator)(game)).setCurrentPlayerInTurn(Player.BLUE);
        game.endOfTurn();

        assertThat(game.getAge(), is(-3900));
    }

}