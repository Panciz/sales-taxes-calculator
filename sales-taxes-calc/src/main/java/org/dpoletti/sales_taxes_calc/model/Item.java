package org.dpoletti.sales_taxes_calc.model;

import java.math.BigDecimal;

import org.dpoletti.sales_taxes_calc.catalog.SalesTaxesCalculator;
import org.dpoletti.sales_taxes_calc.utils.CalculatorUtils;

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
	private ItemType type;

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
			// the STANRDA_DUTY_RATE% of the price Rounded to nerest 0.05
			this.taxAmount=
					CalculatorUtils.roundToNearest(
							(this.getPrice().multiply(this.type.getStandardDutyRate()))
							.divide(new BigDecimal("100")));
			
		}
		return this.taxAmount;
	}

	public void setPrice(BigDecimal price) {
		//Reset tax amount
		this.taxAmount=null;
		this.price=price;
	}
	
}
