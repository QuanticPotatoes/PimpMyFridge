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
    public void setTemp(double temp) {
        model.setTemp(temp);
    }

    @Override
    public void setHumidity(double humidity) {
        model.setHumidity(humidity);
    }

    @Override
    public void setRosee(double rosee) {
        model.setRosee(rosee);
    }

    public void setInside(double inside) {
        model.setInside(inside);
    }

    @Override
    public void setOrder(double order) {
        model.setOrder(order);
    }

    @Override
    public void update(Object o) {
        JSONObject sensor = (JSONObject) o;
        setTemp(Double.valueOf(sensor.get("temp").toString()));
        setHumidity(Double.valueOf(sensor.get("hum").toString()));
        setRosee(Double.valueOf(sensor.get("rosee").toString()));
        setInside(Double.valueOf(sensor.get("inside").toString()));
    }
    @Override
    public void sendData(String type, String value) {
        try {
            serialManager.send(type, value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closePop(String type) {

    }

    @Override
    public void stop() {
        serialManager.close();
    }
}