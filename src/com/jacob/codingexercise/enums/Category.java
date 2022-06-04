package com.jacob.codingexercise.enums;

public enum Category {
	DD("Direct Debit"), 
	GROCERIES("Groceries"), 
	OTHER("Other");
	
	private final String textValue;
	
	Category(String textValue){
		this.textValue = textValue;
	}
	
	public String getTextValue() {
		return textValue;
	}
}
