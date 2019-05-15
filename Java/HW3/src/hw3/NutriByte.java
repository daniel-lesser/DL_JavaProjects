//Daniel Lesser / dlesser
package hw3;

import hw3.NutriProfiler.PhysicalActivityEnum;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class NutriByte extends Application {
	static Model model = new Model(); // made static to make accessible in the controller
	static View view = new View(); // made static to make accessible in the controller
	static Person person; // made static to make accessible in the controller

	Controller controller = new Controller(); // all event handlers

	/**
	 * Uncomment the following three lines if you want to try out the full-size data
	 * files
	 */
	static final String PRODUCT_FILE = "data/Products.csv";
	static final String NUTRIENT_FILE = "data/Nutrients.csv";
	static final String SERVING_SIZE_FILE = "data/ServingSize.csv";

	/**
	 * The following constants refer to the data files to be used for this
	 * application
	 */
//	static final String PRODUCT_FILE = "data/Nutri2Products.csv";
//	static final String NUTRIENT_FILE = "data/Nutri2Nutrients.csv";
//	static final String SERVING_SIZE_FILE = "data/Nutri2ServingSize.csv";

	static final String NUTRIBYTE_IMAGE_FILE = "NutriByteLogo.png"; // Refers to the file holding NutriByte logo image

	static final String NUTRIBYTE_PROFILE_PATH = "profiles"; // folder that has profile data files

	static final int NUTRIBYTE_SCREEN_WIDTH = 1015;
	static final int NUTRIBYTE_SCREEN_HEIGHT = 675;

	@Override
	public void start(Stage stage) throws Exception {
		model.readProducts(PRODUCT_FILE);
		model.readNutrients(NUTRIENT_FILE);
		model.readServingSizes(SERVING_SIZE_FILE);
		view.setupMenus();
		view.setupNutriTrackerGrid();
		view.root.setCenter(view.setupWelcomeScene());
		Background b = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
		view.root.setBackground(b);
		Scene scene = new Scene(view.root, NUTRIBYTE_SCREEN_WIDTH, NUTRIBYTE_SCREEN_HEIGHT);
		view.root.requestFocus(); // this keeps focus on entire window and allows the textfield-prompt to be
									// visible
		setupBindings();
		stage.setTitle("NutriByte 2.0");
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	void setupBindings() {
		view.newNutriProfileMenuItem.setOnAction(controller.new NewMenuItemHandler());
		view.openNutriProfileMenuItem.setOnAction(controller.new OpenMenuItemHandler());
		view.exitNutriProfileMenuItem.setOnAction(event -> Platform.exit());
		view.aboutMenuItem.setOnAction(controller.new AboutMenuItemHandler());

		view.recommendedNutrientNameColumn.setCellValueFactory(recommendedNutrientNameCallback);
		view.recommendedNutrientQuantityColumn.setCellValueFactory(recommendedNutrientQuantityCallback);
		view.recommendedNutrientUomColumn.setCellValueFactory(recommendedNutrientUomCallback);

		view.createProfileButton.setOnAction(controller.new RecommendNutrientsButtonHandler());

		// hw3 new bindings -->>GO CREATE THESE HANDLER/CALLBACKS
		view.searchButton.setOnAction(controller.new SearchButtonHandler());
		view.clearButton.setOnAction(controller.new ClearButtonHandler());
		view.addDietButton.setOnAction(controller.new AddDietButtonHandler());
		view.removeDietButton.setOnAction(controller.new RemoveDietButtonHandler());
		view.closeNutriProfileMenuItem.setOnAction(controller.new CloseButtonHandler());
		view.saveNutriProfileMenuItem.setOnAction(controller.new SaveMenuItemHandler());

		// for quadrant 2 columns
		view.productNutrientNameColumn.setCellValueFactory(productNutrientNameCallback);
		view.productNutrientQuantityColumn.setCellValueFactory(productNutrientQuantityCallback);
		view.productNutrientUomColumn.setCellValueFactory(productNutrientUomCallback);

		// for quadrant 3 columns. turn on as you write the callbacks
		view.dietProductNameColumn.setCellValueFactory(dietProductNameCallBack);
		view.dietServingSizeColumn.setCellValueFactory(dietServingSizeCallBack);
		view.dietServingUomColumn.setCellValueFactory(dietServingUomCallBack);
		view.dietHouseholdSizeColumn.setCellValueFactory(dietHouseholdSizeCallBack);
		view.dietHouseholdUomColumn.setCellValueFactory(dietHouseholdUomCallBack);

		// adding listener for the profile fields
		profileBind.addListener((observable, oldValue, newValue) -> {
			// System.out.println("listening");
		});

		// this is here to update the lower right quadrant's headers and update the
		// product nutrients in lower left
		view.productsComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {

			@Override
			public void changed(ObservableValue<? extends Product> observable, Product oldValue, Product newValue) {

				try {
					ObservableList<Product.ProductNutrient> nuts = FXCollections
							.observableArrayList(newValue.getProductNutrients().values());
					view.productNutrientsTableView.setItems(nuts);
					view.dietServingUomLabel.setText(newValue.getServingUom());
					view.dietHouseholdUomLabel.setText(newValue.getHouseholdUom());
					view.productIngredientsTextArea.setText("Product Ingredients: " + newValue.getIngredients());
					view.servingSizeLabel
							.setText((String.format("%.2f %s", newValue.getServingSize(), newValue.getServingUom())));
					view.householdSizeLabel.setText(
							(String.format("%.2f %s", newValue.getHouseholdSize(), newValue.getHouseholdUom())));
					
				} catch (NullPointerException e) {
					// clear out contents if non-valid search item
					view.productNutrientsTableView.setItems(null);
					view.dietServingUomLabel.setText(null);
					view.dietHouseholdUomLabel.setText(null);
					view.productIngredientsTextArea.setText(null);
					view.servingSizeLabel.setText(null);
					view.householdSizeLabel.setText((null));

				}

			}

		});

	}

	static boolean setTrue = true;
	// bindings for the red text / auto-update of quadrant 1 profile recommended
	// nutrients/text boxes
	ObjectBinding<String> profileBind = new ObjectBinding<>() {

		{
			super.bind(NutriByte.view.ageTextField.textProperty(), NutriByte.view.weightTextField.textProperty(),
					NutriByte.view.heightTextField.textProperty(),
					NutriByte.view.genderComboBox.getSelectionModel().selectedItemProperty(),
					NutriByte.view.physicalActivityComboBox.getSelectionModel().selectedItemProperty());
		}

		@Override
		protected String computeValue() {
			if (setTrue) {
				float age = 0f, weight = 0f, height = 0f;

				// get the activity levels
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

				try { // age
					age = Float.valueOf(NutriByte.view.ageTextField.getText());
					if (age < 0) { // handle it here
						NutriByte.view.ageTextField.setStyle("-fx-text-inner-color: red;");
					} else {
						NutriByte.view.ageTextField.setStyle("-fx-text-inner-color: black;");
					}
				} catch (NumberFormatException e) {
					NutriByte.view.ageTextField.setStyle("-fx-text-inner-color: red;");
				}
				try { // weight
					weight = Float.valueOf(NutriByte.view.weightTextField.getText());
					if (weight < 0) {
						NutriByte.view.weightTextField.setStyle("-fx-text-inner-color: red;");
					} else {
						NutriByte.view.weightTextField.setStyle("-fx-text-inner-color: black;");
					}
				} catch (NumberFormatException e) {
					NutriByte.view.weightTextField.setStyle("-fx-text-inner-color: red;");
				}
				try { // height
					height = Float.valueOf(NutriByte.view.heightTextField.getText());
					if (height < 0) {
						NutriByte.view.heightTextField.setStyle("-fx-text-inner-color: red;");
					} else {
						NutriByte.view.heightTextField.setStyle("-fx-text-inner-color: black;");
					}
				} catch (NumberFormatException e) {
					NutriByte.view.heightTextField.setStyle("-fx-text-inner-color: red;");
				}

				String ingredientsToWatch = NutriByte.view.ingredientsToWatchTextArea.getSelectedText();

				// checks that gender is selected. If so, creates the person
				if (NutriByte.view.genderComboBox.getSelectionModel().getSelectedItem() != null && age > 0 && weight > 0
						&& height > 0) {
					String gender = NutriByte.view.genderComboBox.getSelectionModel().getSelectedItem();

					if (gender.equalsIgnoreCase("female")) {
						Person newperson = new Female(age, weight, height, activity_f, ingredientsToWatch);
						try {
							newperson.dietProductsList = NutriByte.person.dietProductsList;
							newperson.dietNutrientsMap = NutriByte.person.dietNutrientsMap;
						}
						catch (Exception e) {
							//do nothing because the prior list was empty
						}
						NutriByte.person = newperson;

					} else {
						Person newperson = new Male(age, weight, height, activity_f, ingredientsToWatch);
						try {
							newperson.dietProductsList = NutriByte.person.dietProductsList;
							newperson.dietNutrientsMap = NutriByte.person.dietNutrientsMap;							
						}
						catch (Exception e) {
							//do nothing because the prior list was empty
						}
						NutriByte.person = newperson;
					}

					NutriByte.view.recommendedNutrientsTableView.getItems().clear();
					NutriProfiler.createNutriProfile(NutriByte.person);
					NutriByte.view.recommendedNutrientsTableView.setItems(NutriByte.person.recommendedNutrientsList);
					NutriByte.view.nutriChart.updateChart();
					return null;
				} else
					return null;
			}
			return null;
		}

	};

	// callback features return the right nutrient property depending on which
	// nutrient is being called for
	Callback<CellDataFeatures<RecommendedNutrient, String>, ObservableValue<String>> recommendedNutrientNameCallback = new Callback<CellDataFeatures<RecommendedNutrient, String>, ObservableValue<String>>() {
		@Override
		public ObservableValue<String> call(CellDataFeatures<RecommendedNutrient, String> arg0) {
			Nutrient nutrient = Model.nutrientsMap.get(arg0.getValue().getNutrientCode());
			return nutrient.nutrientNameProperty();
		}
	};

	Callback<CellDataFeatures<RecommendedNutrient, String>, ObservableValue<String>> recommendedNutrientQuantityCallback = new Callback<CellDataFeatures<RecommendedNutrient, String>, ObservableValue<String>>() {
		@Override
		public ObservableValue<String> call(CellDataFeatures<RecommendedNutrient, String> arg0) {
			return new SimpleStringProperty(String.format("%5.2f", arg0.getValue().getNutrientQuantity()));

		}
	};

	Callback<CellDataFeatures<RecommendedNutrient, String>, ObservableValue<String>> recommendedNutrientUomCallback = new Callback<CellDataFeatures<RecommendedNutrient, String>, ObservableValue<String>>() {
		@Override
		public ObservableValue<String> call(CellDataFeatures<RecommendedNutrient, String> arg0) {
			Nutrient nutrient = Model.nutrientsMap.get(arg0.getValue().getNutrientCode());
			return nutrient.nutrientUomProperty();
		}
	};

	// adding additional callbacks for HW3.
	Callback<CellDataFeatures<Product.ProductNutrient, String>, ObservableValue<String>> productNutrientNameCallback = new Callback<CellDataFeatures<Product.ProductNutrient, String>, ObservableValue<String>>() {
		@Override
		public ObservableValue<String> call(CellDataFeatures<Product.ProductNutrient, String> arg0) {
			return Model.nutrientsMap.get(arg0.getValue().getNutrientCode().toString().trim()).nutrientNameProperty();

		}
	};
	Callback<CellDataFeatures<Product.ProductNutrient, String>, ObservableValue<String>> productNutrientQuantityCallback = new Callback<CellDataFeatures<Product.ProductNutrient, String>, ObservableValue<String>>() {
		@Override
		public ObservableValue<String> call(CellDataFeatures<Product.ProductNutrient, String> arg0) {
			return new SimpleStringProperty(String.format("%5.2f", arg0.getValue().getNutrientQuantity()));

		}
	};
	Callback<CellDataFeatures<Product.ProductNutrient, String>, ObservableValue<String>> productNutrientUomCallback = new Callback<CellDataFeatures<Product.ProductNutrient, String>, ObservableValue<String>>() {
		@Override
		public ObservableValue<String> call(CellDataFeatures<Product.ProductNutrient, String> arg0) {
			return Model.nutrientsMap.get(arg0.getValue().getNutrientCode().toString().trim()).nutrientUomProperty();

		}
	};

	// 5 callbacks for lower right quadrant
	Callback<CellDataFeatures<Product, String>, ObservableValue<String>> dietProductNameCallBack = new Callback<CellDataFeatures<Product, String>, ObservableValue<String>>() {

		@Override
		public ObservableValue<String> call(CellDataFeatures<Product, String> arg0) {

			Product product = Model.productsMap.get(arg0.getValue().getNdbNumber());
			return new SimpleStringProperty(product.getProductName());

		}

	};

	Callback<CellDataFeatures<Product, Float>, ObservableValue<Float>> dietServingSizeCallBack = new Callback<CellDataFeatures<Product, Float>, ObservableValue<Float>>() {

		@Override
		public ObservableValue<Float> call(CellDataFeatures<Product, Float> arg0) {
			for (Product p : person.dietProductsList) {
				if (p.getNdbNumber().equalsIgnoreCase(arg0.getValue().getNdbNumber())
						&& p.getServingSize() == arg0.getValue().getServingSize()) {
					return (p.servingSizeProperty()).asObject();
				}
			}
			return null;

		}

	};
	Callback<CellDataFeatures<Product, String>, ObservableValue<String>> dietServingUomCallBack = new Callback<CellDataFeatures<Product, String>, ObservableValue<String>>() {

		@Override
		public ObservableValue<String> call(CellDataFeatures<Product, String> arg0) {
			Product product = Model.productsMap.get(arg0.getValue().getNdbNumber());
			return product.servingUomProperty();
		}

	};
	Callback<CellDataFeatures<Product, Float>, ObservableValue<Float>> dietHouseholdSizeCallBack = new Callback<CellDataFeatures<Product, Float>, ObservableValue<Float>>() {

		@Override
		public ObservableValue<Float> call(CellDataFeatures<Product, Float> arg0) {
			for (Product p : person.dietProductsList) {
				if (p.getNdbNumber().equalsIgnoreCase(arg0.getValue().getNdbNumber())
						&& p.getHouseholdSize() == arg0.getValue().getHouseholdSize()) {
					return (p.householdSizeProperty()).asObject();
				}
			}
			return null;

		}

	};
	Callback<CellDataFeatures<Product, String>, ObservableValue<String>> dietHouseholdUomCallBack = new Callback<CellDataFeatures<Product, String>, ObservableValue<String>>() {

		@Override
		public ObservableValue<String> call(CellDataFeatures<Product, String> arg0) {
			Product product = Model.productsMap.get(arg0.getValue().getNdbNumber());
			return product.householdUomProperty();
		}
	};

}
