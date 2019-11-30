/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epmduijfx;

import java.time.LocalTime;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @authors Daniel Gunther, Cameron McFadden
 */
public class EPMDUIJFX extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(EPMDUIJFX.class.getResource("FXMLDocument.fxml"));
        Parent root =  loader.load();
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //launch(args);
        
        MockObject mock = new MockObject(1150);
        DatabaseHandler db = new DatabaseHandler();
        
        db.initDatabase(1);
        
        int bpm;
        int ppm;
        float latitude;
        float longitude;
        LocalTime t;
        
        for (int i = 0; i <= 30; i++) {
        	bpm = mock.getHR();
        	ppm = mock.getGas();
        	latitude = mock.getLat();
        	longitude = mock.getLong();
        	t = mock.getTime();
        	
        	db.add(1, t, bpm, ppm, latitude, longitude);
        }
        
        db.outputTable(1);
        
    }
    
}
