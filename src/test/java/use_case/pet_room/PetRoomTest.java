package use_case.pet_room;

import entities.Pet;
import entities.Room;
import entities.Vet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.PetRoom.*;

import static org.junit.jupiter.api.Assertions.*;

public class PetRoomTest {
    private PetRoomInteractor interactor;
    private FakeRoom fakeRoom;
    private FakePresenter fakePresenter;
    private FakeVet fakeVet;

    @BeforeEach
    public void setUp() {
        fakeRoom = new FakeRoom();
        fakePresenter = new FakePresenter();
        fakeVet = new FakeVet(50, 10);
        interactor = new PetRoomInteractor(fakeRoom, fakePresenter, fakeVet);
    }

    @Test
    public void testExecuteFeedAction() {
        PetRoomInputData inputData = new PetRoomInputData("feed", 0, null);
        interactor.execute(inputData);
        assertTrue(fakeRoom.foodActionCalled);
        assertNotNull(fakePresenter.lastOutputData);
    }

    @Test
    public void testExecuteWaterAction() {
        PetRoomInputData inputData = new PetRoomInputData("water", 0, null);
        interactor.execute(inputData);
        assertTrue(fakeRoom.waterActionCalled);
        assertNotNull(fakePresenter.lastOutputData);
    }

    @Test
    public void testExecuteCleanAction() {
        PetRoomInputData inputData = new PetRoomInputData("clean", 0, null);
        interactor.execute(inputData);
        assertTrue(fakeRoom.cleanActionCalled);
        assertNotNull(fakePresenter.lastOutputData);
    }

    @Test
    public void testExecutePlayAction() {
        PetRoomInputData inputData = new PetRoomInputData("play", 0, null);
        interactor.execute(inputData);
        assertTrue(fakeRoom.happinessActionCalled);
        assertNotNull(fakePresenter.lastOutputData);
    }

    @Test
    public void testExecuteTickActionWithValidStats() {
        fakeRoom.setFood(50);
        fakeRoom.setWater(50);
        fakeRoom.setCleanliness(50);
        fakeRoom.setHappiness(50);

        PetRoomInputData inputData = new PetRoomInputData("tick", 5, null);
        interactor.execute(inputData);

        assertTrue(fakeRoom.tickCalled);
        assertEquals(6, fakePresenter.lastOutputData.getScore());
    }

    @Test
    public void testExecuteTickActionWithInvalidStats() {
        fakeRoom.setFood(10);
        fakeRoom.setWater(10);
        fakeRoom.setCleanliness(10);
        fakeRoom.setHappiness(10);

        PetRoomInputData inputData = new PetRoomInputData("tick", 5, null);
        interactor.execute(inputData);

        assertTrue(fakeRoom.tickCalled);
        assertEquals(5, fakePresenter.lastOutputData.getScore());
    }

    @Test
    public void testExecuteInvalidAction() {
        PetRoomInputData inputData = new PetRoomInputData("invalid", 0, null);
        interactor.execute(inputData);
        assertEquals("Invalid action.", fakePresenter.failMessage);
        assertNull(fakePresenter.lastOutputData);
    }


    @Test
    public void testExecuteWithPetType() {
        PetRoomInputData inputData = new PetRoomInputData("feed", 0, "Dog");
        interactor.execute(inputData);
        assertEquals("Dog", fakeRoom.getPetType());
        assertTrue(fakeRoom.foodActionCalled);
    }

    @Test
    public void testExecuteWithEmptyPetType() {
        fakeRoom.setPetType("Cat");
        PetRoomInputData inputData = new PetRoomInputData("feed", 0, "");
        interactor.execute(inputData);
        assertEquals("Cat", fakeRoom.getPetType());
    }

    @Test
    public void testSetRoomParameters() {
        Pet testPet = new Pet(100, 100, 100, 100);
        interactor.setRoomParameters(60, 70, 80, 90, testPet);

        assertEquals(60, fakeRoom.getFood());
        assertEquals(70, fakeRoom.getWater());
        assertEquals(80, fakeRoom.getCleanliness());
        assertEquals(90, fakeRoom.getHappiness());
        assertEquals(testPet, fakeRoom.getCurrPet());
    }

    @Test
    public void testSwitchToSaveGameView() {
        interactor.switchToSaveGameView();
        assertTrue(fakePresenter.switchToSaveGameViewCalled);
    }

    @Test
    public void testExecuteWithNullAction() {
        PetRoomInputData inputData = new PetRoomInputData(null, 0, null);
        interactor.execute(inputData);
        assertNotNull(fakePresenter.lastOutputData);
    }

    @Test
    public void testExecuteInvalidActionDoesNotCallUpdateValues() {
        PetRoomInputData inputData = new PetRoomInputData("invalid", 0, null);
        interactor.execute(inputData);
        assertEquals("Invalid action.", fakePresenter.failMessage);
        assertNull(fakePresenter.lastOutputData);
    }

