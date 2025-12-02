package interface_adapter.set_parameters;

/**
 * State for the Set Parameters View.
 */
public class SetParamState {
    // error
    private String setParamError;

    // error getter and setter
    public void setSetParamError(String setParamError) {
        this.setParamError = setParamError;
    }

    public String getSetParamError() {
        return setParamError;
    }
}
