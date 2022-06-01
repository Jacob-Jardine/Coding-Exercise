package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.jacob.codingexercise.model.Transaction;
import com.jacob.codingexercise.service.IStatementService;
import com.jacob.codingexercise.service.StatementService;

class StatementServiceTest {

	List<Transaction> t = new ArrayList<Transaction>();
	IStatementService statement = new StatementService(t);
	
	@Test
	void isFalse() {
		assertFalse(statement.CreateTransactions(0));
	}

	@Test
	void isTrue() {
		assertTrue(statement.CreateTransactions(1));
	}
	
	@Test
	void isCorrectSize() {
		int size = 5;
		
		statement.CreateTransactions(5);
	
		assertEquals(t.size(), size);
	}
	
	@Test
	void isIncorrectSize() {
		int size = 5;
		
		statement.CreateTransactions(10);
	
		assertNotEquals(t.size(), size);
	}
}
