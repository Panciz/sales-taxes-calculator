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
	
	public static final Pattern ITEM_QUANTITY_PATTERN =  
			//<quantity> [imported] [packageType] <productName>
			Pattern.compile("[0-9]+\\s+(imported\\s+|[\\S]+\\s+of\\s+)?(imported\\s+|[\\S]+\\s+of\\s+)?(\\S.+)");
	public static final String ITEM_PRICE_SEPARATOR = " at ";
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
		if(m.matches()){
			Item result = new Item(m.group(3), new BigDecimal("0.0"));
			extractGroup(m,result,1);
			extractGroup(m,result,2);
			return result;
		}else{
			throw new ProductParserException("Error parsing  : "+itemQtToken+" not a valid item quantity line");
		}
	}
	
	private void extractGroup(Matcher matcher,Item item,int groupId){
		if(matcher.group(groupId)!=null){
			if(IMPORTED_MARK.equals(matcher.group(groupId).trim())){
				item.setImported(true);
			}else{
				item.setPackageType(matcher.group(groupId).trim());
			}
		}
	}
	
	
	/**
	 * Parse a line and extract the Item quantity,name 
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
