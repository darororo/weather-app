package weather.app;
import backend.apicon;
import org.json.simple.JSONObject;

public class WeatherApp {

    public static void main(String[] args) {
        JSONObject country = apicon.getCountry("cambodia");
        if (country == null) {
            System.out.println("Country not found");
            return;
        }
        JSONObject weather = apicon.getWeather(country);
        System.out.println(apicon.currentTemp(weather));
        
    }
}