    @Test
    public void testExecuteWithNullPetType() {
        fakeRoom.setPetType("Dog");
        PetRoomInputData inputData = new PetRoomInputData("feed", 0, null);
        interactor.execute(inputData);
        assertEquals("Dog", fakeRoom.getPetType());
        assertTrue(fakeRoom.foodActionCalled);
    }

    @Test
    public void testExecuteFeedActionUpdatesRoomType() {
        fakeRoom.setPetType("Dog");
        PetRoomInputData inputData = new PetRoomInputData("feed", 0, "Dog");
        interactor.execute(inputData);
        assertEquals("Dog_room_food.jpg", fakeRoom.getRoomType());
    }

    @Test
    public void testExecuteWaterActionUpdatesRoomType() {
        fakeRoom.setPetType("Cat");
        PetRoomInputData inputData = new PetRoomInputData("water", 0, "Cat");
        interactor.execute(inputData);
        assertEquals("Cat_room_water.jpg", fakeRoom.getRoomType());
    }

    @Test
    public void testExecuteCleanActionUpdatesRoomType() {
        fakeRoom.setPetType("Bird");
        PetRoomInputData inputData = new PetRoomInputData("clean", 0, "Bird");
        interactor.execute(inputData);
        assertEquals("Bird_room_clean.jpg", fakeRoom.getRoomType());
    }

    @Test
    public void testExecutePlayActionUpdatesRoomType() {
        fakeRoom.setPetType("Fish");
        PetRoomInputData inputData = new PetRoomInputData("play", 0, "Fish");
        interactor.execute(inputData);
        assertEquals("Fish_room_happy.jpg", fakeRoom.getRoomType());
    }

    @Test
    public void testExecuteTickActionTriggersHappinessDrop() {
        fakeRoom.setFood(5);
        fakeRoom.setWater(5);
        fakeRoom.setCleanliness(5);
        fakeRoom.setHappiness(50);

        PetRoomInputData inputData = new PetRoomInputData("tick", 5, null);
        interactor.execute(inputData);

        assertTrue(fakeRoom.tickCalled);
        assertEquals(48, fakePresenter.lastOutputData.getFinalHappiness());
    }

    @Test
    public void testExecuteFeedActionIncreasesFood() {
        fakeRoom.setFood(30);
        fakeRoom.setHappiness(30);
        PetRoomInputData inputData = new PetRoomInputData("feed", 0, "Dog");
        interactor.execute(inputData);
        assertTrue(fakePresenter.lastOutputData.getFinalFood() > 30);
        assertTrue(fakePresenter.lastOutputData.getFinalHappiness() > 30);
    }

    @Test
    public void testExecuteWaterActionIncreasesWater() {
        fakeRoom.setWater(30);
        fakeRoom.setHappiness(30);
        PetRoomInputData inputData = new PetRoomInputData("water", 0, "Dog");
        interactor.execute(inputData);
        assertTrue(fakePresenter.lastOutputData.getFinalWater() > 30);
        assertTrue(fakePresenter.lastOutputData.getFinalHappiness() > 30);
    }

    @Test
    public void testExecuteCleanActionIncreasesCleanliness() {
        fakeRoom.setCleanliness(30);
        fakeRoom.setHappiness(30);
        PetRoomInputData inputData = new PetRoomInputData("clean", 0, "Dog");
        interactor.execute(inputData);
        assertTrue(fakePresenter.lastOutputData.getFinalCleanliness() > 30);
        assertTrue(fakePresenter.lastOutputData.getFinalHappiness() > 30);
    }

    @Test
    public void testExecutePlayActionIncreasesHappiness() {
        fakeRoom.setHappiness(30);
        PetRoomInputData inputData = new PetRoomInputData("play", 0, "Dog");
        interactor.execute(inputData);
        assertTrue(fakePresenter.lastOutputData.getFinalHappiness() > 30);
    }

    @Test
    public void testExecuteTickActionDecreasesAllStats() {
        fakeRoom.setFood(50);
        fakeRoom.setWater(50);
        fakeRoom.setCleanliness(50);
        fakeRoom.setHappiness(50);
        PetRoomInputData inputData = new PetRoomInputData("tick", 5, null);
        interactor.execute(inputData);
        assertEquals(49, fakePresenter.lastOutputData.getFinalFood());
        assertEquals(49, fakePresenter.lastOutputData.getFinalWater());
        assertEquals(49, fakePresenter.lastOutputData.getFinalCleanliness());
    }

    @Test
    public void testExecuteTickActionWithPartialValidStats() {
        // This test covers the short-circuit path in the 'tick' case's if statement.
        // food and water are in range, but cleanliness is not.
        fakeRoom.setFood(50);      // In range
        fakeRoom.setWater(50);     // In range
        fakeRoom.setCleanliness(10); // Out of range
        fakeRoom.setHappiness(50);

        PetRoomInputData inputData = new PetRoomInputData("tick", 5, null);
        interactor.execute(inputData);

        assertTrue(fakeRoom.tickCalled);
        // Score should not be incremented because not all stats are in range.
        assertEquals(5, fakePresenter.lastOutputData.getScore());
    }

