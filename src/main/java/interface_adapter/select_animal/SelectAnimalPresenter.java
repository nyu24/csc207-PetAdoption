package interface_adapter.select_animal;

import interface_adapter.ViewManagerModel;
import interface_adapter.high_score.HighScoreState;
import interface_adapter.high_score.HighScoreViewModel;
import use_case.select_animal.SelectAnimalOutputBoundary;
import use_case.select_animal.SelectAnimalOutputData;

public class SelectAnimalPresenter implements SelectAnimalOutputBoundary {

    private final SelectAnimalViewModel selectAnimalViewModel;
    //TODO: for georgia, use your section view model and refactor it (line 13)
    private final HighScoreViewModel petRoomViewModel;
    private final ViewManagerModel viewManagerModel;

    // TODO: replace HighScoreViewModel (in the parameters) to your one
    public SelectAnimalPresenter(SelectAnimalViewModel selectAnimalViewModel, HighScoreViewModel petRoomViewModel,
                             ViewManagerModel viewManagerModel) {
        this.selectAnimalViewModel = selectAnimalViewModel;
        this.petRoomViewModel = petRoomViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    //TODO: Georgia, it wont function right now until you create these files/or if they r names wrong
    //links the view change from selectAnimalView to petRoomView
    @Override
    public void prepareSuccessView(SelectAnimalOutputData response){
        //TODO: arrives here
        System.out.println("some pet vars: " + response.getPet().getCleanliness() + " " +
                response.getPet().getApiPet().getName() +
                " " + response.getPet().getApiPet().getType() + " "
        + response.getPet().getApiPet().getGender());

        //TODO: Georgia this should create the pet you use and switch the view, so uncomment it
        //petRoomState.setPet(response.getPet());

        //TODO: delete, this is for testing purposes
        // replace 'HighScoreState' with your proper state
        final HighScoreState highScoreState = petRoomViewModel.getState();
        this.petRoomViewModel.setState(highScoreState);
        this.petRoomViewModel.firePropertyChanged();

        this.viewManagerModel.setState(selectAnimalViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

}
