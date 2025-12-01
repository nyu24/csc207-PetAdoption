package use_case.buttons;

/**
 * The output boundary for the buttons use case.
 */

public interface ButtonsOutputBoundary {
    /**
     * Prepares success view for buttons use case.
     * @param buttons_output is what will be returned by interactor.
     */
    void prepareSuccessView(ButtonsOutputData buttons_output);

    /**
     * Prepares the failure view for the buttons use case.
     * @param errorMessage is the error messsage to print if the button logic does not work.
     */
    void prepareFailView(String errorMessage);

}
