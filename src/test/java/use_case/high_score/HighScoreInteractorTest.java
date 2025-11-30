package use_case.high_score;

import data_access.FileHighScoreDataAccessObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HighScoreInteractorTest {

    @Test
    void successTest() {

        // test if file contents are successfully read

        HighScoreInputData inputData = new HighScoreInputData(1144, false);
        HighScoreDataAccessInterface highScoreSaveFile = new FileHighScoreDataAccessObject("src/test/java/use_case/high_score/testing_files/high_scores_test_read.csv");


        HighScoreOutputBoundary successPresenter = new HighScoreOutputBoundary() {
            @Override
            public void prepareSuccessView(HighScoreOutputData outputData) {
                List<Integer> highScoreList = outputData.getHighScoreList().getHighScores();

                assertEquals(1144, highScoreList.get(0));
                assertEquals(10, highScoreList.get(1));
                assertEquals(9, highScoreList.get(2));
                assertEquals(3, highScoreList.get(3));
                assertEquals(0, highScoreList.get(4));
                assertEquals(-1, highScoreList.get(5));
            }

            public void prepareFailView(String errorMessage) {}
            public void switchToTitleView() {}
        };

        HighScoreInputBoundary interactor = new HighScoreInteractor(highScoreSaveFile, successPresenter);
        interactor.execute(inputData);


    }
    @Test
    void saveScoreTest() {

        // test if file contents are successfully written

        // reset files for testing
        new File("src/test/java/use_case/high_score/testing_files/high_scores_test_write.csv").delete();
        try {
            new File("src/test/java/use_case/high_score/testing_files/high_scores_test_write.csv").createNewFile();
        }
        catch (IOException e){

        }
        HighScoreInputData inputData = new HighScoreInputData(1144, true);
        HighScoreDataAccessInterface highScoreSaveFile = new FileHighScoreDataAccessObject("src/test/java/use_case/high_score/testing_files/high_scores_test_write.csv");


        HighScoreOutputBoundary successPresenter = new HighScoreOutputBoundary() {
            @Override
            public void prepareSuccessView(HighScoreOutputData outputData) {
                List<Integer> highScoreList = outputData.getHighScoreList().getHighScores();
                assertEquals(1144, highScoreList.get(0));
            }

            public void prepareFailView(String errorMessage) {}
            public void switchToTitleView() {}

        };

        HighScoreInputBoundary interactor = new HighScoreInteractor(highScoreSaveFile, successPresenter);
        interactor.execute(inputData);


    }

    @Test
    void createFileTest() {

        // test if file contents are successfully written

        // reset files for testing
        new File("src/test/java/use_case/high_score/testing_files/high_scores_test_create.csv").delete();
        HighScoreInputData inputData = new HighScoreInputData(1144, true);
        HighScoreDataAccessInterface highScoreSaveFile =
                new FileHighScoreDataAccessObject(
                        "src/test/java/use_case/high_score/testing_files/high_scores_test_create.csv");


        HighScoreOutputBoundary successPresenter = new HighScoreOutputBoundary() {
            @Override
            public void prepareSuccessView(HighScoreOutputData outputData) {
                List<Integer> highScoreList = outputData.getHighScoreList().getHighScores();
                assertEquals(1144, highScoreList.get(0));

                assertEquals(true,
                        new File("src/test/java/use_case/high_score/testing_files/high_scores_test_create.csv").exists());
            }

            public void prepareFailView(String errorMessage) {}
            public void switchToTitleView() {}

        };

        HighScoreInputBoundary interactor = new HighScoreInteractor(highScoreSaveFile, successPresenter);
        interactor.execute(inputData);


    }

}
