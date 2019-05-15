//Daniel Lesser / dlesser
package hw3;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import hw3.NutriProfiler.PhysicalActivityEnum;
import hw3.Product.ProductNutrient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
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

			try {
				if (NutriByte.view.genderComboBox.getSelectionModel().getSelectedItem() != null) {
					String gender = NutriByte.view.genderComboBox.getSelectionModel().getSelectedItem();

					float age = 0f;
					float weight = 0f;
					float height = 0f;

					if (NutriByte.view.ageTextField.getText() != null
							&& !NutriByte.view.ageTextField.getText().equalsIgnoreCase("")) {
						try {
							age = Float.valueOf(NutriByte.view.ageTextField.getText());
							if (age < 0) {
								throw new InvalidProfileException("Age must be a positive number");
							}
						} catch (NumberFormatException e) {
							throw new InvalidProfileException("Incorrect age input.  Must be a number");
						}
					} else {
						throw new InvalidProfileException("Missing age information");
					}
					//
					if (NutriByte.view.weightTextField.getText() != null
							&& !NutriByte.view.weightTextField.getText().equalsIgnoreCase("")) {
						try {
							weight = Float.valueOf(NutriByte.view.weightTextField.getText());
							if (weight < 0) {
								throw new InvalidProfileException("Weight must be a positive number");
							}
						} catch (NumberFormatException e) {
							throw new InvalidProfileException("Incorrect weight input.  Must be a number");
						}
					} else {
						throw new InvalidProfileException("Missing weight information");
					}
					//
					if (NutriByte.view.heightTextField.getText() != null
							&& !NutriByte.view.heightTextField.getText().equalsIgnoreCase("")) {
						try {
							height = Float.valueOf(NutriByte.view.heightTextField.getText());
							if (height < 0) {
								throw new InvalidProfileException("Height must be a positive number");
							}
						} catch (NumberFormatException e) {
							throw new InvalidProfileException("Incorrect height input.  Must be a number");
						}
					} else {
						throw new InvalidProfileException("Missing height information");
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
					NutriByte.view.recommendedNutrientsTableView.setItems(NutriByte.person.recommendedNutrientsList); // changed
																														// from
																														// NutriProfiler.recommendedNutrientsList
																														// for
																														// HW3
					NutriByte.view.recommendedNutrientsTableView.getSelectionModel().selectLast();
				} else { // if no gender selected
					throw new InvalidProfileException("Missing gender information");
				}
			}

			catch (InvalidProfileException e) {
				// do nothing additional beside throw initial messages

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
			NutriByte.setTrue = false;
			try {
				// window for user to select a file
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Select File");
				fileChooser.setInitialDirectory(new File("profiles"));
				fileChooser.getExtensionFilters().addAll(new ExtensionFilter("CSV Files", "*.csv"),
						new ExtensionFilter("XML Files", "*.xml"), new ExtensionFilter("All Files", "*.*"));

				File file = null;
				if ((file = fileChooser.showOpenDialog(new Stage())) != null) {
					try {
						NutriByte.person.dietProductsList.clear();
						NutriByte.person.dietNutrientsMap.clear();
					} catch (Exception e) {
						// do nothing if the lists were already clear.
					}

					NutriByte.model.readProfile(file.getAbsolutePath()); // reads the profile
					// populates GUI with profile
					NutriByte.view.recommendedNutrientsTableView.getItems().clear();

					if (NutriByte.person != null) {
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
						NutriByte.view.ageTextField.setStyle("-fx-text-inner-color: black;");
						NutriByte.view.weightTextField.setText(Float.toString(NutriByte.person.weight));
						NutriByte.view.weightTextField.setStyle("-fx-text-inner-color: black;");
						NutriByte.view.heightTextField.setText(Float.toString(NutriByte.person.height));
						NutriByte.view.heightTextField.setStyle("-fx-text-inner-color: black;");
						NutriByte.view.physicalActivityComboBox.setValue(activityLevelName);
						NutriByte.view.ingredientsToWatchTextArea.setText(NutriByte.person.ingredientsToWatch);
						// populating the recommended nutrients list
						NutriProfiler.createNutriProfile(NutriByte.person);
						NutriByte.view.recommendedNutrientsTableView
								.setItems(NutriByte.person.recommendedNutrientsList); // changed from
																						// NutriProfiler.recommendedNutrientsList
																						// for HW3
						NutriByte.view.recommendedNutrientsTableView.getSelectionModel().selectLast();
						
						try {
							NutriByte.model.searchResultsList.add(NutriByte.person.dietProductsList.get(0));
							NutriByte.view.productsComboBox.setItems(NutriByte.model.searchResultsList);
							NutriByte.view.productsComboBox.getSelectionModel().selectFirst();
						}
						catch (Exception e) {
							//do nothing if the user didn't provide any diets product list, but allow program to continue.
						}

						
					}
					NutriByte.view.root.setCenter(NutriByte.view.nutriTrackerPane);
					NutriByte.person.populateDietNutrientMap();
					NutriByte.view.dietProductsTableView.setItems(NutriByte.person.dietProductsList);

					NutriByte.view.nutriChart.updateChart();

				}
			}
			// clear all the data views if profile failed to register
			catch (InvalidProfileException e) {
				// Quadrant 1
				NutriByte.view.genderComboBox.getSelectionModel().select(null);
				NutriByte.view.ageTextField.setText(null);
				NutriByte.view.weightTextField.setText(null);
				NutriByte.view.heightTextField.setText(null);
				NutriByte.view.physicalActivityComboBox.getSelectionModel().select(null);
				NutriByte.view.ingredientsToWatchTextArea.setText(null);
				NutriByte.view.recommendedNutrientsTableView.getItems().clear();

				// Quadrant 2

				// Quadrant 3

				// Quadrant 4
				NutriByte.view.nutriChart.clearChart();
			}

			NutriByte.setTrue = true;
		}
	}

	// opens up file chooser, takes file name entry, takes person-data from quadrant
	// 1, diet data from region 3, and saves it to a new profile file. performed all
	// required validation and exception handling before saving. calls writefile from csvfiler.
	class SaveMenuItemHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			boolean flag = true;
			if (NutriByte.view.genderComboBox.getSelectionModel().getSelectedItem() != "Male"
					&& NutriByte.view.genderComboBox.getSelectionModel().getSelectedItem() != "Female") {
				flag = false;
				new InvalidProfileException("Missing gender information");
			}
			try {
				float tempAge = Float.valueOf(NutriByte.view.ageTextField.getText());
				if (tempAge < 0) {
					new InvalidProfileException("Invalid age information.  Must be a positive number");
					flag = false;
				}
			}
			catch (Exception e) {
				flag = false;
				new InvalidProfileException("Invalid age information.  Must be a number");
			}
			try {
				float tempWeight = Float.valueOf(NutriByte.view.weightTextField.getText());
				if (tempWeight < 0) {
					flag = false;
					new InvalidProfileException("Invalid weight information.  Must be a positive number");
				}
			}
			catch (Exception e) {
				flag = false;
				new InvalidProfileException("Invalid weight information.  Must be a number");
			}
			try {
				float tempHeight = Float.valueOf(NutriByte.view.heightTextField.getText());
				if (tempHeight<0) {
					flag = false;
					new InvalidProfileException("Invalid height information.  Must be a positive number");
				}
			}
			catch (Exception e) {
				flag = false;
				new InvalidProfileException("Invalid height information.  Must be a number");
			}
			
			if (flag == true) {
				try {
					// window for user to select a file
					FileChooser fileChooser = new FileChooser();
					fileChooser.setTitle("Save File");
					fileChooser.setInitialDirectory(new File("profiles"));
					fileChooser.getExtensionFilters().addAll(new ExtensionFilter("CSV Files", "*.csv"),
							new ExtensionFilter("XML Files", "*.xml"), new ExtensionFilter("All Files", "*.*"));

					File file = null;
					if ((file = fileChooser.showSaveDialog(new Stage())) != null) {
						NutriByte.model.writeProfile(file.getAbsolutePath());
					}
					
				}
				
				catch (InvalidProfileException e) {
					new InvalidProfileException("Your profile is incomplete.  Please fix before saving");
				}
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
			try {
				NutriByte.person.dietProductsList.clear();
				NutriByte.person.dietNutrientsMap.clear();
			} catch (Exception e) {
				// do nothing if the lists were already clear.
			}
			NutriByte.view.recommendedNutrientsTableView.getItems().clear();

			// clearing up other data
			NutriByte.view.nutriChart.clearChart();
			NutriByte.view.productSearchTextField.clear();
			NutriByte.view.nutrientSearchTextField.clear();
			NutriByte.view.ingredientSearchTextField.clear();
			NutriByte.view.productsComboBox.setItems(null);
			NutriByte.view.productNutrientsTableView.setItems(null);
			NutriByte.view.productIngredientsTextArea.clear();

			NutriByte.view.dietProductsTableView.setItems(null);
			NutriByte.view.dietServingSizeTextField.clear();
			NutriByte.view.dietHouseholdSizeTextField.clear();

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

	// hw3 additional handlers
	// this button handles all search functionality for quadrant 2: product
	// nutrients/quantity/uom
	class SearchButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			NutriByte.model.searchResultsList.clear();
			String productSearch = NutriByte.view.productSearchTextField.getText();
			String nutrientSearch = NutriByte.view.nutrientSearchTextField.getText();
			String ingredientSearch = NutriByte.view.ingredientSearchTextField.getText();

			// go through the nutrientsMap to find out which nutrients match
			Map<String, Nutrient> nutrientsMatch = new HashMap<String, Nutrient>();
			for (Map.Entry<String, Nutrient> nut : Model.nutrientsMap.entrySet()) {
				if (nut.getValue().getNutrientName().toLowerCase().contains(nutrientSearch)) {
					nutrientsMatch.put(nut.getKey(), nut.getValue());
				}
			}

			// searches through the productsMap to find products that contain the user
			// inputs
			// for productName, Nutrients, and Ingredients.
			for (Map.Entry<String, Product> p : Model.productsMap.entrySet()) {
				Product pTemp = p.getValue(); // for debugging purposes
				boolean productFlag = false;
				boolean nutrientFlag = false;
				boolean ingredientFlag = false;

				// checks for product match
				if (productSearch.equals("") || productSearch == null) {
					productFlag = true;
				} else if (p.getValue().getProductName().toLowerCase().contains(productSearch.toLowerCase())) {
					productFlag = true;
				}
				// checks if the nutrient is one of the ones searching for and if value > 0
				if (nutrientSearch.equals("") || nutrientSearch == null) {
					nutrientFlag = true;
				} else {
					for (Map.Entry<String, ProductNutrient> n : p.getValue().getProductNutrients().entrySet()) {
						if (nutrientsMatch.containsKey(n.getKey()) && n.getValue().getNutrientQuantity() > 0) {
							nutrientFlag = true;
						}
					}
				}
				// checks if the ingredient is one of the ones searching for and if value > 0
				if (ingredientSearch.equals("") || ingredientSearch == null) {
					ingredientFlag = true;
				} else {
					if (p.getValue().getIngredients().toLowerCase().contains(ingredientSearch.toLowerCase())) {
						ingredientFlag = true;
					}
				}

				// adds the product into a temporary map if all conditions are true
				if (productFlag == true && nutrientFlag == true && ingredientFlag == true) {
					NutriByte.model.searchResultsList.add(p.getValue());
				}
			}

			if (NutriByte.model.searchResultsList.size() > 0) {
				// now that we have a list of products, we need to add them to the drop down
				// menu
				NutriByte.view.productsComboBox.setItems(NutriByte.model.searchResultsList);
				NutriByte.view.productsComboBox.getSelectionModel().selectFirst();
				NutriByte.view.productIngredientsTextArea.setText("Product Ingredients: "
						+ NutriByte.view.productsComboBox.getSelectionModel().getSelectedItem().getIngredients());

				// this is to grab the product nutrients of the first item in the products list.
				// need to convert into an observable array list because you cannot put a map in
				// will need to write more code elsewhere to get it to dynamically update each
				// time
				// you select a new item in the list
				ObservableList<Product.ProductNutrient> myProductNuts = FXCollections
						.observableArrayList(NutriByte.model.searchResultsList.get(0).getProductNutrients().values());
				// populates the quadrant 2 tableview with the productNutrients
				NutriByte.view.productNutrientsTableView.setItems(myProductNuts);
				NutriByte.view.searchResultSizeLabel.setText(NutriByte.model.searchResultsList.size()+ " product(s) found");
			}

		}

	}

	class ClearButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent actionEvent) {

			NutriByte.view.productSearchTextField.clear();
			NutriByte.view.nutrientSearchTextField.clear();
			NutriByte.view.ingredientSearchTextField.clear();
			NutriByte.view.productsComboBox.setItems(null);
			NutriByte.view.productNutrientsTableView.setItems(null);
			NutriByte.view.productIngredientsTextArea.clear();

			NutriByte.view.dietServingSizeTextField.clear();
			NutriByte.view.dietHouseholdSizeTextField.clear();
			NutriByte.view.searchResultSizeLabel.setText("");

		}
	}
	
	class CloseButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			NutriByte.view.root.setCenter(NutriByte.view.setupWelcomeScene());
			NutriByte.person = null;
			
		}
		
	}

	class AddDietButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent actionEvent) {

			try {
				Product p = NutriByte.view.productsComboBox.getSelectionModel().getSelectedItem();

				if (NutriByte.view.dietServingSizeTextField.getText().equals("")
						&& NutriByte.view.dietHouseholdSizeTextField.getText().equals("")) {
					NutriByte.person.dietProductsList.add(p);
					for (ObservableMap.Entry<String, Product.ProductNutrient> pn : p.getProductNutrients().entrySet()) {

						float tempNutrientQuant = p.getServingSize() * pn.getValue().getNutrientQuantity() / 100;
						if (NutriByte.person.dietNutrientsMap.containsKey(pn.getValue().getNutrientCode())) {
							float original = NutriByte.person.dietNutrientsMap.get(pn.getKey()).getNutrientQuantity();
							original += tempNutrientQuant;
							NutriByte.person.dietNutrientsMap.get(pn.getKey()).setNutrientQuantity(original);

						} else {
							RecommendedNutrient tempNutrient = new RecommendedNutrient(pn.getValue().getNutrientCode(),
									tempNutrientQuant);
							NutriByte.person.dietNutrientsMap.put(tempNutrient.getNutrientCode(), tempNutrient);
						}
					}

				}

				else if ((!NutriByte.view.dietServingSizeTextField.getText().equals("")
						&& NutriByte.view.dietHouseholdSizeTextField.getText().equals(""))
						|| !NutriByte.view.dietServingSizeTextField.getText().equals("")
								&& !NutriByte.view.dietHouseholdSizeTextField.getText().equals("")) {

					float tempServ = p.getServingSize();
					float tempHHS = p.getHouseholdSize();
					float newServ = Float.parseFloat(NutriByte.view.dietServingSizeTextField.getText());

					float newHHS = tempHHS / tempServ * newServ;

					Product p2 = new Product(p, newServ, newHHS);
					p2.setServingSize(newServ);
					p2.setHouseholdSize(newHHS);
					NutriByte.person.dietProductsList.add(p2);

					for (ObservableMap.Entry<String, Product.ProductNutrient> pn : p2.getProductNutrients()
							.entrySet()) {
						float tempNutrientQuant = p2.getServingSize() * pn.getValue().getNutrientQuantity() / 100;
						if (NutriByte.person.dietNutrientsMap.containsKey(pn.getValue().getNutrientCode())) {
							float original = NutriByte.person.dietNutrientsMap.get(pn.getKey()).getNutrientQuantity();
							original += tempNutrientQuant;
							NutriByte.person.dietNutrientsMap.get(pn.getKey()).setNutrientQuantity(original);

						} else {
							RecommendedNutrient tempNutrient = new RecommendedNutrient(pn.getValue().getNutrientCode(),
									tempNutrientQuant);
							NutriByte.person.dietNutrientsMap.put(tempNutrient.getNutrientCode(), tempNutrient);
						}
					}

				}

				else if (NutriByte.view.dietServingSizeTextField.getText().equals("")
						&& !NutriByte.view.dietHouseholdSizeTextField.getText().equals("")) {

					float tempServ = p.getServingSize();
					float tempHHS = p.getHouseholdSize();
					float newHHS = Float.parseFloat(NutriByte.view.dietHouseholdSizeTextField.getText());

					float newServ = tempServ / tempHHS * newHHS;

					Product p2 = new Product(p, newServ, newHHS);
					p2.setServingSize(newServ);
					p2.setHouseholdSize(newHHS);
					NutriByte.person.dietProductsList.add(p2);

					for (ObservableMap.Entry<String, Product.ProductNutrient> pn : p2.getProductNutrients()
							.entrySet()) {

						float tempNutrientQuant = p2.getServingSize() * pn.getValue().getNutrientQuantity() / 100;
						if (NutriByte.person.dietNutrientsMap.containsKey(pn.getValue().getNutrientCode())) {
							float original = NutriByte.person.dietNutrientsMap.get(pn.getKey()).getNutrientQuantity();
							original += tempNutrientQuant;
							NutriByte.person.dietNutrientsMap.get(pn.getKey()).setNutrientQuantity(original);

						} else {
							RecommendedNutrient tempNutrient = new RecommendedNutrient(pn.getValue().getNutrientCode(),
									tempNutrientQuant);
							NutriByte.person.dietNutrientsMap.put(tempNutrient.getNutrientCode(), tempNutrient);
						}
					}

				}

				NutriByte.view.dietProductsTableView.setItems(NutriByte.person.dietProductsList);
				NutriByte.view.nutriChart.updateChart();
			}

			catch (Exception e) {
				new InvalidProfileException("Your input is invalid.  Please complete your profile \n "
						+ "and choose a valid input before adding products to your diet.");
			}

		}
	}

	class RemoveDietButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent actionEvent) {
			try {
				Product pToRemove = NutriByte.view.dietProductsTableView.getSelectionModel().getSelectedItem();
				NutriByte.person.dietProductsList.remove(pToRemove);

				for (ObservableMap.Entry<String, Product.ProductNutrient> pn : pToRemove.getProductNutrients()
						.entrySet()) {

					float tempNutrientQuant = pToRemove.getServingSize() * pn.getValue().getNutrientQuantity() / 100;
					if (NutriByte.person.dietNutrientsMap.containsKey(pn.getValue().getNutrientCode())) {
						float original = NutriByte.person.dietNutrientsMap.get(pn.getKey()).getNutrientQuantity();
						original -= tempNutrientQuant;
						NutriByte.person.dietNutrientsMap.get(pn.getKey()).setNutrientQuantity(original);

					}
				}

				NutriByte.view.dietProductsTableView.setItems(NutriByte.person.dietProductsList);
				NutriByte.view.nutriChart.updateChart();
			} catch (Exception e) {
				new InvalidProfileException("Nothing selected to remove!");
			}

		}
	}

}
