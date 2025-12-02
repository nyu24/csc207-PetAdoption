package use_case.buttons;

/**
 * The output boundary for the buttons use case.
 */

public interface ButtonsOutputBoundary {
    /**
     * Prepares success view for buttons use case.
     *  @param buttons_output is the output data returned by the method.
     * */

    void prepareSuccessView(ButtonsOutputData buttons_output);

    /**
     * Prepares the failure view for the buttons use case.
     * @param errorMessage is the error message printed by the fail view.
     */
    void prepareFailView(String errorMessage);
}
