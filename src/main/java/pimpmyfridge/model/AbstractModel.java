package pimpmyfridge.model;

import java.util.Observable;

public abstract class AbstractModel extends Observable {
    public abstract int getTemp();
    public abstract void saveTemp(int temp);
    public abstract void tooglePop(String type);
    public abstract void saveHumidity(int humidity);
}
