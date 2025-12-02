package use_case.switch_view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test case for the switching view use case used in the title screen.
 */

public class SwitchViewInteractorTest {

    @Test
    void switchToSetParamViewTest() {
        SwitchViewOutputBoundary setParamPresenter = new SwitchViewOutputBoundary() {
            @Override
            public void switchToLoadGameView() {
                fail("This view switch is unexpected.");
            }

            @Override
            public void switchToSetParamView() {
                // This is expected.
            }

            @Override
            public void switchToHighScoreView() {
                fail("This view switch is unexpected.");
            }
        };

        SwitchViewInteractor interactor = new SwitchViewInteractor(setParamPresenter);
        interactor.switchToSetParamView();
    }

    @Test
    void switchToLoadGameView() {
        SwitchViewOutputBoundary loadGamePresenter = new SwitchViewOutputBoundary() {
            @Override
            public void switchToLoadGameView() {
                // This is expected
            }

            @Override
            public void switchToSetParamView() {
                fail("This view switch is unexpected.");
            }

            @Override
            public void switchToHighScoreView() {
                fail("This view switch is unexpected.");
            }
        };

        SwitchViewInteractor interactor = new SwitchViewInteractor(loadGamePresenter);
        interactor.switchToLoadGameView();
    }

    @Test
    void switchToHighScoreView() {
        SwitchViewOutputBoundary highScorePresenter = new SwitchViewOutputBoundary() {
            @Override
            public void switchToLoadGameView() {
                fail("This view switch is unexpected.");
            }

            @Override
            public void switchToSetParamView() {
                fail("This view switch is unexpected.");
            }

            @Override
            public void switchToHighScoreView() {
                // This is expected.
            }
        };

        SwitchViewInteractor interactor = new SwitchViewInteractor(highScorePresenter);
        interactor.switchToHighScoreView();
    }
}