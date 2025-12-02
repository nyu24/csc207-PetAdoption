package interface_adapter.load_game;

import org.jetbrains.annotations.NotNull;

import entities.SaveFile;
import interface_adapter.PetRoom.PetRoomState;
import interface_adapter.PetRoom.PetRoomViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.title.TitleViewModel;
import use_case.load_game.LoadGameOutputBoundary;
import use_case.load_game.LoadGameOutputData;

public class LoadGamePresenter implements LoadGameOutputBoundary {

    private final LoadGameViewModel loadGameViewModel;
    private final PetRoomViewModel petRoomViewModel;
    private final TitleViewModel titleViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoadGamePresenter(ViewManagerModel viewManagerModel,
                             PetRoomViewModel petRoomViewModel,
                             TitleViewModel titleViewModel,
                             LoadGameViewModel loadGameViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.petRoomViewModel = petRoomViewModel;
        this.titleViewModel = titleViewModel;
        this.loadGameViewModel = loadGameViewModel;
    }

    @Override
    public void prepareSuccessLoadView(LoadGameOutputData response) {
        final SaveFile saveFile = response.getSaveFile();
        final PetRoomState petRoomState = getPetRoomState(saveFile);
        this.petRoomViewModel.firePropertyChange("timer_start");
        petRoomViewModel.setState(petRoomState);

        loadGameViewModel.setState(new LoadGameState());

        this.viewManagerModel.setState(petRoomViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @NotNull
    private PetRoomState getPetRoomState(SaveFile saveFile) {
        final PetRoomState petRoomState = petRoomViewModel.getState();
        petRoomState.setPetType(saveFile.getApiPet().getType());
        petRoomState.setTimer(saveFile.getTimeLeft());
        petRoomState.setScore(saveFile.getCurrScore());
        petRoomState.setFood(saveFile.getCurrPet().getHunger());
        petRoomState.setWater(saveFile.getCurrPet().getThirst());
        petRoomState.setHappiness(saveFile.getCurrPet().getHappiness());
        petRoomState.setCleanliness(saveFile.getCurrPet().getCleanliness());
        petRoomState.setCurrPet(saveFile.getCurrPet());
        return petRoomState;
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final LoadGameState loadGameState = loadGameViewModel.getState();
        loadGameState.setSaveDataExist(false);
        loadGameViewModel.firePropertyChanged();
    }

    @Override
    public void switchToTitleView() {
        this.viewManagerModel.setState(titleViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
