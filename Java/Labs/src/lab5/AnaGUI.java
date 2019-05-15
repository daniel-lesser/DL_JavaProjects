//Daniel Lesser / dlesser
package lab5;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AnaGUI extends Application {

	BorderPane root = new BorderPane(); // holds the textGrid and buttonGrid
	Button findButton, clearButton, exitButton;
	Label messageLabel; // to display messages such as 'No anagrams found'
	TextField userWord; // to capture user input
	TextArea anagramsTextArea; // area to display anagrams found, it any
	Label textEnter, textAnagram, welcomeMessage; // labels

	Anagrammar ag = new Anagrammar(); // This will have all file-reading and word-anagram finding activities

	public class FindButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			anagramsTextArea.clear();
			if (userWord.getText().equals("")) {
				userWord.clear();
				anagramsTextArea.clear();
				messageLabel.setText("Please enter input");
				return;
			}
			ag.findAnagrams(userWord.getText());
			StringBuilder sb = new StringBuilder();

			if (ag.hasAnagrams == true) {

				if (ag.isInDictionary == false) {
					messageLabel.setText(userWord.getText() + " not in dictionary \n" + ag.anagramArray.length
							+ " anagram(s) found for " + userWord.getText());
				} else {
					messageLabel.setText(ag.anagramArray.length + " anagram(s) found for " + userWord.getText());

				}

				for (int i = 0; i < ag.anagramArray.length; i++) {
					sb.append(ag.anagramArray[i].toString() + "\n");
				}
				anagramsTextArea.appendText(sb.toString());

			} else {
				if (ag.isInDictionary == false) {
					messageLabel.setText(userWord.getText() + " not in dictionary \n" + "No anagrams found for "
							+ userWord.getText());
				} else {
					messageLabel.setText("No anagrams found for " + userWord.getText());
				}

			}

		}

	}

	public class ClearButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			userWord.clear();
			anagramsTextArea.clear();
			messageLabel.setText("");

		}

	}

	public class ExitButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			Platform.exit();

		}

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ag.loadWords();
		primaryStage.setTitle("Anagrammar");
		setupScreen();
		Scene scene = new Scene(root, 250, 250);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void setupScreen() {
		GridPane textGrid = new GridPane();
		GridPane buttonGrid = new GridPane();
		textEnter = new Label("Enter word");
		textAnagram = new Label("Anagrams");
		welcomeMessage = new Label("Find the Anagrams");
		messageLabel = new Label("");
		findButton = new Button("Find");
		clearButton = new Button("Clear");
		exitButton = new Button("Exit");
		anagramsTextArea = new TextArea();
		userWord = new TextField();

		textGrid.setVgap(10);
		textGrid.setHgap(10);
		buttonGrid.setHgap(10);

		textGrid.add(welcomeMessage, 0, 0, 2, 1);
		GridPane.setHalignment(welcomeMessage, HPos.CENTER);
		textGrid.add(textEnter, 0, 1);
		textGrid.add(textAnagram, 0, 2);
		textGrid.add(messageLabel, 0, 3, 2, 1);
		textGrid.add(userWord, 1, 1);
		textGrid.add(anagramsTextArea, 1, 2);

		buttonGrid.add(findButton, 0, 0);
		buttonGrid.add(clearButton, 1, 0);
		buttonGrid.add(exitButton, 2, 0);

		userWord.setPrefSize(150, 10);
		anagramsTextArea.setPrefSize(150, 100);
		findButton.setPrefSize(70, 20);
		clearButton.setPrefSize(70, 20);
		exitButton.setPrefSize(70, 20);

		root.setTop(textGrid);
		root.setCenter(buttonGrid);

		textGrid.setAlignment(Pos.CENTER);
		buttonGrid.setAlignment(Pos.CENTER);

		findButton.setOnAction(new FindButtonHandler());
		clearButton.setOnAction(new ClearButtonHandler());
		exitButton.setOnAction(new ExitButtonHandler());

	}

}
