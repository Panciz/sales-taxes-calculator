package org.dpoletti.sales_taxes_calc;

import java.math.BigDecimal;

import org.dpoletti.sales_taxes_calc.catalog.FileProductCatalog;
import org.dpoletti.sales_taxes_calc.model.Item;
import org.dpoletti.sales_taxes_calc.model.ItemType;
import org.dpoletti.sales_taxes_calc.utils.CalculatorUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SalesTaxesCalculator{
	
	public final static String CATALOG_FILE_NAME="src/test/resources/catalog.properties";
	public static FileProductCatalog catalog = new FileProductCatalog(CATALOG_FILE_NAME);
	
	@BeforeClass
	public static void initCatalgo() throws Exception{
		catalog.initialize();
	}
	
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
		Assert.assertEquals("Error calculating taxes for "+nomalTax,new BigDecimal("1.5"),nomalTax.getTaxAmount() );

	}
	@Test
	public void calculateBasketTotal(){
		
		
	}
	@Test
	public void printOutPut(){
		
	}
	
	@Test
	public void testFileCatalog(){
		Item unknownItem = new Item("spaceShuttle",new BigDecimal("1000000000000"));
		Assert.assertEquals("Error getting item type",ItemType.UNKNOWN,catalog.getItemType(unknownItem));
		Item book = new Item("book",new BigDecimal("10.12"));
		Assert.assertEquals("Error getting item type",ItemType.BOOK,catalog.getItemType(book));
	}
	
	
	
}
