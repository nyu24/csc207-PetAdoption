package interface_adapter;
import interface_adapter.ViewModel;
/**
 * The view model for buttons view
 */

public class buttons_viewModel extends ViewModel<buttons_State> {


    public buttons_viewModel() {
        super("buttons");
        setState(new buttons_State());
    }

}
