package use_case.buttons;

public class TestPresenter implements ButtonsOutputBoundary {
    ButtonsOutputData buttonsOutputData;
    String error;

    @Override
    public void prepareSuccessView(ButtonsOutputData buttonsOutputData) {
        this.buttonsOutputData = buttonsOutputData;
    }

    @Override
    public void prepareFailView(String error) {
        this.error = error;
    }

}
