package pimpmyfridge.model;

public class AppModel extends AbstractModel {

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
}

