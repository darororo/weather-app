package controller;

import backend.apicon;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class sunny {
    @FXML Button searchCountry;
    @FXML TextField tfCountry;
    @FXML Text currentTemp;
    @FXML Text mainTime;
    @FXML Text mainDate;
    @FXML Text day1;
    @FXML Text td1;
    @FXML Text day2;
    @FXML Text td2;
    @FXML Text day3;
    @FXML Text td3;
    @FXML Text day4;
    @FXML Text td4;
    @FXML Text day5;
    @FXML Text td5;
    @FXML Text day6;
    @FXML Text td6;
    @FXML Text day7;
    @FXML Text td7;
    

    
    public void initialize() {
        defaultTime();
        defaultDate();
    }
    
    public void searchClicked() {
        try {  
            
            
            System.out.println("Hello mom");
            String countryName = tfCountry.getText();
            apicon Country = new apicon(countryName);
            
            
            
            
            setCurrentTemp(Country);
            setDailyForecast(Country);
            

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void setCurrentTemp(apicon Country) throws Exception {
        double curTemp = Country.currentTemp();
        currentTemp.setText(curTemp + "°C");
    }
    
    private void setDailyForecast(apicon Country) throws Exception {
        JSONArray data = Country.getDailyForecast();
        td1.setText(data.get(0).toString());
        td2.setText(data.get(1).toString());
        td3.setText(data.get(2).toString());
        td4.setText(data.get(3).toString());
        td5.setText(data.get(4).toString());
        td6.setText(data.get(5).toString());
        td7.setText(data.get(6).toString());
        
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
    
    public void defaultTime() {
        String dt = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"));
        mainTime.setText(dt);
    }
    
    public void defaultDate() {
        String dt = LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy"));
        mainDate.setText(dt);
    }
    
    private String shortDay(String date) {
        String day = LocalDate.parse(date).format(DateTimeFormatter.ofPattern("EEEE"));
        return day;
    }

    
}
