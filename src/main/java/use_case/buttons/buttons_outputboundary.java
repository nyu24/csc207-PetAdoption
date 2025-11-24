package use_case.buttons;

/**
 * The output boundary for the buttons use case.
 */

public interface buttons_outputboundary {
    /**
     * prepares success view for buttons use case
     */
    void prepareSuccessView(buttons_OutputData buttons_output);
    /**
     * prepares the failure view for the buttons use case
     */
    void prepareFailView(buttons_OutputData buttons_outputData);





}
