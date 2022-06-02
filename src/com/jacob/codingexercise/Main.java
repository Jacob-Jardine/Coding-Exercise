package com.jacob.codingexercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.jacob.codingexercise.enums.Category;
import com.jacob.codingexercise.model.Transaction;
import com.jacob.codingexercise.service.IStatementService;
import com.jacob.codingexercise.service.StatementService;


public class Main {

	public static void main(String[] args) {
		
		IStatementService Statement = SetUp();
		
		String[] options = {
				"1- View all transaction for a given category",
				"2- Total outgoing per category",
				"3- Montly average spend for a given category",
				"4- Highest spend for a given category, for a given year",
				"5- Lowest spend for a given category, for a given year",
				"6- Exit"
		};
		
		Scanner scanner = new Scanner(System.in);
		int option = 1;
		while (true) {
			PrintMenu(options);
			try {
				option = scanner.nextInt();
				switch(option) {
				case 1:
					option1(Statement);
					break;
				case 2:
					option2(Statement);
					break;
				case 3:
					option3();
					break;
				case 4:
					option4(Statement);
					break;
				case 5:
					option5(Statement);
					break;
				case 6:
					option6();
					break;
				}
			}catch(Exception e) {
				System.out.println(String.format("Please enter an integer value between 1 and %s", options.length));
				scanner.next();
			}
		}
	}
	
	private static IStatementService SetUp() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
		statement.addTransaction(20);
		statement.sortStatement();
		return statement;
	}
	
	public static void PrintMenu(String[] options) {
		for(String option : options) {
			System.out.println(option);
		}
	}
	
	public static void ReadStatement(List<Transaction> transactionList) {
		for(Transaction item: transactionList) {
			System.out.println(item.toString());
		}
	}
	
	// Options
	private static void option1(IStatementService statement) {
		String[] options = {
				"1- Direct Debit",
				"2- Groceries",
				"3- Other",
				"4- Back"
		};
		
		List<Transaction> t = new ArrayList<Transaction>();
		
		Scanner scanner = new Scanner(System.in);
		int option = 1;
		while (true) {
			PrintMenu(options);
			try {
				option = scanner.nextInt();
				switch(option) {
				case 1:
					t = statement.filterTransactionsByCategory(Category.DD);
					ReadStatement(t);
					break;
				case 2:
					t = statement.filterTransactionsByCategory(Category.GROCERIES);
					ReadStatement(t);
					break;
				case 3:
					t = statement.filterTransactionsByCategory(Category.OTHER);
					ReadStatement(t);
					break;
				case 4:
					return;
				}
			}catch(Exception e) {
				System.out.println(String.format("Please enter an integer value between 1 and %s", options.length));
				scanner.next();
			}
		}
	}
	
	private static void option2(IStatementService statement) {
		statement.totalAmountPerCategory();	
	}
	
	private static void option3() {
		System.out.println("Option3");
	}
	
	private static void option4(IStatementService statement) {
		String[] options = {
				"1- Direct Debit",
				"2- Groceries",
				"3- Other",
				"4- Back"
		};
		
		List<Transaction> t = new ArrayList<Transaction>();
		List<Integer> iList = new ArrayList<Integer>();
		Scanner scanner = new Scanner(System.in);
		int option = 1;
		while (true) {
			PrintMenu(options);
			try {
				option = scanner.nextInt();
				switch(option) {
				case 1:
					t = statement.filterTransactionsByCategory(Category.DD);
					iList = statement.getCategoryYear(t);
					option7(statement, Category.DD, iList);
					break;
				case 2:
					t = statement.filterTransactionsByCategory(Category.GROCERIES);
					iList = statement.getCategoryYear(t);
					option7(statement, Category.GROCERIES,  iList);
					break;
				case 3:
					t = statement.filterTransactionsByCategory(Category.OTHER);
					iList = statement.getCategoryYear(t);
					option7(statement, Category.OTHER,  iList);
					break;
				case 4:
					return;
				}
			}catch(Exception e) {
				System.out.println(String.format("Please enter an integer value between 1 and %s", options.length));
				scanner.next();
			}
		}
	}
	
	private static void option5(IStatementService statement) {
		String[] options = {
				"1- Direct Debit",
				"2- Groceries",
				"3- Other",
				"4- Back"
		};
		
		List<Transaction> t = new ArrayList<Transaction>();
		List<Integer> iList = new ArrayList<Integer>();
		Scanner scanner = new Scanner(System.in);
		int option = 1;
		while (true) {
			PrintMenu(options);
			try {
				option = scanner.nextInt();
				switch(option) {
				case 1:
					t = statement.filterTransactionsByCategory(Category.DD);
					iList = statement.getCategoryYear(t);
					option8(statement, Category.DD, iList);
					break;
				case 2:
					t = statement.filterTransactionsByCategory(Category.GROCERIES);
					iList = statement.getCategoryYear(t);
					option8(statement, Category.GROCERIES,  iList);
					break;
				case 3:
					t = statement.filterTransactionsByCategory(Category.OTHER);
					iList = statement.getCategoryYear(t);
					option8(statement, Category.OTHER,  iList);
					break;
				case 4:
					return;
				}
			}catch(Exception e) {
				System.out.println(String.format("Please enter an integer value between 1 and %s", options.length));
				scanner.next();
			}
		}
	}
	
	private static void option6() {
		System.out.println("Exiting");
		System.exit(0);
	}
	
	private static void option7(IStatementService statement, Enum<Category> category, List<Integer> iList) {
		Scanner scanner = new Scanner(System.in);
		int option = 1;
		while (true) {
			for(Integer item : iList) {
				System.out.println(item);
			}
			try {
				option = scanner.nextInt();
				if(iList.contains(option)) {
					statement.highestSpendByCategory(category, option);
					return;
				}
				else {
					return;
				}
			}catch(Exception e) {
				System.out.println(String.format("Please enter a year within range"));
				return;
			}
		}
	}
	
	private static void option8(IStatementService statement, Enum<Category> category, List<Integer> iList) {
		Scanner scanner = new Scanner(System.in);
		int option = 1;
		while (true) {
			for(Integer item : iList) {
				System.out.println(item);
			}
			try {
				option = scanner.nextInt();
				if(iList.contains(option)) {
					statement.lowestSpendByCategory(category, option);
					return;
				}
				else {
					return;
				}
			}catch(Exception e) {
				System.out.println(String.format("Please enter a year within range"));
				return;
			}
		}
	}
}
