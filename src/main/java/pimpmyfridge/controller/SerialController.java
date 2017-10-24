package pimpmyfridge.controller;
import org.json.JSONObject;
import pimpmyfridge.model.AbstractModel;

import java.io.IOException;

public class SerialController extends AbstractController {

    private SerialManager serialManager;
    private boolean bootValue = false;

    public SerialController(AbstractModel model) {
        super(model);
        serialManager = new SerialManager(this);
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
        try {
            JSONObject sensor = new JSONObject((String) o);
            setTemp(sensor.getDouble("temp"));
            setHumidity(sensor.getDouble("hum"));
            setRosee(sensor.getDouble("rosee"));
            setInside(sensor.getDouble("inside"));
            setFrooze(sensor.getInt("frooze") == 1);
            setDoor(sensor.getInt("door") == 1);

            if(model.getOrder() != sensor.getDouble("order") && !bootValue) {
                model.setOrder(sensor.getDouble("order"));
                bootValue = true;
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
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
        bootValue = false;
    }

    @Override
    public void setGoal(double goal) {
        model.setGoal(goal);
    }

    @Override
    public void setConnected(boolean connected) {
        model.setSerial(connected);
    }

    @Override
    public void launch() {
        serialManager.initialize();
    }

    @Override
    public void setPower(boolean power) {
        // True = off
        // False = on
        // so we inverse
        model.setPower(!power);
        try {
            serialManager.send("power",String.valueOf(!power));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setFrooze(boolean frooze) {
        model.setFrooze(frooze);
    }

    @Override
    public void setDoor(boolean door) {
        model.setDoor(door);
    }

    @Override
    public void reloadConnect() {
        serialManager.initialize();
    }


}