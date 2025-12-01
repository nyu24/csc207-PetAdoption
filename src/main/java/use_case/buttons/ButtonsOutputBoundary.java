package use_case.buttons;

/**
 * The output boundary for the buttons use case.
 */

public interface ButtonsOutputBoundary {
    /**
     * prepares success view for buttons use case
     */
    void prepareSuccessView(ButtonsOutputData buttons_output);
    /**
     * prepares the failure view for the buttons use case
     */
    void prepareFailView(String errorMessage);
    //TODO : double check this


}
