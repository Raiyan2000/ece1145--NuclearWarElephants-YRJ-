package hotciv.standard;
import hotciv.framework.*;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.Iterator;

public class TestEpsilonCiv {

    private Game game1;
    private ThreeAttackWinner testWin;
    private EpsilonAttack attackObject;
    private TileImpl[][] world_layout;
    @Before
    public void setUp() {
        game1 = new GameImpl(new EpsilonCivFactory());
        world_layout = new TileImpl[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
        testWin = new ThreeAttackWinner();
        attackObject = new EpsilonAttack();
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
        //attacking terrain and units
        Position p1 = new Position(3,6);
        Position p2 = new Position(3,7);

        //defending terrain and units
        Position p3 = new Position(4,6);
        Position p4 = new Position(4,7);

        //place units on the board
        Unit Archer = new UnitImpl(GameConstants.ARCHER,Player.RED);
        Unit Archer2 = new UnitImpl(GameConstants.ARCHER,Player.BLUE);
        Unit Settler = new UnitImpl(GameConstants.SETTLER,Player.RED);
        ((GameImpl)(game1)).test_setUnitPosition(p1,Archer);
        ((GameImpl)(game1)).test_setUnitPosition(p2,Settler);
        ((GameImpl)(game1)).test_setUnitPosition(p3,Archer2);
        ((GameImpl)(game1)).test_setUnitPosition(p4,Archer2);

        assertThat(attackObject.getTerrainFactor(game1,p1),is(1));
        assertThat(attackObject.getFriendlyUnitsFactor(game1,p1),is(1));

       assertThat(attackObject.getTerrainFactor(game1,p3),is(1));
       assertThat(attackObject.getFriendlyUnitsFactor(game1,p3),is(1));
    }
}
