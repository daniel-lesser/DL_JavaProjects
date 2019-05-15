//fully coded, do not turn in
//Daniel Lesser / dlesser
package hw2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class View {
	BorderPane root = new BorderPane();  //this holds the menu at the top and welcome scene or nutriTrackerPane in the center region.
	
	BorderPane nutriTrackerPane = new BorderPane();  //holds the nutriProfilerGrid in the top region. More components will be added to it in NutriByte 3.0
	GridPane nutriProfilerGrid = new GridPane();	// holds all the nutriProfiler components as required in NutriByte 2.0
	
	//topGrid components
	MenuItem newNutriProfileMenuItem= new MenuItem("New...");
	MenuItem openNutriProfileMenuItem = new MenuItem("Open...");
	MenuItem exitNutriProfileMenuItem = new MenuItem("Exit");
	MenuItem aboutMenuItem = new MenuItem("About");
	
	//nutriProfiler grid components
	ComboBox<String> genderComboBox = new ComboBox<>(); 
	TextField ageTextField = new TextField();
	TextField weightTextField = new TextField();
	TextField heightTextField = new TextField();
	ComboBox<String> physicalActivityComboBox = new ComboBox<>();
	TextArea ingredientsToWatchTextArea = new TextArea();
	
	TableView<RecommendedNutrient> recommendedNutrientsTableView = new TableView<>();
	TableColumn<RecommendedNutrient, String> recommendedNutrientNameColumn = new TableColumn<>("Nutrient");
	TableColumn<RecommendedNutrient, String> recommendedNutrientQuantityColumn = new TableColumn<>("Recommended \nquantity per day");
	TableColumn<RecommendedNutrient, String> recommendedNutrientUomColumn = new TableColumn<>("UOM");
	
	Button createProfileButton = new Button("Recommend Nutrients");
	
	//shows the opening scene
	StackPane setupWelcomeScene() {
		StackPane pane = new StackPane();
		pane.setPrefSize(NutriByte.NUTRIBYTE_SCREEN_WIDTH - 20, NutriByte.NUTRIBYTE_SCREEN_HEIGHT - 20);
		ImageView imageView = new ImageView();

		imageView.setFitHeight(pane.getPrefHeight()-10);
		imageView.setFitWidth(pane.getPrefWidth()-10);
		imageView.setPreserveRatio(true);
		imageView.setSmooth(true);
		Image image = new Image(getClass().getClassLoader().getResource(NutriByte.NUTRIBYTE_IMAGE_FILE).toString());

		imageView.setImage(image);
		pane.getChildren().add(imageView);
		return pane;
	}
	
	//sets up the menus
	void setupMenus() {
		MenuBar menuBar = new MenuBar();
		Menu nutriProfileMenu = new Menu("NutriProfile");
		Menu aboutMenu = new Menu("About");

		nutriProfileMenu.getItems().addAll(newNutriProfileMenuItem, openNutriProfileMenuItem, exitNutriProfileMenuItem);
		aboutMenu.getItems().add(aboutMenuItem);
		menuBar.getMenus().addAll(nutriProfileMenu, aboutMenu);
		root.setTop(menuBar);
	}

	
	void setupNutriTrackerGrid() {
		setupNutriProfilerGrid();
		nutriTrackerPane.setTop(nutriProfilerGrid);
		Background b1 = new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY));
		nutriProfilerGrid.setBackground(b1);
		initializePrompts();
	}
	
	@SuppressWarnings("unchecked")
	void setupNutriProfilerGrid() {
		nutriProfilerGrid.setVgap(5);
		nutriProfilerGrid.setHgap(5);
		nutriProfilerGrid.setPrefHeight(225);

		recommendedNutrientsTableView.getColumns().setAll(recommendedNutrientNameColumn, recommendedNutrientQuantityColumn, recommendedNutrientUomColumn );

		nutriProfilerGrid.add(genderComboBox, 0, 0, 2, 1);
		nutriProfilerGrid.add(ageTextField, 0, 1);
		nutriProfilerGrid.add(new Label("years"), 1, 1);
		nutriProfilerGrid.add(weightTextField, 0, 2);
		nutriProfilerGrid.add(new Label("kg"), 1, 2);
		nutriProfilerGrid.add(heightTextField, 0, 3);
		nutriProfilerGrid.add(new Label("cm"), 1, 3);
		nutriProfilerGrid.add(physicalActivityComboBox, 0, 4, 2, 1);
		nutriProfilerGrid.add(new Label("Ingredients to watch"), 0, 5);
		nutriProfilerGrid.add(ingredientsToWatchTextArea, 0, 6);
		nutriProfilerGrid.add(createProfileButton, 2, 0, 3, 1);
		nutriProfilerGrid.add(recommendedNutrientsTableView, 2, 1, 3, 6);

		// set all grid columns' width 
		genderComboBox.setPrefWidth(150);
		physicalActivityComboBox.setPrefWidth(150);
		nutriProfilerGrid.getColumnConstraints().add(new ColumnConstraints(150)); //inputs
		nutriProfilerGrid.getColumnConstraints().add(new ColumnConstraints(30)); //units
		nutriProfilerGrid.getColumnConstraints().add(new ColumnConstraints(150)); //recommendedNutrientTableview
		nutriProfilerGrid.getColumnConstraints().add(new ColumnConstraints(100)); //recommendedNutrientTableview
		nutriProfilerGrid.getColumnConstraints().add(new ColumnConstraints(50)); //recommendedNutrientTableview
		
		createProfileButton.setPrefWidth(320); 

		recommendedNutrientsTableView.getColumns().get(0).setPrefWidth(150);
		recommendedNutrientsTableView.getColumns().get(1).setPrefWidth(100);
		recommendedNutrientsTableView.getColumns().get(2).setPrefWidth(50);
		
		ingredientsToWatchTextArea.setWrapText(true);
		
		BorderPane.setMargin(nutriProfilerGrid, new Insets(5,5,5,5));
	}
	
	
	//this method clears up all the input controls and 
	//shows the prompts for the user to reenter all the inputs.
	void initializePrompts() {
		genderComboBox.getSelectionModel().clearSelection();
		ObservableList<String> genderOptions = FXCollections.observableArrayList("Female", "Male");
		genderComboBox.setItems(genderOptions);
		genderComboBox.setPromptText("Gender");

		physicalActivityComboBox.getSelectionModel().clearSelection();
		ObservableList<String> physicalActivityOptions = 
				FXCollections.observableArrayList(
						NutriProfiler.PhysicalActivityEnum.SEDENTARY.getName(), 
						NutriProfiler.PhysicalActivityEnum.LOW_ACTIVE.getName(), 
						NutriProfiler.PhysicalActivityEnum.ACTIVE.getName(), 
						NutriProfiler.PhysicalActivityEnum.VERY_ACTIVE.getName());
		physicalActivityComboBox.setItems(physicalActivityOptions);
		physicalActivityComboBox.setPromptText("Physical activity");
		
		ingredientsToWatchTextArea.clear();
		ingredientsToWatchTextArea.setPromptText("Enter ingredients to watch separated by commas");

		ageTextField.clear();
		ageTextField.setPromptText("Enter age in years");
		weightTextField.clear();
		weightTextField.setPromptText("Enter weight in kg");
		heightTextField.clear();
		heightTextField.setPromptText("Enter height in cm");
	}
}
