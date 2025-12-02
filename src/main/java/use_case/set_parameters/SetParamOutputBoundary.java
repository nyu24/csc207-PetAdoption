package use_case.set_parameters;

/**
 * Output Boundary for set param use case.
 */
public interface SetParamOutputBoundary {

    /**
     * Prepares the success view for the Set Param Use case.
     * @param outputData the output data
     */
    void prepareSuccessView(SetParamOutputData outputData);

    /**
     * Prepares the failure view for the Set Param Use case.
     * @param errorMessage the explanation for failure
     */
    void prepareFailView(String errorMessage);
}