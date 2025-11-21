package use_case.set_parameters;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface SetParamInputBoundary {

    /**
     * Executes the set parameter use case.
     * @param setParamInputData the input data
     */
    void execute(SetParamInputData setParamInputData);
}