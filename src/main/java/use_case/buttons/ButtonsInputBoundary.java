package use_case.buttons;

/**
 * Input boundary for actions related to the button-actions for the pet.
 * executes buttons use case.
 * */
public interface ButtonsInputBoundary {

    /**
     * Implimented in interactor.
     * @param buttonsInputData is the inputdata taken in but the inputboundary.
     */
    void execute(ButtonsInputData buttonsInputData);
}
