package com.jacob.codingexercise.enums;

public enum Type {
	CARD("Card"), 
	DD("Direct Debit"), 
	INTERNET("Internet");
	
	private final String textValue;
	
	Type(String textValue){
		this.textValue = textValue;
	}
	
	public String getTextValue() {
		return textValue;
	}
}
