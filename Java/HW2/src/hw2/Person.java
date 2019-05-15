//Daniel Lesser / dlesser
package hw2;

import hw2.NutriProfiler.AgeGroupEnum;

// Person parent class to Male/Female. Has two abstract methods to be
// implemented by the children. Holds the formula to calculate nutrient
// requirements for each person based on their individual characteristics
public abstract class Person {

	float age;
	float weight;
	float height;
	float physicalActivityLevel; // age in years, weight in kg, height in cm
	String ingredientsToWatch;
	float[][] nutriConstantsTable = new float[NutriProfiler.RECOMMENDED_NUTRI_COUNT][NutriProfiler.AGE_GROUP_COUNT];

	// used to ID the appropriate constants to use in nutriConstantsTable to calc
	// the recommended quantities
	NutriProfiler.AgeGroupEnum ageGroup;

	// abstract methods to be implemented in children (Male, Female)
	abstract void initializeNutriConstantsTable();

	abstract float calculateEnergyRequirement();

	// non-default constructor to initialize member variables.
	public Person(float age, float weight, float height, float physicalActivityLevel, String ingredientsToWatch) {
		super();
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.physicalActivityLevel = physicalActivityLevel;
		this.ingredientsToWatch = ingredientsToWatch;

		if (age < AgeGroupEnum.MAX_AGE_3M.getAge()) {
			this.ageGroup = AgeGroupEnum.MAX_AGE_3M;
		} else if (age < AgeGroupEnum.MAX_AGE_6M.getAge()) {
			this.ageGroup = AgeGroupEnum.MAX_AGE_6M;
		} else if (age < AgeGroupEnum.MAX_AGE_1Y.getAge()) {
			this.ageGroup = AgeGroupEnum.MAX_AGE_1Y;
		} else if (age < AgeGroupEnum.MAX_AGE_3Y.getAge()) {
			this.ageGroup = AgeGroupEnum.MAX_AGE_3Y;
		} else if (age < AgeGroupEnum.MAX_AGE_8Y.getAge()) {
			this.ageGroup = AgeGroupEnum.MAX_AGE_8Y;
		} else if (age < AgeGroupEnum.MAX_AGE_13Y.getAge()) {
			this.ageGroup = AgeGroupEnum.MAX_AGE_13Y;
		} else if (age < AgeGroupEnum.MAX_AGE_18Y.getAge()) {
			this.ageGroup = AgeGroupEnum.MAX_AGE_18Y;
		} else if (age < AgeGroupEnum.MAX_AGE_30Y.getAge()) {
			this.ageGroup = AgeGroupEnum.MAX_AGE_30Y;
		} else if (age < AgeGroupEnum.MAX_AGE_50Y.getAge()) {
			this.ageGroup = AgeGroupEnum.MAX_AGE_50Y;
		} else {
			this.ageGroup = AgeGroupEnum.MAX_AGE_ABOVE;

		}

	}

	// returns an array of nutrient values of size
	// NutriProfiler.RECOMMENDED_NUTRI_COUNT. Each value is calculated as follows:
	// For Protein, it multiples the constant with the person's weight. For Carb and
	// Fiber, it simply takes the constant from the nnutriConstantsTable based on
	// NutriEnums' nutriIndex and the person's ageGroup. For others, it multiples
	// the constant with the person's weight and divides by 1000. Try not to use any
	// literals or hard-coded values for age group, nutrient name, array-index, etc.
	float[] calculateNutriRequirement() {

		float[] nutrientReqs = new float[NutriProfiler.RECOMMENDED_NUTRI_COUNT];

		int ageGroupIndex = ageGroup.getAgeGroupIndex();

		// Protein requirement
		nutrientReqs[0] = nutriConstantsTable[0][ageGroupIndex] * weight;
		// Carb and Fiber requirements
		nutrientReqs[1] = nutriConstantsTable[1][ageGroupIndex];
		nutrientReqs[2] = nutriConstantsTable[2][ageGroupIndex];
		// requirements for the 11 amino acids
		for (int i = 3; i < nutrientReqs.length; i++) {
			nutrientReqs[i] = nutriConstantsTable[i][ageGroupIndex] * weight / 1000;
		}

		return nutrientReqs;

	}

}
