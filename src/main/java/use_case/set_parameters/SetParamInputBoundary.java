package use_case.set_parameters;

import java.util.ArrayList;

/**
 * Input Boundary for actions which are related to setting up parameters
 */
public interface SetParamInputBoundary {

    /**
     * Executes the set parameter use case.
     * @param setParamInputData the input data
     */
    void execute(SetParamInputData setParamInputData);

    /**
     * returns the type attributes list
     * @param type
     */
    ArrayList<ArrayList<String>> getTypeAttributes(String type);
}