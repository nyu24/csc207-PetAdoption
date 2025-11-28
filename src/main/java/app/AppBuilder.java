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
import data_access.APIPetDataAccessObject;
import interface_adapter.select_animal.SelectAnimalViewModel;
import interface_adapter.set_parameters.SetParamController;
import interface_adapter.set_parameters.SetParamPresenter;
import interface_adapter.set_parameters.SetParamViewModel;
import use_case.set_parameters.SetParamInputBoundary;
import use_case.set_parameters.SetParamInteractor;
import use_case.set_parameters.SetParamOutputBoundary;
import view.SelectAnimalView;
import view.SetParamView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AppBuilder {
    //final variables
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

    final APIPetDataAccessObject apiPetDataAccessObject = new APIPetDataAccessObject();

    //setting up views
    private SelectAnimalViewModel selectAnimalViewModel; //UNFINISHED
    private SelectAnimalView selectAnimalView; //the entire JFrame/Panel UNFINISHED
    private SetParamViewModel setParamViewModel; //UNFINISHED
    private SetParamView setParamView; //the entire JFrame UNIFINSIHED

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
        final HighScoreInputBoundary highScoreInteractor = new HighScoreInteractor(fileHighScoreDataAccessObject, highScoreOutputBoundary);
        HighScoreController controller = new HighScoreController(highScoreInteractor);
        highScoreView.setHighScoreController(controller);

        return this;
    }
    //implementing the 2 views for API set params and select animal -----------------
    public AppBuilder addSelectAnimalView(){
        selectAnimalViewModel = new SelectAnimalViewModel();
        //selectAnimalView = new SelectAnimalView(selectAnimalViewModel);
        //cardPanel.add(selectAnimalView, selectAnimalView.getViewName());
        return this;
    }

    public AppBuilder addSetParamView(){
        setParamViewModel = new SetParamViewModel();
        setParamView = new SetParamView(setParamViewModel);
        cardPanel.add(setParamView, setParamView.getViewName());
        return this;
    }

    //implementing use cases -------------------
    public AppBuilder addSetParamUseCase(){
        final SetParamOutputBoundary setParamOutputBoundary = new SetParamPresenter(setParamViewModel,
                selectAnimalViewModel, viewManagerModel);
        final SetParamInputBoundary setParamInteractor = new SetParamInteractor(
                apiPetDataAccessObject, setParamOutputBoundary);

        SetParamController setParamController = new SetParamController(setParamInteractor);
        setParamView.setSetParamController(setParamController);
        return this;
    }


    //setting up the JFrame ---------------
    public JFrame build(){
        final JFrame application = new JFrame("Pet Game");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(highScoreView.getViewName());
        viewManagerModel.firePropertyChanged();
        viewManagerModel.setState(setParamView.getViewName());
//        viewManagerModel.firePropertyChanged(); // TODO: we need to make a proper way to change windows

        return application;
    }

}
