package weather;


// Subject Interface
public interface WeatherStation {
    void addObserver(WeatherObserver observer);
    void removeObserver(WeatherObserver observer);
    void notifyObservers();
}
