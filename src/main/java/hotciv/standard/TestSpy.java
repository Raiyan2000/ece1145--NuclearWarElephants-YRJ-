package hotciv.standard;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;

public class TestSpy implements GameObserver {

    public Position worldChangePosition, tileFocusChange;
    public Player next_player;

    public int gameAge;
    public void worldChangedAt(Position pos)
    {
        worldChangePosition = pos;
    }

    public void turnEnds(Player nextPlayer, int age)
    {
        gameAge = age;
        next_player = nextPlayer;
    }
    public void tileFocusChangedAt(Position position)
    {
        tileFocusChange = position;
    }
}
