package org.dpoletti.sales_taxes_calc;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class SalesTaxesCalculator{
	
	
	@Test
	public void parseInput(){
		
		
	}
	
	@Test
	public void roundToTheNearestCents(){
		BigDecimal amount = new  BigDecimal("1.99");
		Assert.assertEquals(CalculatorUtils.roundToNearest(amount).toString(),"2.00");
		 amount = new  BigDecimal("1.999");
		Assert.assertEquals(CalculatorUtils.roundToNearest(amount).toString(),"2.00");
		
		 amount = new  BigDecimal("1.4566");
		Assert.assertEquals(CalculatorUtils.roundToNearest(amount).toString(),"1.45");
		 amount = new  BigDecimal("100.223223");
		Assert.assertEquals(CalculatorUtils.roundToNearest(amount).toString(),"100.25");

		 amount = new  BigDecimal("0.01");
		Assert.assertEquals(CalculatorUtils.roundToNearest(amount).toString(),"0.00");

	}
	
	@Test
	public void calculateTaxes(){
		Item noTaxItem = new Item("book",12.49d);
		Assert.assertEquals("Error calculating taxes for "+noTaxItem,noTaxItem.getTaxAmount(), 0,0);
		Item nomalTax = new Item("music CD",14.99d);
		Assert.assertEquals("Error calculating taxes for "+noTaxItem,noTaxItem.getTaxAmount(), 1.5,0);

	}
	
	public void calculateBasketTotal(){
		
		
	}
	
	public void printOutPut(){
		
	}
	
	
	
}
