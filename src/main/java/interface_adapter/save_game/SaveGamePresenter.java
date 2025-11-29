package interface_adapter.save_game;

import interface_adapter.PetRoom.PetRoomViewModel;
import interface_adapter.PetRoom.PetRoomState;
import interface_adapter.ViewManagerModel;
import use_case.save_game.SaveGameOutputBoundary;
import use_case.save_game.SaveGameOutputData;

public class SaveGamePresenter implements SaveGameOutputBoundary {

    private final SaveGameViewModel saveGameViewModel;
    private final PetRoomViewModel petRoomViewModel;
    private final ViewManagerModel viewManagerModel;

    public SaveGamePresenter(ViewManagerModel viewManagerModel,
                             PetRoomViewModel petRoomViewModel,
                             SaveGameViewModel saveGameViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.petRoomViewModel = petRoomViewModel;
        this.saveGameViewModel = saveGameViewModel;
    }

    @Override
    public void prepareSavedGameView(SaveGameOutputData response) {
        final PetRoomState petRoomState = petRoomViewModel.getState();

        //petRoomState.getBackgroundImageName(response.get);

        petRoomViewModel.firePropertyChanged();

        viewManagerModel.setState(petRoomViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareCancelView(SaveGameOutputData response) {
        //petRoomState.getBackgroundImageName(response.get);

        petRoomViewModel.firePropertyChanged();

        viewManagerModel.setState(petRoomViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void switchToPetRoomView() {
        viewManagerModel.setState(petRoomViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }



}
