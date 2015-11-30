package org.dpoletti.sales_taxes_calc.model;

import java.math.BigDecimal;

public enum ItemType {
	

	FOOD(new BigDecimal("0").setScale(2)), 
	MEDICAL_PRODUCT(new BigDecimal("0").setScale(2)),
	BOOK(new BigDecimal("0").setScale(2)),
	OTHER(new BigDecimal("10.00").setScale(2));

	public static final BigDecimal IMPORTED_DURY_RATE=new BigDecimal("5.00").setScale(2);

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
