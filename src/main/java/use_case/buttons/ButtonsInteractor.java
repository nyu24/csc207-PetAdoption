package use_case.buttons;
import entities.Pet;

/**
 *
 */
public class ButtonsInteractor implements ButtonsInputBoundary {
    private int MAXIMUM_VALUE = 100;
    private int SCORE_INCREASE = 20;
    private final ButtonsOutputBoundary buttons_presenter;
    private ButtonsDataAccessObject buttons_data_acess;

    public ButtonsInteractor(ButtonsOutputBoundary ButtonsOutputBoundary, ButtonsDataAccessObject buttons_data_acess) {
        this.buttons_presenter = ButtonsOutputBoundary;
        this.buttons_data_acess = buttons_data_acess;
    }

    @Override
    public void execute(ButtonsInputData ButtonsInputData) {
        Pet pet = buttons_data_acess.load();
        switch (ButtonsInputData.getAction()) {
            case "FEED":
                if (pet.getHunger() + SCORE_INCREASE < MAXIMUM_VALUE) {
                pet.setHunger(pet.getHunger() + SCORE_INCREASE);}

                else {
                    pet.setHunger(MAXIMUM_VALUE);
                }
                break;

            case "WATER":
                if (pet.getThirst() + SCORE_INCREASE < MAXIMUM_VALUE) {
                    pet.setThirst(pet.getThirst() + SCORE_INCREASE);}

                else {
                    pet.setThirst(MAXIMUM_VALUE);
                }
                break;

            case "CLEAN":
                if (pet.getCleanliness() + SCORE_INCREASE < MAXIMUM_VALUE) {
                    pet.setCleanliness(pet.getCleanliness() + SCORE_INCREASE);}

                else {
                    pet.setCleanliness(MAXIMUM_VALUE);
                }
                break;

            case "PLAY":
                if (pet.getHappiness() + SCORE_INCREASE < MAXIMUM_VALUE) {
                    pet.setHappiness(pet.getHappiness() + SCORE_INCREASE);}

                else {
                    pet.setHappiness(MAXIMUM_VALUE);
                }
                break;
        }
        final ButtonsOutputData buttonsOutputData = new ButtonsOutputData(pet.getHunger(), pet.getThirst(), pet.getCleanliness(), pet.getHappiness());
        buttons_presenter.prepareSuccessView(buttonsOutputData);
    }
}


