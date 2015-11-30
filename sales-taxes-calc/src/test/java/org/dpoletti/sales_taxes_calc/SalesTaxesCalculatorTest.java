package org.dpoletti.sales_taxes_calc;

import java.math.BigDecimal;

import org.dpoletti.sales_taxes_calc.catalog.FileProductCatalog;
import org.dpoletti.sales_taxes_calc.catalog.SalesTaxesCalculator;
import org.dpoletti.sales_taxes_calc.io.ProductListParser;
import org.dpoletti.sales_taxes_calc.io.ProductParserException;
import org.dpoletti.sales_taxes_calc.model.Item;
import org.dpoletti.sales_taxes_calc.model.ItemType;
import org.dpoletti.sales_taxes_calc.utils.CalculatorUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SalesTaxesCalculatorTest{
	
	public final static String CATALOG_FILE_NAME="src/test/resources/catalog.properties";
	public static FileProductCatalog catalog = new FileProductCatalog(CATALOG_FILE_NAME);
	public static ProductListParser parser= new ProductListParser();
	
	@BeforeClass
	public static void initCatalgo() throws Exception{
		catalog.initialize();
		SalesTaxesCalculator.productCatalog=catalog;
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
	public void calculateTaxes() throws ProductParserException{
		Item noTaxItem = new Item("book",new BigDecimal("12.49"),"",false);
		Assert.assertEquals("Error calculating taxes for "+noTaxItem,new BigDecimal("0.00"),noTaxItem.getTaxAmount() );
		Item normalTax = new Item("music CD",new BigDecimal("14.99"),"",false);
		Assert.assertEquals("Error calculating taxes for "+normalTax,new BigDecimal("1.50"),normalTax.getTaxAmount() );
		Item normalTax2 = new Item("perfume",new BigDecimal("18.99"),"",false);
		Assert.assertEquals("Error calculating taxes for "+normalTax2,new BigDecimal("1.90"),normalTax2.getTaxAmount() );
		Item onlyImportDuty= new Item("book",new BigDecimal("10.00"),"",true);
		Assert.assertEquals("Error calculating taxes for "+onlyImportDuty,new BigDecimal("1.00"),onlyImportDuty.getTaxAmount() );
		
		Item onlyImportDuty2= new Item("book",new BigDecimal("20.10"),"",true);
		Assert.assertEquals("Error calculating taxes for "+onlyImportDuty2,new BigDecimal("2.50"),onlyImportDuty2.getTaxAmount() );
		


	}
	@Test
	public void calculateBasketTotal(){
		
		
	}
	@Test
	public void printOutPut(){
		
	}
	
	@Test
	public void testFileCatalog(){
		Item unknownItem = new Item("spaceShuttle",new BigDecimal("1000000000000"),"",false);
		Assert.assertEquals("Error getting item type",ItemType.OTHER,catalog.getItemType(unknownItem));
		Item book = new Item("book",new BigDecimal("10.12"),"",false);
		Assert.assertEquals("Error getting item type",ItemType.BOOK,catalog.getItemType(book));
		Item pills = new Item("headache pills",new BigDecimal("10.12"),"",false);
		Assert.assertEquals("Error getting item type",ItemType.MEDICAL_PRODUCT,catalog.getItemType(pills));
		Item packetCocholate = new Item("chocolate bar",new BigDecimal("10.12"),"",false);
		Assert.assertEquals("Error getting item type",ItemType.FOOD,catalog.getItemType(packetCocholate));
	}
	

	
	
}
