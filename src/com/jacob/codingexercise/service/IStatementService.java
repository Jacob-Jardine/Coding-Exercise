package com.jacob.codingexercise.service;

import com.jacob.codingexercise.enums.Category;
import com.jacob.codingexercise.model.Transaction;
import java.util.List;
import java.util.Map;

public interface IStatementService {
	public Boolean addTransaction(int numOfTransactions);
	
	public Boolean readStatement();
	
	public Boolean sortStatement();
	
	public Map<Enum<Category>, Double> totalAmountPerCategory(); 
	
	public List<Transaction> filterTransactionsByCategory(Enum<Category> category);
	
	public List<Integer> getCategoryYear(List<Transaction> transactionList);
	
	public Double highestSpendByCategory(Enum<Category> category, int year);
	
	public Double lowestSpendByCategory(Enum<Category> category, int year);
	
	public Double monthlyAverageSpendByCategory(Enum<Category> category);
	
	public Boolean assignCategory(Transaction transaction, Enum<Category> category);
}
