package use_case.vet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import entities.Vet;
import use_case.Vet.VetOutputBoundary;
import use_case.Vet.VetUseCaseInteractor;
import use_case.Vet.VetInputData;
import use_case.Vet.VetOutputData;

import java.util.HashMap;
import java.util.Map;

public class VetTest {

    private VetUseCaseInteractor vetUseCaseInteractor;
    private Vet vet;
    private TestVetPresenter testPresenter;
    private VetInputData mockInputData;

    private static class TestVetPresenter implements VetOutputBoundary {
        private VetOutputData lastPresentedData;
        private int prepareSuccessViewCount = 0;
        private int switchToScoreViewCount = 0;

        @Override
        public void prepareSuccessView(VetOutputData response) {
            this.lastPresentedData = response;
            this.prepareSuccessViewCount++;
        }

        @Override
        public void switchToScoreView(VetOutputData vetOutputData) {
            this.lastPresentedData = vetOutputData;
            this.switchToScoreViewCount++;
        }

        public VetOutputData getLastPresentedData() {
            return lastPresentedData;
        }

        public int getPrepareSuccessViewCount() {
            return prepareSuccessViewCount;
        }

        public int getSwitchToScoreViewCount() {
            return switchToScoreViewCount;
        }
    }


    @Before
    public void setUp() {
        testPresenter = new TestVetPresenter();
        Map<String, Integer> map = new HashMap<>();
        map.put("health", 80);
        mockInputData = new VetInputData(map, 90);
        vet = new Vet(80, 30);
        vetUseCaseInteractor = new VetUseCaseInteractor(vet, testPresenter);
    }

    @Test
    public void testVetInitialization() {
        assertNotNull(vetUseCaseInteractor);
        assertNotNull(vet);
    }

    @Test
    public void testPresenterIsCalled() {
        vetUseCaseInteractor.execute(mockInputData);
        assertEquals(1, testPresenter.getPrepareSuccessViewCount());
    }

    @Test
    public void testPresenterReceivesCorrectData() {
        vetUseCaseInteractor.execute(mockInputData);
        VetOutputData capturedData = testPresenter.getLastPresentedData();
        assertNotNull(capturedData);
    }

    @Test
    public void testSwitchToScoreView() {
        VetOutputData outputData = new VetOutputData(null, 90);
        vetUseCaseInteractor.switchToScoreView(outputData);
        assertEquals(1, testPresenter.getSwitchToScoreViewCount());
        assertEquals(outputData, testPresenter.getLastPresentedData());
    }


}
