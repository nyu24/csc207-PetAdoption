package interface_adapter;

import interface_adapter.ViewModel;
import use_case.buttons.buttons_outputboundary;
import use_case.buttons.buttons_OutputData;
import interface_adapter.ViewManagerModel;
import interface_adapter.buttons_State;

/**
 * presenter for the Buttons use case.
 */

public class buttons_presenter implements buttons_outputboundary {

    private final buttons_viewModel buttonsViewModel;
    private final ViewManagerModel viewManagerModel;

    public buttons_presenter(ViewManagerModel viewManagerModel, buttons_viewModel buttonsViewModel ) {
        this.viewManagerModel = viewManagerModel;
        this.buttonsViewModel = buttonsViewModel;
    }

    @Override
    public void prepareSuccessView(buttons_OutputData response) {
        final buttons_State buttonsState = buttonsViewModel.getState();
        buttonsState.setHapiness(response.getHapiness());
        buttonsState.setCleanliness(response.getCleanliness());
        buttonsState.setHunger(response.getHunger());
        buttonsState.setThirst(response.getThirst());
        buttonsViewModel.firePropertyChange();


    }

    @Override
    public void prepareFailView(buttons_OutputData response) {
        //TODO : impliment this ?

    }

}






