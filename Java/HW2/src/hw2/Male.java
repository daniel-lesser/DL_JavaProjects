//Daniel Lesser / dlesser
package hw2;

//creates a Male object to be included in Persons grouping.  
//Males have slightly different nutrient and energy requirements than Females
public class Male extends Person {
	float[][] nutriConstantsTableMale = new float[][] {
			// AgeGroups: 3M, 6M, 1Y, 3Y, 8Y, 13Y, 18Y, 30Y, 50Y, ABOVE
			{ 1.52f, 1.52f, 1.2f, 1.05f, 0.95f, 0.95f, 0.73f, 0.8f, 0.8f, 0.8f }, // Protein
			{ 60, 60, 95, 130, 130, 130, 130, 130, 130, 130 }, // Carbohydrate
			{ 19, 19, 19, 19, 25, 31, 38, 38, 38, 30 }, // Fiber
			{ 36, 36, 32, 21, 16, 17, 15, 14, 14, 14 }, // Histidine
			{ 88, 88, 43, 28, 22, 22, 21, 19, 19, 19 }, // isoleucine
			{ 156, 156, 93, 63, 49, 49, 47, 42, 42, 42 }, // leucine
			{ 107, 107, 89, 58, 46, 46, 43, 38, 38, 38 }, // lysine
			{ 59, 59, 43, 28, 22, 22, 21, 19, 19, 19 }, // methionine
			{ 59, 59, 43, 28, 22, 22, 21, 19, 19, 19 }, // cysteine
			{ 135, 135, 84, 54, 41, 41, 38, 33, 33, 33 }, // phenylalanine
			{ 135, 135, 84, 54, 41, 41, 38, 33, 33, 33 }, // tyrosine
			{ 73, 73, 49, 32, 24, 24, 22, 20, 20, 20 }, // threonine
			{ 28, 28, 13, 8, 6, 6, 6, 5, 5, 5 }, // tryptophan
			{ 87, 87, 58, 37, 28, 28, 27, 24, 24, 24 } // valine
	};

	//constructor for male
	Male(float age, float weight, float height, float physicalActivityLevel, String ingredientsToAvoid) {
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
			energyReq = (float) (88.5 - (61.9 * this.age)
					+ this.physicalActivityLevel * (26.7 * this.weight + 903 * this.height / 100) + 20);
			break;
		}
		case MAX_AGE_13Y: {
			energyReq = (float) (88.5 - (61.9 * this.age)
					+ this.physicalActivityLevel * (26.7 * this.weight + 903 * this.height / 100) + 20);
			break;
		}
		case MAX_AGE_18Y: {
			energyReq = (float) (88.5 - (61.9 * this.age)
					+ this.physicalActivityLevel * (26.7 * this.weight + 903 * this.height / 100) + 20);
			break;
		}
		case MAX_AGE_30Y: {
			energyReq = (float) (662 - (9.53 * this.age)
					+ this.physicalActivityLevel * (15.91 * this.weight + 539.6 * this.height / 100));
			break;
		}
		case MAX_AGE_50Y: {
			energyReq = (float) (662 - (9.53 * this.age)
					+ this.physicalActivityLevel * (15.91 * this.weight + 539.6 * this.height / 100));
			break;
		}
		case MAX_AGE_ABOVE: {
			energyReq = (float) (662 - (9.53 * this.age)
					+ this.physicalActivityLevel * (15.91 * this.weight + 539.6 * this.height / 100));
			break;
		}
		}

		return energyReq;
	}
	
	// copies the values from the nutriConstantsTableMale to the person's
	// nutriConstantsTable
	@Override
	void initializeNutriConstantsTable() {
		for (int i = 0; i < nutriConstantsTableMale.length; i++) {
			for (int j = 0; j < nutriConstantsTableMale[i].length; j++) {
				this.nutriConstantsTable[i][j] = nutriConstantsTableMale[i][j];
			}
		}
	}
}
