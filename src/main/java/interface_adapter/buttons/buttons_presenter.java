package interface_adapter.buttons;

import interface_adapter.PetRoom.PetRoomViewModel;
import interface_adapter.PetRoom.PetRoomState;
import interface_adapter.ViewManagerModel;
import use_case.buttons.buttons_outputboundary;
import use_case.buttons.buttons_OutputData;
import interface_adapter.ViewModel;
import interface_adapter.ViewManagerModel;

/**
 * presenter for the Buttons use case.
 */

public class buttons_presenter implements buttons_outputboundary {

    private final buttons_viewModel buttonsViewModel;
    private final ViewManagerModel viewManagerModel;
    private final PetRoomViewModel petRoomViewModel;

    public buttons_presenter(ViewManagerModel viewManagerModel, buttons_viewModel buttonsViewModel, PetRoomViewModel petRoomViewModel ) {
        this.viewManagerModel = viewManagerModel;
        this.buttonsViewModel = buttonsViewModel;
        this.petRoomViewModel = petRoomViewModel;
    }

    @Override
    public void prepareSuccessView(buttons_OutputData response) {
        final buttons_State buttonsState = buttonsViewModel.getState();
        buttonsState.setHappiness(response.getHapiness());
        buttonsState.setCleanliness(response.getCleanliness());
        buttonsState.setHunger(response.getHunger());
        buttonsState.setThirst(response.getThirst());
        buttonsViewModel.firePropertyChange("buttons");

        // UPDATE PET ROOM STATE - THIS IS THE KEY PART
        final PetRoomState petRoomState = petRoomViewModel.getState();
        petRoomState.setFood((int) response.getHunger());
        petRoomState.setWater((int) response.getThirst());
        petRoomState.setCleanliness((int) response.getCleanliness());
        petRoomState.setHappiness((int) response.getHapiness());

    }



    @Override
    public void prepareFailView(String errorMessage) {
        //TODO : impliment this?
        final buttons_State buttonsState = buttonsViewModel.getState();
        //TODO : eroor

    }

}






