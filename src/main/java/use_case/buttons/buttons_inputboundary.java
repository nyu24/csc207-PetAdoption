package use_case.buttons;
/**
 * Input boundary for actions related to the button-actions for the pet.
 * executes buttons use case
 * */
public interface buttons_inputboundary {

    /**
     * feeds the pet
     * */
    void feed(buttons_inputData buttonsInputData);

    /**
     * cleans the pet
     */
    void clean(buttons_inputData buttons_InputData);

    /**
     * gives water to the pet
     */
    //TODO : change name of this method perchance? it sounds a little silly
    void water(buttons_inputData buttonsInputData);

    /**
     * brings joy to the pet by playing with them
     */
    void play(buttons_inputData buttonsInputData);




}
