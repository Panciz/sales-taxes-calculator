package org.dpoletti.sales_taxes_calc;

import org.junit.Assert;
import org.junit.Test;

public class SalesTaxesCalculator{
	
	
	@Test
	public void applyTaxes(){
		Item noTaxItem = new Item("book",12.49d);
		Assert.assertEquals("Error calculating taxes for "+noTaxItem,noTaxItem.getTaxAmount(), 12.49,0);
		
	}
	
}
