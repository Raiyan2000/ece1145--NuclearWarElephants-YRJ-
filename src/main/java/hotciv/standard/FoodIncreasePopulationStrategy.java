package hotciv.standard;

import hotciv.framework.*;

public class FoodIncreasePopulationStrategy implements CityPopulationStrategy
{
    public void UpdatePopulationAndFood(City cityToBeUpdated)
    {
        //obtain amount of food in the city
        int foodCount = ((CityImpl)cityToBeUpdated).getFoodCount();

        //obtain city population
        int cityPopulation = ((CityImpl)cityToBeUpdated).getPopulation();


        if(foodCount > 5+cityPopulation*3)
        {
            //if city population is less than 9, increment population by 1
            if(cityPopulation < 9) {
                ((CityImpl)cityToBeUpdated).setPopulation(cityPopulation + 1);
            }

            //reset food count to zero
            ((CityImpl)cityToBeUpdated).resetFoodCount();
        }
    }

}
