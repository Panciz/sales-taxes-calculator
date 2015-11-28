package org.dpoletti.sales_taxes_calc;

import org.junit.Assert;
import org.junit.Test;

public class SalesTaxesCalculator{
	
	
	@Test
	public void parseInput(){
		
		
	}
	
	
	@Test
	public void calculateTaxes(){
		Item noTaxItem = new Item("book",12.49d);
		Assert.assertEquals("Error calculating taxes for "+noTaxItem,noTaxItem.getTaxAmount(), 0,0);
		
	}
	
	public void calculateBasketTotal(){
		
		
	}
	
	public void printOutPut(){
		
	}
	
	
	
}
