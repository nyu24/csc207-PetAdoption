package interface_adapter.title;

import interface_adapter.ViewModel;

public class TitleViewModel extends ViewModel<TitleState> {
    public TitleViewModel() {
        super("title");
        setState(new TitleState());
    }
}
