package use_case.buttons;
import entities.Pet;
/**
 *
 */

public class buttons_interactor implements buttons_inputboundary {
    private final buttons_outputboundary buttons_presenter;
    private  DAO buttons_data_acess;

    public buttons_interactor(buttons_outputboundary buttons_outputboundary, DAO buttons_data_acess) {
        this.buttons_presenter = buttons_outputboundary;
        this.buttons_data_acess = buttons_data_acess;
    }

    @Override
    public void execute(buttons_inputData buttons_inputData) {
        Pet pet = buttons_data_acess.load();
        switch (buttons_inputData.getAction()) {
            case "FEED":
                if (pet.getHunger() + 20 < 100) {
                pet.setHunger(pet.getHunger() + 20);}

                else {
                    pet.setHunger(100);
                }
                buttons_OutputData buttons_outputData = new buttons_OutputData(pet.getHunger(), pet.getThirst(), pet.getCleanliness(), pet.getHappiness());
                buttons_presenter.prepareSuccessView(buttons_outputData);

            case "WATER":
                if (pet.getThirst() + 20 < 100) {
                    pet.setThirst(pet.getThirst() + 20);}

                else {
                    pet.setThirst(100);
                }
            case "CLEAN":
                if (pet.getCleanliness() + 20 < 100) {
                    pet.setCleanliness(pet.getCleanliness() + 20);}

                else {
                    pet.setCleanliness(100);
                }
            case "PLAY":
                if (pet.getHappiness() + 20 < 100) {
                    pet.setHappiness(pet.getHappiness() + 20);}

                else {
                    pet.setHappiness(100);
                }
        }
        final buttons_OutputData buttonsOutputData = new buttons_OutputData(pet.getHunger(), pet.getThirst(), pet.getCleanliness(), pet.getHappiness());
        buttons_presenter.prepareSuccessView(buttonsOutputData);
    }
}


