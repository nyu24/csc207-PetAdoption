package interface_adapter.buttons;
import use_case.buttons.ButtonsInputBoundary;
import use_case.buttons.ButtonsInputData;

/**
 * The controller class for the buttons use case
 */

public class ButtonsController {

    private ButtonsInputBoundary buttons_usecase_interactor;

    public ButtonsController(ButtonsInputBoundary buttons_usecase_interactor) {
        this.buttons_usecase_interactor = buttons_usecase_interactor;
    }


    /**
     * executes buttons use case (feeds,gives water to, plays and cleans the given pet)
     * */
    public void FeedClicked() {
        buttons_usecase_interactor.execute(new ButtonsInputData("FEED"));

    }
    public void WaterClicked() {
        buttons_usecase_interactor.execute(new ButtonsInputData("WATER"));
    }

    public void CleanClicked() {
        buttons_usecase_interactor.execute(new ButtonsInputData("CLEAN"));
    }

    public void PlayClicked() {
        buttons_usecase_interactor.execute(new ButtonsInputData("PLAY"));
    }
}
