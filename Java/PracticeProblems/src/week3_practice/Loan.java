package week3_practice;

import java.math.*;
import java.util.Scanner;

//formula for EMI = [P x R x (1+R)^N]/[(1+R)^N-1], where R is monthly interest and N is number of months	
public class Loan {	
	int years;
	long principal;
	double interestRate;
	BigDecimal bigInterestRate;

	//complete this method
	public double calcEMI() {	
		
		double emi = principal * ((interestRate * Math.pow(1+interestRate, years))
				/((Math.pow(1+interestRate, years)-1)));
		
		return emi;								
	}

	//complete this method
	public BigDecimal calcBigEMI() {
		
		BigDecimal emi;
		emi = BigDecimal.valueOf(principal * ((interestRate * Math.pow(1+interestRate, years))
				/((Math.pow(1+interestRate, years)-1))));
		
		return emi;
	}

	//complete this method
	public void getInputs() {
		Scanner input = new Scanner (System.in);
		System.out.println("Please enter the number of years:");
		years = input.nextInt() * 12;
		System.out.println("Please enter the principal:");
		principal = input.nextLong();
		System.out.println("Please enter the interest rate:");
		bigInterestRate = input.nextBigDecimal()
				.divide(BigDecimal.valueOf(12),20,RoundingMode.HALF_DOWN);
		interestRate = bigInterestRate.doubleValue();
		input.close();
	}

	//don't change this method
	public static void main(String[] args) {
		Loan l = new Loan();
		l.getInputs();     							//initialize all variables
		
		double doubleEMI = l.calcEMI();				//calculate EMI with double interest rate
		BigDecimal bigEMI = l.calcBigEMI();			//calculate EMI with BigDecimal interest rate

		System.out.println("********** Results ************");
		System.out.printf("Principal: $%,d%n", l.principal);
		System.out.printf("Interest rate: %.2f%%%n", l.interestRate*100);
			
		System.out.println("Monthly EMI using double: " + doubleEMI);
		
		//following statement rounds up 20 digits after decimal place. Otherwise throws Arithmetic Exception if number cannot be represented 
		System.out.println("Monthly EMI using BigDecimal: " + bigEMI.setScale(20, RoundingMode.HALF_UP));	

		double difference = (bigEMI.setScale(20, RoundingMode.HALF_UP).doubleValue() - doubleEMI)*12*l.years;

		System.out.printf("Difference bigEMI - doubleEMI = %.15f%n", (bigEMI.subtract(BigDecimal.valueOf(doubleEMI))));
		System.out.printf("Total difference = %.15f%n", difference);
	}
}
