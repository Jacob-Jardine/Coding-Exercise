package com.jacob.codingexercise.service;

import com.jacob.codingexercise.enums.Category;
import com.jacob.codingexercise.model.Transaction;
import java.util.List;

public interface IStatementService {
	public Boolean addTransaction(int numOfTransactions);
	public Boolean ReadStatement();
	public Boolean SortStatement();
	public Boolean TotalPerCategory(); 
	public List<Transaction> filterTransactionsByCategory(Enum<Category> category);
	public List<Integer> GetCategoryYear(List<Transaction> transactionList);
	public Boolean HighestSpend(Enum<Category> category, int year);
	public Boolean LowestSpend(Enum<Category> category, int year);
}
