package org.dpoletti.sales_taxes_calc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

import org.dpoletti.sales_taxes_calc.catalog.FileProductCatalog;
import org.dpoletti.sales_taxes_calc.catalog.SalesTaxesCalculator;
import org.dpoletti.sales_taxes_calc.io.ProductListParser;
import org.dpoletti.sales_taxes_calc.io.ProductParserException;
import org.dpoletti.sales_taxes_calc.model.Item;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ParserTest {
	
	public static ProductListParser parser= new ProductListParser();
	public final static String TEST_FILE = "src/test/resources/io_files/testFile1";

	public final static String CATALOG_FILE_NAME="src/test/resources/catalog.properties";
	public static FileProductCatalog catalog = new FileProductCatalog(CATALOG_FILE_NAME);
	
	@BeforeClass
	public static void initCatalgo() throws Exception{
		catalog.initialize();
		SalesTaxesCalculator.productCatalog=catalog;
	}
	
	
	@Test
	public void parseInput() throws ProductParserException{
		String inputLine = "1 book at 12.49 ";
		Item itemExpeted = new Item("book", new  BigDecimal("12.49"),"",false,1);
		Item parsedItem = parser.parseLine(inputLine);
		Assert.assertEquals("Error parsing line name mistmatch ",itemExpeted.getName(),parsedItem.getName());
		Assert.assertEquals("Error parsing line price mistmatch ",itemExpeted.getPrice(),parsedItem.getPrice());
		Assert.assertEquals("Error parsing line package type","",parsedItem.getPackageType());
		Assert.assertFalse("Error parsing line imported flag",parsedItem.isImported());	
		
		 inputLine = "1 book ";
		 itemExpeted = new Item("book", new  BigDecimal("0.0"),"",false,1);
		 parsedItem = parser.parseLine(inputLine);
		Assert.assertEquals("Error parsing line name mistmatch ",itemExpeted.getName(),parsedItem.getName());
		Assert.assertEquals("Error parsing line price mistmatch ",itemExpeted.getPrice(),parsedItem.getPrice());
		Assert.assertEquals("Error parsing line package type","",parsedItem.getPackageType());
		Assert.assertFalse("Error parsing line imported flag",parsedItem.isImported());	
		
		 inputLine = "   10 book at 122.2";
		itemExpeted = new Item("book", new  BigDecimal("122.20"),"",false,1);
		 parsedItem = parser.parseLine(inputLine);
		Assert.assertEquals("Error parsing line name mistmatch ",itemExpeted.getName(),parsedItem.getName());
		Assert.assertEquals("Error parsing line price mistmatch ",itemExpeted.getPrice(),parsedItem.getPrice());
		Assert.assertEquals("Error parsing line package type","",parsedItem.getPackageType());
		Assert.assertFalse("Error parsing line imported flag",parsedItem.isImported());	
		
		inputLine = "   10 music CD at 122.2";
		itemExpeted = new Item("music CD", new  BigDecimal("122.20"),"",false,1);
		parsedItem = parser.parseLine(inputLine);
		Assert.assertEquals("Error parsing line name mistmatch ",itemExpeted.getName(),parsedItem.getName());
		Assert.assertEquals("Error parsing line price mistmatch ",itemExpeted.getPrice(),parsedItem.getPrice());
		Assert.assertEquals("Error parsing line package type","",parsedItem.getPackageType());
		Assert.assertFalse("Error parsing line imported flag",parsedItem.isImported());		
		
		inputLine = "10 imported bottle of perfume at 122.2";
		itemExpeted = new Item("perfume", new  BigDecimal("122.20"),"bottle of",true,1);
		parsedItem = parser.parseLine(inputLine);
		Assert.assertEquals("Error parsing line name mistmatch ",itemExpeted.getName(),parsedItem.getName());
		Assert.assertEquals("Error parsing line price mistmatch ",itemExpeted.getPrice(),parsedItem.getPrice());
		Assert.assertEquals("Error parsing line package type","bottle of",parsedItem.getPackageType());
		Assert.assertTrue("Error parsing line imported flag",parsedItem.isImported());
		
		inputLine = "1 package of imported cigars at 1.243";
		itemExpeted = new Item("cigars", new  BigDecimal("1.24"),"package of",true,1);
		parsedItem = parser.parseLine(inputLine);
		Assert.assertEquals("Error parsing line name mistmatch ",itemExpeted.getName(),parsedItem.getName());
		Assert.assertEquals("Error parsing line price mistmatch ",itemExpeted.getPrice(),parsedItem.getPrice());
		Assert.assertEquals("Error parsing line package type","package of",parsedItem.getPackageType());
		Assert.assertTrue("Error parsing line imported flag",parsedItem.isImported());
	}
	
	@Test(expected=ProductParserException.class)
	public void lineFailure() throws ProductParserException {
		String inputLine = "bb 1 book at 12.49 ";
		parser.parseLine(inputLine);
	}
	
	@Test
	public void completeParserPrinterTest() throws IOException{
		Assert.assertTrue("Error readint test input files "+TEST_FILE,compareInputWithOutput(TEST_FILE)>0);
	}
	
	private static int compareInputWithOutput(String fileName) throws IOException
	{
	    File fileInput = new File(fileName+"_input.txt");
	    File fileOutput = new File(fileName+"_output.txt");
	   try( BufferedReader brInput = new BufferedReader(new FileReader(fileInput));
			   BufferedReader brOutput = new BufferedReader(new FileReader(fileOutput));
			   ){
	    String lineInput;
	    String lineOutPut;
	    int i = 0;
	    while((lineInput = brInput.readLine()) != null && (lineOutPut = brOutput.readLine()) != null){
			Assert.assertEquals("Error on line "+i,lineOutPut,parser.parseLine(lineInput).toString());
			i++;
	    }
	    return i;
	   }catch(IOException ie){
		   
		   System.err.println("Error reading test file "+ie);
		   return -1;
	   }
	}

}
