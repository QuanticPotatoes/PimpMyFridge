package pimpmyfridge.controller;

import pimpmyfridge.model.AbstractModel;

public abstract class AbstractController {

    protected AbstractModel model;
    public AbstractController(AbstractModel model) {
        this.model = model;
    }

    /**
     * send the temperature to the model
     * @param temp temperature in °C
     */
    public abstract void setTemp(int temp);

    public abstract void setHumidity(int humidity);

    public abstract void setRosee(int rosee);

    public abstract void setOrder(int order);

    public abstract void update(Object o);

    public abstract void sendData(int b);
    /**
     * Give the event from the user to close a Pop-Up
     * @param type the type of the Pop-Up
     */
    public abstract void closePop(String type);
}
