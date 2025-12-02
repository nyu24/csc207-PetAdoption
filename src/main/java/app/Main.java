package app;

import java.awt.Dimension;

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

        final int dimensionSize = 1080;
        application.setPreferredSize(new Dimension(dimensionSize, dimensionSize));

        application.pack();
        application.setVisible(true);
    }
}
