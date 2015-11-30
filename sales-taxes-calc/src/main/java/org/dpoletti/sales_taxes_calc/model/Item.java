package org.dpoletti.sales_taxes_calc.model;

import java.math.BigDecimal;

public class Item {

	@Override
	public String toString() {
		return null;
	}

	private String name;
	private BigDecimal price;
	private String packageType= "";
	private boolean imported = false;
	private BigDecimal taxAmount = null;

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

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
		if(this.taxAmount==null){
			
		}
		return new BigDecimal("0");
	}

	public void setPrice(BigDecimal price) {
		//Reset tax amount
		this.taxAmount=null;
		this.price=price;
	}
	
}
