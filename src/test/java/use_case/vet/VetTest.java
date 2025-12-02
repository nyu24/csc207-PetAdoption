package use_case.vet;

import entities.Pet;
import entities.Vet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.Vet.VetInputData;
import use_case.Vet.VetOutputData;
import use_case.Vet.VetUseCaseInteractor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VetTest {
    private VetUseCaseInteractor vetUseCaseInteractor;
    private TestVetPresenter testPresenter;
    private Vet vet;

    @BeforeEach
    public void setUp() {
        vet = new Vet(50, 10);
        testPresenter = new TestVetPresenter();
        vetUseCaseInteractor = new VetUseCaseInteractor(vet, testPresenter);
    }

    @Test
    public void testExecuteWithValidInput() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("Hunger", 50);
        stats.put("Thirst", 50);
        Pet pet = new Pet(100, 100, 100, 100);
        pet.setName("Buddy");

        VetInputData inputData = new VetInputData(stats, 100, pet);
        vetUseCaseInteractor.execute(inputData);

        assertNotNull(testPresenter.lastOutputData);
        assertEquals(100, testPresenter.lastOutputData.getScore());
    }

    @Test
    public void testExecuteChecksRequirements() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("Hunger", 50);
        stats.put("Thirst", 60);
        Pet pet = new Pet(100, 100, 100, 100);

        VetInputData inputData = new VetInputData(stats, 100, pet);
        vetUseCaseInteractor.execute(inputData);

        List<List<String>> requirements = testPresenter.lastOutputData.getRequirements();
        assertNotNull(requirements);
        assertEquals(2, requirements.size());
    }

    @Test
    public void testExecuteChecksPassedRequirements() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("Hunger", 50);
        Pet pet = new Pet(100, 100, 100, 100);

        VetInputData inputData = new VetInputData(stats, 100, pet);
        vetUseCaseInteractor.execute(inputData);

        List<List<String>> requirements = testPresenter.lastOutputData.getRequirements();
        assertEquals("PASSED", requirements.get(0).get(1));
    }

    @Test
    public void testExecuteChecksFailedRequirements() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("Hunger", 100);
        Pet pet = new Pet(100, 100, 100, 100);

        VetInputData inputData = new VetInputData(stats, 100, pet);
        vetUseCaseInteractor.execute(inputData);

        List<List<String>> requirements = testPresenter.lastOutputData.getRequirements();
        assertEquals("FAILED", requirements.get(0).get(1));
    }

    @Test
    public void testExecutePassesCorrectScore() {
        Map<String, Integer> stats = new HashMap<>();
        Pet pet = new Pet(100, 100, 100, 100);

        VetInputData inputData = new VetInputData(stats, 250, pet);
        vetUseCaseInteractor.execute(inputData);

        assertEquals(250, testPresenter.lastOutputData.getScore());
    }

    @Test
    public void testExecuteSetsPetInOutputData() {
        Map<String, Integer> stats = new HashMap<>();
        Pet pet = new Pet(100, 100, 100, 100);
        pet.setName("Fluffy");

        VetInputData inputData = new VetInputData(stats, 100, pet);
        vetUseCaseInteractor.execute(inputData);

        assertEquals(pet, testPresenter.lastOutputData.getCurrPet());
        assertEquals("Fluffy", testPresenter.lastOutputData.getCurrPet().getName());
    }

    @Test
    public void testExecuteWithEmptyStats() {
        Map<String, Integer> stats = new HashMap<>();
        Pet pet = new Pet(100, 100, 100, 100);

        VetInputData inputData = new VetInputData(stats, 0, pet);
        vetUseCaseInteractor.execute(inputData);

        assertNotNull(testPresenter.lastOutputData);
        assertEquals(0, testPresenter.lastOutputData.getScore());
    }

    @Test
    public void testExecuteWithMultipleStats() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("Hunger", 45);
        stats.put("Thirst", 55);
        stats.put("Cleanliness", 50);
        stats.put("Happiness", 48);
        Pet pet = new Pet(100, 100, 100, 100);

        VetInputData inputData = new VetInputData(stats, 100, pet);
        vetUseCaseInteractor.execute(inputData);

        List<List<String>> requirements = testPresenter.lastOutputData.getRequirements();
        assertEquals(4, requirements.size());
    }

    @Test
    public void testSwitchToScoreView() {
        VetOutputData outputData = new VetOutputData(null, 200);
        Pet pet = new Pet(100, 100, 100, 100);
        outputData.setPet(pet);

        vetUseCaseInteractor.switchToScoreView(outputData);

        assertNotNull(testPresenter.lastSwitchedOutputData);
        assertEquals(200, testPresenter.lastSwitchedOutputData.getScore());
    }

    @Test
    public void testSwitchToScoreViewPassesScore() {
        VetOutputData outputData = new VetOutputData(null, 300);

        vetUseCaseInteractor.switchToScoreView(outputData);

        assertEquals(300, testPresenter.lastSwitchedOutputData.getScore());
    }

    @Test
    public void testSwitchToScoreViewWithPet() {
        Pet pet = new Pet(100, 100, 100, 100);
        pet.setName("Buddy");
        VetOutputData outputData = new VetOutputData(null, 150);
        outputData.setPet(pet);

        vetUseCaseInteractor.switchToScoreView(outputData);

        assertEquals(pet, testPresenter.lastSwitchedOutputData.getCurrPet());
    }

    private static class TestVetPresenter implements use_case.Vet.VetOutputBoundary {
        VetOutputData lastOutputData;
        VetOutputData lastSwitchedOutputData;

        @Override
        public void prepareSuccessView(VetOutputData vetOutputData) {
            this.lastOutputData = vetOutputData;
        }

        @Override
        public void switchToScoreView(VetOutputData vetOutputData) {
            this.lastSwitchedOutputData = vetOutputData;
        }
    }
}
