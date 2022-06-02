package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.jacob.codingexercise.enums.Category;
import com.jacob.codingexercise.enums.Type;
import com.jacob.codingexercise.model.Transaction;
import com.jacob.codingexercise.service.IStatementService;
import com.jacob.codingexercise.service.StatementService;

class StatementServiceTest {
	@Test
	void addTransactionIsFalse() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		assertFalse(statement.addTransaction(0));
	}

	@Test
	void addTransactionIsTrue() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		
		assertTrue(statement.addTransaction(1));
	}
	
	@Test
	void addTransactionException() {
		List<Transaction> t = null;
		IStatementService statement = new StatementService(t);
		
		assertFalse(statement.addTransaction(1));
	}
	
	@Test
	void isCorrectSize() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		
		int size = 5;
		
		statement.addTransaction(size);
	
		assertEquals(t.size(), size);
	}
	
	@Test
	void isIncorrectSize() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		
		int size = 5;
		
		statement.addTransaction(10);
	
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
						
		statement.addTransaction(10);
		
		assertTrue(statement.ReadStatement());
	}
	
	@Test
	void readStatementException() {
		List<Transaction> t = null;
		IStatementService statement = new StatementService(t);
						
		assertFalse(statement.ReadStatement());
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
		
		statement.addTransaction(10);
		
		assertTrue(statement.SortStatement());
	}
	
	@Test
	void sortStatementException() {
		List<Transaction> t = null;
		IStatementService statement = new StatementService(t);
		
		assertFalse(statement.SortStatement());
	}
	
	@Test
	void filterTransactionsByCategoryException() {
		List<Transaction> t = null;
		IStatementService statement = new StatementService(t);
		Category category = Category.DD;
		
		assertNull(statement.filterTransactionsByCategory(category));
	}
	
	@Test
	void allTransactionForCategoryDDTrue() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		Category category = Category.DD;
		
		statement.addTransaction(10);
		List<Transaction> result = statement.filterTransactionsByCategory(category);
		
		for(Transaction transaction : result) {
			assertTrue(transaction.getCategory().equals(category));
		}
	}
	
	@Test
	void allTransactionForCategoryDirectDebitDoesntContainOtherValues() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		Category category = Category.DD;
		
		statement.addTransaction(10);
		List<Transaction> result = statement.filterTransactionsByCategory(category);
		
		for(Transaction transaction : result) {
			assertTrue(!transaction.getCategory().equals(Category.OTHER) && !transaction.getCategory().equals(Category.GROCERIES));
		}
	}
	
	@Test
	void allTransactionForCategoryGroceriesTrue() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		Category category = Category.GROCERIES;
		
		statement.addTransaction(10);
		List<Transaction> result = statement.filterTransactionsByCategory(category);
		
		for(Transaction transaction : result) {
			assertTrue(transaction.getCategory().equals(category));
		}
	}
	
	@Test
	void allTransactionForCategoryGroceriesDoesntContainOtherValues() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		Category category = Category.GROCERIES;
		
		statement.addTransaction(10);
		List<Transaction> result = statement.filterTransactionsByCategory(category);
		
		for(Transaction transaction : result) {
			assertTrue(!transaction.getCategory().equals(Category.OTHER) && !transaction.getCategory().equals(Category.DD));
		}
	}
	
	@Test
	void allTransactionForCategoryOtherTrue() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		Category category = Category.OTHER;
		
		statement.addTransaction(10);
		List<Transaction> result = statement.filterTransactionsByCategory(category);
		
		for(Transaction transaction : result) {
			assertTrue(transaction.getCategory().equals(category));
		}
	}
	
	@Test
	void allTransactionForCategoryOtherDoesntContainOtherValues() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		Category category = Category.OTHER;
		
		statement.addTransaction(10);
		List<Transaction> result = statement.filterTransactionsByCategory(category);
		
		for(Transaction transaction : result) {
			assertTrue(!transaction.getCategory().equals(Category.GROCERIES) && !transaction.getCategory().equals(Category.DD));
		}
	}
	
	@Test
	void getCategoryYearException() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		
		assertNull(statement.GetCategoryYear(t));
	}
	
	@Test
	void getCategoryYearTrue() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		
		Transaction t1 = new Transaction(1.1, Category.DD, LocalDate.now().minusYears(1), Type.DD, "CYBG");
		Transaction t2 = new Transaction(1.1, Category.DD, LocalDate.now(), Type.DD, "CYBG");
		t.add(t1);
		t.add(t2);
		
		List<Integer> result = statement.GetCategoryYear(t);
		
		for(int i = 0; i < t.size(); i++) {
			assertEquals(result.get(i), t.get(i).getTransactionDate().getYear());
		}
	}
	
	@Test
	void highestSpendException() {
		List<Transaction> t = null;
		IStatementService statement = new StatementService(t);
		
		Category category = Category.DD;
		int year = LocalDate.now().getYear();
		
		assertNull(statement.HighestSpend(category, year));
	}
	
	@Test
	void highestSpendTrue() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		
		Category category = Category.DD;
		int year = LocalDate.now().getYear();
		
		Transaction t1 = new Transaction(1.1, Category.DD, LocalDate.now().minusYears(1), Type.DD, "CYBG");
		Transaction t2 = new Transaction(1.1, Category.DD, LocalDate.now(), Type.DD, "CYBG");
		Transaction t3 = new Transaction(10.10, Category.DD, LocalDate.now(), Type.DD, "CYBG");
		t.add(t1);
		t.add(t2);
		t.add(t3);

		assertTrue(statement.HighestSpend(category, year));
	}
	
	@Test
	void lowestSpendException() {
		List<Transaction> t = null;
		IStatementService statement = new StatementService(t);
		
		Category category = Category.DD;
		int year = LocalDate.now().getYear();
		
		assertNull(statement.LowestSpend(category, year));
	}
	
	@Test
	void lowestSpendTrue() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		
		Category category = Category.DD;
		int year = LocalDate.now().getYear();
		
		Transaction t1 = new Transaction(1.1, Category.DD, LocalDate.now().minusYears(1), Type.DD, "CYBG");
		Transaction t2 = new Transaction(10.10, Category.DD, LocalDate.now(), Type.DD, "CYBG");
		Transaction t3 = new Transaction(1.1, Category.DD, LocalDate.now(), Type.DD, "CYBG");
		t.add(t1);
		t.add(t2);
		t.add(t3);

		assertTrue(statement.LowestSpend(category, year));
	}
	
	@Test
	void totalPerCategoryException() {
		List<Transaction> t = null;
		IStatementService statement = new StatementService(t);
		
		assertFalse(statement.TotalPerCategory());
	}
	
	@Test
	void totalPerCategoryTrue() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		
		statement.addTransaction(10);
		
		statement.SortStatement();
		
		assertTrue(statement.TotalPerCategory());
	}
	
	@Test
	void monthlyAverageSpendForCategory() {
		List<Transaction> t = new ArrayList<Transaction>();
		IStatementService statement = new StatementService(t);
		
		statement.addTransaction(20);
		
		statement.SortStatement();
		
		assertTrue(statement.monthlyAverageSpendForCategory(Category.DD));
	}
	
	@Test
	void monthlyAverageSpendForCategoryException() {
		List<Transaction> t = null;
		IStatementService statement = new StatementService(t);
		
		assertFalse(statement.monthlyAverageSpendForCategory(null));
	}
}
