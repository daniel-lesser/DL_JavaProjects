//Daniel Lesser / dlesser
package hw3;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

//the product class is a java bean that has a default constructor and a constructor to 
//initialize member variables. Member variables are private properties that must 
//be accessed through getters.  It has a member variable productNutrients that has a key, 
//value pair where the key is the nutrientCode and the value is the type ProductNutrient. 
public class Product implements Cloneable {

	private StringProperty ndbNumber = new SimpleStringProperty();
	private StringProperty productName = new SimpleStringProperty();
	private StringProperty manufacturer = new SimpleStringProperty();
	private StringProperty ingredients = new SimpleStringProperty();
	private StringProperty servingUom = new SimpleStringProperty();
	private StringProperty householdUom = new SimpleStringProperty();

	private FloatProperty servingSize = new SimpleFloatProperty();
	private FloatProperty householdSize = new SimpleFloatProperty();
	private ObservableMap<String, ProductNutrient> productNutrients = FXCollections.observableHashMap();

	@Override
	public String toString() { // overriden for the searchButton productsComboBox
		return (this.getProductName() + " by " + this.getManufacturer());
	}

	@Override
	public Product clone() {
		final Product clone;
		try {
			clone = (Product) super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("Clone failed");
			throw new RuntimeException("clone failed2", e);
		}
		clone.ndbNumber = this.ndbNumber;
		clone.productName = this.productName;
		clone.manufacturer = this.manufacturer;
		clone.ingredients = this.ingredients;
		clone.servingUom = this.servingUom;
		clone.householdUom = this.householdUom;
		clone.servingSize = this.servingSize;
		clone.householdSize = this.householdSize;
		clone.productNutrients = this.productNutrients;
		return clone;

	}

	// default constructor initializes all strings to empty strings
	Product() {
		ndbNumber.set("");
		productName.set("");
		manufacturer.set("");
		ingredients.set("");
		servingUom.set("");
		householdUom.set("");
	}

	// constructor that initializes key member variables
	public Product(String ndbNumber, String productName, String manufacturer, String ingredients) {
		this.ndbNumber.set(ndbNumber);
		this.productName.set(productName);
		this.manufacturer.set(manufacturer);
		this.ingredients.set(ingredients);
	}

	// for CSVFiler adding into serving size and household size for a specific
	// person
	public Product(Product oldProd, float servingSize, float householdSize) {
		this(oldProd.getNdbNumber(), oldProd.getProductName(), oldProd.getManufacturer(), oldProd.getIngredients());

		this.servingUom.set(oldProd.getServingUom());
		this.servingSize.set(servingSize);
		this.householdUom.set(oldProd.getHouseholdUom());
		this.householdSize.set(householdSize);
		this.productNutrients = oldProd.productNutrients;
	}

	// all the getters and setters for the member variables
	public final String getNdbNumber() {
		return ndbNumber.get();
	}

	public final StringProperty ndbNumberProperty() {
		return ndbNumber;
	}

	public final void setNdbNumber(String ndbNumber) {
		this.ndbNumber.set(ndbNumber);
	}
	//

	public final String getProductName() {
		return productName.get();
	}

	public final StringProperty productNameProperty() {
		return productName;
	}

	public final void setProductName(String productName) {
		this.productName.set(productName);
	}
	//

	public final String getManufacturer() {
		return manufacturer.get();
	}

	public final StringProperty manufacturerProperty() {
		return manufacturer;
	}

	public final void setManufacturer(String manufacturer) {
		this.manufacturer.set(manufacturer);
	}
	//

	public final String getIngredients() {
		return ingredients.get();
	}

	public final StringProperty ingredientsProperty() {
		return ingredients;
	}

	public final void setIngredients(String ingredients) {
		this.ingredients.set(ingredients);
	}
	//

	public final String getServingUom() {
		return servingUom.get();
	}

	public final StringProperty servingUomProperty() {
		return servingUom;
	}

	public final void setServingUom(String servingUom) {
		this.servingUom.set(servingUom);
	}
	//

	public final String getHouseholdUom() {
		return householdUom.get();
	}

	public final StringProperty householdUomProperty() {
		return householdUom;
	}

	public final void setHouseholdUom(String householdUom) {
		this.householdUom.set(householdUom);
	}
	//

	public final float getServingSize() {
		return servingSize.get();
	}

	public final FloatProperty servingSizeProperty() {
		return servingSize;
	}

	public final void setServingSize(float servingSize) {
		this.servingSize.set(servingSize);
	}
	//

	public final float getHouseholdSize() {
		return householdSize.get();
	}

	public final FloatProperty householdSizeProperty() {
		return householdSize;
	}

	public final void setHouseholdSize(float householdSize) {
		this.householdSize.set(householdSize);
	}
	//

	public final void setProductNutrients(ObservableMap<String, ProductNutrient> productNutrients) {
		this.productNutrients = productNutrients;
	}

	public ObservableMap<String, ProductNutrient> getProductNutrients() {
		return productNutrients;
	}

	// the inner class is also a java bean that is used to store the nutrient's
	// information about the product. It has a default constructor that simply
	// initializes thenutrientCode to a blank string and another constructor to
	// initialize member variables. it has setters and getters for the member
	// variables
	public class ProductNutrient {
		private StringProperty nutrientCode = new SimpleStringProperty();
		private FloatProperty nutrientQuantity = new SimpleFloatProperty();

		public ProductNutrient() {
			nutrientCode.set("");
			nutrientQuantity.set(0);
		}

		public ProductNutrient(String nutrientCode, Float nutrientQuantity) {
			this.nutrientCode.set(nutrientCode);
			this.nutrientQuantity.set(nutrientQuantity);
		}

		public final String getNutrientCode() {
			return nutrientCode.get();
		}

		public final StringProperty nutrientCodeProperty() {
			return nutrientCode;
		}

		public final void setNutrientCode(String nutrientCode) {
			this.nutrientCode.set(nutrientCode);
		}
		//

		public final float getNutrientQuantity() {
			return nutrientQuantity.get();
		}

		public final FloatProperty nutrientQuantityProperty() {
			return nutrientQuantity;
		}

		public final void setNutrientQuantity(float nutrientQuantity) {
			this.nutrientQuantity.set(nutrientQuantity);
		}
		//
	}

//	public void addProductNut(String nutrientCode, float nutrientQuant) {
//		this.productNutrients.put(nutrientCode, new ProductNutrient(nutrientCode,nutrientQuant));
//	}

}
