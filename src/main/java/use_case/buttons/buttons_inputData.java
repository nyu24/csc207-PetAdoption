package use_case.buttons;

/**
 * The input Data for the buttons Use case
 */

public class buttons_inputData {
    private final String Action; //Action is FEED, WATER, CLEAN OR PLAY

    public buttons_inputData(String action) {
        this.Action = action;

    }

    public String getAction() {
        return Action;
    }
}