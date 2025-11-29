package use_case.buttons;
import entities.Pet;

/**
 *
 */
public class buttons_interactor implements buttons_inputboundary {
    private double MAXIMUM_VALUE = 100;
    private double SCORE_INCREASE = 20;
    private final buttons_outputboundary buttons_presenter;
    private DAO buttons_data_acess;

    public buttons_interactor(buttons_outputboundary buttons_outputboundary, DAO buttons_data_acess) {
        this.buttons_presenter = buttons_outputboundary;
        this.buttons_data_acess = buttons_data_acess;
    }

    @Override
    public void execute(buttons_inputData buttons_inputData) {
        Pet pet = buttons_data_acess.load();
        switch (buttons_inputData.getAction()) {
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
        final buttons_OutputData buttonsOutputData = new buttons_OutputData(pet.getHunger(), pet.getThirst(), pet.getCleanliness(), pet.getHappiness());
        buttons_presenter.prepareSuccessView(buttonsOutputData);
    }
}


