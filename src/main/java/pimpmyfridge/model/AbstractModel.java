package pimpmyfridge.model;

import java.util.Observable;

public abstract class AbstractModel extends Observable {

    protected double temp;
    protected double inside;
    protected double humidity;
    protected double rosee;
    protected double order;
    protected double goal;
    protected boolean serial;
    protected boolean bluetooth;
    protected boolean power;
    protected boolean frooze;
    protected boolean door;


    public abstract boolean isPower();

    public abstract void setPower(boolean power);

    public abstract double getTemp();

    public abstract void setTemp(double temp);

    public abstract double getHumidity();

    public abstract void setHumidity(double humidity);

    public abstract double getRosee();

    public abstract void setRosee(double rosee);

    public abstract double getOrder();

    public abstract void setOrder(double order);

    public abstract double getInside();

    public abstract void setInside(double inside);

    public abstract void tooglePop(String type);

    public abstract double getGoal();

    public abstract boolean isSerial();

    public abstract void setSerial(boolean serial);

    public abstract boolean isBluetooth();

    public abstract void setBluetooth(boolean bluetooth);

    public abstract double progressGoal();

    public abstract void setGoal(double goal);

    public abstract void setFrooze(Boolean frooze);

    public abstract boolean isFrooze();

    public abstract boolean isDoor();

    public abstract void setDoor(boolean door);
}
