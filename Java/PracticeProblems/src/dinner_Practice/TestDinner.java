package dinner_Practice;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestDinner {
	Dinner dinner = new Dinner();

	@Before 
	public void resetCalories(){
		Food.calories = 0;
	}

	@Test
	public void test1_ChipsEat() {
		Chips chips = new Chips();
		assertEquals("Test Chips eating sound", "Crunch Crunch", chips.eat());
	}

	@Test
	public void test2_ChipsCalories() {
		Chips chips = new Chips();
		chips.eat();
		assertEquals("Test Chips calories", 200, Food.calories);
	}

	@Test
	public void test3_PizzaEat() {
		Pizza pizza = new Pizza();
		assertEquals("Test Pizza eating sound", "Chomp Chomp", pizza.eat());
	}

	@Test
	public void test4_PizzaCalories() {
		Pizza pizza = new Pizza();
		pizza.eat();
		assertEquals("Test Pizza calories", 800, Food.calories);
	}

	@Test
	public void test5_PizzaTemperature() {
		Pizza pizza = new Pizza();
		((Heatable)pizza).heatIt();
		assertEquals("Test Pizza temperature", 165, pizza.temperature);
	}

	@Test
	public void test6_IcecreamEat() {
		IceCream iceCream = new IceCream();
		assertEquals("Test IceCream eating sound", "Slurp Slurp", iceCream.eat());
	}

	@Test
	public void test7_IcecreamCalories() {
		IceCream iceCream = new IceCream();
		iceCream.eat();
		assertEquals("Test IceCream calories", 500, Food.calories);
	}

	@Test
	public void test8_AllCalories(){
		Chips chips = new Chips();
		chips.eat();
		Pizza pizza = new Pizza();
		pizza.eat();
		IceCream iceCream = new IceCream();
		iceCream.eat();
		assertEquals("Test All calories", 1500, Food.calories);
	}
	
	@Test
	public void test9_DinnerGetFood() {
		assertEquals("Test getFood type Pizza", "Pizza", dinner.getFood(1).getClass().getSimpleName());
	}
	
	@Test
	public void test10_DinnerGetFood() {
		assertEquals("Test getFood type Chips", "Chips", dinner.getFood(2).getClass().getSimpleName());
	}
	
	@Test
	public void test11_DinnerGetFood() {
		assertEquals("Test getFood type IceCream", "IceCream", dinner.getFood(3).getClass().getSimpleName());
	}
}
