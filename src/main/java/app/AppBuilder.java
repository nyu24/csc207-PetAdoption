package app;
import interface_adapter.ViewManagerModel;
import interface_adapter.select_animal.SelectAnimalViewModel;
import interface_adapter.set_parameters.SetParamViewModel;
import view.SelectAnimalView;
import view.SetParamView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    //final variables
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    //setting up views
    private SelectAnimalViewModel selectAnimalViewModel; //UNFINISHED
    private SelectAnimalView selectAnimalView; //the entire JFrame/Panel UNFINISHED
    private SetParamViewModel setParamViewModel; //UNFINISHED
    private SetParamView setParamView; //the entire JFrame UNIFINSIHED

    public AppBuilder(){
        cardPanel.setLayout(cardLayout);
    }

    //implementing the 2 views -----------------
    public AppBuilder addSelectAnimalView(){
        selectAnimalViewModel = new SelectAnimalViewModel();
        selectAnimalView = new SelectAnimalView(selectAnimalViewModel);
        cardPanel.add(selectAnimalView, selectAnimalView.getViewName());
        return this;
    }

    public AppBuilder addSetParamView(){
        setParamViewModel = new SetParamViewModel();
        setParamView = new SetParamView(setParamViewModel);
        cardPanel.add(setParamView, setParamView.getViewName());
        return this;
    }

    //implementing use cases -------------------


    //setting up the JFrame ---------------
    public JFrame build(){
        final JFrame application = new JFrame("User Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(setParamView.getViewName());
        viewManagerModel.firePropertyChange();

        return application;
    }

}
