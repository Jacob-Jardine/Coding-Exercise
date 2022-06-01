package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.jacob.codingexercise.enums.Type;

class TypeTest {

	@Test
	void testGetTextValue() {
		assertAll(
				() -> assertEquals("Card", Type.CARD.getTextValue()),
				() -> assertEquals("Direct Debit", Type.DD.getTextValue()),
				() -> assertEquals("Internet", Type.INTERNET.getTextValue())
				);
	}
}
