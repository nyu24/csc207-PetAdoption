package use_case.set_parameters;

import java.util.ArrayList;

/**
 * Input Boundary for actions which are related to setting up parameters.
 */
public interface SetParamInputBoundary {

    /**
     * Executes the set parameter use case.
     * @param setParamInputData the input data
     */
    void execute(SetParamInputData setParamInputData);

    /**
     * Returns the type attributes list.
     * @param type of animal to get attributes of
     */
    ArrayList<ArrayList<String>> getTypeAttributes(String type);

    /**
     * Returns a list of types.
     */
    ArrayList<String> getTypes();

}
