package interface_adapter.select_animal;


import entities.APIPet;
import use_case.select_animal.SelectAnimalInputBoundary;
import use_case.select_animal.SelectAnimalInputData;

public class SelectAnimalController {

    private final SelectAnimalInputBoundary selectAnimalUseCaseInteractor;

    public SelectAnimalController(SelectAnimalInputBoundary selectAnimalUseCaseInteractor) {
        this.selectAnimalUseCaseInteractor = selectAnimalUseCaseInteractor;
    }

    /**
     * Executes the Select animal Parameter Use Case
     * @param selected, the chosen APIPet
     */
    public void execute(APIPet selected) {
        final SelectAnimalInputData selectAnimalInputData = new SelectAnimalInputData(selected);
        selectAnimalUseCaseInteractor.execute(selectAnimalInputData);
    }
}
