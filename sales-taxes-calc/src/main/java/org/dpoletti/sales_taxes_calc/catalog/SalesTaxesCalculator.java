package org.dpoletti.sales_taxes_calc.catalog;

import java.math.BigDecimal;

public class SalesTaxesCalculator {
	public static final BigDecimal IMPORTED_DUTY_RATE=new BigDecimal("5.00").setScale(2);
	public static final BigDecimal STANDARD_DUTY_RATE=new BigDecimal("10.00").setScale(2);
	public static final BigDecimal NO_DUTY_RATE=new BigDecimal("0.00").setScale(2);
	
	public static  ProductCatalog productCatalog;
	
}
