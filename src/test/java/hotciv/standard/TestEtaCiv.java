package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class TestEtaCiv {


    private Game game;

    @Before
    public void setUp()
    {
        game = new GameImpl(new EtaCivFactory());
    }

    @Test
    public void TestRedCityProductionFocusAtOneOne() {
        //Check for city in correct position
        assertThat(game.getCityAt(new Position(1,1)), is(notNullValue()));

        //Set city to production focus
        ((CityImpl)game.getCityAt(new Position(1,1))).setWorkFocus(GameConstants.productionFocus);

        //Check initial treasury
        assertThat(game.getCityAt(new Position(1,1)).getTreasury(), is(0));
        assertThat(game.getCityAt(new Position(1,1)).getSize(), is(1));

        //Simulate end of round
        game.endOfTurn();
        game.endOfTurn();

        /*
            City's treasury is incremented by 6 and an additional of 1 because of the
            1 population in the city. The surrounding tiles are plains so there is no
            additional increments.
         */

        assertThat(game.getCityAt(new Position(1,1)).getTreasury(), is(7));

    }

    @Test
    public void TestRedCityFoodFocusAtOneOne() {
        //Check for city in correct position
        assertThat(game.getCityAt(new Position(1,1)), is(notNullValue()));

        //Set city to production focus
        ((CityImpl)game.getCityAt(new Position(1,1))).setWorkFocus(GameConstants.foodFocus);

        //Check initial treasury
        assertThat(((CityImpl) game.getCityAt(new Position(1,1))).getFoodCount(), is(0));
        assertThat(game.getCityAt(new Position(1,1)).getSize(), is(1));

        //Simulate end of round
        game.endOfTurn();
        game.endOfTurn();

        /*
            City's food Count is 0. After the end of round, it should increment
            by 1 since the population is 1.
         */

        assertThat(((CityImpl) game.getCityAt(new Position(1, 1))).getFoodCount(), is(1));

    }


    @Test
    public void TestRedCityFoodFocusAtOneOnePopulationSix() {
        //Check for city in correct position
        assertThat(game.getCityAt(new Position(1,1)), is(notNullValue()));

        //Set city to production focus
        ((CityImpl)game.getCityAt(new Position(1,1))).setWorkFocus(GameConstants.foodFocus);

        //Check initial treasury
        assertThat(((CityImpl) game.getCityAt(new Position(1,1))).getFoodCount(), is(0));
        assertThat(game.getCityAt(new Position(1,1)).getSize(), is(1));

        //Set Population to 6
        ((CityImpl)game.getCityAt(new Position(1,1))).setPopulation(6);

        //Simulate end of round
        game.endOfTurn();
        game.endOfTurn();

        /*
            City's food Count is 0. After the end of round, it should increment
            by 16 since the population is 6 and the terrains are plain.
         */

        assertThat(((CityImpl) game.getCityAt(new Position(1, 1))).getFoodCount(), is(16));

    }


    @Test
    public void TestCityAtTopLeftCornerFoodFocusPopulationThree() {
        // Set the city and Check for city in correct position
        ((GameImpl)game).test_setCityPosition(new Position(0,0), Player.RED);
        assertThat(game.getCityAt(new Position(0,0)), is(notNullValue()));

        //Set city to production focus
        ((CityImpl)game.getCityAt(new Position(0,0))).setWorkFocus(GameConstants.foodFocus);

        //Check initial treasury
        assertThat(((CityImpl) game.getCityAt(new Position(0,0))).getFoodCount(), is(0));
        assertThat(game.getCityAt(new Position(0,0)).getSize(), is(1));

        //Set Population to 3
        ((CityImpl)game.getCityAt(new Position(0,0))).setPopulation(3);

        //Simulate end of round
        game.endOfTurn();
        game.endOfTurn();

        /*
            City's food Count is 0. After the end of round, it should increment
            by 4 since the population is 2 and the best terrains are one plain, the city and the ocean.
         */

        assertThat(((CityImpl) game.getCityAt(new Position(0, 0))).getFoodCount(), is(5));

    }
    @Test
    public void TestCityAtTopLeftCornerProductionFocusPopulation2() {
        // Set the city and Check for city in correct position
        ((GameImpl)game).test_setCityPosition(new Position(0,0), Player.RED);
        assertThat(game.getCityAt(new Position(0,0)), is(notNullValue()));

        //Set city to production focus
        ((CityImpl)game.getCityAt(new Position(0,0))).setWorkFocus(GameConstants.foodFocus);

        //Check initial treasury
        assertThat(((CityImpl) game.getCityAt(new Position(0,0))).getFoodCount(), is(0));
        assertThat(game.getCityAt(new Position(0,0)).getSize(), is(1));

        //Set Population to 2
        ((CityImpl)game.getCityAt(new Position(0,0))).setPopulation(2);

        //Simulate end of round
        game.endOfTurn();
        game.endOfTurn();

        /*
            City's food Count is 0. After the end of round, it should increment
            by 4 since the population is 2 and the best terrains are one hill and the city.
         */

        assertThat(((CityImpl) game.getCityAt(new Position(0, 0))).getFoodCount(), is(4));


    }

    @Test
    public void TestFoodResetToZeroAndPopulationIncrease() {
        // Set the city and Check for city in correct position
        ((GameImpl)game).test_setCityPosition(new Position(0,0), Player.RED);
        assertThat(game.getCityAt(new Position(0,0)), is(notNullValue()));

        //Set city to production focus
        ((CityImpl)game.getCityAt(new Position(0,0))).setWorkFocus(GameConstants.foodFocus);

        //Check initial treasury
        assertThat(((CityImpl) game.getCityAt(new Position(0,0))).getFoodCount(), is(0));
        assertThat(game.getCityAt(new Position(0,0)).getSize(), is(1));

        //Set city population to 2
        ((CityImpl)game.getCityAt(new Position(0,0))).setPopulation(2);

        //max food count is 5+3*2 = 11

        game.endOfTurn();
        game.endOfTurn();

        assertThat(((CityImpl) game.getCityAt(new Position(0, 0))).getFoodCount(), is(4));
        assertThat(((CityImpl) game.getCityAt(new Position(0, 0))).getPopulation(),is(2));

        game.endOfTurn();
        game.endOfTurn();

        assertThat(((CityImpl) game.getCityAt(new Position(0, 0))).getFoodCount(), is(8));
        assertThat(((CityImpl) game.getCityAt(new Position(0, 0))).getPopulation(),is(2));

        game.endOfTurn();
        game.endOfTurn();

        assertThat(((CityImpl) game.getCityAt(new Position(0, 0))).getFoodCount(), is(0));
        assertThat(((CityImpl) game.getCityAt(new Position(0, 0))).getPopulation(),is(3));

    }

    @Test
    public void TestPopulationDoesNotIncreasePast9() {
        // Set the city and Check for city in correct position
        ((GameImpl)game).test_setCityPosition(new Position(1,1), Player.RED);
        assertThat(game.getCityAt(new Position(1,1)), is(notNullValue()));

        //Set city to production focus
        ((CityImpl)game.getCityAt(new Position(1,1))).setWorkFocus(GameConstants.foodFocus);

        //Check initial treasury
        assertThat(((CityImpl) game.getCityAt(new Position(1,1))).getFoodCount(), is(0));
        assertThat(game.getCityAt(new Position(1,1)).getSize(), is(1));

        //Set city population to 2
        ((CityImpl)game.getCityAt(new Position(1,1))).setPopulation(9);

        //max food count is 5+3*9 = 32
        game.endOfTurn();
        game.endOfTurn();

        assertThat(((CityImpl) game.getCityAt(new Position(1, 1))).getFoodCount(), is(17));
        assertThat(((CityImpl) game.getCityAt(new Position(1, 1))).getPopulation(),is(9));

        game.endOfTurn();
        game.endOfTurn();

        assertThat(((CityImpl) game.getCityAt(new Position(1, 1))).getFoodCount(), is(0));
        assertThat(((CityImpl) game.getCityAt(new Position(1, 1))).getPopulation(),is(9));

    }
}
