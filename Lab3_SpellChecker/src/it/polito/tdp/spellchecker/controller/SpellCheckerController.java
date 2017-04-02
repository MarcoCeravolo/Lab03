/**
 * Sample Skeleton for 'SpellChecker.fxml' Controller Class
 */

package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {
	
	Dictionary model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="lblTime"
    private Label lblTime; // Value injected by FXMLLoader

    @FXML // fx:id="cmbLanguage"
    private ComboBox<String> cmbLanguage; // Value injected by FXMLLoader

    @FXML // fx:id="txtText"
    private TextArea txtText; // Value injected by FXMLLoader

    @FXML // fx:id="btnSpellCheck"
    private Button btnSpellCheck; // Value injected by FXMLLoader

    @FXML // fx:id="txtErrors"
    private TextArea txtErrors; // Value injected by FXMLLoader

    @FXML // fx:id="lblNumberErrors"
    private Label lblNumberErrors; // Value injected by FXMLLoader

    @FXML // fx:id="btnClearText"
    private Button btnClearText; // Value injected by FXMLLoader

    @FXML
    void doClearText(ActionEvent event) {
    	txtText.clear();
    	txtErrors.clear();
    	
    	lblNumberErrors.setVisible(false);
    	lblTime.setVisible(false);
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	long time = System.nanoTime();
    	
    	if(cmbLanguage.getValue()==null)
    		return;
    	
    	model.loadDictionary(cmbLanguage.getValue());
		
    	String testo = txtText.getText();
    	testo.replaceAll("[\\p{Punct}]", "");
    	
    	List <String> listaParole = new LinkedList<String>();
    	String array[]=testo.split(" ");
    	for(int i=0; i<array.length; i++){
    		listaParole.add(array[i].toLowerCase());
    	}
    	
    	int contErrori = 0;
    	for(RichWord parola : model.spellCheckText(listaParole))
    		if(parola.correttaErrata()==false){
    			txtErrors.appendText(parola.getWord()+"\n");
    			contErrori++;
    		}
    	lblNumberErrors.setVisible(true);
    	lblNumberErrors.setText("The text contains "+contErrori+" errors");
    	
    	lblTime.setVisible(true);
    	lblTime.setText("Spell check completed in "+(System.nanoTime()-time)/1e9+" seconds");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert cmbLanguage != null : "fx:id=\"cmbLanguage\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtText != null : "fx:id=\"txtText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtErrors != null : "fx:id=\"txtErrors\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblNumberErrors != null : "fx:id=\"lblNumberErrors\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'SpellChecker.fxml'.";

        cmbLanguage.getItems().addAll("English", "Italian");
        if(cmbLanguage.getItems().size() > 0)
        	cmbLanguage.setValue(cmbLanguage.getItems().get(0));
    }

	/**
	 * @param model the model to set
	 */
	public void setModel(Dictionary model) {
		this.model = model;
	}
}
