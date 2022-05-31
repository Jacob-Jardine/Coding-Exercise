package com.jacob.codingexercise.service;

import com.jacob.codingexercise.model.Transaction;
import java.util.List;

public interface IStatementService {
	public Boolean CreateTransactions(int numOfTransactions);
	public void ReadStatement();
	public List<Transaction> SortStatement();
}
