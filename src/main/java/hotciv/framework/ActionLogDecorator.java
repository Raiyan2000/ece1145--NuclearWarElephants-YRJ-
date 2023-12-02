package hotciv.framework;

//import hotciv.standard.*;

import hotciv.standard.GameImpl;

public class ActionLogDecorator implements Game {
    private Game game;

    public ActionLogDecorator(Game gm) {
        game = gm;
    }

    //untouched
    public void setCurrentPlayerInTurn(Player name) {
        ((GameImpl)(game)).setCurrentPlayerInTurn(name);
    }

    //untouched
    public void test_setUnitPosition(Position pos, Unit troop) {
        ((GameImpl)(game)).test_setUnitPosition(pos, troop);
    }

    //untouched
    public void checkAdjacentCityTiles(int x, int y) {
        ((GameImpl)(game)).checkAdjacentCityTiles(x, y);
    }

    //untouched
    public String getGameType() {
        return ((GameImpl)(game)).getGameType();
    }

    //untouched
    public Tile getTileAt(Position p) {
        return game.getTileAt(p);
    }

    //untouched
    public Unit getUnitAt(Position p) {
        return game.getUnitAt(p);
    }

    //untouched
    public City getCityAt(Position p) {
        return game.getCityAt(p);
    }

    //untouched
    public Player getPlayerInTurn() {
        return game.getPlayerInTurn();
    }

    //untouched
    public Player getWinner() {
        return game.getWinner();
    }

    //untouched
    public int getAge() {
        return game.getAge();
    }

    //untouched
    public void setAge(int x) {
        ((GameImpl)(game)).setAge(x);
    }

    //test
    public boolean moveUnit(Position from, Position to) {
        //log the movement of a unit
        System.out.print("\n" + game.getPlayerInTurn() + " moves " +
                game.getUnitAt(from).getTypeString() + " from " + from.toString() +
                " to " + to.toString() + ".");

        return game.moveUnit(from, to);
    }

    //test
    public void endOfTurn() {
        //log the player changing their turn
        System.out.print("\n" + game.getPlayerInTurn() + " ends turn.");
        game.endOfTurn();
    }

    //test
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        game.changeWorkForceFocusInCityAt(p, balance);
        //log the change in workforce focus
        System.out.print("\n" + game.getPlayerInTurn() + " changes work force focus in city at " +
                p.toString() + " to " + balance + ".");
    }

    //test
    public void changeProductionInCityAt(Position p, String unitType) {
        game.changeProductionInCityAt(p, unitType);
        //log the change in production focus
        System.out.print("\n" + game.getPlayerInTurn() + " changes production in city at " +
                p.toString() + " to " + unitType + ".");
    }

    //untouched for now. Not sure how to properly implement
    public void performUnitActionAt(Position p) {
        game.performUnitActionAt(p);
    }

    //untouched
    public int GetNumAttackWins(Player p) {
        return ((GameImpl)game).GetNumAttackWins(p);
    }

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public void setTileFocus(Position position) {

    }
}
