package interface_adapter.buttons;

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

    public buttons_presenter(ViewManagerModel viewManagerModel, buttons_viewModel buttonsViewModel ) {
        this.viewManagerModel = viewManagerModel;
        this.buttonsViewModel = buttonsViewModel;
    }

    @Override
    public void prepareSuccessView(buttons_OutputData response) {
        final buttons_State buttonsState = buttonsViewModel.getState();
        buttonsState.setHappiness(response.getHapiness());
        buttonsState.setCleanliness(response.getCleanliness());
        buttonsState.setHunger(response.getHunger());
        buttonsState.setThirst(response.getThirst());
        buttonsViewModel.firePropertyChange("buttons");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        //TODO : impliment this?
        final buttons_State buttonsState = buttonsViewModel.getState();
        //TODO : error

    }

}






