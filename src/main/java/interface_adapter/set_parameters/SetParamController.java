package interface_adapter.set_parameters;

import use_case.set_parameters.SetParamInputBoundary;
import use_case.set_parameters.SetParamInputData;

public class SetParamController {

    private final SetParamInputBoundary setParamUseCaseInteractor;

    public SetParamController(SetParamInputBoundary setParamUseCaseInteractor) {
        this.setParamUseCaseInteractor = setParamUseCaseInteractor;
    }

    /**
     * Executes the Set Parameter Use Case
     * @param type type of the animal
     * @param breed breed of the animal
     * @param colour colour of the animal
     * @param coat coat length of the animal
     * @param gender gender of the animal
     */
    public void execute(String type, String breed, String colour, String coat, String gender) {
        final SetParamInputData setParamInputData = new SetParamInputData(
                type, breed, colour, coat, gender);

        setParamUseCaseInteractor.execute(setParamInputData);
    }
}
