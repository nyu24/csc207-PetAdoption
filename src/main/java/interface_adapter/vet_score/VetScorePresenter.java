package interface_adapter.vet_score;

import interface_adapter.ViewManagerModel;
import use_case.Vet.VetOutputBoundary;
import use_case.Vet.VetOutputData;

import javax.swing.*;

public class VetScorePresenter implements VetOutputBoundary {
    private final VetScoreViewModel vetScoreViewModel;
    private final ViewManagerModel viewManagerModel;

    public VetScorePresenter(VetScoreViewModel vetScoreViewModel,
                             ViewManagerModel viewManagerModel) {

        this.vetScoreViewModel = vetScoreViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(VetOutputData vetOutputData) {

//
//        System.out.println("Presenter: prepareSuccessView called at " + System.currentTimeMillis());
//
//        VetScoreState newState = new VetScoreState();
//        newState.setMetRequirements(vetOutputData.getRequirements());
//
//        // Update the state (but don't fire yet)
//        vetScoreViewModel.setState(newState);
//
//        // FIRST: Switch to the vet view
//        this.viewManagerModel.setState(vetScoreViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();
//
//        // THEN: Fire the property change to update the data
//        // Use invokeLater to ensure the view switch completes first
//        SwingUtilities.invokeLater(() -> {
//            vetScoreViewModel.firePropertyChanged();
//        });

        VetScoreState newState = new VetScoreState();
        newState.setMetRequirements(vetOutputData.getRequirements());
        vetScoreViewModel.printListeners();
        // replace state in viewmodel
        vetScoreViewModel.setState(newState);
        vetScoreViewModel.firePropertyChanged();

        this.viewManagerModel.setState(vetScoreViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        }

    @Override
    public void prepareFailView(String message) {

    }

}
