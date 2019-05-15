//Daniel Lesser / dlesser
package hw2;

import java.io.File;

import hw2.NutriProfiler.PhysicalActivityEnum;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Controller {

	// Takes all the data from the GUI controls to create a profile
	// Populates the recommendedNutrientsTableView with the profile
	// includes case handling for when cells are left empty
	class RecommendNutrientsButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {

			if (NutriByte.view.genderComboBox.getSelectionModel().getSelectedItem() != null) {
				String gender = NutriByte.view.genderComboBox.getSelectionModel().getSelectedItem();

				float age = 0f;
				float weight = 0f;
				float height = 0f;

				if (NutriByte.view.ageTextField.getText() != null
						&& !NutriByte.view.ageTextField.getText().equalsIgnoreCase("")) {
					age = Float.valueOf(NutriByte.view.ageTextField.getText());
				} else {
					age = 0.00f;
				}
				//
				if (NutriByte.view.weightTextField.getText() != null
						&& !NutriByte.view.weightTextField.getText().equalsIgnoreCase("")) {
					weight = Float.valueOf(NutriByte.view.weightTextField.getText());
				} else {
					weight = 0f;
				}
				//
				if (NutriByte.view.heightTextField.getText() != null
						&& !NutriByte.view.heightTextField.getText().equalsIgnoreCase("")) {
					height = Float.valueOf(NutriByte.view.heightTextField.getText());
				} else {
					height = 0f;
				}
				//

				float activity_f = 0f;
				if (NutriByte.view.physicalActivityComboBox.getSelectionModel().getSelectedItem() != null) {
					String activity = NutriByte.view.physicalActivityComboBox.getSelectionModel().getSelectedItem();
					for (PhysicalActivityEnum e : PhysicalActivityEnum.values()) {
						if (e.getName() == activity)
							activity_f = e.getPhysicalActivityLevel();
					}
				} else {
					activity_f = PhysicalActivityEnum.SEDENTARY.getPhysicalActivityLevel();
				}

				String ingredientsToWatch = NutriByte.view.ingredientsToWatchTextArea.getSelectedText();

				if (gender.equalsIgnoreCase("female")) {
					Person newperson = new Female(age, weight, height, activity_f, ingredientsToWatch);
					NutriByte.person = newperson;
				} else {
					Person newperson = new Male(age, weight, height, activity_f, ingredientsToWatch);
					NutriByte.person = newperson;
				}

				NutriByte.view.recommendedNutrientsTableView.getItems().clear();
				// populating the recommended nutrients list
				NutriProfiler.createNutriProfile(NutriByte.person);
				NutriByte.view.recommendedNutrientsTableView.setItems(NutriProfiler.recommendedNutrientsList);
				NutriByte.view.recommendedNutrientsTableView.getSelectionModel().selectLast();
			}
		}

	}

	// Opens the FileChooser for the user to choose a profile of .csv or .xml
	// Passes the selected file to model's readProfile() method
	// displays the profile in the GUI
	// invokes NutriProfiler's createNutriProfile() method to populate the
	// recommended Nutrients list
	class OpenMenuItemHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {

			// window for user to select a file
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Select File");
			fileChooser.setInitialDirectory(new File("profiles"));
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("CSV Files", "*.csv"),
					new ExtensionFilter("XML Files", "*.xml"), new ExtensionFilter("All Files", "*.*"));

			File file = null;
			if ((file = fileChooser.showOpenDialog(new Stage())) != null) {
				NutriByte.model.readProfile(file.getAbsolutePath()); // reads the profile
				// populates GUI with profile
				String gender;
				if (NutriByte.person instanceof Female) {
					gender = "Female";
				} else {
					gender = "Male";
				}

				String activityLevelName = " ";
				for (PhysicalActivityEnum e : PhysicalActivityEnum.values()) {
					if (e.getPhysicalActivityLevel() == NutriByte.person.physicalActivityLevel)
						activityLevelName = e.getName();
				}

				NutriByte.view.genderComboBox.getSelectionModel().select(gender);
				NutriByte.view.ageTextField.setText(String.valueOf(NutriByte.person.age)); 
				NutriByte.view.weightTextField.setText(Float.toString(NutriByte.person.weight));
				NutriByte.view.heightTextField.setText(Float.toString(NutriByte.person.height));
				NutriByte.view.physicalActivityComboBox.setValue(activityLevelName);
				NutriByte.view.ingredientsToWatchTextArea.setText(NutriByte.person.ingredientsToWatch);

				NutriByte.view.recommendedNutrientsTableView.getItems().clear();
				NutriByte.view.root.setCenter(NutriByte.view.nutriTrackerPane);

				// populating the recommended nutrients list
				NutriProfiler.createNutriProfile(NutriByte.person);
				NutriByte.view.recommendedNutrientsTableView.setItems(NutriProfiler.recommendedNutrientsList);
				NutriByte.view.recommendedNutrientsTableView.getSelectionModel().selectLast();

			}

		}
	}

	// Suppose to switch the screen from welcome screen to
	// NutriByte.view.nutriTrackerPane
	// invokes initializePrompts() method of view class
	// clears the recommended nutrients table view
	class NewMenuItemHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			NutriByte.view.nutriTrackerPane.requestFocus();
			NutriByte.view.initializePrompts();
			NutriByte.view.root.setCenter(NutriByte.view.nutriTrackerPane);
			NutriByte.view.recommendedNutrientsTableView.getItems().clear();
		}
	}

	// fully coded
	class AboutMenuItemHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("About");
			alert.setHeaderText("NutriByte");
			alert.setContentText(
					"Version 2.0 \nRelease 1.0\nCopyleft Java Nerds\nThis software is designed purely for educational purposes.\nNo commercial use intended");
			Image image = new Image(getClass().getClassLoader().getResource(NutriByte.NUTRIBYTE_IMAGE_FILE).toString());
			ImageView imageView = new ImageView();
			imageView.setImage(image);
			imageView.setFitWidth(300);
			imageView.setPreserveRatio(true);
			imageView.setSmooth(true);
			alert.setGraphic(imageView);
			alert.showAndWait();
		}
	}
}
