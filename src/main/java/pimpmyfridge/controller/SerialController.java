package pimpmyfridge.controller;
import pimpmyfridge.model.AbstractModel;

public class SerialController extends AbstractController {

    private SerialManager serialManager;

    public SerialController(AbstractModel model) {
        super(model);

        this.serialManager = new SerialManager(this);
        this.serialManager.initialize();
    }

    @Override
    public void setTemp(int temp) {
        this.model.saveTemp(temp);

    }

    @Override
    public void setHumidity(int humidity) {
        this.model.saveHumidity(humidity);
    }

    public void closePop(String type) {

    }

}