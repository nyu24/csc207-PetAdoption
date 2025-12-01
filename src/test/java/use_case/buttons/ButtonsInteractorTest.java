package use_case.buttons;

import entities.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ButtonsInteractorTest {
    // Test for making sure the values of the
    // pets attributes are updates when buttons are clicked
    private Pet testpet;
    private ButtonsInteractor buttonsInteractor;
    private ButtonsDataAcessObject mockButtonsDataAcessObject;
    private ButtonsOutputBoundary buttonsOutputboundary;

    @BeforeEach
    void setup() {
        testpet = new Pet(100,100,100,100);
        testpet.setHunger(0);
        testpet.setThirst(0);
        testpet.setCleanliness(0);
        testpet.setHappiness(0);
        mockButtonsDataAcessObject = new ButtonsDataAcessObject(testpet);
        buttonsOutputboundary = new TestPresenter();
        buttonsInteractor = new ButtonsInteractor(buttonsOutputboundary, mockButtonsDataAcessObject);
    }

    private void pressAllButtons() {
        ButtonsInputData buttonsInputData1 = new ButtonsInputData("FEED");
        ButtonsInputData buttonsInputData2 = new ButtonsInputData("WATER");
        ButtonsInputData buttonsInputData3 = new ButtonsInputData("CLEAN");
        ButtonsInputData buttonsInputData4 = new ButtonsInputData("PLAY");

        buttonsInteractor.execute(buttonsInputData1);
        buttonsInteractor.execute(buttonsInputData2);
        buttonsInteractor.execute(buttonsInputData3);
        buttonsInteractor.execute(buttonsInputData4);
    }


    @Test
    public void testNormalIncrease() {
        pressAllButtons();
        Assertions.assertEquals(20,testpet.getHunger());
        Assertions.assertEquals(20,testpet.getThirst());
        Assertions.assertEquals(20, testpet.getCleanliness());
        Assertions.assertEquals(20, testpet.getHappiness());

    }

    @Test
    public void testCapsAtMaximumValue() {
        testpet.setHunger(90);
        testpet.setThirst(90);
        testpet.setCleanliness(90);
        testpet.setHappiness(90);
        pressAllButtons();
        Assertions.assertEquals(100, testpet.getHappiness());
        Assertions.assertEquals(100,testpet.getThirst());
        Assertions.assertEquals(100, testpet.getCleanliness());
        Assertions.assertEquals(100, testpet.getHunger());

    }

}
