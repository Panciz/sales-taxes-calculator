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
	private int quantity=0;

	public boolean isImported() {
		return imported;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public String getPackageType() {
		return packageType;
	}


	public Item(String name, BigDecimal price,String packageType,boolean imported,int quantity) {
		super();
		this.name = name;
		this.price = price;
		this.packageType = packageType;
		this.imported=imported;
		this.quantity=quantity;
	}


	public ItemType getType() {
		if(this.type==null){
			this.type=SalesTaxesCalculator.productCatalog.getItemType(this);
		}
		return type;
	}

	public String getName() {
		return name;
	}

	
	public BigDecimal getTaxAmount(){
		if(this.taxAmount==null){
			// the STANRDA_DUTY_RATE% of the price Rounded to nerest 0.05
			BigDecimal standardDuty = CalculatorUtils.roundToNearest(
					(this.price.multiply(this.getType().getStandardDutyRate()))
					.divide(new BigDecimal("100")));
			BigDecimal importedDuty = new BigDecimal("0.00");
			if(this.isImported()){
				importedDuty=CalculatorUtils.roundToNearest(
						(this.price.multiply(SalesTaxesCalculator.IMPORTED_DUTY_RATE))
						.divide(new BigDecimal("100")));
			}
			
			this.taxAmount=standardDuty.add(importedDuty);
			
		}
		return this.taxAmount;
	}

}
