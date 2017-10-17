package pimpmyfridge.model;

public class AppModel extends AbstractModel {

    private int temp;

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
}
