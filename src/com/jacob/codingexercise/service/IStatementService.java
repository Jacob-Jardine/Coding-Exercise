package com.jacob.codingexercise.service;

import com.jacob.codingexercise.enums.Category;
import com.jacob.codingexercise.model.Transaction;
import java.util.List;

public interface IStatementService {
	public Boolean CreateTransactions(int numOfTransactions);
	public void ReadStatement();
	public Boolean SortStatement();
	public Boolean TotalPerCategory(); 
	public List<Transaction> AllTransactionsForCategory(Enum<Category> category);
	public List<String> GetCategoryYear(List<Transaction> transactionList);
	public Boolean HighestSpend(Enum<Category> category, int year);
	public Boolean LowestSpend(Enum<Category> category, int year);
}