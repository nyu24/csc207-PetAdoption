package use_case.buttons;

import entities.Pet;

/**
 * Interactor for the buttons use case.
 */
public class ButtonsInteractor implements ButtonsInputBoundary {
    private final int maximumvalue = 100;
    private final int scoreincrease = 20;
    private final ButtonsOutputBoundary buttonsPresenter;
    private ButtonsDataAccessObject buttonsDataAccess;

    public ButtonsInteractor(ButtonsOutputBoundary ButtonsOutputBoundary, ButtonsDataAccessObject buttonsDataAccess) {
        this.buttonsPresenter = ButtonsOutputBoundary;
        this.buttonsDataAccess = buttonsDataAccess;
    }

    @Override
    public void execute(ButtonsInputData ButtonsInputData) {
        final Pet pet = buttonsDataAccess.load();
        switch (ButtonsInputData.getAction()) {
            case "FEED":
                if (pet.getHunger() + scoreincrease < maximumvalue) {
                    pet.setHunger(pet.getHunger() + scoreincrease);
                }

                else {
                    pet.setHunger(maximumvalue);
                }
                break;

            case "WATER":
                if (pet.getThirst() + scoreincrease < maximumvalue) {
                    pet.setThirst(pet.getThirst() + scoreincrease);
                }

                else {
                    pet.setThirst(maximumvalue);
                }
                break;

            case "CLEAN":
                if (pet.getCleanliness() + scoreincrease < maximumvalue) {
                    pet.setCleanliness(pet.getCleanliness() + scoreincrease);
                }

                else {
                    pet.setCleanliness(maximumvalue);
                }
                break;

            case "PLAY":
                if (pet.getHappiness() + scoreincrease < maximumvalue) {
                    pet.setHappiness(pet.getHappiness() + scoreincrease);
                }

                else {
                    pet.setHappiness(maximumvalue);
                }
                break;
            default:
                System.out.println("invalid input");
        }
        final ButtonsOutputData buttonsOutputData =
                new ButtonsOutputData(pet.getHunger(), pet.getThirst(), pet.getCleanliness(), pet.getHappiness());
        buttonsPresenter.prepareSuccessView(buttonsOutputData);
    }
}
