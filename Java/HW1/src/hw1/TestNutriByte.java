package hw1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestNutriByte {
	
	NutriByte nutriByte;
	
	@Before
	public void setupClass() {
		nutriByte = new NutriByte();
		nutriByte.model.readProducts(NutriByte.PRODUCT_FILE); 
		nutriByte.model.readNutrients(NutriByte.NUTRIENT_FILE);
		nutriByte.model.readServingSizes(NutriByte.SERVING_SIZE_FILE);
		
	}

	@Test
	public void test1_searchProductsWithIngredients_single_existing_size() {
		Product[] products = nutriByte.searchProductsWithSelectedIngredients("milk");
		assertEquals(36, products.length);
	}
	
	@Test
	public void test2_searchProductsWithIngredients_double_existing_size() {
		Product[] products = nutriByte.searchProductsWithSelectedIngredients("milk cream");
		assertEquals(38, products.length);
	}
	
	@Test
	public void test3_searchProductsWithIngredients_single_nonexisting_size() {
		Product[] products = nutriByte.searchProductsWithSelectedIngredients("qwert");
		assertEquals(0, products.length);
	}
	
	@Test
	public void test4_getProductsWithSelectedNutrient_size() {
		Product[] products = nutriByte.searchProductsWithSelectedNutrient(1);
		assertEquals(11, products.length);
	}
	
	@Test
	public void test5_getProductsWithSelectedNutrient_content() {
		Product[] products = nutriByte.searchProductsWithSelectedNutrient(1); //1 is protein
		boolean found = false;
		for (Product p: products) {
			if (p.ndbNumber.equals("45302674")) found = true;  //45302674 is QUAKER, OLD FASHIONED WHOLE GRAIN OATS
		}
		assertTrue(found);
	}

}
