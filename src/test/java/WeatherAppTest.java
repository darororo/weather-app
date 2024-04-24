import backend.apicon;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WeatherAppTest {
    

    public static void main(String[] args){
        
        try {
            apicon w = new apicon("japan"); 
            double t1 = w.currentTemp();
            System.out.println("Today in " + w.getCountryName() + " is " + w.getCurrentDate() );
            System.out.println("Current time in " + w.getCountryName() + " : " + w.getCurrentTime() );
            System.out.println("Current temperature in " + w.getCountryName() + "= " + t1);
            System.out.println("Today's Temperature in " + w.getCountryName() + " at 10PM: " + w.getHourlyForecast().get(19) );
            System.out.println("Temperature in 5 days from today in " + w.getCountryName() + " : " + w.getDailyForecast().get(4) );

//            String country = "Cambodia";
//            double temp = apicon.currentTemp(country);
//            System.out.println("Current temperature in " + country + "= " + temp);
//            
//            JSONArray forecastHourly = apicon.getHourlyForecast(country); 
//            System.out.println("Today's Temperature in " + country + " at 10PM: " + forecastHourly.get(19) );
//            
//            JSONArray forecastDaily = apicon.getDailyForecast(country); 
//            System.out.println("Temperature in 5 days from today in " + country + " : " + forecastDaily.get(4) );
//            
//            // throw exception when country not found
//            temp = apicon.currentTemp("england");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        

    }


}
