package series_practice10;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**PickASeries has three buttons in an array named buttons[] declared in setScene() method.
 * buttons[0] is for Prime, buttons[1] is for Fibonacci, and buttons[2] is for [Factorial].
 * You need to attach event handlers to these buttons. 
 * These handlers must use the three objects defined in setupData() method : 
 * series[0] for Prime, series[1] for Fibonacci, and series[2] for Factorial
 * 
 */
public class PickASeries extends Application{
	TextField inputText = new TextField("0");  //will take user input
	Label resultLabel = new Label("Enter a number and pick a Series");  //will display the result
	Series[] series = new Series[3];  //holds the series objects - Prime, Fibonacci, and Factorial

	public static void main(String[] args) {
		launch(args);
	}
	
	//DO NOT CHANGE THIS METHOD
	@Override
	public void start(Stage primaryStage) throws Exception {
		setupData();
		Scene scene = new Scene(setupScreen(), 600, 225);
		primaryStage.setTitle("Pick a Series");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	//DO NOT CHANGE THIS METHOD
	void setupData() {
		series[0] = new Prime();
		series[1] = new Fibonacci();
		series[2] = new Factorial();
	}

	
	BorderPane setupScreen() {
		Button[] buttons = new Button[3]; //to be tied to handlers

		
		//setup various GUI components
		BorderPane root = new BorderPane();
		String[] options = {"Primes", "Fibonaccis", "Factorials"};
		Background background = new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY));
		GridPane seriesGrid = new GridPane();	
		GridPane inputGrid = new GridPane();
		root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		Label inputLabel = new Label("Which number in the series?");
		inputLabel.setBackground(background);
		inputLabel.setFont(Font.font(20));
		inputLabel.setPrefSize(300, 75);
		inputText.setFont(Font.font(20));
		inputText.setAlignment(Pos.CENTER);
		inputText.setPrefSize(300, 75);
		inputGrid.add(inputLabel, 0, 0);
		inputGrid.add(inputText, 1, 0);
		inputGrid.setStyle("-fx-border-color: grey");
		seriesGrid.setStyle("-fx-border-color: grey");
		resultLabel.setFont(Font.font(20));
		resultLabel.setPrefSize(600,100);
		resultLabel.setBackground(background);
		resultLabel.setAlignment(Pos.CENTER);
		BorderPane.setAlignment(resultLabel, Pos.CENTER);

		//setup the buttons
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new Button(options[i]);
			buttons[i].setPrefSize(200, 50);
			buttons[i].setFont(Font.font(20));
			buttons[i].setWrapText(true);
			seriesGrid.add(buttons[i],  i, 0);
		}	
		
		//tying buttons to handlers
		buttons[0].setOnAction(new PrimeButtonHandler());
		buttons[1].setOnAction(new FibonnaciButtonHandler());
		buttons[2].setOnAction(new FactorialButtonHandler());
		
		//set up root
		root.setTop(seriesGrid);	
		root.setCenter(inputGrid);
		root.setBottom(resultLabel);
		return root;
	}
	
	public class PrimeButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			int primeNum = series[0].getNthNumber(Integer.parseInt(inputText.getText()));
			resultLabel.setText("The " + inputText.getText() + "th number in Primes is: " + primeNum);
			
		}
	}
	
	public class FactorialButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			int factorialNum = series[2].getNthNumber(Integer.parseInt(inputText.getText()));
			resultLabel.setText("The " + inputText.getText() + "th number in Factorials is: " + factorialNum);
			
		}
	}
	
	public class FibonnaciButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			int fibonnaciNum = series[1].getNthNumber(Integer.parseInt(inputText.getText()));
			resultLabel.setText("The " + inputText.getText() + "th number in Fibonnacis is: " + fibonnaciNum);
			
		}
	}
	
}
