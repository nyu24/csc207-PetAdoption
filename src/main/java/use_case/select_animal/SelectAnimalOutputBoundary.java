package use_case.select_animal;

public interface SelectAnimalOutputBoundary {

    /**
     * Prepares the success view for the Select Animal Use case
     * @param outputData the output data
     */
    void prepareSuccessView(SelectAnimalOutputData outputData);

}
