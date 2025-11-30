package use_case.select_animal;


/**
 * Input Boundary for actions which are related to selecting an animal
 */
public interface SelectAnimalInputBoundary {
    /**
     * Executes the select animal use case.
     * @param selectAnimalInputData the input data
     */
    void execute(SelectAnimalInputData selectAnimalInputData);

    /**
     * returns the user to the set parameters view
     */
    void executeBack();
}
