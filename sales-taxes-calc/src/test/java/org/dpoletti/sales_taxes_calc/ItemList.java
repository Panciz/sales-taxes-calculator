package org.dpoletti.sales_taxes_calc;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.dpoletti.sales_taxes_calc.model.Item;

public class ItemList {

	
	private List<Item> content = new ArrayList<>();
	
	private static final String TOTAL_TAXES_PATTERN = "Sales Taxes: %s";
	private static final String TOTAL_PATTERN = "Total: %s";
	
	private BigDecimal totalTaxes = new BigDecimal("0.00").setScale(2);
	private BigDecimal totalGlobal= new BigDecimal("0.00").setScale(2);;

	public void emptyList(){
		totalTaxes=new BigDecimal("0.00").setScale(2);
		totalGlobal=new BigDecimal("0.00").setScale(2);
		content.clear();
	}
	
	public void addItem(Item item){
		this.content.add(item);
		totalTaxes=totalTaxes.add(item.getTaxAmount());
		totalGlobal=totalGlobal.add(item.getTotal());
	}
	
	public BigDecimal getTotalTaxes() {
		return totalTaxes;
	}
	public BigDecimal getTotalGlobal() {
		return totalGlobal;
	}
	
	public void printList(PrintStream output){
		for(Item item:content){
			output.println(item.toString());
		}
		output.println(String.format(ItemList.TOTAL_TAXES_PATTERN, this.getTotalTaxes().toString()));
		output.println(String.format(ItemList.TOTAL_PATTERN, this.getTotalGlobal().toString()));
		
	}
	
}
