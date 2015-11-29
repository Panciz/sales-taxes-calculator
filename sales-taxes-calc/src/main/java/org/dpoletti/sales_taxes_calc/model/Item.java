package org.dpoletti.sales_taxes_calc.model;

import java.math.BigDecimal;

public class Item {

	private String name;
	private BigDecimal price;
	
	public Item(String name, BigDecimal price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	public BigDecimal getTaxAmount(){
		return new BigDecimal("0");
		
	}
	
	
	
}
