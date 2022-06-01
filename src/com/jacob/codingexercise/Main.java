package com.jacob.codingexercise;

import java.util.ArrayList;
import java.util.List;

import com.jacob.codingexercise.model.Transaction;
import com.jacob.codingexercise.service.IStatementService;
import com.jacob.codingexercise.service.StatementService;


public class Main {

	public static void main(String[] args) {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
		
		statement.CreateTransactions(10);
		statement.SortStatement();
		statement.ReadStatement();
		statement.TotalPerCategory();	
	}
}
