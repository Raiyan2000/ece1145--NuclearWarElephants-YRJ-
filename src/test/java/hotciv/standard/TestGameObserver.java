package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class TestGameObserver {

    private Game game;

    private GameObserver myGameObserver;

    @Before
    public void setUp()
    {

        game = new GameImpl(new BetaCivFactory());
        game.addObserver(myGameObserver);
    }

    @Test
    public void WorldChangedAt() {

    }
}
