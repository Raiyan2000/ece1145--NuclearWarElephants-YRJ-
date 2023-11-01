package hotciv.framework;

public interface AttackStrategy
{
    public boolean Attack(Position from, Position to,Game game_board);

    public int getTerrainFactor(Game game_board, Position position);

}
