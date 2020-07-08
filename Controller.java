package com.SumedhaComp.javafxapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

	@FXML
	public Label welcomeLabel;

	@FXML
	public ChoiceBox<String> choiceBox;

	@FXML
	public TextField userInputField;

	@FXML
	public Button convertBtn;

	private boolean isC_To_F = true;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		choiceBox.getItems().add("Celsius to Fahrenheit");
		choiceBox.getItems().add("Fahrenheit to Celsius");
		choiceBox.setValue("Celsius to Fahrenheit");


		choiceBox.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
		if(t1.equals("Fahrenheit to Celsius"))
		{
			isC_To_F = false;
		}
		else
		{
			isC_To_F = true;
		}
		});


		convertBtn.setOnAction(event -> {
			convert();
				}
		);
	}

	private void convert() {

		String input= userInputField.getText();
		double temp = 0.0;

		try{
			temp=Double.parseDouble(input);
		}
		catch ( Exception e)
		{
			warnUser();
			return;
		}
		double result = 0.0;
		if(isC_To_F)
		{
			result = (temp * 9 / 5) + 32;
		}
		else {
			result = (temp - 32) * 5 / 9;
		}
		display(result);
	}

	private void warnUser() {
		Alert alert= new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error occured");
		alert.setHeaderText("Invalid temperature entered");
		alert.setContentText("Please enter a valid temperature.");
		alert.show();
	}

	private void display(double result) {
		String n= isC_To_F? " F":" C";
		System.out.println("The new temperature is "+ result + n);

		Alert alert= new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Result");
		alert.setContentText("The new temperature is "+ result + n);
		alert.show();
	}

}
