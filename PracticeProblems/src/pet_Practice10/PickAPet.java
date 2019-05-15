package pet_Practice10;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PickAPet extends Application {

	Pet[] pets = new Pet[3];
	Label resultLabel = new Label("Pick a Pet");
	Label[] countLabels = new Label[3];

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		setupData();
		Scene scene = new Scene(setupScreen(), 600, 225);
		primaryStage.setTitle("Pick a Pet");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void setupData() {
		pets[0] = new Cat();
		pets[1] = new Dog();
		pets[2] = new Bird();
	}

	BorderPane setupScreen() {
		BorderPane root = new BorderPane();
		String[] options = { "Cat", "Dog", "Bird" };

		Button[] buttons = new Button[3]; // to be attached to event handlers

		GridPane petsGrid = new GridPane();
		GridPane countGrid = new GridPane();

		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new Button(options[i]); // text on the buttons
			buttons[i].setPrefSize(200, 75);
			buttons[i].setFont(Font.font(30));
			petsGrid.add(buttons[i], i, 0);
		}

		//attaching buttons to event handler
		buttons[0].setOnAction(new CatHandler());
		buttons[1].setOnAction(new DogHandler());
		buttons[2].setOnAction(new BirdHandler());

		for (int i = 0; i < countLabels.length; i++) {
			countLabels[i] = new Label();
			countLabels[i].setPrefSize(200, 75);
			countLabels[i].setFont(Font.font(20));
			countLabels[i].setAlignment(Pos.CENTER);
			countLabels[i].setText(options[i] + " count: 0"); // initial text and count on labels
			countLabels[i]
					.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
			countGrid.add(countLabels[i], i, 0);
		}

		resultLabel.setFont(Font.font(20));
		resultLabel.setPrefSize(600, 75);
		resultLabel.setBackground(new Background(new BackgroundFill(Color.BISQUE, CornerRadii.EMPTY, Insets.EMPTY)));
		resultLabel.setAlignment(Pos.CENTER);
		BorderPane.setAlignment(resultLabel, Pos.CENTER);

		// attach components to the root
		root.setTop(petsGrid);
		root.setCenter(countGrid);
		root.setBottom(resultLabel);

		return root;
	}

	private class CatHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			resultLabel.setText(pets[0].talk());
			countLabels[0].setText("Cat count: " + ((Cat) pets[0]).catCount);

		}

	}

	private class DogHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			resultLabel.setText(pets[1].talk());
			countLabels[1].setText("Dog count: " + ((Dog) pets[1]).dogCount);

		}

	}

	private class BirdHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			resultLabel.setText(pets[2].talk());
			countLabels[2].setText("Bird count: " + ((Bird) pets[2]).birdCount);

		}

	}

}
