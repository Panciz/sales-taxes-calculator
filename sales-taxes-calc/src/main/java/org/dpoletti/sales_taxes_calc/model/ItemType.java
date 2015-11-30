package org.dpoletti.sales_taxes_calc.model;

import java.math.BigDecimal;

import org.dpoletti.sales_taxes_calc.catalog.SalesTaxesCalculator;

public enum ItemType {
	

	
	FOOD(SalesTaxesCalculator.NO_DUTY_RATE), 
	MEDICAL_PRODUCT(SalesTaxesCalculator.NO_DUTY_RATE),
	BOOK(SalesTaxesCalculator.NO_DUTY_RATE),
	OTHER(SalesTaxesCalculator.STANDARD_DUTY_RATE);



	private ItemType(BigDecimal value) {
		this.standardDutyRate = value;
	}

	private final BigDecimal standardDutyRate;

	public BigDecimal getStandardDutyRate() {
		return standardDutyRate;
	}

	public static ItemType fromString(String itemTypeStr){
		try{
			return ItemType.valueOf(itemTypeStr);
		}catch(Throwable e){
			return ItemType.OTHER;
		}
	}

	

}
