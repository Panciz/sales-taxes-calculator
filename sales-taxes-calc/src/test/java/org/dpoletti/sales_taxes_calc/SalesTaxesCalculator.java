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
		Assert.assertEquals("2.00",CalculatorUtils.roundToNearest(amount).toString());
		 amount = new  BigDecimal("1.999");
		Assert.assertEquals("2.00",CalculatorUtils.roundToNearest(amount).toString());
		
		 amount = new  BigDecimal("1.4566");
		Assert.assertEquals("1.50",CalculatorUtils.roundToNearest(amount).toString());
		 amount = new  BigDecimal("100.223223");
		Assert.assertEquals("100.25",CalculatorUtils.roundToNearest(amount).toString());

		 amount = new  BigDecimal("0.01");
		Assert.assertEquals("0.05",CalculatorUtils.roundToNearest(amount).toString());

	}
	
	@Test
	public void calculateTaxes(){
		Item noTaxItem = new Item("book",new BigDecimal("12.49"));
		Assert.assertEquals("Error calculating taxes for "+noTaxItem,new BigDecimal("0"),noTaxItem.getTaxAmount() );
		Item nomalTax = new Item("music CD",new BigDecimal("14.99"));
		Assert.assertEquals("Error calculating taxes for "+noTaxItem,new BigDecimal("1.5"),noTaxItem.getTaxAmount() );

	}
	
	public void calculateBasketTotal(){
		
		
	}
	
	public void printOutPut(){
		
	}
	
	
	
}
