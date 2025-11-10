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
    public void FeedClicked(Pet pet) {
        buttons_usecase_interactor.feed(new buttons_inputData(pet));

    }
    public void WaterClicked(Pet pet) {
        buttons_usecase_interactor.water(new buttons_inputData(pet));
    }

    public void CleanClicked(Pet pet) {
        buttons_usecase_interactor.clean(new buttons_inputData(pet));
    }

    public void PlayClicked(Pet pet) {
        buttons_usecase_interactor.play(new buttons_inputData(pet));
    }
}
