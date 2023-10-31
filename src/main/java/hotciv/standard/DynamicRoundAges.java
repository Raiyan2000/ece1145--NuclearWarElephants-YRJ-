package hotciv.standard;

import hotciv.framework.*;
public class DynamicRoundAges implements WorldAgeStrategy {
    public int agePostRound(int age) {
        if (age >= -4000 && age < -100) {
            age+=100;
        }

        else if (age == -100) {
            age=-1;
        }

        else if (age == -1) {
            age=1;
        }

        else if (age == 1) {
            age=50;
        }

        else if (age >= 50 && age < 1750) {
            age+=50;
        }

        else if (age >= 1750 && age < 1900) {
            age+=25;
        }

        else if (age >= 1900 && age < 1970) {
            age+=5;
        }

        //technically we could remove the 2000 limiter since that's the ending year of the game
        else if (age >= 1970 && age < 2000) {
            age+=1;
        }

        //if something goes wrong, this should catch it and return that. Later will replace with a proper error code
        else {
            return -9999;
        }
        return age;
    }

}
