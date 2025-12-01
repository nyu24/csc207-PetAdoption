package app;

import javax.swing.*;

public class Main {

    /**
     * The main program from the project that runs the app builder.
     * @param args default public static void main argument.
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
                .addbuttonsUseCase()
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
