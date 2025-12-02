package interface_adapter.select_animal;


import entities.ApiPet;
import use_case.select_animal.SelectAnimalInputBoundary;
import use_case.select_animal.SelectAnimalInputData;

/**
 * Controller for select animal use case.
 */
public class SelectAnimalController {

    private final SelectAnimalInputBoundary selectAnimalUseCaseInteractor;

    public SelectAnimalController(SelectAnimalInputBoundary selectAnimalUseCaseInteractor) {
        this.selectAnimalUseCaseInteractor = selectAnimalUseCaseInteractor;
    }

    /**
     * Executes the Select animal Parameter Use Case.
     * @param selected the chosen APIPet
     */
    public void execute(ApiPet selected) {
        final SelectAnimalInputData selectAnimalInputData = new SelectAnimalInputData(selected);
        selectAnimalUseCaseInteractor.execute(selectAnimalInputData);
    }

    /**
     * Brings the user back to the set parameters view.
     */
    public void executeBack() {
        selectAnimalUseCaseInteractor.executeBack();
    }
}
