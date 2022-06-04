package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.jacob.codingexercise.enums.Category;

class CategoryTest {

	@Test
	void testGetTextValue() {
		assertAll(
				() -> assertEquals("Direct Debit", Category.DD.getTextValue()),
				() -> assertEquals("Groceries", Category.GROCERIES.getTextValue()),
				() -> assertEquals("Other", Category.OTHER.getTextValue())
				);
	}

}
