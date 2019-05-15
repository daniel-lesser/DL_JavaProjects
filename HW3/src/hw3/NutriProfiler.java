//Daniel Lesser / dlesser
package hw3;

import java.util.ArrayList;
import java.util.EnumSet;

public class NutriProfiler {


	
	static final int RECOMMENDED_NUTRI_COUNT = 14;  //this includes Protein, Carbohydrate, fiber, and 11 amino acids
	static final int AGE_GROUP_COUNT = 10; 
	
	static final String ENERGY_NUTRIENT_CODE = "208"; 
	
	//enums discussed wk9 notes
	public enum NutriEnum {
		PROTEIN("203", 0), CARBOHYDRATE("205", 1), FIBER("291", 2), 
		
		//The following nutrients are aminoacids 
		HISTIDINE("512", 3), ISOLEUCINE("503", 4), LEUCINE("504", 5), LYSINE("505", 6), 
		METHIONINE("506", 7), CYSTEINE("526", 8), PHENYLALANINE("508", 9), TYROSINE("509", 10), 
		THREONINE("502", 11), TRYPTOPHAN("501", 12), VALINE("510", 13);
		
		private String nutrientCode;
		private int nutriIndex;
		NutriEnum(String nutrientCode, int nutriIndex) {
			this.nutrientCode = nutrientCode;
			this.nutriIndex = nutriIndex;
		}

		String getNutrientCode() { return nutrientCode; }
		int getNutriIndex() { return nutriIndex; }
	}

	public enum AgeGroupEnum{
		//The age group enum represents two things: highest age in years in a group, and the index to fetch the value for that age in Person's nutriConstantsTable
		MAX_AGE_3M(0.25f, 0), MAX_AGE_6M(0.5f, 1), MAX_AGE_1Y(1, 2), MAX_AGE_3Y(3, 3), MAX_AGE_8Y(8, 4), MAX_AGE_13Y(13, 5), 
		MAX_AGE_18Y(18, 6), MAX_AGE_30Y(30, 7), MAX_AGE_50Y(50, 8), MAX_AGE_ABOVE(150, 9);  //age 150 is given to provide some numeric limit. 
		private float age;
		private int ageGroupIndex;
		AgeGroupEnum(float age, int ageGroupIndex) { 
			this.age = age; 
			this.ageGroupIndex = ageGroupIndex;
		}
		float getAge() { return age; }
		int getAgeGroupIndex() { return ageGroupIndex; }
	}
	
	public enum PhysicalActivityEnum {
		//The physical activity enum represents two things: text to display in View's combo box, and the constant value to be used for calculations
		SEDENTARY("Sedentary", 1), LOW_ACTIVE("Low active", 1.1f), ACTIVE("Active", 1.25f), VERY_ACTIVE("Very active", 1.48f);
		private String name;
		private float physicalActivityLevel;

		PhysicalActivityEnum(String name, float level) { 
			this.name= name;
			physicalActivityLevel = level; 
		}
		String getName() { return name; }
		float getPhysicalActivityLevel() { return physicalActivityLevel;}
	}

	// populate the (observable) recommendedNutrientsList with energy requirements
	// and nutrient requirements
	//for HW3, changed all the recommendedNutrientsList to person.recommendedNurtientsList
	static void createNutriProfile(Person person) {
		person.recommendedNutrientsList.clear();
		
		// creating an array of nutrients and NutriEnums so I can loop through and add
		// the correct quantities for each NutriEnum rather than do it 1 by 1
		float[] nutrientReqs = person.calculateNutriRequirement();
		ArrayList<NutriEnum> nutriEnumArray = new ArrayList<NutriEnum>();
		EnumSet.allOf(NutriEnum.class).forEach(n -> nutriEnumArray.add(n));
		
		//adding the Energy Nutrient separately
		float energy = person.calculateEnergyRequirement();
		String sp1 = (ENERGY_NUTRIENT_CODE);
		float fp1 = (energy);
		RecommendedNutrient tempNut1 = new RecommendedNutrient(sp1, fp1);
		person.recommendedNutrientsList.add(tempNut1);
		
		//adds all the regular nutrients (ex-Energy)
		for (int i = 0; i < nutrientReqs.length; i++) {
			String sp = (nutriEnumArray.get(i).getNutrientCode());
			float fp = (nutrientReqs[i]);
			RecommendedNutrient tempNut = new RecommendedNutrient(sp, fp);
			person.recommendedNutrientsList.add(tempNut);
		}
		


	}
	
}
