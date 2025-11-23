package interface_adapter;

/**
 * Model for the View Manager. Its state is the name of the View which
 * is currently active. Initial state is ""
 */
public class ViewManagerModel extends ViewModel{

    public ViewManagerModel() {
        super("view manager");
        this.setState("");
    }
}