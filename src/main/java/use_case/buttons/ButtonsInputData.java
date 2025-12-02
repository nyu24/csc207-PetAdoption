package use_case.buttons;

/**
 * The input Data for the buttons Use case.
 */
public class ButtonsInputData {
    private final String action;

    public ButtonsInputData(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
