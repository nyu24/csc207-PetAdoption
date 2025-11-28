package use_case.select_animal;

import entities.APIPet;

import java.util.ArrayList;

public interface SelectAnimalDataAccessInterface {
    /**
     * gets the APIPet list created in SetParamView
     */
    ArrayList<APIPet> getSetParamList();

}
