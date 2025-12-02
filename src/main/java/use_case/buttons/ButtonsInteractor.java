
package use_case.buttons;

import entities.Pet;

/**
 * Buttons interactor.
 */
public class ButtonsInteractor implements ButtonsInputBoundary {
    private final int MAXIMUM_VALUE = 100;
    private final int SCORE_INCREASE = 20;
    private final ButtonsOutputBoundary buttonsPresenter;
    private ButtonsDataAccessObject buttonsDataAcess;

    public ButtonsInteractor(ButtonsOutputBoundary ButtonsOutputBoundary, ButtonsDataAccessObject buttonsDataAccess) {
        this.buttonsPresenter = ButtonsOutputBoundary;
        this.buttonsDataAcess = buttonsDataAccess;
    }

    @Override
    public void execute(ButtonsInputData ButtonsInputData) {
        final Pet pet = buttonsDataAcess.load();
        switch (ButtonsInputData.getAction()) {
            case "FEED":
                if (pet.getHunger() + SCORE_INCREASE < MAXIMUM_VALUE) {
                    pet.setHunger(pet.getHunger() + SCORE_INCREASE);
                }

                else {
                    pet.setHunger(MAXIMUM_VALUE);
                }
                break;

            case "WATER":
                if (pet.getThirst() + SCORE_INCREASE  < MAXIMUM_VALUE) {
                    pet.setThirst(pet.getThirst() + SCORE_INCREASE);
                }

                else {
                    pet.setThirst(MAXIMUM_VALUE);
                }
                break;

            case "CLEAN":
                if (pet.getCleanliness() + SCORE_INCREASE  < MAXIMUM_VALUE) {
                    pet.setCleanliness(pet.getCleanliness() + SCORE_INCREASE);
                }

                else {
                    pet.setCleanliness(MAXIMUM_VALUE);
                }
                break;

            case "PLAY":
                if (pet.getHappiness() + SCORE_INCREASE < MAXIMUM_VALUE) {
                    pet.setHappiness(pet.getHappiness() + SCORE_INCREASE);
                }

                else {
                    pet.setHappiness(MAXIMUM_VALUE);
                }
                break;

            default:
                System.out.println("invalid input");
        }
        final ButtonsOutputData buttonsOutputData = new ButtonsOutputData(pet.getHunger(),
                pet.getThirst(), pet.getCleanliness(), pet.getHappiness());
        buttonsPresenter.prepareSuccessView(buttonsOutputData);
    }
}