package pimpmyfridge.model;

public class AppModel extends AbstractModel {

    @Override
    public boolean isPower() {
        return power;
    }

    @Override
    public void setPower(boolean power) {
        this.power = power;
    }

    public double getTemp() {
        return temp;

    }

    public void setTemp(double temp) {
        this.temp = temp;
        setChanged();
        notifyObservers("temp");
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
        setChanged();
        notifyObservers("humidity");
    }

    public double getRosee() {
        return rosee;
    }

    public void setRosee(double rosee) {
        this.rosee = rosee;
        setChanged();
        notifyObservers("rosee");
    }

    public double getOrder() {
        return order;
    }

    public void setOrder(double order) {
        this.order = order;
        setChanged();
        notifyObservers("order");
    }

    @Override
    public double getInside() {
        return inside;
    }

    @Override
    public void setInside(double inside) {
        this.inside = inside;
        setChanged();
        notifyObservers("inside");
    }

    public AppModel() {
        this.temp = 0;
    }

    public void tooglePop(String type) {

    }

    @Override
    public double getGoal() {
        return goal;
    }

    @Override
    public boolean isSerial() {
        return serial;
    }

    @Override
    public void setSerial(boolean serial) {
        this.serial = serial;
        setChanged();
        notifyObservers("serial");
    }

    @Override
    public boolean isBluetooth() {
        return bluetooth;
    }

    @Override
    public void setBluetooth(boolean bluetooth) {

    }

    @Override
    public double progressGoal() {
        double progress = 1 - (Math.abs(order - temp ) / goal);
        return (progress < 0)? 0 : progress;
    }

    @Override
    public void setGoal(double goal) {
        /**
         * Check if the goal is invariant
         */
            this.goal = Math.abs(goal - temp);
    }

    @Override
    public void setFrooze(Boolean frooze) {
        if(this.frooze == frooze) {
            return;
        }

        this.frooze = frooze;
        setChanged();
        notifyObservers("frooze");
    }

    @Override
    public boolean getFrooze() {
        return frooze;
    }
}

