//Daniel Lesser / dlesser
package hw2;

import java.util.ArrayList;
import java.util.EnumSet;

import hw2.NutriProfiler.NutriEnum;

//creates a Female object to be included in Persons grouping.  
//Females have slightly different nutrient and energy requirements than males
public class Female extends Person {

	float[][] nutriConstantsTableFemale = new float[][] {
			// AgeGroups: 3M, 6M, 1Y, 3Y, 8Y, 13Y, 18Y, 30Y, 50Y, ABOVE
			{ 1.52f, 1.52f, 1.2f, 1.05f, 0.95f, 0.95f, 0.71f, 0.8f, 0.8f, 0.8f }, // 0: Protein constants
			{ 60, 60, 95, 130, 130, 130, 130, 130, 130, 130 }, // 1: Carbohydrate
			{ 19, 19, 19, 19, 25, 26, 26, 25, 25, 21 }, // 2: Fiber constants
			{ 36, 36, 32, 21, 16, 15, 14, 14, 14, 14 }, // 3: Histidine
			{ 88, 88, 43, 28, 22, 21, 19, 19, 19, 19 }, // 4: isoleucine
			{ 156, 156, 93, 63, 49, 47, 44, 42, 42, 42 }, // 5: leucine
			{ 107, 107, 89, 58, 46, 43, 40, 38, 38, 38 }, // 6: lysine
			{ 59, 59, 43, 28, 22, 21, 19, 19, 19, 19 }, // 7: methionine
			{ 59, 59, 43, 28, 22, 21, 19, 19, 19, 19 }, // 8: cysteine
			{ 135, 135, 84, 54, 41, 38, 35, 33, 33, 33 }, // 9: phenylalanine
			{ 135, 135, 84, 54, 41, 38, 35, 33, 33, 33 }, // 10: phenylalanine
			{ 73, 73, 49, 32, 24, 22, 21, 20, 20, 20 }, // 11: threonine
			{ 28, 28, 13, 8, 6, 6, 5, 5, 5, 5 }, // 12: tryptophan
			{ 87, 87, 58, 37, 28, 27, 24, 24, 24, 24 } // 13: valine
	};

	//constructor for female
	Female(float age, float weight, float height, float physicalActivityLevel, String ingredientsToAvoid) {
		super(age, weight, height, physicalActivityLevel, ingredientsToAvoid);
		initializeNutriConstantsTable();

	}

	// calculates the required energy levels for various age groups using Table 2
	// recommendations from the National Academies Press
	@Override
	float calculateEnergyRequirement() {
		float energyReq = 0;
		switch (this.ageGroup) {
		case MAX_AGE_3M: {
			energyReq = 89 * this.weight + 75;
			break;
		}
		case MAX_AGE_6M: {
			energyReq = 89 * this.weight - 44;
			break;
		}
		case MAX_AGE_1Y: {
			energyReq = 89 * this.weight - 78;
			break;
		}
		case MAX_AGE_3Y: {
			energyReq = 89 * this.weight - 80;
			break;
		}
		case MAX_AGE_8Y: {
			energyReq = (float) (135.3 - (30.8 * this.age)
					+ this.physicalActivityLevel * (10 * this.weight + 934 * this.height / 100) + 20);
			break;
		}
		case MAX_AGE_13Y: {
			energyReq = (float) (135.3 - (30.8 * this.age)
					+ this.physicalActivityLevel * (10 * this.weight + 934 * this.height / 100) + 20);
			break;
		}
		case MAX_AGE_18Y: {
			energyReq = (float) (135.3 - (30.8 * this.age)
					+ this.physicalActivityLevel * (10 * this.weight + 934 * this.height / 100) + 20);
			break;
		}
		case MAX_AGE_30Y: {
			energyReq = (float) (354 - (6.91 * this.age)
					+ this.physicalActivityLevel * (9.36 * this.weight + 726 * this.height / 100));
			break;
		}
		case MAX_AGE_50Y: {
			energyReq = (float) (354 - (6.91 * this.age)
					+ this.physicalActivityLevel * (9.36 * this.weight + 726 * this.height / 100));
			break;
		}
		case MAX_AGE_ABOVE: {
			energyReq = (float) (354 - (6.91 * this.age)
					+ this.physicalActivityLevel * (9.36 * this.weight + 726 * this.height / 100));
			break;
		}
		}

		return energyReq;
	}

	// copies the values from the nutriConstantsTableFemale to the person's
	// nutriConstantsTable
	@Override
	void initializeNutriConstantsTable() {
		for (int i = 0; i < nutriConstantsTableFemale.length; i++) {
			for (int j = 0; j < nutriConstantsTableFemale[i].length; j++) {
				this.nutriConstantsTable[i][j] = nutriConstantsTableFemale[i][j];
			}
		}
	}
	
//	public static void main(String[] args) {
//	Female f = new Female(20f, 150f, 60f, 3.5f, "protein");
//
//	// testing to ensure constants table initialized correctly. Delete when done
//	
//	 
//	 for (int i = 0; i < f.nutriConstantsTable.length; i++) { for (int j = 0; j <
//	 f.nutriConstantsTable[i].length; j++) {
//	 System.out.print(f.nutriConstantsTable[i][j] +", "); }
//	 System.out.println(""); }
//	 
//	
//	//testing the calculateNutriRequirements method in Person
//	float [] reqs = f.calculateNutriRequirement();
//	for (int i = 0; i < reqs.length; i++) {
//		System.out.println(reqs[i]);
//	}
//	
//	//testing the createNutriProfile method in NutriProfiler
//	System.out.println(f.calculateEnergyRequirement());
//	NutriProfiler.createNutriProfile(f);
//	for (RecommendedNutrient n : NutriProfiler.recommendedNutrientsList) {
//		System.out.println(n.getNutrientCode() + ", " + n.getNutrientQuantity());
//	}
//	
//}
}
