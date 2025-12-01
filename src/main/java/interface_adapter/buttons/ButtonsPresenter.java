package interface_adapter.buttons;

import interface_adapter.PetRoom.PetRoomViewModel;
import interface_adapter.ViewManagerModel;
import use_case.buttons.ButtonsOutputBoundary;
import use_case.buttons.ButtonsOutputData;

/**
 * presenter for the Buttons use case.
 */

public class ButtonsPresenter implements ButtonsOutputBoundary {

    private final ButtonsViewModel buttonsViewModel;
    private final ViewManagerModel viewManagerModel;
    private final PetRoomViewModel petRoomViewModel;

    public ButtonsPresenter(ViewManagerModel viewManagerModel, ButtonsViewModel buttonsViewModel, PetRoomViewModel petRoomViewModel ) {
        this.viewManagerModel = viewManagerModel;
        this.buttonsViewModel = buttonsViewModel;
        this.petRoomViewModel = petRoomViewModel;
    }

    @Override
    public void prepareSuccessView(ButtonsOutputData response) {
        final ButtonsState buttonsState = buttonsViewModel.getState();
        buttonsState.setHappiness(response.getHapiness());
        buttonsState.setCleanliness(response.getCleanliness());
        buttonsState.setHunger(response.getHunger());
        buttonsState.setThirst(response.getThirst());
        buttonsViewModel.firePropertyChange("buttons");
    }



    @Override
    public void prepareFailView(String errorMessage) {
        //TODO : impliment this?
        final ButtonsState buttonsState = buttonsViewModel.getState();
        //TODO : eroor

    }

}






