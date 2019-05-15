//Daniel Lesser / dlesser
package hw3;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// will hold the information about the nutrients in a java bean property.
// Properties have a code, a name and unit of measurement. Includes
// getters/setters for member variables. Includes a default constructor that
// sets all variables to an empty string as well as a non-default constructor
// that initializes variables with passed parameters
public class Nutrient {

	private StringProperty nutrientCode = new SimpleStringProperty();
	private StringProperty nutrientName = new SimpleStringProperty();
	private StringProperty nutrientUom = new SimpleStringProperty();
	
	public Nutrient() {
		nutrientCode.set("");
		nutrientName.set("");
		nutrientUom.set("");
	}

	public Nutrient(String nutrientCode, String nutrientName, String nutrientUom) {
		super();
		this.nutrientCode.set(nutrientCode);
		this.nutrientName.set(nutrientName);
		this.nutrientUom.set(nutrientUom);
	}
	
	//getters and setters for the code String and the Property
	public final String getNutrientCode() {return nutrientCode.get();}
	public final StringProperty nutrientCodeProperty() {return nutrientCode;}
	public final void setNutrientCode(String nutrientCode) {this.nutrientCode.set(nutrientCode);}
	//

	public final String getNutrientName() {return nutrientName.get();}
	public final StringProperty nutrientNameProperty() {return nutrientName;}
	public final void setNutrientName(String nutrientName) {this.nutrientName.set(nutrientName);}
	//

	public final String getNutrientUom() {return nutrientUom.get();}
	public final StringProperty nutrientUomProperty() {return nutrientUom;}
	public final void setNutrientUom(String nutrientUom) {this.nutrientUom.set(nutrientUom);}
	//
}
