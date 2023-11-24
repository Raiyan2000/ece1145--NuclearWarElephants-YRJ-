package hotciv.standard;

import hotciv.framework.*;

public class CityImpl implements City{

    private Player owner_city;

    private int population_size;

    private String work_focus;

    private String unit_production;

    private int treasury_of_city;

    private int production_cost;



    public CityImpl(Player name, String workFocus) {
        owner_city = name;
        population_size = 1;
        work_focus = workFocus;
        unit_production = null;
        treasury_of_city = 0;
        this.calculateProductionCost();
    }

    public void incrementProductionPerRound(){
        treasury_of_city += 6;
    }

    public void setWorkFocus(String type){
        work_focus = type;
    }

    public void setOwnerCity(Player name){
        owner_city = name;
    }

    //sets unit to be produced in city
    public void setProductionType(String unit){
        unit_production = unit;
    }

    public void calculateProductionCost(){

        if(unit_production == GameConstants.ARCHER)
        {
            production_cost = 10;
        }
        else if(unit_production == GameConstants.LEGION)
        {
            production_cost = 15;
        }
        else if(unit_production == GameConstants.SETTLER)
        {
            production_cost = 30;
        }
        else
        {
            production_cost = 0;
        }
    }

    public void setTreasury(int amount){
        treasury_of_city = amount;
    }

    public int getProductionCost(){
        return production_cost;
    }

    public Player getOwner(){
        return owner_city;
    }

    public int getSize(){
        return population_size;
    }


    public int getTreasury() {
        return treasury_of_city;
    }

    public void setProduction(String unit)
    {
        unit_production = unit;
    }

    public String getProduction() {
        return unit_production;
    }

    public String getWorkforceFocus(){
        return work_focus;
    }

    public void setPopulation(int people)
    {
        population_size = people;
    }

    public int getPopulation()
    {
        return population_size;
    }
}
