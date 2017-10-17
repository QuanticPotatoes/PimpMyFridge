package pimpmyfridge.controller;
import pimpmyfridge.model.AbstractModel;

public class SerialController extends AbstractController {

    public SerialController(AbstractModel model) {
        super(model);
    }

    public void sendTemp(int temp) {
        System.out.println(temp);
        this.model.saveTemp(temp);
    }

    public void closePop(String type) {

    }
}
