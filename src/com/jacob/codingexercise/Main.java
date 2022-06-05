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
		
		IStatementService Statement = setUp();
		
		String[] options = {
				"1- Assign category",
				"2- View all transaction for a given category",
				"3- Total outgoing per category",
				"4- Montly average spend for a given category",
				"5- Highest spend for a given category, for a given year",
				"6- Lowest spend for a given category, for a given year",
				"7- Exit"
		};
		
		Scanner scanner = new Scanner(System.in);
		int option = 1;
		while (true) {
			printMenu(options);
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
					option3(Statement);
					break;
				case 4:
					option4(Statement);
					break;
				case 5:
					option5(Statement);
					break;
				case 6:
					option6(Statement);
					break;
				case 7:
					exitApplication();
					break;
				}
			}catch(Exception e) {
				System.out.println(String.format("Please enter an integer value between 1 and %s", options.length));
				scanner.next();
			}
		}
	}
	
	private static IStatementService setUp() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
		statement.addTransaction(20);
		statement.sortStatement();
		return statement;
	}
	
	public static void printMenu(String[] options) {
		for(String option : options) {
			System.out.println(option);
		}
	}
	
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
			System.out.println("Select category to view transactions");
			printMenu(options);
			try {
				option = scanner.nextInt();
				switch(option) {
				case 1:
					t = statement.filterTransactionsByCategory(Category.DD);
					statement.readStatement(t);
					option7(statement, t);
					break;
				case 2:
					t = statement.filterTransactionsByCategory(Category.GROCERIES);
					statement.readStatement(t);
					option7(statement, t);
					break;
				case 3:
					t = statement.filterTransactionsByCategory(Category.OTHER);
					statement.readStatement(t);
					option7(statement, t);
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
			printMenu(options);
			try {
				option = scanner.nextInt();
				switch(option) {
				case 1:
					t = statement.filterTransactionsByCategory(Category.DD);
					statement.readStatement(t);
					break;
				case 2:
					t = statement.filterTransactionsByCategory(Category.GROCERIES);
					statement.readStatement(t);
					break;
				case 3:
					t = statement.filterTransactionsByCategory(Category.OTHER);
					statement.readStatement(t);
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
	
	private static void option3(IStatementService statement) {
		statement.totalAmountPerCategory();	
	}
	
	private static void option4(IStatementService statement) {
		String[] options = {
				"1- Direct Debit",
				"2- Groceries",
				"3- Other",
				"4- Back"
		};

		Scanner scanner = new Scanner(System.in);
		int option = 1;
		while (true) {
			printMenu(options);
			try {
				option = scanner.nextInt();
				switch(option) {
				case 1:
					statement.monthlyAverageSpendByCategory(Category.DD);
					break;
				case 2:
					statement.monthlyAverageSpendByCategory(Category.GROCERIES);
					break;
				case 3:
					statement.monthlyAverageSpendByCategory(Category.OTHER);
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
			printMenu(options);
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
	
	private static void option6(IStatementService statement) {
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
			printMenu(options);
			try {
				option = scanner.nextInt();
				switch(option) {
				case 1:
					t = statement.filterTransactionsByCategory(Category.DD);
					iList = statement.getCategoryYear(t);
					option9(statement, Category.DD, iList);
					break;
				case 2:
					t = statement.filterTransactionsByCategory(Category.GROCERIES);
					iList = statement.getCategoryYear(t);
					option9(statement, Category.GROCERIES,  iList);
					break;
				case 3:
					t = statement.filterTransactionsByCategory(Category.OTHER);
					iList = statement.getCategoryYear(t);
					option9(statement, Category.OTHER,  iList);
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
	
	
	private static void option7(IStatementService statement, List<Transaction> transactionList) {
		Scanner scanner = new Scanner(System.in);
		int option = 1;
		int nextOption = 1;
		while (true) {
			try {
				System.out.println("Type transaction number to change category");
				option = scanner.nextInt();
				if(option <= transactionList.size()) {
					System.out.println(transactionList.get(option).toString());
					System.out.println("1- Change to Direct Debit \n"
							+ "2- Change to Groceries \n"
							+ "3- Change to Other \n"
							+ "4- Back");
					nextOption = scanner.nextInt();
					if(nextOption == 1 && transactionList.get(option).getCategory() == Category.DD || 
							nextOption == 2 && transactionList.get(option).getCategory() == Category.GROCERIES ||
							nextOption == 3 && transactionList.get(option).getCategory() == Category.OTHER) {
						System.out.println("Can't change category to same category");
						return;
						
					}
					switch(nextOption) {
					case 1:
						statement.assignCategory(transactionList.get(option), Category.DD);
						System.out.println(transactionList.get(option).toString() + "Updated!");
						return;
					case 2:
						statement.assignCategory(transactionList.get(option), Category.GROCERIES);
						System.out.println(transactionList.get(option).toString() + "Updated!");
						return;
					case 3:
						statement.assignCategory(transactionList.get(option), Category.OTHER);
						System.out.println(transactionList.get(option).toString() + "Updated!");
						return;
					case 4:
						return;
					}
				}
				
			}catch(Exception e) {
				System.out.println("Please enter an integer value between 1 and 4");
				scanner.next();
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
	
	private static void option9(IStatementService statement, Enum<Category> category, List<Integer> iList) {
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
	
	private static void exitApplication() {
		System.out.println("Exiting");
		System.exit(0);
	}
}
