package org.dpoletti.sales_taxes_calc;

import java.math.BigDecimal;

import org.dpoletti.sales_taxes_calc.io.ProductListParser;
import org.dpoletti.sales_taxes_calc.io.ProductParserException;
import org.dpoletti.sales_taxes_calc.model.Item;
import org.junit.Assert;
import org.junit.Test;

public class ParserTest {
	
	public static ProductListParser parser= new ProductListParser();

	
	@Test
	public void parseInput() throws ProductParserException{
		String inputLine = "1 book at 12.49 ";
		Item itemExpeted = new Item("book", new  BigDecimal("12.49"));
		Item parsedItem = parser.parseLine(inputLine);
		Assert.assertEquals("Error parsing line name mistmatch ",itemExpeted.getName(),parsedItem.getName());
		Assert.assertEquals("Error parsing line price mistmatch ",itemExpeted.getPrice(),parsedItem.getPrice());
		
		 inputLine = "1 book ";
		 itemExpeted = new Item("book", new  BigDecimal("0.0"));
		 parsedItem = parser.parseLine(inputLine);
		Assert.assertEquals("Error parsing line name mistmatch ",itemExpeted.getName(),parsedItem.getName());
		Assert.assertEquals("Error parsing line price mistmatch ",itemExpeted.getPrice(),parsedItem.getPrice());
		
		 inputLine = "   10 book at 122.2";
		itemExpeted = new Item("book", new  BigDecimal("122.20"));
		 parsedItem = parser.parseLine(inputLine);
		Assert.assertEquals("Error parsing line name mistmatch ",itemExpeted.getName(),parsedItem.getName());
		Assert.assertEquals("Error parsing line price mistmatch ",itemExpeted.getPrice(),parsedItem.getPrice());

		inputLine = "   10 music CD at 122.2";
		itemExpeted = new Item("music CD", new  BigDecimal("122.20"));
		parsedItem = parser.parseLine(inputLine);
		Assert.assertEquals("Error parsing line name mistmatch ",itemExpeted.getName(),parsedItem.getName());
		Assert.assertEquals("Error parsing line price mistmatch ",itemExpeted.getPrice(),parsedItem.getPrice());
		
		
		inputLine = "   10 imported bottle of perfume at 122.2";
		itemExpeted = new Item("perfume", new  BigDecimal("122.20"));
		parsedItem = parser.parseLine(inputLine);
		Assert.assertEquals("Error parsing line name mistmatch ",itemExpeted.getName(),parsedItem.getName());
		Assert.assertEquals("Error parsing line price mistmatch ",itemExpeted.getPrice(),parsedItem.getPrice());
	
	}
	
	@Test(expected=ProductParserException.class)
	public void lineFailure() throws ProductParserException {
		String inputLine = "bb 1 book at 12.49 ";
		parser.parseLine(inputLine);
	}
}
