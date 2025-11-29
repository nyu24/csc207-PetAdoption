package interface_adapter.set_parameters;

import use_case.set_parameters.SetParamInputBoundary;
import use_case.set_parameters.SetParamInputData;

import java.util.ArrayList;

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
                type, coat, colour, breed, gender);

        setParamUseCaseInteractor.execute(setParamInputData);
    }

    /**
     * returns the attributes as a list for the given type
     * @param type, type of animal to obtain attributes for
     * @return attributesList
     */
    public ArrayList<ArrayList<String>> getTypeAttributes(String type) {
        return setParamUseCaseInteractor.getTypeAttributes(type);
    }

    /**
     * returns a list of possible animal types
     * @return typesList
     */
    public ArrayList<String> getTypes(){
        return setParamUseCaseInteractor.getTypes();
    }
}
