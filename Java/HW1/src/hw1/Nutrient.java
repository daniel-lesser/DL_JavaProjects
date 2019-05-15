//Daniel Lesser dlesser
package hw1;

public class Nutrient {

	// Creating all member variables for this class
	String nutrientCode;
	String nutrientName;
	
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

	public String getNutrientUom() {
		return nutrientUom;
	}

	public void setNutrientUom(String nutrientUom) {
		this.nutrientUom = nutrientUom;
	}

	String nutrientUom;

	// Single constructor required for this class
	public Nutrient(String nutrientCode, String nutrientName, String nutrientUom) {
		super();
		this.nutrientCode = nutrientCode;
		this.nutrientName = nutrientName;
		this.nutrientUom = nutrientUom;
	}

}
