package interface_adapter.load_game;

import interface_adapter.PetRoom.PetRoomState;
import interface_adapter.PetRoom.PetRoomViewModel;
import interface_adapter.ViewManagerModel;
import use_case.load_game.LoadGameOutputBoundary;
import use_case.load_game.LoadGameOutputData;

public class LoadGamePresenter implements LoadGameOutputBoundary {

    private final LoadGameViewModel loadGameViewModel;
    private final PetRoomViewModel petRoomViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoadGamePresenter(ViewManagerModel viewManagerModel,
                             PetRoomViewModel petRoomViewModel,
                             LoadGameViewModel loadGameViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.petRoomViewModel = petRoomViewModel;
        this.loadGameViewModel = loadGameViewModel;
    }

    @Override
    public void prepareSuccessLoadView(LoadGameOutputData loadGameOutputData) {
        final PetRoomState petRoomState = petRoomViewModel.getState();

        this.petRoomViewModel.firePropertyChanged();

        loadGameViewModel.setState(new LoadGameState());

        this.viewManagerModel.setState(petRoomViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareCancelView() {
        //petRoomState.getBackgroundImageName(response.get);

        petRoomViewModel.firePropertyChanged();

        viewManagerModel.setState(petRoomViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String errorMessage) {
        final LoadGameState loadGameState = loadGameViewModel.getState();
        loadGameState.setSaveDataExist(false);
        loadGameViewModel.firePropertyChanged();
    }

    @Override
    public void switchToPetRoomView() {
        viewManagerModel.setState(petRoomViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToLoadView() {
        viewManagerModel.setState(loadGameViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
