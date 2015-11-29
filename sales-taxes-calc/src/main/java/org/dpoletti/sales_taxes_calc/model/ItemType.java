package org.dpoletti.sales_taxes_calc.model;

public enum ItemType {

	FOOD("F"), MEDICAL_PRODUCT("M"), BOOK("B"), UNKNOWN("X");

	private ItemType(String value) {
		this.value = value;
	}

	private final String value;

	public String getValue() {
		return value;
	}

	public static ItemType fromString(String value) {
		switch (value) {
		case "F":
			return ItemType.FOOD;
		case "M":
			return ItemType.MEDICAL_PRODUCT;
		case "B":
			return ItemType.BOOK;
		default:
			return ItemType.UNKNOWN;
		}
	}

}
