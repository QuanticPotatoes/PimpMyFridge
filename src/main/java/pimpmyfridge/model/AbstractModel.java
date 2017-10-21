package pimpmyfridge.model;

import java.util.Observable;

public abstract class AbstractModel extends Observable {

    protected float temp;
    protected float inside;
    protected float humidity;
    protected float rosee;
    protected float order;

    public abstract float getTemp();

    public abstract void setTemp(float temp);

    public abstract float getHumidity();

    public abstract void setHumidity(float humidity);

    public abstract float getRosee();

    public abstract void setRosee(float rosee);

    public abstract float getOrder();

    public abstract void setOrder(float order);

    public abstract float getInside();

    public abstract void setInside(float inside);

    public abstract void tooglePop(String type);
}
