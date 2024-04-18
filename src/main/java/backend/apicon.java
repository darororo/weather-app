package backend;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class apicon {
     public static JSONObject getCountry(String counName) {
        JSONObject country = null;
        
         try {
            
            URL url = new URL("https://restcountries.com/v3.1/name/"
                    + counName.toLowerCase()
                 );
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();

                System.out.println(informationString);

                //JSON simple library Setup with Maven is used to convert strings to JSON
                JSONParser parse = new JSONParser();
                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
                country = (JSONObject) dataObject.get(0);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         
       return (JSONObject) country;
    }
    
    public static JSONObject getWeather(JSONObject country) {
        JSONObject weather = null;
        
        try {
            JSONArray latlng = (JSONArray) country.get("latlng");
            
            URL url = new URL("https://api.open-meteo.com/v1/forecast?"
                    + "latitude=" + latlng.get(0)
                    + "&longitude=" + latlng.get(1)
                    + "&current=temperature_2m,precipitation"
                    + "&hourly=temperature_2m"
                    + "&daily=weather_code,temperature_2m_max,precipitation_hours"
                    + "&timezone=auto");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();

                System.out.println(informationString);

                //JSON simple library Setup with Maven is used to convert strings to JSON
                JSONParser parse = new JSONParser();
                weather = (JSONObject) parse.parse(String.valueOf(informationString));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
                
        return weather;
    
    }
    
    public static double currentTemp (JSONObject weather) {
        JSONObject current = (JSONObject) weather.get("current");
        
        double temp = (double) current.get("temperature_2m");
        return temp;
    }
    
    public static double currentTemp (String countryName) throws Exception {
        JSONObject country = getCountry(countryName);
        if (country == null) {
            throw new Exception("ERROR " + countryName + " not found.");
//            System.out.println( "ERROR: " + countryName + " not found");
//            return 0;
        }
        
        JSONObject weather = getWeather(country);
        JSONObject current = (JSONObject) weather.get("current");
        double temp = (double) current.get("temperature_2m");
        
        return temp;
    }
}
