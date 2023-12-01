package hotciv.standard;

import hotciv.framework.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class TestThetaCiv {

    private Game game;
    /** Fixture for Thetaciv testing. */
    @Before

    public void setUp()
    {
        game = new GameImpl(new ThetaCivFactory());
    }
    @Test
    /*This tests the ability for cities to produce ufo units*/
    public void ufoUnitProduction()
    {
        Position p = new Position(2,5);
        ((TileImpl)game.getTileAt(p)).setCityOwner(Player.BLUE);
        assertThat(game.getTileAt(p).getCity().getOwner(),is(Player.BLUE));
        game.changeProductionInCityAt(p,GameConstants.UFO);
        assertThat(game.getTileAt(p).getCity().getProduction(),is(GameConstants.UFO));
    }

    public void ufoMovementWithoutDefendingUnit()
    {
        Position p = new Position(2,5);
        ((TileImpl)game.getTileAt(p)).setCityOwner(Player.BLUE);
        game.changeProductionInCityAt(p,GameConstants.UFO);
        assertThat(((TileImpl)(game.getTileAt(p))).getUnit().getTypeString(),is(GameConstants.UFO));

        Position enemyPos = new Position(2,6);
        ((TileImpl)game.getTileAt(enemyPos)).setCityOwner((Player.RED));

        game.moveUnit(p,enemyPos);

        //make sure ufo is over enemy territory, but territory hasn't been conquered
        assertThat(((TileImpl)(game.getTileAt(enemyPos))).getUnit().getTypeString(),is(GameConstants.UFO));
        assertThat(game.getCityAt(enemyPos).getOwner(),is(Player.RED));
    }

    public void TestUFOinAction()
    {
        Position p = new Position(2,5);
        ((TileImpl)game.getTileAt(p)).setCityOwner(Player.BLUE);
        game.changeProductionInCityAt(p,GameConstants.UFO);
        assertThat(((TileImpl)(game.getTileAt(p))).getUnit().getTypeString(),is(GameConstants.UFO));

        Position enemyPos = new Position(2,6);
        ((TileImpl)game.getTileAt(enemyPos)).setCityOwner((Player.RED));
        ((CityImpl)((TileImpl)game.getTileAt(enemyPos)).getCity()).setPopulation(2);
        assertThat(((CityImpl)((TileImpl)(game.getTileAt(enemyPos))).getCity()).getPopulation(),is(2));

        game.moveUnit(p,enemyPos);
        game.performUnitActionAt(enemyPos);

        assertThat(((CityImpl)((TileImpl)(game.getTileAt(enemyPos))).getCity()).getPopulation(),is(1));

        //remove city once population reaches zero
        game.performUnitActionAt(enemyPos);
        assertThat(((TileImpl)(game.getTileAt(enemyPos))).getCity(),is(nullValue()));
    }
}
