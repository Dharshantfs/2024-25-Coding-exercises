package weather;


// Observer Interface
public interface WeatherObserver {
    void update(float temperature, float humidity, float pressure);
}
