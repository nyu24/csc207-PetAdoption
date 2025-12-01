package use_case.select_animal;

public interface SelectAnimalOutputBoundary {

    /**
     * Prepares the success view for the Select Animal Use case
     * @param outputData the output data
     */
    void prepareSuccessView(SelectAnimalOutputData outputData);

    /**
     * Prepares the success view for the back button in Select Animal Use case
     */
    void prepareSuccessViewBack();
}
