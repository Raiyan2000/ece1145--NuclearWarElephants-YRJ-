package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class TestThetaCiv {

    private Game game;


    /** Fixture for Thetaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new ThetaCivFactory());
    }
    @Test
    public void CityConquerWinner() {
    }
}
