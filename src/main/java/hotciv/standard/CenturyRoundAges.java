package hotciv.standard;

import hotciv.framework.*;

public class CenturyRoundAges implements WorldAgeStrategy {
    public int agePostRound(int input) {
        input +=100;
        return input;
    }

}
