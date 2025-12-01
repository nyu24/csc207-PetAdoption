package app;

import data_access.FileHighScoreDataAccessObject;
import data_access.FileSaveDataAccessObject;
import entities.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.buttons.ButtonsPresenter;
import interface_adapter.high_score.HighScoreController;
import interface_adapter.high_score.HighScorePresenter;
import interface_adapter.high_score.HighScoreViewModel;
import interface_adapter.load_game.LoadGameController;
import interface_adapter.load_game.LoadGamePresenter;
import interface_adapter.load_game.LoadGameViewModel;
import interface_adapter.save_game.SaveGameController;
import interface_adapter.save_game.SaveGamePresenter;
import interface_adapter.save_game.SaveGameViewModel;
import interface_adapter.select_animal.SelectAnimalController;
import interface_adapter.select_animal.SelectAnimalPresenter;
import interface_adapter.title.SwitchViewController;
import interface_adapter.title.SwitchViewPresenter;
import interface_adapter.title.TitleViewModel;
import use_case.load_game.LoadGameInputBoundary;
import use_case.load_game.LoadGameInteractor;
import use_case.load_game.LoadGameOutputBoundary;
import use_case.select_animal.SelectAnimalInputBoundary;
import use_case.select_animal.SelectAnimalInteractor;
import use_case.select_animal.SelectAnimalOutputBoundary;
import use_case.switch_view.SwitchViewInputBoundary;
import use_case.switch_view.SwitchViewInteractor;
import use_case.switch_view.SwitchViewOutputBoundary;
import view.HighScoreView;
import interface_adapter.vet_score.VetScoreController;
import interface_adapter.vet_score.VetScorePresenter;
import interface_adapter.vet_score.VetScoreViewModel;
import use_case.PetRoom.PetRoomInputBoundary;
import use_case.Vet.VetInputBoundary;
import use_case.Vet.VetOutputBoundary;
import use_case.Vet.VetUseCaseInteractor;
import use_case.buttons.DAO;
import use_case.high_score.HighScoreInputBoundary;
import use_case.high_score.HighScoreInteractor;
import use_case.high_score.HighScoreOutputBoundary;
import use_case.save_game.SaveGameInputBoundary;
import use_case.save_game.SaveGameInteractor;
import use_case.save_game.SaveGameOutputBoundary;
import view.*;
import data_access.APIPetDataAccessObject;
import interface_adapter.select_animal.SelectAnimalViewModel;
import interface_adapter.set_parameters.SetParamController;
import interface_adapter.set_parameters.SetParamPresenter;
import interface_adapter.set_parameters.SetParamViewModel;
import use_case.set_parameters.SetParamInputBoundary;
import use_case.set_parameters.SetParamInteractor;
import use_case.set_parameters.SetParamOutputBoundary;

import interface_adapter.buttons.ButtonsViewModel;
import interface_adapter.buttons.ButtonsController;
import use_case.buttons.ButtonsInputBoundary;
import use_case.buttons.ButtonsInteractor;
import use_case.buttons.ButtonsOutputBoundary;

import interface_adapter.PetRoom.PetRoomViewModel;
import interface_adapter.PetRoom.PetRoomController;
import interface_adapter.PetRoom.PetRoomPresenter;
import use_case.PetRoom.PetRoomOutputBoundary;
import use_case.PetRoom.PetRoomInteractor;
import view.PetRoomView;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    //final variables
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // DAO version using local file storage
    final FileHighScoreDataAccessObject fileHighScoreDataAccessObject = new FileHighScoreDataAccessObject("src/main/resources/high_score_save_files/high_scores.csv");
    private HighScoreView highScoreView;
    private HighScoreViewModel highScoreViewModel;

    final APIPetDataAccessObject apiPetDataAccessObject = new APIPetDataAccessObject();

    private SaveFileFactory saveFileFactory = new SaveFileFactory();
    private final FileSaveDataAccessObject fileSaveDataAccessObject = new FileSaveDataAccessObject("src/main/resources/save/savedata.json");
    private SaveGameView saveGameView;
    private SaveGameViewModel saveGameViewModel;

    private LoadGameView loadGameView;
    private LoadGameViewModel loadGameViewModel;

    private TitleView titleView;
    private TitleViewModel titleViewModel;

    //setting up views for selectAnimal and setParameter ----------------
    private SetParamViewModel setParamViewModel;
    private SetParamView setParamView;
    private SelectAnimalViewModel selectAnimalViewModel;
    private SelectAnimalView selectAnimalView;
    private VetView vetScoreView;

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
        final HighScoreOutputBoundary highScoreOutputBoundary = new HighScorePresenter(
                viewManagerModel, highScoreViewModel, titleViewModel);
        final HighScoreInputBoundary highScoreInteractor = new HighScoreInteractor(
                fileHighScoreDataAccessObject, highScoreOutputBoundary);

        HighScoreController controller = new HighScoreController(highScoreInteractor);
        highScoreView.setHighScoreController(controller);
        return this;
    }
