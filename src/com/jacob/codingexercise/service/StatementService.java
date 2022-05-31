package com.jacob.codingexercise.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

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
				
				TransactionDate = TransactionDate(365);
				
				_transaction.add(new Transaction(Amount, Category, TransactionDate, Type, Vendor));
				
				return true;
			}
			
		}catch(Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public void ReadStatement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Transaction> GroubByCategory() {
		// TODO Auto-generated method stub
		return null;
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
