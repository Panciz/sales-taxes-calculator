package org.dpoletti.sales_taxes_calc.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorUtils {
	
	public static final BigDecimal standardDutyRate = new BigDecimal("10").setScale(2);
	public static final BigDecimal importDutyRate = new BigDecimal("10").setScale(2);
	
	public static BigDecimal roundToNearest(BigDecimal bd) {
		if(bd==null){
			throw new NullPointerException("Canno round null bigdecimal"); 
			
		}
		bd=bd.setScale(2,BigDecimal.ROUND_HALF_UP);
		BigDecimal divisor = new BigDecimal("20").setScale(0);
		
		BigDecimal result = ((bd.multiply(divisor)).setScale(0,RoundingMode.UP)).divide(divisor);
	    return result.setScale(2,RoundingMode.HALF_UP);
	}
	
	
}
