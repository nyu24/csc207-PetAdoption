package interface_adapter;
import entities.Pet;
import use_case.buttons.buttons_inputboundary;
import use_case.buttons.buttons_inputData;

/**
 * The controller class for the buttons use case
 */

public class buttons_controller {

    private  buttons_inputboundary buttons_usecase_interactor;

    public buttons_controller(buttons_inputboundary buttons_usecase_interactor) {
        this.buttons_usecase_interactor = buttons_usecase_interactor;
    }


    /**
     * executes buttons use case (feeds,gives water to, plays and cleans the given pet)
     * */
    public void FeedClicked() {
        buttons_usecase_interactor.execute(new buttons_inputData("FEED"));

    }
    public void WaterClicked() {
        buttons_usecase_interactor.execute(new buttons_inputData("WATER"));
    }

    public void CleanClicked() {
        buttons_usecase_interactor.execute(new buttons_inputData("CLEAN"));
    }

    public void PlayClicked() {
        buttons_usecase_interactor.execute(new buttons_inputData("PLAY"));
    }
}
