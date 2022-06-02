package com.jacob.codingexercise.service;

import com.jacob.codingexercise.enums.Category;
import com.jacob.codingexercise.model.Transaction;
import java.util.List;

public interface IStatementService {
	public Boolean addTransaction(int numOfTransactions);
	
	public Boolean readStatement();
	
	public Boolean sortStatement();
	
	public Boolean totalAmountPerCategory(); 
	
	public List<Transaction> filterTransactionsByCategory(Enum<Category> category);
	
	public List<Integer> getCategoryYear(List<Transaction> transactionList);
	
	public Boolean highestSpendByCategory(Enum<Category> category, int year);
	
	public Boolean lowestSpendByCategory(Enum<Category> category, int year);
	
	public Boolean monthlyAverageSpendByCategory(Enum<Category> category);
}
