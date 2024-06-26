package weather.app;

import backend.apicon;
import controller.sunny;
import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

public class WeatherApp extends Application{
    
    @Override
    public void init() {
    }
    
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("view-sunny"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WeatherApp.class.getResource("/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }
   

    public static void main(String[] args){
        launch(args);
        
    }

}
