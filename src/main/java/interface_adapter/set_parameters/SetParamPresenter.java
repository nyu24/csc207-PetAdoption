package interface_adapter.set_parameters;

import interface_adapter.ViewManagerModel;
import interface_adapter.select_animal.SelectAnimalState;
import interface_adapter.select_animal.SelectAnimalViewModel;
import use_case.set_parameters.SetParamOutputBoundary;
import use_case.set_parameters.SetParamOutputData;

public class SetParamPresenter implements SetParamOutputBoundary {

    private final SetParamViewModel setParamViewModel;
    private final SelectAnimalViewModel selectAnimalViewModel;
    private final ViewManagerModel viewManagerModel;

    public SetParamPresenter(SetParamViewModel setParamViewModel, SelectAnimalViewModel selectAnimalViewModel,
                             ViewManagerModel viewManagerModel) {
        this.setParamViewModel = setParamViewModel;
        this.selectAnimalViewModel = selectAnimalViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SetParamOutputData response){
        final SelectAnimalState selectAnimalState = selectAnimalViewModel.getState();
        selectAnimalState.setApiPetList(response.getApiPetArrayList());

        //setting parameters
        this.selectAnimalViewModel.setState(selectAnimalState);
        this.selectAnimalViewModel.firePropertyChanged();

        this.viewManagerModel.setState(selectAnimalViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage){
        final SetParamState setParamState = setParamViewModel.getState();
        setParamState.setSetParamError(errorMessage);
        setParamViewModel.firePropertyChanged();
    }

}
