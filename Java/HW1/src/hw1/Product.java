//Daniel Lesser dlesser
package hw1;

public class Product {

	// Creating all member variables for this class.
	String ndbNumber;
	String productName;
	String manufacturer;
	String ingredients;

	float servingSize;
	String servingUom;
	float householdSize;
	String householdUom;

	public String getNdbNumber() {
		return ndbNumber;
	}

	public void setNdbNumber(String ndbNumber) {
		this.ndbNumber = ndbNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public float getServingSize() {
		return servingSize;
	}

	public void setServingSize(float servingSize) {
		this.servingSize = servingSize;
	}

	public String getServingUom() {
		return servingUom;
	}

	public void setServingUom(String servingUom) {
		this.servingUom = servingUom;
	}

	public float getHouseholdSize() {
		return householdSize;
	}

	public void setHouseholdSize(float householdSize) {
		this.householdSize = householdSize;
	}

	public String getHouseholdUom() {
		return householdUom;
	}

	public void setHouseholdUom(String householdUom) {
		this.householdUom = householdUom;
	}

	// Single constructor required for this class
	public Product(String ndbNumber, String productName, String manufacturer, String ingredients) {
		super();
		this.ndbNumber = ndbNumber;
		this.productName = productName;
		this.manufacturer = manufacturer;
		this.ingredients = ingredients;
	}

}
