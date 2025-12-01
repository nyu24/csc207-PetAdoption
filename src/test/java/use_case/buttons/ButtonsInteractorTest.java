package use_case.buttons;

import entities.Pet;
import use_case.buttons.TestPresenter;
import interface_adapter.buttons.buttons_presenter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import use_case.buttons.buttons_outputboundary;
import use_case.buttons.buttons_interactor;




public class ButtonsInteractorTest {
    // Test for making sure the values of the
    // pets attributes are updates when buttons are clicked
    private Pet testpet;
    private buttons_interactor buttonsInteractor;
    private DAO mockDAO;
    private buttons_outputboundary mockpresenter;
    private buttons_outputboundary buttonsOutputboundary;

    @BeforeEach
    void setup() {
        testpet = new Pet(100,100,100,100);
        testpet.setHunger(0);
        testpet.setThirst(0);
        testpet.setCleanliness(0);
        testpet.setHappiness(0);
        mockDAO = new DAO(testpet);
        buttonsOutputboundary = new TestPresenter();
        buttonsInteractor = new buttons_interactor(buttonsOutputboundary, mockDAO);
    }

    private void pressAllButtons() {
        buttons_inputData buttonsInputData1 = new buttons_inputData("FEED");
        buttons_inputData buttonsInputData2 = new buttons_inputData("WATER");
        buttons_inputData buttonsInputData3 = new buttons_inputData("CLEAN");
        buttons_inputData buttonsInputData4 = new buttons_inputData("PLAY");

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
