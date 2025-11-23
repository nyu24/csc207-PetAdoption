package app;
import data_access.FileHighScoreDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.high_score.HighScoreController;
import interface_adapter.high_score.HighScorePresenter;
import interface_adapter.high_score.HighScoreViewModel;
import use_case.high_score.HighScoreInputBoundary;
import use_case.high_score.HighScoreInteractor;
import use_case.high_score.HighScoreOutputBoundary;
import view.HighScoreView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private HighScoreView highScoreView;
    private HighScoreViewModel highScoreViewModel;


    final FileHighScoreDataAccessObject fileHighScoreDataAccessObject;

    {
        try {
            fileHighScoreDataAccessObject = new FileHighScoreDataAccessObject("src/test/java/high_scores.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public AppBuilder(){
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addHighScoreView(){
        highScoreViewModel = new HighScoreViewModel();
        highScoreView = new HighScoreView(highScoreViewModel);
        cardPanel.add(highScoreView, highScoreView.getViewName());
        return this;
    }

    public AppBuilder addHighScoreUseCase(){
        final HighScoreOutputBoundary highScoreOutputBoundary = new HighScorePresenter(viewManagerModel, highScoreViewModel);
        final HighScoreInputBoundary highScoreInteractor = new HighScoreInteractor();
        HighScoreController controller = new HighScoreController(highScoreInteractor);
        highScoreView.setHighScoreController(controller);

        return this;
    }
    public JFrame build(){
        final JFrame application = new JFrame("Pet Game");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(highScoreView.getViewName());
        viewManagerModel.firePropertyChange("h");

        return application;
    }

}