package org.dpoletti.sales_taxes_calc.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.dpoletti.sales_taxes_calc.catalog.SalesTaxesCalculator;
import org.dpoletti.sales_taxes_calc.io.ProductListParser;
import org.dpoletti.sales_taxes_calc.utils.CalculatorUtils;

public class Item {

	private static final String ITEM_OUTPUT_PATTERN = "%d%s%s %s: %s";
	
	@Override
	public String toString() {
		return String.format(ITEM_OUTPUT_PATTERN, this.getQuantity(),this.isImported()?" "+ProductListParser.IMPORTED_MARK:"",
				this.getPackageType().trim().length()>0?" "+this.getPackageType():"",this.getName(),this.getTotal().toString());
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
	public int getQuantity() {
		return quantity;
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


	
	/**
	 * Get the type by item name usign the catalog
	 * 
	 * @return
	 */
	public ItemType getType() {
		if(this.type==null){
			this.type=SalesTaxesCalculator.productCatalog.getItemType(this.getName());
		}
		return type;
	}

	public String getName() {
		return name;
	}

	
	public BigDecimal getTotal(){
		return this.price.add(getTaxAmount()).setScale(2, RoundingMode.HALF_UP);
	}
	
	/**
	 * The taxes are lazily Calculated
	 * 
	 * @return
	 */
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
