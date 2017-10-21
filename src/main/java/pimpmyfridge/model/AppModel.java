package pimpmyfridge.model;

public class AppModel extends AbstractModel {

    public float getTemp() {
        return temp;

    }

    public void setTemp(float temp) {
        this.temp = temp;
        this.setChanged();
        this.notifyObservers(this);
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
        this.setChanged();
        this.notifyObservers(this);
    }

    public float getRosee() {
        return rosee;
    }

    public void setRosee(float rosee) {
        this.rosee = rosee;
    }

    public float getOrder() {
        return order;
    }

    public void setOrder(float order) {
        this.order = order;
        this.setChanged();
        this.notifyObservers(this);
    }

    @Override
    public float getInside() {
        return inside;
    }

    @Override
    public void setInside(float inside) {
        this.inside = inside;
    }

    public AppModel() {
        this.temp = 0;
    }

    public void tooglePop(String type) {

    }
}
