//Daniel Lesser / dlesser
package hw2;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// a java bean with nutrientCode and nutrientQuantity as private properties with
// public getters/setters. Includes a default constructor that sets the code to
// a blank string and a non-default constructor that initializes member
// variables with passed parameters
public class RecommendedNutrient {
	private StringProperty nutrientCode = new SimpleStringProperty();
	private FloatProperty nutrientQuantity = new SimpleFloatProperty();

	public RecommendedNutrient() {
		nutrientCode.set("");
		nutrientQuantity.set(0);
	}

	public RecommendedNutrient(String nutrientCode, float nutrientQuantity) {
		super();
		this.nutrientCode.set(nutrientCode);
		this.nutrientQuantity.set(nutrientQuantity);
	}
	
	public final String getNutrientCode() {return nutrientCode.get();}
	public final StringProperty nutrientCodeProperty() {return nutrientCode;}
	public final void setNutrientCode(String nutrientCode) {this.nutrientCode.set(nutrientCode);}
	//
	
	public final float getNutrientQuantity() {return nutrientQuantity.get();}
	public final FloatProperty nutrientQuantityProperty() {return nutrientQuantity;}
	public final void setNutrientQuantity(float nutrientQuantity) {this.nutrientQuantity.set(nutrientQuantity);}
	//

}
