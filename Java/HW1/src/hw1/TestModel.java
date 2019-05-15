package hw1;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class TestModel {
	Model sampleModel, fullModel;
	final String SAMPLE_PRODUCT_FILE = "data/Sampleproducts.csv";
	final String SAMPLE_NUTRIENT_FILE = "data/Samplenutrients.csv";
	final String SAMPLE_SERVING_SIZE_FILE = "data/SampleServingSize.csv";

	final String PRODUCT_FILE = "data/Products.csv";
	final String NUTRIENT_FILE = "data/Nutrients.csv";
	final String SERVING_SIZE_FILE = "data/ServingSize.csv";

	@Before
	public void setupClass() {
		sampleModel = new Model();
		fullModel = new Model();
		sampleModel.readProducts(SAMPLE_PRODUCT_FILE);
		sampleModel.readNutrients(SAMPLE_NUTRIENT_FILE);
		sampleModel.readServingSizes(SAMPLE_SERVING_SIZE_FILE);
		
		/** Uncomment the two lines below and the last two test-cases
		 * to test with full-size data files*/
		
//		fullModel.readProducts(PRODUCT_FILE);
//		fullModel.readNutrients(NUTRIENT_FILE);
	}

	@Test
	public void test1_readSampleProductsFile() {
		assertEquals(198, sampleModel.products.length);
	}
	
	@Test
	public void test2_readSampleNutrientFile() {
		assertEquals(26, sampleModel.nutrients.length);
	}
	
	@Test
	public void test3_readSampleNutrientsFile() {
		assertEquals(243, sampleModel.productNutrients.length);
	}
	
	@Test
	public void test4_readSampleServingSizeFile() {
		for (Product p : sampleModel.products) {
			if (p.ndbNumber.equals("45302674")) {
				assertEquals(40, p.servingSize, 0);
				break;
			}
		}
	}
	
	/** Uncomment the test-cases below to test with full size data files */
	
//	@Test
//	public void test5_readProductsFile() {
//		assertEquals(239089, fullModel.products.length);
//	}
//	
//	@Test
//	public void test6_readNutrientFile() {
//		assertEquals(95, fullModel.nutrients.length);
//	}
}
