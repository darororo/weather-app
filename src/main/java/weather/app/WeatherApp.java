package weather.app;
import backend.apicon;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WeatherApp {

    public static void main(String[] args){
        
        try {
            String country = "Cambodia";
            double temp = apicon.currentTemp(country);
            System.out.println("Current temperature in " + country + "= " + temp);
            
            JSONArray forecastHourly = apicon.getHourlyForecast(country); 
            System.out.println("Today's Temperature in " + country + " at 10PM: " + forecastHourly.get(19) );
            
            JSONArray forecastDaily = apicon.getDailyForecast(country); 
            System.out.println("Temperature in 5 days from today in " + country + " : " + forecastDaily.get(4) );
            
            // throw exception when country not found
            temp = apicon.currentTemp("england");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        

    }
}
