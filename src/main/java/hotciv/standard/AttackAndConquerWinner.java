package hotciv.standard;

import hotciv.framework.*;
public class AttackAndConquerWinner implements WinStrategy {

    public Player isWinner(int age, TileImpl[][] world_board) {
        //not finished
        WinStrategy attackWinner = new ThreeAttackWinner();
        WinStrategy conquerWinner = new CityConquerWinner();

        if (conquerWinner.isWinner(age, world_board) != null) { return conquerWinner.isWinner(age, world_board); };

        if (attackWinner.isWinner(age, world_board) != null && age >= -2000) { return attackWinner.isWinner(age, world_board); }

        //if no other cases are reached, there is no winner at this time
        return null;
    }

}