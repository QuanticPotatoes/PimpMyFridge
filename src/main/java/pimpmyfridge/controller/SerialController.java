package pimpmyfridge.controller;
import org.json.JSONObject;
import pimpmyfridge.model.AbstractModel;

import java.io.IOException;

public class SerialController extends AbstractController {

    private SerialManager serialManager;

    public SerialController(AbstractModel model) {
        super(model);

        serialManager = new SerialManager(this);
        serialManager.initialize();
    }

    @Override
    public void setTemp(int temp) {
        this.model.setTemp(temp);

    }

    @Override
    public void setHumidity(int humidity) {
        model.setHumidity(humidity);
    }

    @Override
    public void setRosee(int rosee) {
        model.setRosee(rosee);
    }

    @Override
    public void setOrder(int order) {
        model.setOrder(order);
    }

    @Override
    public void update(Object o) {
        JSONObject sensor = (JSONObject) o;
        setTemp((Integer) sensor.get("temp"));
        setHumidity((Integer) sensor.get("hum"));
        setRosee((Integer) sensor.get("rose"));
    }

    @Override
    public void sendData(int type, int value) {
        try {
            serialManager.send(type, value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closePop(String type) {

    }

}