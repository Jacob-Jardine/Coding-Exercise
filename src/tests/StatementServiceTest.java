package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.jacob.codingexercise.enums.Category;
import com.jacob.codingexercise.model.Transaction;
import com.jacob.codingexercise.service.IStatementService;
import com.jacob.codingexercise.service.StatementService;

class StatementServiceTest {
	@Test
	void isFalse() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		assertFalse(statement.CreateTransactions(0));
	}

	@Test
	void isTrue() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		
		assertTrue(statement.CreateTransactions(1));
	}
	
	@Test
	void isCorrectSize() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		
		int size = 5;
		
		statement.CreateTransactions(size);
	
		assertEquals(t.size(), size);
	}
	
	@Test
	void isIncorrectSize() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		
		int size = 5;
		
		statement.CreateTransactions(10);
	
		assertNotEquals(t.size(), size);
	}
	
	@Test
	void readStatementFalse() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		
		assertFalse(statement.ReadStatement());
	}
	
	@Test
	void readStatementTrue() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
						
		statement.CreateTransactions(10);
		
		assertTrue(statement.ReadStatement());
	}
	
	@Test
	void sortStatementFalse() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		
		assertFalse(statement.SortStatement());
	}
	
	@Test
	void sortStatementTrue() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		
		statement.CreateTransactions(10);
		
		assertTrue(statement.SortStatement());
	}
	
	@Test
	void allTransactionForCategoryDDTrue() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		Category category = Category.DD;
		
		statement.CreateTransactions(10);
		List<Transaction> result = statement.AllTransactionsForCategory(category);
		
		for(Transaction transaction : result) {
			assertTrue(transaction.getCategory().equals(category));
		}
	}
	
	@Test
	void allTransactionForCategoryDirectDebitDoesntContainOtherValues() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		Category category = Category.DD;
		
		statement.CreateTransactions(10);
		List<Transaction> result = statement.AllTransactionsForCategory(category);
		
		for(Transaction transaction : result) {
			assertTrue(!transaction.getCategory().equals(Category.OTHER) && !transaction.getCategory().equals(Category.GROCERIES));
		}
	}
	
	@Test
	void allTransactionForCategoryGroceriesTrue() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		Category category = Category.GROCERIES;
		
		statement.CreateTransactions(10);
		List<Transaction> result = statement.AllTransactionsForCategory(category);
		
		for(Transaction transaction : result) {
			assertTrue(transaction.getCategory().equals(category));
		}
	}
	
	@Test
	void allTransactionForCategoryGroceriesDoesntContainOtherValues() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		Category category = Category.GROCERIES;
		
		statement.CreateTransactions(10);
		List<Transaction> result = statement.AllTransactionsForCategory(category);
		
		for(Transaction transaction : result) {
			assertTrue(!transaction.getCategory().equals(Category.OTHER) && !transaction.getCategory().equals(Category.DD));
		}
	}
	
	@Test
	void allTransactionForCategoryOtherTrue() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		Category category = Category.OTHER;
		
		statement.CreateTransactions(10);
		List<Transaction> result = statement.AllTransactionsForCategory(category);
		
		for(Transaction transaction : result) {
			assertTrue(transaction.getCategory().equals(category));
		}
	}
	
	@Test
	void allTransactionForCategoryOtherDoesntContainOtherValues() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		Category category = Category.OTHER;
		
		statement.CreateTransactions(10);
		List<Transaction> result = statement.AllTransactionsForCategory(category);
		
		for(Transaction transaction : result) {
			assertTrue(!transaction.getCategory().equals(Category.GROCERIES) && !transaction.getCategory().equals(Category.DD));
		}
	}
}
