package use_case.buttons;

/**
 * Input boundary for actions related to the button-actions for the pet.
 * executes buttons use case
 * */
public interface ButtonsInputBoundary {

    /**
     * Executes the buttons use case.
     * @param buttonsInputData is the input data taken in to execute the use case.
     */
    void execute(ButtonsInputData buttonsInputData);

}
