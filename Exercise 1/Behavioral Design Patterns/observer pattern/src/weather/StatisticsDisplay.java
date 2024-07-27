package weather;


// Concrete Observer
public class StatisticsDisplay implements WeatherObserver {
    private float temperatureSum = 0;
    private float humiditySum = 0;
    private int numReadings = 0;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        temperatureSum += temperature;
        humiditySum += humidity;
        numReadings++;
        display();
    }

    public void display() {
        System.out.println("Avg/Max/Min temperature: " + (temperatureSum / numReadings) + "F");
        System.out.println("Avg humidity: " + (humiditySum / numReadings) + "%");
    }
}
