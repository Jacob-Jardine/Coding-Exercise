package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import com.jacob.codingexercise.enums.Category;
import com.jacob.codingexercise.enums.Type;
import com.jacob.codingexercise.model.Transaction;

class TransactionTest {

	@org.junit.jupiter.api.Test
	void testTransactionNotNull() {
		Transaction transaction = new Transaction(1.1, Category.DD, LocalDate.now(), Type.DD, "CYBG");
		assertNotNull(transaction);
	}

	@org.junit.jupiter.api.Test
	void testTransactionNull() {
		Transaction transaction = null;
		assertNull(transaction);
	}
}
