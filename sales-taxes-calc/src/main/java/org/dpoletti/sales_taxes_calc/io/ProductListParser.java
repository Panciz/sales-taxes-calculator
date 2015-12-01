package org.dpoletti.sales_taxes_calc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dpoletti.sales_taxes_calc.ItemList;
import org.dpoletti.sales_taxes_calc.model.Item;

/**
 * Item List Parser
 * 
 * @author dapol
 *
 */
public class ProductListParser { 
	
	public static final Pattern ITEM_QUANTITY_PATTERN = 
			//<quantity> [imported] [packageType] <productName>
			Pattern.compile("[0-9]+\\s+(imported\\s+|[\\S]+\\s+of\\s+)?(imported\\s+|[\\S]+\\s+of\\s+)?(\\S.+)");
	public static final String ITEM_PRICE_SEPARATOR = " at ";
	public static final String IMPORTED_MARK = "imported"; 
	
	/**
	 * Parse a line and extract the Item quantity,name,package type and imported flag
	 * 
	 * 
	 * @param line
	 * @return
	 * @throws ProductParserException
	 */
	public Item parseLine(String line) throws ProductParserException{
		if(line==null){
			return null;
		}
		
		line=line.trim();
		String itemQtToken = null;
		String priceToken = null;
		
		String[] split= line.split(ITEM_PRICE_SEPARATOR);
		if(split.length>1){
			itemQtToken=split[0];
			priceToken=split[1];
		}else{
			itemQtToken=split[0];
		}
		BigDecimal price = parsePrice(priceToken);
		Matcher m = ITEM_QUANTITY_PATTERN.matcher(itemQtToken);
		if(m.matches()){
			boolean imported=extractimported(m);
			String packageType = extractPackageType(m);
			String name=m.group(3).trim();
			Item result = new Item(name, price,packageType,imported,1);
			return result;
		}else{
			throw new ProductParserException("Error parsing  : "+itemQtToken+" not a valid item quantity line");
		}
	}
	
	private BigDecimal parsePrice(String priceToken) throws ProductParserException{
		if(priceToken!=null){
			try{
				BigDecimal price = new  BigDecimal(priceToken.trim());
				price=price.setScale(2, RoundingMode.HALF_UP);
				return price;
			}catch(NumberFormatException ne){
				throw new ProductParserException("Error parsing prive : "+priceToken+" not a valid price");
			}
		}
		return new BigDecimal("0.0");
	}
	
	private boolean extractimported(Matcher matcher){
		if(matcher.group(1)!=null){
			if(IMPORTED_MARK.equals(matcher.group(1).trim())){
				return true;
			}
		}
		if(matcher.group(2)!=null){
			if(IMPORTED_MARK.equals(matcher.group(2).trim())){
				return true;
			}
		}
		return false;
	}
	private String extractPackageType(Matcher matcher){
		if(matcher.group(1)!=null){
			if(!IMPORTED_MARK.equals(matcher.group(1).trim())){
				return matcher.group(1).trim();
			}
		}
		if(matcher.group(2)!=null){
			if(!IMPORTED_MARK.equals(matcher.group(2).trim())){
				return matcher.group(2).trim();
			}
		}
		return "";
	}
	
	public ItemList parseList(File listInput){
		if(!listInput.exists() || !listInput.canRead()){
			throw new RuntimeException("Error file "+listInput.getAbsolutePath()+" or cannot be read");
		}
		ItemList result = new ItemList();
		 try( BufferedReader brInput = new BufferedReader(new FileReader(listInput))){
			   String lineOutPut;
		    while(( lineOutPut = brInput.readLine()) != null){
		    	Item item = parseLine(lineOutPut);
		    	result.addItem(item);
		    }
		  }catch(IOException ie){
			   System.err.println("Error reading test file "+listInput.getPath()+": "+ie);
				throw new RuntimeException("Error file "+listInput.getAbsolutePath()+" or cannot be read");
		  }
		 return result;
	}




	
}
