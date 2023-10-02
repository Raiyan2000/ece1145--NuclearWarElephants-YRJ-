package hotciv.standard;


import hotciv.framework.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class TestGammaCiv {
    private Game game;

    /**
     * Fixture for alphaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new AlphaCivWorld(), new UnitActionGamma());
    }

    //Test that the settler action fortifies the square
    @Test
    public void ArcherUnitAction() {
        Position city_pos = new Position(2, 0);
        Unit currUnit = game.getUnitAt(city_pos);
        assertThat(currUnit.getTypeString(), is(GameConstants.ARCHER));
        game.performUnitActionAt(city_pos);
        //fortify status should be on
        assertThat(currUnit.getArcherState(), is(1));
        //defensive value should be twice as original
        assertThat(currUnit.getDefensiveStrength(), is(8));
        //make sure unit is immobile
        assertThat(currUnit.getMoveCount(), is(0));

        //If action performed again, stats should return to original
        game.performUnitActionAt(city_pos);
        //fortify should be removed
        assertThat(currUnit.getArcherState(), is(0));
        //defensive value should be halved
        assertThat(currUnit.getDefensiveStrength(), is(4));
        //make sure unit is mobile
        assertThat(currUnit.getMoveCount(), is(1));
    }


    @Test
    public void SettlerUnitAction() {
        Position p = new Position(4, 3);
        //make sure settler is at the square
        Unit currUnit = game.getUnitAt(p);
        assertThat(currUnit.getTypeString(), is(GameConstants.SETTLER));

        game.performUnitActionAt(p);
        //make sure settler unit is removed
        assertThat(game.getUnitAt(p),is(nullValue()));
        //make sure city population is 1
        assertThat(game.getCityAt(p).getPopulation(),is(1));
        //make sure owner is still the same
        assertThat(game.getCityAt(p).getOwner(),is(Player.RED));
    }
}