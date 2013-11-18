package dk.itu.gp2013.homework7.exercise1.room.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dk.itu.gp2013.homework7.exercise1.room.feature.Item;

/**
 * Item test.
 * 
 * @author Vlad Manea (vlam@itu.dk).
 */
public class ItemTest {
	private static final String expectedName = "C3PO";
	private static final String expectedType = "robot";
	private static final int expectedValue = 10;

	@Test
	public void testGetName() {
		Item item = new Item(expectedName, expectedType, expectedValue);
		final String actualName = item.getName();
		assertEquals(expectedName, actualName);
	}

	@Test
	public void testGetType() {
		Item item = new Item(expectedName, expectedType, expectedValue);
		final String actualType = item.getType();
		assertEquals(expectedType, actualType);
	}

	@Test
	public void testGetValue() {
		Item item = new Item(expectedName, expectedType, expectedValue);
		final int actualValue = item.getValue();
		assertEquals(expectedValue, actualValue);
	}
}
