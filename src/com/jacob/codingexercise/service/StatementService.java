package com.jacob.codingexercise.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.jacob.codingexercise.enums.Category;
import com.jacob.codingexercise.enums.Type;
import com.jacob.codingexercise.model.Transaction;


public class StatementService implements IStatementService{

	private List<Transaction> _transaction;
	
	public StatementService(List<Transaction> _transaction) {
		this._transaction = _transaction;
	}
	
	@Override
	public Boolean CreateTransactions(int numOfTransactions) {
		if(numOfTransactions == 0) {
			return false;
		}
		
		try {
			// Arrays that contain vendor names associated to categories
			String[] ddArr = {"CYBG", "PureGym"};
			String[] groceriesArr = {"Asda", "Morrisons", "M&S"};
			String[] otherArr = {"McMillan"};
			
			double Amount = 0.0;
			Category Category = null;
			LocalDate TransactionDate;
			Type Type = null;
			String Vendor = "";
			
			for(int i = 0; i < numOfTransactions; i++) {
				switch(RandomNumber(3)) {
					case 0:
						Amount = (double) Math.round(RandomNumber(999)/10.0);
						Category = Category.DD;
						Type = Type.DD;
						Vendor = ddArr[RandomNumber(ddArr.length)].toString();
						break;
					case 1:
						Amount = (double) Math.round(RandomNumber(999)/10.0);
						Category = Category.GROCERIES;
						Type = Type.CARD;
						Vendor = groceriesArr[RandomNumber(groceriesArr.length)].toString();
						break;
					case 2:
						Amount = (double) Math.round(RandomNumber(999)/10.0);
						Category = Category.OTHER;
						Type = Type.INTERNET;
						Vendor = otherArr[RandomNumber(otherArr.length)].toString();
						break;
				}
				
				TransactionDate = TransactionDate(RandomNumber(365));
				
				_transaction.add(new Transaction(Amount, Category, TransactionDate, Type, Vendor));
			}
			return true;
			
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public void ReadStatement() {
		for(Transaction t : _transaction) {
			System.out.print(t.toString()+ "\n");
		}
	}

	@Override
	public List<Transaction> SortStatement() {
		try {
			List<Transaction> temp = _transaction;
			
			Collections.sort(temp, Comparator.comparing(Transaction::getTransactionDate));
			Collections.sort(temp, (x, y) -> x.getCategory().compareTo((Category) y.getCategory()));
			
			return temp;
		}catch(Exception e) {
			return null;
		}
	}
	
	private int RandomNumber(int range) {
		Random randomNumber = new Random();
		return randomNumber.nextInt(range);
	}
	
	private LocalDate TransactionDate(int days) {
		LocalDate date = LocalDate.now();
		return date.minusDays(days);
	}
}
