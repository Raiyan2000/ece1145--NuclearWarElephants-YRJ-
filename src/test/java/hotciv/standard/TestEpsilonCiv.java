package hotciv.standard;
import hotciv.framework.*;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.Iterator;

public class TestEpsilonCiv {

    private Game game;
    private ThreeAttackWinner testWin;
    private EpsilonAttack attackObject;
    private TileImpl[][] world_layout;
    @Before
    public void setUp() {
        game = new GameImpl(new EpsilonCivFactory());
        world_layout = new TileImpl[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
        testWin = new ThreeAttackWinner();
    }
    @Test
    public void TestEpsilonWin()
    {
        testWin.setAttackWinCount(Player.RED);
        testWin.setAttackWinCount(Player.RED);
        testWin.setAttackWinCount(Player.RED);
        assertThat(testWin.isWinner(5,world_layout),is(Player.RED));
    }

    @Test
    public void TestEpsilonNoWinner()
    {
        testWin.setAttackWinCount(Player.BLUE);
        testWin.setAttackWinCount(Player.RED);
        testWin.setAttackWinCount(Player.RED);
        assertThat(testWin.isWinner(5,world_layout),is(nullValue()));
    }

    @Test
    public void TestEpsilonAttack1()
    {
        Position p1 = new Position(3,5);
        Unit Archer = new UnitImpl(GameConstants.ARCHER,Player.RED);
        Unit Settler = new UnitImpl(GameConstants.SETTLER,Player.RED);
        game.test_setUnitPosition(p1,Archer);
    }

    @Test
    public void TestEpsilonAttack2()
    {
        Position p1 = new Position(3,5);
        Unit Archer = new UnitImpl(GameConstants.ARCHER,Player.RED);
        Unit Settler = new UnitImpl(GameConstants.SETTLER,Player.RED);
        game.test_setUnitPosition(p1,Archer);

    }


}
