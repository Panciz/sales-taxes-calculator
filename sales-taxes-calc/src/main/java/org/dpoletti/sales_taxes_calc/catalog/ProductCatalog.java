package org.dpoletti.sales_taxes_calc.catalog;

import org.dpoletti.sales_taxes_calc.model.Item;
import org.dpoletti.sales_taxes_calc.model.ItemType;


public interface ProductCatalog {

	
	public ItemType getItemType(Item item);
}
