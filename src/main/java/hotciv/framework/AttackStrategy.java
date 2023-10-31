package hotciv.framework;

public interface AttackStrategy
{
    public void Attack();

    public int GetNumAttackWins(Player player);

}
