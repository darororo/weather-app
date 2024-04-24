package controller;

import backend.apicon;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import weather.app.WeatherApp;

public class sunny {
    @FXML Button searchCountry;
    @FXML TextField tfCountry;
    @FXML Text greetings;
    @FXML Text currentTemp;
    @FXML Text mainTime;
    @FXML Text mainDate;
    @FXML Text day1;
    @FXML Text day2;
    @FXML Text day3;
    @FXML Text day4;
    @FXML Text day5;
    @FXML Text day6;
    @FXML Text day7;
    @FXML Text td1;     // temperature of day 1
    @FXML Text td2;
    @FXML Text td3;
    @FXML Text td4;
    @FXML Text td5;
    @FXML Text td6;
    @FXML Text td7;
    @FXML Label hour1;
    @FXML Label hour2;
    @FXML Label hour3;
    @FXML Label hour4;
    @FXML Label hour5;
    @FXML Text th1;     // temperature of hour 1
    @FXML Text th2;
    @FXML Text th3;
    @FXML Text th4;
    @FXML Text th5;
    @FXML Text sunrise;
    @FXML Text sunset;
    @FXML Text windSpeed;
    @FXML Text humidity;
    @FXML Text precip;
    @FXML ImageView rightView;
    @FXML Text city;
     
    
    
    

    public void initialize() {
        defaultGreetings();
        defaultTime();
        defaultDate();
    }
    
    public void searchClicked() {
        try {  
           
            System.out.println("Hello mom");
            String countryName = tfCountry.getText();
            apicon Country = new apicon(countryName);
             
            updateGreetings(Country);
            updateTime(Country);
            updateDate(Country);
            updateCurrentTemp(Country);
            updateDailyForecast(Country);
            updateHourlyForecast(Country);
            updateSun(Country);
            updateHumidity(Country);
            updateWindSpeed(Country);
            updatePrecipitationProb(Country);
            updateCity(Country);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void updateCurrentTemp(apicon Country) throws Exception {
        double curTemp = Country.currentTemp();
        currentTemp.setText(curTemp + "°C");
    }
    
    private void updateDailyForecast(apicon Country) throws Exception {
        JSONArray data = Country.getDailyForecast();
        td1.setText(data.get(0).toString()+ "°C");
        td2.setText(data.get(1).toString()+ "°C");
        td3.setText(data.get(2).toString()+ "°C");
        td4.setText(data.get(3).toString()+ "°C");
        td5.setText(data.get(4).toString()+ "°C");
        td6.setText(data.get(5).toString()+ "°C");
        td7.setText(data.get(6).toString()+ "°C");
        
        String d1 = Country.getObjectCurrentDate().format(DateTimeFormatter.ofPattern("EEE"));
        String d2 = Country.getObjectCurrentDate().plusDays(1).format(DateTimeFormatter.ofPattern("EEE"));
        String d3 = Country.getObjectCurrentDate().plusDays(2).format(DateTimeFormatter.ofPattern("EEE"));
        String d4 = Country.getObjectCurrentDate().plusDays(3).format(DateTimeFormatter.ofPattern("EEE"));
        String d5 = Country.getObjectCurrentDate().plusDays(4).format(DateTimeFormatter.ofPattern("EEE"));
        String d6 = Country.getObjectCurrentDate().plusDays(5).format(DateTimeFormatter.ofPattern("EEE"));
        String d7 = Country.getObjectCurrentDate().plusDays(6).format(DateTimeFormatter.ofPattern("EEE"));

        day1.setText(d1);
        day2.setText(d2);
        day3.setText(d3);
        day4.setText(d4);
        day5.setText(d5);
        day6.setText(d6);
        day7.setText(d7);
        
    }
    
    private void updateHourlyForecast(apicon Country) throws Exception {
        JSONArray data = Country.getHourlyForecast();
        th1.setText(data.get(0).toString());
        th2.setText(data.get(1).toString());
        th3.setText(data.get(2).toString());
        th4.setText(data.get(3).toString());
        th5.setText(data.get(4).toString());

        String h2 = Country.getObjectCurrentDate().plusHours(1).format(DateTimeFormatter.ofPattern("h a"));
        String h3 = Country.getObjectCurrentDate().plusHours(2).format(DateTimeFormatter.ofPattern("h a"));
        String h4 = Country.getObjectCurrentDate().plusHours(3).format(DateTimeFormatter.ofPattern("h a"));
        String h5 = Country.getObjectCurrentDate().plusHours(4).format(DateTimeFormatter.ofPattern("h a"));
        
        int currTime = Country.getObjectCurrentDate().getHour();
        updateRightView(currTime);   

        hour2.setText(h2);
        hour3.setText(h3);
        hour4.setText(h4);
        hour5.setText(h5);

    }
  
    
    private void updateRightView(int currentTime) {
        String root = WeatherApp.class.getResource("/Image and Icon/").toString();
        String night = root + "night/bg.png";
        
        // Night
        if( currentTime > 10 && currentTime < 24 ) {
            rightView.setImage(new Image(night));
        } 
        
        // After Midnight
        if( currentTime >= 0 && currentTime < 5 ) {
            rightView.setImage(new Image(night));
        } 
    }
    
    
    private void updateGreetings(apicon Country) {
        String country = Country.getCountryName();
        greetings.setText("YOOOO " + country);
    }
    
    private void updateTime(apicon Country) {
        String time = Country.getObjectCurrentDate().format(DateTimeFormatter.ofPattern("hh:mm a"));
        mainTime.setText(time);
    }
    
    private void updateDate(apicon Country) {
        String date = Country.getObjectCurrentDate().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy"));
        mainDate.setText(date);
    }
    
    private void updateSun(apicon Country) throws Exception {
        LocalDateTime ldt = LocalDateTime.parse(Country.getSunrise().get(0).toString());
        String srise = ldt.format(DateTimeFormatter.ofPattern("h:mm a"));
        sunrise.setText(srise);
        
        ldt = LocalDateTime.parse(Country.getSunset().get(0).toString());
        String sset = ldt.format(DateTimeFormatter.ofPattern("h:mm a"));
        sunset.setText(sset);
    }
    
    private void updateHumidity(apicon Country) throws Exception {
        double hum = Country.getHumidity();
        humidity.setText(hum + "%");
    }
    
    private void updateWindSpeed(apicon Country) throws Exception {
        double speed = Country.getWindSpeed();
        windSpeed.setText(speed + " km/h");
    }
    
    private void updatePrecipitationProb(apicon Country) throws Exception {
        long prob = (long) Country.getPrecipitationProb().get(0);
        precip.setText(prob + "%");
    }
    
    private void updateCity(apicon Country) throws Exception {
        JSONArray cap = (JSONArray) Country.getCountryJSON().get("capital");
        String name = cap.get(0).toString();
        city.setText(name);
    }
   
    
    public void defaultTime() {
        String dt = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"));
        mainTime.setText(dt);
    }
    
    public void defaultDate() {
        String dt = LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy"));
        mainDate.setText(dt);
    }
    
    private void defaultGreetings() {
        greetings.setText("KONICHIWA");
    }
        
}
