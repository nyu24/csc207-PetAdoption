package app;

import javax.swing.*;

/**
 * Makes the game.
 */

public class Main {

    /**
     * Creates the game.
     * @param args arguments for main.
     */

    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addPetRoomView()
                .addVetScoreView()
                .addSetParamView()
                .addSelectAnimalView()
                .addHighScoreView()
                .addSaveGameView()
                .addLoadGameView()
                .addTitleView()
                .addHighScoreUseCase()
                .addSelectAnimalUseCase()
                .addSetParamUseCase()
                .addButtonsUseCase()
                .addPetRoomUseCase()
                .addVetUseCase()
                .addSaveGameUseCase()
                .addLoadGameUseCase()
                .addSwitchViewUseCase()
                .addHighScoreUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
