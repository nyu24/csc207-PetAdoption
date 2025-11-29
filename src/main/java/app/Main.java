package app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        AppBuilder appBuilder = new AppBuilder();
        JFrame application = appBuilder
                .addHighScoreView()
                .addHighScoreUseCase()
                .addSelectAnimalView()
                .addSelectAnimalUseCase()
                .addSetParamView()
                .addSetParamUseCase()
                .addVetScoreView()
                .addPetRoomView()
                .addbuttonsUseCase()
                .addPetRoomUseCase()
                .addVetUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