//    public AppBuilder addHighScoreView(){
//        highScoreViewModel = new HighScoreViewModel();
//        highScoreView = new HighScoreView(highScoreViewModel);
//        cardPanel.add(highScoreView, highScoreView.getViewName());
//        return this;
//    }
//
//    public AppBuilder addHighScoreUseCase(){
//        final HighScoreOutputBoundary highScoreOutputBoundary = new HighScorePresenter(viewManagerModel, highScoreViewModel);
//        final HighScoreInputBoundary highScoreInteractor = new HighScoreInteractor();
//        HighScoreController controller = new HighScoreController(highScoreInteractor);
//        highScoreView.setHighScoreController(controller);
//
//        return this;
//    }
    //implementing the 2 views for API set params and select animal -----------------
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
    public AppBuilder addSetParamUseCase(){
        final SetParamOutputBoundary setParamOutputBoundary = new SetParamPresenter(setParamViewModel,
                selectAnimalViewModel, viewManagerModel);
        final SetParamInputBoundary setParamInteractor = new SetParamInteractor(
                apiPetDataAccessObject, setParamOutputBoundary);

        SetParamController setParamController = new SetParamController(setParamInteractor);
        setParamView.setSetParamController(setParamController);
        return this;
    }

    //TODO: refactor 'highScoreViewModel' to whatever the name of the petRoomViewModel ends up to be :D
    public AppBuilder addSelectAnimalUseCase(){
        final SelectAnimalOutputBoundary selectAnimalOutputBoundary = new SelectAnimalPresenter(
                selectAnimalViewModel, petRoomViewModel, setParamViewModel, viewManagerModel);
        //petRoomViewModel
        //TODO: Georgia, change the name here if there's an issue
        final SelectAnimalInputBoundary selectAnimalInteractor = new SelectAnimalInteractor(
                selectAnimalOutputBoundary);

        SelectAnimalController selectAnimalController = new SelectAnimalController(selectAnimalInteractor);
        selectAnimalView.setSelectAnimalController(selectAnimalController);
        return this;
    }

    private ButtonsController buttonsController;
    private ButtonsViewModel buttonsViewModel;
    private PetRoomView petRoomView;
    private PetRoomViewModel petRoomViewModel;
    private VetScoreViewModel vetScoreViewModel;

    private final Room room = new Room();

    private final Vet vet = new Vet(80, 30);

    /**
     * Adds the pet room view.
     * @return AppBuilder with the pet room view.
     */

    public AppBuilder addPetRoomView(){
        petRoomViewModel = new PetRoomViewModel();
        buttonsViewModel = new ButtonsViewModel();
        petRoomView = new view.PetRoomView(petRoomViewModel, buttonsViewModel);
        cardPanel.add(petRoomView, petRoomView.getViewName());
        return this;
    }

    /**
     * Adds the add buttons use case.
     * @return AppBuilder with the add buttons use case.
     */

    public AppBuilder addButtonsUseCase() {
        Pet pet = new Pet(Constants.MAX_STAT,Constants.MAX_STAT,Constants.MAX_STAT,Constants.MAX_STAT);
        ButtonsOutputBoundary buttonsPresenter = new ButtonsPresenter(
                viewManagerModel,
                buttonsViewModel,
                petRoomViewModel
        );
        ButtonsInputBoundary buttonsInteractor = new ButtonsInteractor(buttonsPresenter, new DAO(pet));
        buttonsController = new ButtonsController(buttonsInteractor);
        return this;
    }

    /**
     * Adds the pet roo, use case.
     * @return AppBuilder with the pet room use case.
     */

    public AppBuilder addPetRoomUseCase() {
        PetRoomOutputBoundary petRoomPresenter = new PetRoomPresenter(petRoomViewModel,
                viewManagerModel, saveGameViewModel);
        PetRoomInputBoundary petRoomInteractor = new PetRoomInteractor(room, petRoomPresenter, vet);
        final VetOutputBoundary vetScorePresenter = new VetScorePresenter(vetScoreViewModel, viewManagerModel, highScoreViewModel);
        final VetInputBoundary vetUseCaseInteractor = new VetUseCaseInteractor(vet, vetScorePresenter);
        final PetRoomController petRoomController = new PetRoomController(petRoomInteractor, vetUseCaseInteractor);
        petRoomView.setPetRoomController(petRoomController);
        petRoomView.setButtonsController(buttonsController);
        return this;

    }

    /**
     * Adds the vet score view.
     * @return AppBuilder with the vet score view.
     */

    public AppBuilder addVetScoreView() {
        vetScoreViewModel = new VetScoreViewModel();
        vetScoreView = new view.VetView(vetScoreViewModel);
        cardPanel.add(vetScoreView, vetScoreView.getViewName());
        return this;
    }

    /**
     * Adds the vet use case.
     * @return AppBuilder with the vet use case.
     */

    public AppBuilder addVetUseCase() {
        final VetOutputBoundary vetScorePresenter = new VetScorePresenter(vetScoreViewModel,
                viewManagerModel, highScoreViewModel);
        final VetInputBoundary vetUseCaseInteractor = new VetUseCaseInteractor(vet, vetScorePresenter);
        final VetScoreController vetScoreController = new VetScoreController(vetUseCaseInteractor);
        vetScoreView.setVetScoreController(vetScoreController);
        return this;
    }

    /**
     * Adds the save game view.
     * @return AppBuilder with the save game view.
     */

    public AppBuilder addSaveGameView() {
        saveGameViewModel = new SaveGameViewModel();
        saveGameView = new SaveGameView(saveGameViewModel);
        cardPanel.add(saveGameView, saveGameView.getViewName());
        return this;
    }

    /**
     * Adds the save game use case.
     * @return AppBuilder with the save game use case.
     */

    public AppBuilder addSaveGameUseCase() {
        final SaveGameOutputBoundary saveGameOutputBoundary = new SaveGamePresenter(viewManagerModel, petRoomViewModel,
                saveGameViewModel);
        final SaveGameInputBoundary saveGameInteractor = new SaveGameInteractor(fileSaveDataAccessObject,
                saveGameOutputBoundary, saveFileFactory);

        final SaveGameController controller = new SaveGameController(saveGameInteractor);
        saveGameView.setSaveGameController(controller);
        return this;

    }

    /**
     * Adds the load game view.
     * @return AppBuilder with the load game view.
     */

    public AppBuilder addLoadGameView() {
        loadGameViewModel = new LoadGameViewModel();
        loadGameView = new LoadGameView(loadGameViewModel);
        cardPanel.add(loadGameView, loadGameView.getViewName());
        return this;
    }

    /**
     * Adds the load game use case.
     * @return AppBuilder with the load game use case.
     */

    public AppBuilder addLoadGameUseCase() {
        final LoadGameOutputBoundary loadGameOutputBoundary = new LoadGamePresenter(viewManagerModel, petRoomViewModel,
                titleViewModel, loadGameViewModel);
        final LoadGameInputBoundary loadGameInteractor = new LoadGameInteractor(fileSaveDataAccessObject,
                loadGameOutputBoundary);

        final LoadGameController controller = new LoadGameController(loadGameInteractor);
        loadGameView.setLoadGameController(controller);
        return this;

    }

    /**
     * Adds the title view.
     * @return AppBuilder with the title view.
     */

    public AppBuilder addTitleView() {
        titleViewModel = new TitleViewModel();
        titleView = new TitleView(titleViewModel);
        cardPanel.add(titleView, titleView.getViewName());
        return this;
    }

    /**
     * Adds the switch view use case.
     * @return AppBuilder with the switch view use case.
     */

    public AppBuilder addSwitchViewUseCase() {
        final SwitchViewOutputBoundary switchViewOutputBoundary = new SwitchViewPresenter(viewManagerModel,
                loadGameViewModel, setParamViewModel, highScoreViewModel);
        final SwitchViewInputBoundary switchViewInteractor = new SwitchViewInteractor(switchViewOutputBoundary);

        final SwitchViewController controller = new SwitchViewController(switchViewInteractor);
        titleView.setSwitchViewController(controller);
        return this;
    }

    /**
     * Build method for the AppBuilder.
     * @return JFrame containing the views and use cases specified.
     */

    public JFrame build() {
        final JFrame application = new JFrame("Pet Game");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(titleView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
