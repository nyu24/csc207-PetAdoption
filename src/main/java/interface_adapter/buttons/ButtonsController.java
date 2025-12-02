package interface_adapter.buttons;

import use_case.buttons.ButtonsInputBoundary;
import use_case.buttons.ButtonsInputData;

/**
 * The controller class for the buttons use case.
 */

public class ButtonsController {

    private ButtonsInputBoundary buttonsUsecaseInteractor;

    public ButtonsController(ButtonsInputBoundary buttonsUsecaseInteractor) {
        this.buttonsUsecaseInteractor = buttonsUsecaseInteractor;
    }
    /**
     * Executes buttons use case (feeds the given pet).
     *
     */

    public void feedClicked() {
        buttonsUsecaseInteractor.execute(new ButtonsInputData("FEED"));

    }

    /**
     * Executes buttons use case (gives water to the given pet).
     *
     */
    public void waterClicked() {
        buttonsUsecaseInteractor.execute(new ButtonsInputData("WATER"));
    }

    /**
     * Executes buttons use case (cleans the given pet).
     *
     */
    public void cleanClicked() {
        buttonsUsecaseInteractor.execute(new ButtonsInputData("CLEAN"));
    }

    /**
     * Executes buttons use case (plays with the given pet).
     *
     */
    public void playClicked() {
        buttonsUsecaseInteractor.execute(new ButtonsInputData("PLAY"));
    }
}
