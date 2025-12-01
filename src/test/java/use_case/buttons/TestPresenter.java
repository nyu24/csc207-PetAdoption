package use_case.buttons;

import use_case.buttons.buttons_OutputData;

public class TestPresenter implements buttons_outputboundary {
    buttons_OutputData buttonsOutputData;
    String error;

    @Override
    public void prepareSuccessView(buttons_OutputData buttonsOutputData) {
        this.buttonsOutputData = buttonsOutputData;
    }

    @Override
    public void prepareFailView(String error) {
        this.error = error;
    }

}
