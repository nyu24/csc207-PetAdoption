package use_case.buttons;

/**
 *
 */

public class buttons_interactor implements buttons_inputboundary {
    private final buttons_outputboundary buttons_presenter;
    private  buttons_DataAcess buttons_data_acess;

    public buttons_interactor(buttons_outputboundary buttons_outputboundary) {
        this.buttons_presenter = buttons_outputboundary;
        this.buttons_data_acess = buttons_data_acess;
    }

    @Override
    public void feed(buttons_inputData buttons_inputData) {
        if (buttons_inputData.getHunger() < 100) {
            buttons_data_acess.updateHunger(buttons_inputData.getpet());
            final buttons_OutputData buttons_outputData = new buttons_OutputData(buttons_inputData.getpet());
            buttons_presenter.prepareSuccessView(buttons_outputData);
        }
        else {
            //TODO : Think of what to do in this case
        }

    }

    @Override
    public void clean(buttons_inputData buttonsinputData) {
        if (buttonsinputData.getCleanliness() < 100) {
            buttons_data_acess.updateCleanliness(buttonsinputData.getpet());
            final buttons_OutputData buttons_outputData = new buttons_OutputData(buttonsinputData.getpet());
            buttons_presenter.prepareSuccessView(buttons_outputData);

        }
        else {
            //TODO : Impliment this case
        }

    }

    @Override
    public void water(buttons_inputData buttonsinputData) {
        if (buttonsinputData.getThirst() < 100) {
            buttons_data_acess.updateThirst(buttonsinputData.getpet());
            final buttons_OutputData buttons_outputData = new buttons_OutputData(buttonsinputData.getpet());
            buttons_presenter.prepareSuccessView(buttons_outputData);

        }
        else {
            //TODO: Impliment this case
        }
    }

    @Override
    public void play(buttons_inputData buttonsinputData) {
        if (buttonsinputData.getHapiness() < 100) {
            buttons_data_acess.updateHapiness(buttonsinputData.getpet());
            final buttons_OutputData buttons_outputData = new buttons_OutputData(buttonsinputData.getpet());
            buttons_presenter.prepareSuccessView(buttons_outputData);

        }
        else {
            //TODO: Impliment this case
        }

    }
}


