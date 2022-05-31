package com.jacob.codingexercise.model;

import java.time.LocalDate;

public class Transaction {
	private double Amount;
	private Enum Category;
	private LocalDate TransactionDate;
	private Enum Type;
	private String Vendor;
	
	public Transaction(double Amount, Enum Category, LocalDate TransactionDate, Enum Type, String Vendor) {
		this.Amount = Amount;
		this.Category = Category;
		this.TransactionDate = TransactionDate;
		this.Type = Type;
		this.Vendor = Vendor;
	}
	
	public double getAmount() {
		return Amount;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}

	public Enum getCategory() {
		return Category;
	}

	public void setCategory(Enum category) {
		Category = category;
	}

	public LocalDate getTransactionDate() {
		return TransactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		TransactionDate = transactionDate;
	}

	public Enum getType() {
		return Type;
	}

	public void setType(Enum type) {
		Type = type;
	}

	public String getVendor() {
		return Vendor;
	}

	public void setVendor(String vendor) {
		Vendor = vendor;
	}
	
	@Override
	public String toString() {
		return String.format(" Transaction Amount: %s \n Category: %s \n Transaction Date: %s \n Type: %s \n Vendor %s", Amount, Category, TransactionDate, Type, Vendor);
	}
}
