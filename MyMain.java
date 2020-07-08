package com.SumedhaComp.javafxapp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class MyMain extends Application {

	public static void main(String[] args)
	{
		System.out.println("main");//but this main method is not part of the lifecycle methods of our application
		launch(args);
	}

	@Override
	public void init() throws Exception {
		System.out.println("init");//init method creates an instance of our application i.e.,initializes the application
		super.init();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		System.out.println("start");//starts the application and shows it to the user
		FXMLLoader loader = new
				FXMLLoader(getClass().getResource("app_layout.fxml"));//connects this java code to the fxml file
		VBox rootNode = loader.load();

		MenuBar menuBar=createMenu();
		rootNode.getChildren().add(0,menuBar);

		Scene scene = new Scene(rootNode);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Temperature Converter Tool");
		//primaryStage.setResizable(false); makes the window of a fixed size which cannot be changed
		primaryStage.show();
	}

	private MenuBar createMenu()
	{
		Menu fileMenu=new Menu("File");
		MenuItem newMenuItem=new MenuItem("New");

		newMenuItem.setOnAction(event -> System.out.println("New Menu Item Clicked"));

		SeparatorMenuItem separatorMenuItem= new SeparatorMenuItem();
		MenuItem quitMenuItem=new MenuItem("Quit");

		quitMenuItem.setOnAction(event -> {
			//System.out.println("Quit Menu Item Clicked");
			Platform.exit();
			System.exit(0);
		});

		fileMenu.getItems().addAll(newMenuItem,separatorMenuItem ,quitMenuItem);

		Menu helpMenu=new Menu("Help");
		MenuItem aboutApp=new MenuItem("About");

		aboutApp.setOnAction(event -> aboutApplication());

		helpMenu.getItems().addAll(aboutApp);

		MenuBar menuBar=new MenuBar();
		menuBar.getMenus().addAll(fileMenu,helpMenu);
		return menuBar;
	}

	private void aboutApplication() {
		Alert alertDialog= new Alert(Alert.AlertType.INFORMATION);
		alertDialog.setTitle("About");
		alertDialog.setHeaderText("Java App");
		alertDialog.setContentText("I am a beginner and I am learning. This is exciting. You should try it too!");

		ButtonType yesbtn=new ButtonType("Yes");
		ButtonType nobtn=new ButtonType("No");
		alertDialog.getButtonTypes().setAll(yesbtn,nobtn);
		Optional<ButtonType> clickedbtn= alertDialog.showAndWait();

		if(clickedbtn.isPresent() && clickedbtn.get() == yesbtn){
			System.out.println("So you're interested in trying too! Let's get started!");
		}
		else if (clickedbtn.isPresent() && clickedbtn.get()== nobtn){
			System.out.println("Try it. It is fun!");
		}

		//alertDialog.show();
	}

	@Override
	public void stop() throws Exception {
		System.out.println("stop");//called when the application is stopped and thus is about to shut down
		super.stop();
	}
}
