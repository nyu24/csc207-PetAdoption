package app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        AppBuilder appBuilder = new AppBuilder();
        JFrame application = appBuilder
                .addPetRoomView()
                .addHighScoreView()
                .addHighScoreUseCase()
                .addSelectAnimalView()
                .addSelectAnimalUseCase()
               .addSetParamView()
                .addSetParamUseCase()
                .addVetScoreView()
                .addbuttonsUseCase()
                .addPetRoomUseCase()
                .addVetUseCase()
                .addSaveGameView()
                .addSaveGameUseCase()
                .addLoadGameView()
                .addLoadGameUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