    @Test
    public void testExecuteTickActionWithPartialValidStatsSecondCondition() {
        // This test covers the short-circuit path in the 'tick' case's if statement.
        // food is in range, but water is not.
        fakeRoom.setFood(50);  // In range for vet (50 +/- 10)
        fakeRoom.setWater(10);  // Out of range for vet
        fakeRoom.setCleanliness(50);
        fakeRoom.setHappiness(50);

        PetRoomInputData inputData = new PetRoomInputData("tick", 5, null);
        interactor.execute(inputData);

        assertTrue(fakeRoom.tickCalled);
        // Score should not be incremented because not all stats are in range.
        assertEquals(5, fakePresenter.lastOutputData.getScore());
    }


    @Test
    public void testExecuteTickActionTriggersSingleHappinessDrop() {
        // This test covers the 'else' branch in Room.tick()
        // Food is high, so the special happiness drop condition is not met.
        fakeRoom.setFood(50);
        fakeRoom.setWater(5);
        fakeRoom.setCleanliness(5);
        fakeRoom.setHappiness(50);

        PetRoomInputData inputData = new PetRoomInputData("tick", 5, null);
        interactor.execute(inputData);

        assertTrue(fakeRoom.tickCalled);
        // Happiness should drop by the standard 1, not 2.
        assertEquals(49, fakePresenter.lastOutputData.getFinalHappiness());
    }
    @Test
    public void testTickShortCircuitHappinessDrop() {
        // This test covers the short-circuit path in Room.tick()
        // Food is low, but Water is not, so the 'else' branch is taken.
        fakeRoom.setFood(5);
        fakeRoom.setWater(50);
        fakeRoom.setCleanliness(5);
        fakeRoom.setHappiness(50);

        PetRoomInputData inputData = new PetRoomInputData("tick", 5, null);
        interactor.execute(inputData);

        assertTrue(fakeRoom.tickCalled);
        // Happiness should drop by the standard 1, not 2.
        assertEquals(49, fakePresenter.lastOutputData.getFinalHappiness());
    }
    @Test
    public void testTickShortCircuitOnCleanliness() {
        // This test covers the short-circuit path in Room.tick() where
        // food and water are low, but cleanliness is not.
        fakeRoom.setFood(5);      // Low
        fakeRoom.setWater(5);     // Low
        fakeRoom.setCleanliness(50); // Not low
        fakeRoom.setHappiness(50);

        PetRoomInputData inputData = new PetRoomInputData("tick", 5, null);
        interactor.execute(inputData);

        assertTrue(fakeRoom.tickCalled);
        // Happiness should drop by the standard 1, not 2, because the 'else' branch is taken.
        assertEquals(49, fakePresenter.lastOutputData.getFinalHappiness());
    }

    @Test
    public void testTickShortCircuitOnHappiness() {
        // This test covers the short-circuit path in the 'tick' case's if statement
        // where the first three conditions pass but the last one (happiness) fails.
        fakeRoom.setFood(50);        // In range
        fakeRoom.setWater(50);       // In range
        fakeRoom.setCleanliness(50); // In range
        fakeRoom.setHappiness(10);   // Out of range

        PetRoomInputData inputData = new PetRoomInputData("tick", 5, null);
        interactor.execute(inputData);

        assertTrue(fakeRoom.tickCalled);
        // Score should not be incremented because happiness is out of range.
        assertEquals(5, fakePresenter.lastOutputData.getScore());
    }

    // Test Doubles
    private static class FakeRoom extends Room {
        boolean foodActionCalled = false;
        boolean waterActionCalled = false;
        boolean cleanActionCalled = false;
        boolean happinessActionCalled = false;
        boolean tickCalled = false;

        @Override
        public void applyFoodAction() {
            foodActionCalled = true;
            super.applyFoodAction();
        }

        @Override
        public void applyWaterAction() {
            waterActionCalled = true;
            super.applyWaterAction();
        }

        @Override
        public void applyCleanlinessAction() {
            cleanActionCalled = true;
            super.applyCleanlinessAction();
        }

        @Override
        public void applyHappinessAction() {
            happinessActionCalled = true;
            super.applyHappinessAction();
        }

        @Override
        public void tick() {
            tickCalled = true;
            super.tick();
        }
    }

    private static class FakePresenter implements PetRoomOutputBoundary {
        PetRoomOutputData lastOutputData;
        String failMessage;
        boolean switchToSaveGameViewCalled = false;

        @Override
        public void updateValues(PetRoomOutputData petRoomOutputData) {
            this.lastOutputData = petRoomOutputData;
        }

        @Override
        public void prepareFailView(String message) {
            this.failMessage = message;
        }

        @Override
        public void switchToSaveGameView() {
            switchToSaveGameViewCalled = true;
        }
    }

    private static class FakeVet extends Vet {
        public FakeVet(int passLevel, int errorMargin) {
            super(passLevel, errorMargin);
        }
    }
}
