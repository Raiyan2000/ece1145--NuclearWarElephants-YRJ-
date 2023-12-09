package hotciv.standard;

import hotciv.framework.*;
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
        myGameObserver = new TestSpy();
        game = new GameImpl(new AlphaCivFactory());
        game.addObserver(new TestSpy());
    }

    @Test
    public void WorldChangedAtFiveSix()
    {
        boolean choice;
        Unit archer = new UnitImpl(GameConstants.ARCHER, Player.RED);
        Position pos = new Position(5, 5);
        ((GameImpl) game).test_setUnitPosition(pos, archer);

        Position newPos = new Position(5,6);
        choice = game.moveUnit(pos, newPos);

        assertThat(choice, is(Boolean.TRUE));
        assertThat(((TestSpy)(((GameImpl) game).civGameObserver)).worldChangePosition.getRow(), is(5));
        assertThat(((TestSpy)(((GameImpl) game).civGameObserver)).worldChangePosition.getColumn(), is(6));
    }

    @Test
    public void TileFocusChangedTest()
    {
        Position newpos = new Position(5,5);
        game.setTileFocus(newpos);

        assertThat(((TestSpy)(((GameImpl) game).civGameObserver)).tileFocusChange, is(newpos));
    }

    @Test
    public void EndOfTurnAgeChange()
    {
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(), is(Player.RED));
        assertThat(game.getAge(), is(-3900));

        assertThat(((TestSpy)(((GameImpl) game).civGameObserver)).gameAge, is(-3900));
        assertThat(((TestSpy)(((GameImpl) game).civGameObserver)).next_player, is(Player.RED));

    }
}
