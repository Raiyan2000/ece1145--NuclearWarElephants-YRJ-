package hotciv.standard;

import hotciv.framework.*;
public class DynamicRoundAges implements WorldAgeStrategy {
    public int agePostRound(int input) {
        if (input >= -4000 && input < -100) {
            input+=100;
            return input;
        }

        if (input == -100) {
            input=-1;
            return input;
        }

        if (input == -1) {
            input=1;
            return input;
        }

        if (input == 1) {
            input=50;
            return input;
        }

        if (input >= 50 && input < 1750) {
            input+=50;
            return input;
        }

        if (input >= 1750 && input < 1900) {
            input+=25;
            return input;
        }

        if (input >= 1900 && input < 1970) {
            input+=5;
            return input;
        }

        //technically we could remove the 2000 limiter since that's the ending year of the game
        if (input >= 1970 && input < 2000) {
            input+=1;
            return input;
        }

        return -99999;
    }

}
