//Daniel Lesser dlesser
package hw1;

public class ProductNutrient {

	// Creating all member variables for this class
	String ndbNumber;
	String nutrientCode;
	String nutrientName;
	float quantity;

	public String getNdbNumber() {
		return ndbNumber;
	}

	public void setNdbNumber(String ndbNumber) {
		this.ndbNumber = ndbNumber;
	}

	public String getNutrientCode() {
		return nutrientCode;
	}

	public void setNutrientCode(String nutrientCode) {
		this.nutrientCode = nutrientCode;
	}

	public String getNutrientName() {
		return nutrientName;
	}

	public void setNutrientName(String nutrientName) {
		this.nutrientName = nutrientName;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public String getNutrientUom() {
		return nutrientUom;
	}

	public void setNutrientUom(String nutrientUom) {
		this.nutrientUom = nutrientUom;
	}

	String nutrientUom;

	// Single constructor required for this class
	public ProductNutrient(String ndbNumber, String nutrientCode, String nutrientName, float quantity,
			String nutrientUom) {
		super();
		this.ndbNumber = ndbNumber;
		this.nutrientCode = nutrientCode;
		this.nutrientName = nutrientName;
		this.quantity = quantity;
		this.nutrientUom = nutrientUom;
	}

}
