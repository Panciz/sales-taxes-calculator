package org.dpoletti.sales_taxes_calc.io;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dpoletti.sales_taxes_calc.model.Item;

/**
 * Item List Parser
 * 
 * @author dapol
 *
 */
public class ProductListParser { 
	public static final Pattern ITEM_QUANTITY_PATTERN =  Pattern.compile("[0-9]+\\s+(\\S.+)");
	public static final String ITEM_PRICE_SEPARATOR = " at ";
	public static final String[] PACKAGE_NAMES = new String[]{
		"bottle of",
		"box of",
		"packet of"
	};
	public static final String IMPORTED_MARK = "imported"; 
	
	/**
	 * Extract quantity and item name and creates the Item OB 
	 * 
	 * @param itemQtToken
	 * @return
	 * @throws ProductParserException
	 */
	private Item parseItemqt(String itemQtToken) throws ProductParserException{
		Matcher m = ITEM_QUANTITY_PATTERN.matcher(itemQtToken);
		if(m.matches() && m.groupCount()>0){
			Item result = new Item(m.group(1), new BigDecimal("0.0"));
			return result;
		}else{
			throw new ProductParserException("Error parsing  : "+itemQtToken+" not a valid item quantity line");
		}
	}
	
	public static String extractTypeName(String name){
		String result=name;
		result=result.replace(IMPORTED_MARK, "");
		result=result.trim();
		for(String packagingTypeName:PACKAGE_NAMES){
			if(result.startsWith(packagingTypeName)){
				result=result.replace(packagingTypeName,"");
			}
		}
		return result.trim();
	}
	
	
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
		Item item = parseItemqt(itemQtToken);
		
		if(priceToken!=null){
			try{
				BigDecimal price = new  BigDecimal(priceToken.trim());
				price=price.setScale(2, RoundingMode.HALF_UP);
				item.setPrice(price);
			}catch(NumberFormatException ne){
				throw new ProductParserException("Error parsing prive : "+priceToken+" not a valid price");
			}
		}
		return item;
		
	}
	
}
