package weather.app;
import backend.apicon;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

public class WeatherApp {

    public static void main(String[] args){
        
        try {
            String country = "Cambodia";
            double temp = apicon.currentTemp(country);
            System.out.println("Current temperature in " + country + "= " + temp);
            
            // throw exception when country not found
            temp = apicon.currentTemp("england");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
       

    }
}
