/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epmduijfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Daniel
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private Label label;
    @FXML private javafx.scene.control.Button exitButton;
    @FXML private javafx.scene.control.Button searchButton;
    
    @FXML private javafx.scene.control.TextField idField;
    @FXML private javafx.scene.control.TextField dayField;
    @FXML private javafx.scene.control.TextField monthField;
    @FXML private javafx.scene.control.TextField yearField;
    @FXML private javafx.scene.control.TextField hourField;
    @FXML private javafx.scene.control.TextField minuteField;
    
    private String searchData;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    private void exitButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) exitButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    
    @FXML
    private void searchButtonAction(){
        Search searchData = new Search();
        searchData.setID(Integer.parseInt(idField.getText()));
        searchData.setDay(Integer.parseInt(dayField.getText()));
        searchData.setMonth(Integer.parseInt(monthField.getText()));
        searchData.setYear(Integer.parseInt(yearField.getText()));
        searchData.setHour(Integer.parseInt(hourField.getText()));
        searchData.setMinute(Integer.parseInt(minuteField.getText()));
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
