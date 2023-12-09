package hotciv.standard;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;

public class TestSpy implements GameObserver {

    Position worldChangePosition, tileFocusChange;
    Player next_player;

    int gameAge;
    public void worldChangedAt(Position pos)
    {
        worldChangePosition = pos;
    }

    public void turnEnds(Player nextPlayer, int age)
    {
        gameAge = age;
    }
    public void tileFocusChangedAt(Position position)
    {
        tileFocusChange = position;
    }
}
