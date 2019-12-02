/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epmduijfx;

import java.net.*;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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
    
	private static java.sql.Time time;
	private static ArrayList<Integer> ids = new ArrayList<Integer>();
	
    @Override
    public void start(Stage stage) throws Exception {
    	/**
        FXMLLoader loader = new FXMLLoader(EPMDUIJFX.class.getResource("FXMLDocument.fxml"));
        Parent root =  loader.load();
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        */
    }

    public static void main(String[] args) {
        //launch(args);
        udpReciever listener = new udpReciever(2057);
        udpSender sender = new udpSender();
        DatabaseHandler db = new DatabaseHandler();
        DatagramPacket packet;
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        
        for (;;) {
        	// Wait for a packet to come in
        	listener.recieve();
        	
        	// Turn raw string into array of strings containing each data piece
        	packet = listener.getPacket();
        	String str = new String((packet.getData())).trim();
        	String[] data = str.split("-");
        	
        	// Cast each piece of data to respective types
        	int seqID = Integer.parseInt(data[0]);
        	String identifier = data[1];
        	int id = Integer.parseInt(data[2]);
        	
        	try {
				time = new java.sql.Time(dateFormat.parse(data[3]).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
        	
        	int bpm = Integer.parseInt(data[4]);
        	int ppm = Integer.parseInt(data[5]);
        	float latitude = Float.parseFloat(data[6]);
        	float longitude = Float.parseFloat(data[7]);
        	
        	if (!ids.contains(id)) {
        		db.initDatabase(id);
        		ids.add(id);
        	}
        	
        	// Add data to database
        	db.add(id, time, bpm, ppm, latitude, longitude);
        	
        	// Send acknowledgement
        	String toSend = seqID + "-" + identifier;
        	byte[] b = toSend.getBytes();
        	DatagramPacket ack = new DatagramPacket(b, b.length, packet.getAddress(), packet.getPort());
        	sender.sendPacket(packet);
        }
        
        
    }
    
}
