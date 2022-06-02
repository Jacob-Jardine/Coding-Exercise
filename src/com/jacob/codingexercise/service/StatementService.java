package com.jacob.codingexercise.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
			
			double amount = 0.0;
			Category category = null;
			LocalDate transactionDate;
			Type type = null;
			String vendor = "";
			
			for(int i = 0; i < numOfTransactions; i++) {
				switch(randomNumber(3)) {
					case 0:
						amount = (double) Math.round(randomNumber(999)/10.0);
						category = category.DD;
						type = type.DD;
						vendor = ddArr[randomNumber(ddArr.length)].toString();
						break;
					case 1:
						amount = (double) Math.round(randomNumber(999)/10.0);
						category = category.GROCERIES;
						type = type.CARD;
						vendor = groceriesArr[randomNumber(groceriesArr.length)].toString();
						break;
					case 2:
						amount = (double) Math.round(randomNumber(999)/10.0);
						category = category.OTHER;
						type = type.INTERNET;
						vendor = otherArr[randomNumber(otherArr.length)].toString();
						break;
				}
				
				transactionDate = getTransactionDate(randomNumber(365));
				
				_transaction.add(new Transaction(amount, category, transactionDate, type, vendor));
			}
			return true;
			
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public Boolean readStatement() {
		try {
			if(_transaction.size() == 0) {
				return false;
			}
			for(Transaction transaction : _transaction) {
				System.out.print(transaction.toString()+ "\n");
			}
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public Boolean sortStatement() {
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
	public Boolean totalAmountPerCategory() {
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
	
	@Override
	public List<Transaction> filterTransactionsByCategory(Enum<Category> category) {
		try {
			List<Transaction> transactionList = _transaction.stream().filter(x -> x.getCategory()== category).toList();
			return transactionList;
		}catch(Exception e) {
			return null;
		}
	}
	
	@Override
	public List<Integer> getCategoryYear(List<Transaction> transactionList) {
		try {
			List<Integer> integerList = new ArrayList<Integer>();
			int year = transactionList.get(0).getTransactionDate().getYear();
			integerList.add(year);
			for(int i = 0; i < transactionList.size(); i ++) {
				if(year < transactionList.get(i).getTransactionDate().getYear()) {
					year = transactionList.get(i).getTransactionDate().getYear();
					integerList.add(year);
				}
			}
			return integerList;
		}catch(Exception e){
			return null;
		}
	}
	
	@Override
	public Boolean highestSpendByCategory(Enum<Category> category, int year) {
		try {
			List<Transaction> transactionList = _transaction.stream().filter(x -> x.getCategory()== category).filter(x -> x.getTransactionDate().getYear() == year).toList();
			
			Double total = 0.0;
			for(int i = 0; i < transactionList.size(); i++) {
				if (transactionList.get(i).getAmount() > total) {
					total = transactionList.get(i).getAmount();
				}
			}
			
			System.out.println(String.format("Highest Spend For Category: %s For Year : %s = %s", category, year, total));
			
			return true;
		}catch(Exception e) {
			return null;
		}
	}
	
	@Override
	public Boolean lowestSpendByCategory(Enum<Category> category, int year) {
		try {
			List<Transaction> transactionList = _transaction.stream().filter(x -> x.getCategory()== category).filter(x -> x.getTransactionDate().getYear() == year).toList();
			
			Double total = transactionList.get(0).getAmount();
			for(int i = 0; i < transactionList.size(); i++) {
				if (transactionList.get(i).getAmount() < total) {
					total = transactionList.get(i).getAmount();
				}
			}
			
			System.out.println(String.format("Lowest Spend For Category: %s For Year : %s = %s", category, year, total));
			
			return true;
		}catch(Exception e) {
			return null;
		}
	}
	
	
	
	@Override
	public Boolean monthlyAverageSpendByCategory(Enum<Category> category) {
		try {
			List<Transaction> transactionList = _transaction.stream().filter(x -> x.getCategory()== category).toList();
			int size = transactionList.size();
			LocalDate startDate = transactionList.get(0).getTransactionDate();
			LocalDate endDate = transactionList.get(size-1).getTransactionDate();
			
			long dateDiff = ChronoUnit.MONTHS.between(startDate, endDate);
			int months = (int)dateDiff;
			
			Double total = 0.0;
			for(Transaction t : transactionList) {
				total += t.getAmount();
			}
			
			Double average = total/months;
			
			System.out.println(String.format("Monthly Average Spend For: %s = %s", category, average));
			
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	private int randomNumber(int range) {
		Random randomNumber = new Random();
		return randomNumber.nextInt(range);
	}
	
	private LocalDate getTransactionDate(int days) {
		LocalDate date = LocalDate.now();
		return date.minusDays(days);
	}
}
