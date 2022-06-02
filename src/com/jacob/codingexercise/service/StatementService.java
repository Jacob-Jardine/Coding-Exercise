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
	public Boolean addTransaction(int numOfTransactions) {
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
	public Boolean ReadStatement() {
		try {
			if(_transaction.size() == 0) {
				return false;
			}
			for(Transaction t : _transaction) {
				System.out.print(t.toString()+ "\n");
			}
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public Boolean SortStatement() {
		try {
			if(_transaction.size() == 0) {
				return false;
			}
			Collections.sort(_transaction, Comparator.comparing(Transaction::getTransactionDate));
			Collections.sort(_transaction, (x, y) -> x.getCategory().compareTo((Category) y.getCategory()));
			
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	@Override
	public List<Transaction> filterTransactionsByCategory(Enum<Category> category) {
		try {
			List<Transaction> temp = _transaction.stream().filter(x -> x.getCategory()== category).toList();
			return temp;
		}catch(Exception e) {
			return null;
		}
	}
	
	@Override
	public List<Integer> GetCategoryYear(List<Transaction> transactionList) {
		try {
			List<Integer> temp = new ArrayList<Integer>();
			int year = transactionList.get(0).getTransactionDate().getYear();
			temp.add(year);
			for(int i = 0; i < transactionList.size(); i ++) {
				if(year < transactionList.get(i).getTransactionDate().getYear()) {
					year = transactionList.get(i).getTransactionDate().getYear();
					temp.add(year);
				}
			}
			return temp;
		}catch(Exception e){
			return null;
		}
	}
	
	@Override
	public Boolean HighestSpend(Enum<Category> category, int year) {
		try {
			List<Transaction> temp = _transaction.stream().filter(x -> x.getCategory()== category).filter(x -> x.getTransactionDate().getYear() == year).toList();
			
			Double total = 0.0;
			for(int i = 0; i < temp.size(); i++) {
				if (temp.get(i).getAmount() > total) {
					total = temp.get(i).getAmount();
				}
			}
			
			System.out.println(String.format("Highest Spend For Category: %s For Year : %s = %s", category, year, total));
			
			return true;
		}catch(Exception e) {
			return null;
		}
	}
	
	@Override
	public Boolean LowestSpend(Enum<Category> category, int year) {
		try {
			List<Transaction> temp = _transaction.stream().filter(x -> x.getCategory()== category).filter(x -> x.getTransactionDate().getYear() == year).toList();
			
			Double total = temp.get(0).getAmount();
			for(int i = 0; i < temp.size(); i++) {
				if (temp.get(i).getAmount() < total) {
					total = temp.get(i).getAmount();
				}
			}
			
			System.out.println(String.format("Lowest Spend For Category: %s For Year : %s = %s", category, year, total));
			
			return true;
		}catch(Exception e) {
			return null;
		}
	}
	
	@Override
	public Boolean TotalPerCategory() {
		try {
			String category1 = "";
			String category2 = "";
			List<String> totalList = new ArrayList<String>();
			double total = 0.0;
			for(int i = 0; i < _transaction.size(); i++) {
				total = 0.0;
				category1 = _transaction.get(i).getCategory().toString();
				for(int j = 0; j < _transaction.size() -1; j++) {
					category2 = _transaction.get(j).getCategory().toString();
					if(category1 == category2) {
						total += _transaction.get(j).getAmount();
					}
					
				}
				
				if (totalList.size() == 0) {
					totalList.add(_transaction.get(i).getCategory().toString() + " " + total);
					System.out.println(_transaction.get(i).getCategory().toString() + " " + total);
				}
				if(!totalList.contains(_transaction.get(i).getCategory().toString() + " " + total)) {
					totalList.add(_transaction.get(i).getCategory().toString() + " " + total);
					System.out.println(_transaction.get(i).getCategory().toString() + " " + total);
				}
				
			}
			return true;
		}catch(Exception e) {
			return false;
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
