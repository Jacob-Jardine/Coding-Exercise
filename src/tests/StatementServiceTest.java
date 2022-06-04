package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

import com.jacob.codingexercise.enums.Category;
import com.jacob.codingexercise.enums.Type;
import com.jacob.codingexercise.model.Transaction;
import com.jacob.codingexercise.service.IStatementService;
import com.jacob.codingexercise.service.StatementService;

class StatementServiceTest {
	@Test
	void addTransactionIsFalse() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
		assertFalse(statement.addTransaction(0));
	}

	@Test
	void addTransactionIsTrue() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
		
		assertTrue(statement.addTransaction(1));
	}
	
	@Test
	void addTransactionException() {
		List<Transaction> transactionList = null;
		IStatementService statement = new StatementService(transactionList);
		
		assertFalse(statement.addTransaction(1));
	}
	
	@Test
	void isCorrectSize() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
		
		int size = 5;
		
		statement.addTransaction(size);
	
		assertEquals(transactionList.size(), size);
	}
	
	@Test
	void isIncorrectSize() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
		
		int size = 5;
		
		statement.addTransaction(10);
	
		assertNotEquals(transactionList.size(), size);
	}
	
	@Test
	void readStatementFalse() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
		
		assertFalse(statement.readStatement());
	}
	
	@Test
	void readStatementTrue() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
						
		statement.addTransaction(10);
		
		assertTrue(statement.readStatement());
	}
	
	@Test
	void readStatementException() {
		List<Transaction> transactionList = null;
		IStatementService statement = new StatementService(transactionList);
						
		assertFalse(statement.readStatement());
	}
	
	@Test
	void sortStatementFalse() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
		
		assertFalse(statement.sortStatement());
	}
	
	@Test
	void sortStatementTrue() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
		
		statement.addTransaction(10);
		
		assertTrue(statement.sortStatement());
	}
	
	@Test
	void sortStatementException() {
		List<Transaction> transactionList = null;
		IStatementService statement = new StatementService(transactionList);
		
		assertFalse(statement.sortStatement());
	}
	
	@Test
	void filterTransactionsByCategoryException() {
		List<Transaction> transactionList = null;
		IStatementService statement = new StatementService(transactionList);
		Category category = Category.DD;
		
		assertNull(statement.filterTransactionsByCategory(category));
	}
	
	@Test
	void allTransactionForCategoryDDTrue() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
		Category category = Category.DD;
		
		statement.addTransaction(10);
		List<Transaction> result = statement.filterTransactionsByCategory(category);
		
		for(Transaction transaction : result) {
			assertTrue(transaction.getCategory().equals(category));
		}
	}
	
	@Test
	void allTransactionForCategoryDirectDebitDoesntContainOtherValues() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
		Category category = Category.DD;
		
		statement.addTransaction(10);
		List<Transaction> result = statement.filterTransactionsByCategory(category);
		
		for(Transaction transaction : result) {
			assertTrue(!transaction.getCategory().equals(Category.OTHER) && !transaction.getCategory().equals(Category.GROCERIES));
		}
	}
	
	@Test
	void allTransactionForCategoryGroceriesTrue() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
		Category category = Category.GROCERIES;
		
		statement.addTransaction(10);
		List<Transaction> result = statement.filterTransactionsByCategory(category);
		
		for(Transaction transaction : result) {
			assertTrue(transaction.getCategory().equals(category));
		}
	}
	
	@Test
	void allTransactionForCategoryGroceriesDoesntContainOtherValues() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
		Category category = Category.GROCERIES;
		
		statement.addTransaction(10);
		List<Transaction> result = statement.filterTransactionsByCategory(category);
		
		for(Transaction transaction : result) {
			assertTrue(!transaction.getCategory().equals(Category.OTHER) && !transaction.getCategory().equals(Category.DD));
		}
	}
	
	@Test
	void allTransactionForCategoryOtherTrue() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
		Category category = Category.OTHER;
		
		statement.addTransaction(10);
		List<Transaction> result = statement.filterTransactionsByCategory(category);
		
		for(Transaction transaction : result) {
			assertTrue(transaction.getCategory().equals(category));
		}
	}
	
	@Test
	void allTransactionForCategoryOtherDoesntContainOtherValues() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
		Category category = Category.OTHER;
		
		statement.addTransaction(10);
		List<Transaction> result = statement.filterTransactionsByCategory(category);
		
		for(Transaction transaction : result) {
			assertTrue(!transaction.getCategory().equals(Category.GROCERIES) && !transaction.getCategory().equals(Category.DD));
		}
	}
	
	@Test
	void getCategoryYearException() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
		
		assertNull(statement.getCategoryYear(transactionList));
	}
	
	@Test
	void getCategoryYearTrue() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
		
		Transaction t1 = new Transaction(1.1, Category.DD, LocalDate.now().minusYears(1), Type.DD, "CYBG");
		Transaction t2 = new Transaction(1.1, Category.DD, LocalDate.now(), Type.DD, "CYBG");
		transactionList.add(t1);
		transactionList.add(t2);
		
		List<Integer> result = statement.getCategoryYear(transactionList);
		
		for(int i = 0; i < transactionList.size(); i++) {
			assertEquals(result.get(i), transactionList.get(i).getTransactionDate().getYear());
		}
	}
	
	@Test
	void highestSpendException() {
		List<Transaction> transactionList = null;
		IStatementService statement = new StatementService(transactionList);
		
		Category category = Category.DD;
		int year = LocalDate.now().getYear();
		
		assertNull(statement.highestSpendByCategory(category, year));
	}
	
	@Test
	void highestSpendTrue() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
		
		Category category = Category.DD;
		int year = LocalDate.now().getYear();
		
		Transaction t1 = new Transaction(1.1, Category.DD, LocalDate.now().minusYears(1), Type.DD, "CYBG");
		Transaction t2 = new Transaction(1.1, Category.DD, LocalDate.now(), Type.DD, "CYBG");
		Transaction t3 = new Transaction(10.10, Category.DD, LocalDate.now(), Type.DD, "CYBG");
		transactionList.add(t1);
		transactionList.add(t2);
		transactionList.add(t3);

		assertEquals(statement.highestSpendByCategory(category, year), t3.getAmount());
	}
	
	@Test
	void lowestSpendException() {
		List<Transaction> transactionList = null;
		IStatementService statement = new StatementService(transactionList);
		
		Category category = Category.DD;
		int year = LocalDate.now().getYear();
		
		assertNull(statement.lowestSpendByCategory(category, year));
	}
	
	@Test
	void lowestSpendTrue() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
		
		Category category = Category.DD;
		int year = LocalDate.now().getYear();
		
		Transaction t1 = new Transaction(1.1, Category.DD, LocalDate.now().minusYears(1), Type.DD, "CYBG");
		Transaction t2 = new Transaction(10.10, Category.DD, LocalDate.now(), Type.DD, "CYBG");
		Transaction t3 = new Transaction(2.0, Category.DD, LocalDate.now(), Type.DD, "CYBG");
		transactionList.add(t1);
		transactionList.add(t2);
		transactionList.add(t3);

		assertEquals(statement.lowestSpendByCategory(category, year), t3.getAmount());
	}
	
	@Test
	void totalPerCategoryException() {
		List<Transaction> transactionList = null;
		IStatementService statement = new StatementService(transactionList);
		
		assertNull(statement.totalAmountPerCategory());
	}
	
	@Test
	void totalPerCategoryTrue() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
		
		Transaction t1 = new Transaction(1.1, Category.DD, LocalDate.now().minusYears(1), Type.DD, "CYBG");	
		Transaction t2 = new Transaction(2.0, Category.OTHER, LocalDate.now(), Type.DD, "CYBG");
		Transaction t3 = new Transaction(10.10, Category.GROCERIES, LocalDate.now(), Type.DD, "CYBG");
		transactionList.add(t1);
		transactionList.add(t2);
		transactionList.add(t3);
		
		statement.sortStatement();
		
		Map<Enum<Category>, Double> result = statement.totalAmountPerCategory();
		Map<Enum<Category>, Double> expected = new HashMap<Enum<Category>, Double>();
		
		expected.put(Category.DD, 1.1);
		expected.put(Category.OTHER, 2.0);
		expected.put(Category.GROCERIES, 10.10);

		assertEquals(expected, result);
}
	
	@Test
	void monthlyAverageSpendForCategory() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
		
		Transaction t1 = new Transaction(10.0, Category.DD, LocalDate.now(), Type.DD, "CYBG");
		Transaction t2 = new Transaction(10.0, Category.DD, LocalDate.now().minusMonths(2), Type.DD, "CYBG");
	
		transactionList.add(t1);
		transactionList.add(t2);
		
		double expected = 10.0;
		
		statement.sortStatement();
		
		assertEquals(statement.monthlyAverageSpendByCategory(Category.DD), expected);
	}
	
	@Test
	void monthlyAverageSpendForCategoryException() {
		List<Transaction> transactionList = null;
		IStatementService statement = new StatementService(transactionList);
		
		assertNull(statement.monthlyAverageSpendByCategory(null));
	}
	
	@Test
	void assignCategoryException() {
		List<Transaction> transactionList = null;
		IStatementService statement = new StatementService(transactionList);
		
		assertNull(statement.assignCategory(null, null));
	}
	
	@Test
	void assignCategoryTrue() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(transactionList);
		
		Transaction t1 = new Transaction(10.0, Category.DD, LocalDate.now(), Type.DD, "CYBG");

		transactionList.add(t1);
		
		statement.assignCategory(t1, Category.GROCERIES);
		
		assertTrue(t1.getCategory() == Category.GROCERIES);
	}
}
