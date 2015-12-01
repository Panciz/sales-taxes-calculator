package org.dpoletti.sales_taxes_calc.catalog;

import java.io.File;
import java.math.BigDecimal;

import org.dpoletti.sales_taxes_calc.io.ProductListParser;
import org.dpoletti.sales_taxes_calc.model.ItemList;

public class SalesTaxesCalculator {
	public static final BigDecimal IMPORTED_DUTY_RATE=new BigDecimal("5.00").setScale(2);
	public static final BigDecimal STANDARD_DUTY_RATE=new BigDecimal("10.00").setScale(2);
	public static final BigDecimal NO_DUTY_RATE=new BigDecimal("0.00").setScale(2);
	
	public static  ProductCatalog productCatalog ;
	
	public static void main(String args[]){
		if(args.length<1){
			System.out.println("Provide Input File name");
			return;
		}
		try{
			productCatalog=new FileProductCatalog("src/test/resources/catalog.properties");
		}catch(Exception e){
			System.err.println("Error reading catalog :"+e.toString());
		}
		File file=new File(args[0]);
		ProductListParser plp= new  ProductListParser();
		ItemList  list = plp.parseList(file);
		list.printList(System.out);
		
	}
	
}
