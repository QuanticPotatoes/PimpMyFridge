package pimpmyfridge.model;

public class AppModel extends AbstractModel {

    public int getTemp() {
        return temp;

    }

    public void setTemp(int temp) {
        this.temp = temp;
        this.setChanged();
        this.notifyObservers(this);
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
        this.setChanged();
        this.notifyObservers(this);
    }

    public int getRosee() {
        return rosee;
    }

    public void setRosee(int rosee) {
        this.rosee = rosee;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
        this.setChanged();
        this.notifyObservers(this);
    }

    public AppModel() {
        this.temp = 0;
    }

    public void tooglePop(String type) {

    }
}
