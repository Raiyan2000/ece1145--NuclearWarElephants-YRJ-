package hotciv.standard;

import hotciv.framework.*;

public class CityImpl implements City{

    private Player owner_city;

    private int population_size;

    private String work_focus;

    private int production_of_city;


    public CityImpl(Player name, String workFocus) {
        owner_city = name;
        population_size = 1;
        production_of_city = 0;
        workFocus = null;
        work_focus = workFocus;
    }

    public void incrementProductionPerRound(){
        production_of_city += 6;
    }

    public void setWorkFocus(String troop){
        work_focus = troop;
    }

    public void setOwnerCity(Player name){
        owner_city = name;
    }
    public Player getOwner(){
        return owner_city;
    }

    public int getSize(){
        return population_size;
    }


    public int getTreasury() {
        return 0;
    }


    public String getProduction() {
        return null;
    }

    public int getProductionAmount(){
        return production_of_city;
    }
    public String getWorkforceFocus(){
        return work_focus;
    }
}
