package pimpmyfridge.model;

import java.util.Observable;

public abstract class AbstractModel extends Observable {

    protected int temp;
    protected int humidity;
    protected int rosee;
    protected int order;

    public abstract int getTemp();

    public abstract void setTemp(int temp);

    public abstract int getHumidity();

    public abstract void setHumidity(int humidity);

    public abstract int getRosee();

    public abstract void setRosee(int rosee);

    public abstract int getOrder();

    public abstract void setOrder(int order);
    public abstract void tooglePop(String type);
}
