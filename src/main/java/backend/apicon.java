package backend;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class apicon {
    // TODO create object methods to avoid re-accessing the api server
    
    private String CountryName;
    private JSONObject Country;
    private JSONObject Weather;
    private LocalDateTime CurrentDate;
    
    // Create an object to avoid re-accessing the api server
    public apicon (String name) {
        CountryName = name;
        Country = getCountry(CountryName);
        Weather = getWeather(Country);  
        CurrentDate = setCurrentDate();
        
    }
    
    private LocalDateTime setCurrentDate() {
        JSONObject current = (JSONObject) Weather.get("current");
        String time = current.get("time").toString();
        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime currentDate = LocalDateTime.parse(time);
        return currentDate;
    }
    
    public String getCountryName() {
        String name = CountryName.substring(0, 1).toUpperCase() + CountryName.substring(1, CountryName.length());
        return name;
    }
    
    public String getCurrentTime() {
        LocalTime lt = CurrentDate.toLocalTime();
        String hour = lt.format(DateTimeFormatter.ofPattern("hh:mm a"));
        return hour;
      
    }
    
    public String getCurrentDate() {  
        String day = CurrentDate.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy")).toString();
        return day;
    }
    
    public String getCurrentDayOfWeek() {
        JSONObject current = (JSONObject) Weather.get("current");
        String time = current.get("time").toString();
        
        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime date = LocalDateTime.parse(time);
  
        String day = date.getDayOfWeek().toString();
       
        return day;
      
    }
    
    
    public double currentTemp() throws Exception {
        if (Country == null) { throw new Exception("ERROR " + CountryName + " not found."); }
        
        JSONObject current = (JSONObject) Weather.get("current");
        double temp = (double) current.get("temperature_2m");
        
        return temp;
    }
    
    public JSONArray getHourlyForecast() throws Exception {
        if (Country == null) { throw new Exception("ERROR " + CountryName + " not found."); }
        
        JSONObject hourly =  (JSONObject) Weather.get("hourly");
        JSONArray forecast = (JSONArray) hourly.get("temperature_2m");
       
        return forecast;
    }
    
    public JSONArray getDailyForecast() throws Exception {
        if (Country == null) { throw new Exception("ERROR " + CountryName + " not found."); }

        JSONObject daily =  (JSONObject) Weather.get("daily");
        JSONArray forecast = (JSONArray) daily.get("apparent_temperature_max");
       
        return forecast;
    }
    
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
                    + "&daily=weather_code,apparent_temperature_max,precipitation_hours"
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
        if (country == null) { throw new Exception("ERROR " + countryName + " not found."); }
        
        JSONObject weather = getWeather(country);
        JSONObject current = (JSONObject) weather.get("current");
        double temp = (double) current.get("temperature_2m");
        
        return temp;
    }
    
    public static JSONArray getHourlyForecast (String countryName) throws Exception {
        JSONObject country = getCountry(countryName);
        if (country == null) { throw new Exception("ERROR " + countryName + " not found."); }
        
        JSONObject weather = getWeather(country);
        JSONObject hourly =  (JSONObject) weather.get("hourly");
        JSONArray forecast = (JSONArray) hourly.get("temperature_2m");
       
        return forecast;
    }
    
    public static JSONArray getDailyForecast (String countryName) throws Exception {
        JSONObject country = getCountry(countryName);
        if (country == null) { throw new Exception("ERROR " + countryName + " not found."); }
        
        JSONObject weather = getWeather(country);
        JSONObject daily =  (JSONObject) weather.get("daily");
        JSONArray forecast = (JSONArray) daily.get("apparent_temperature_max");
       
        return forecast;
    }
}
