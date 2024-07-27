package weather;



// Demo
public class ObserverPatternWeatherExample {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay();

        weatherData.addObserver(currentDisplay);
        weatherData.addObserver(statisticsDisplay);

        weatherData.setMeasurements(80, 65, 30.4f);  // Updates all displays
        weatherData.setMeasurements(82, 70, 29.2f);  // Updates all displays
    }
}
