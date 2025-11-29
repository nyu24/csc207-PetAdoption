package interface_adapter.vet_score;

import entities.Vet;
import interface_adapter.ViewModel;

public class VetScoreViewModel extends ViewModel<VetScoreState> {

    public VetScoreViewModel() {
        super("vetView");
        setState(new VetScoreState());
    }

}
