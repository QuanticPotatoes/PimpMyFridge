package pimpmyfridge.model;

public class AppModel extends AbstractModel {

    private int temp;
    private int humidity;

    public AppModel() {
        this.temp = 0;
    }

    public int getTemp() {
        return temp;
    }

    public void saveTemp(int temp) {
        this.temp = temp;
        this.setChanged();
        this.notifyObservers(this);
    }

    public void tooglePop(String type) {

    }

    public void saveHumidity(int humidity) {
        this.humidity = humidity;
        this.setChanged();
        this.notifyObservers(this);
    }
}
