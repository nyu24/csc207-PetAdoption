package app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        AppBuilder appBuilder = new AppBuilder();
        JFrame application = appBuilder
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
