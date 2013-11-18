package dk.itu.gp2013.homework7.exercise1.room.test;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;

import dk.itu.gp2013.homework7.exercise1.room.feature.Employee;
import dk.itu.gp2013.homework7.exercise1.room.feature.FineReceipt;
import dk.itu.gp2013.homework7.exercise1.room.feature.Item;

/**
 * Fine receipt test.
 * 
 * @author Vlad Manea (vlam@itu.dk).
 */
public class FineReceiptTest {
	private static final Item expectedItem = new Item("C3PO", "robot", 10);
	private static final Employee expectedEmployee = new Employee("Luke");

	@SuppressWarnings("deprecation")
	private static final Date expectedBorrowDate = new Date(3000, 0, 1);

	@SuppressWarnings("deprecation")
	private static final Date expectedExpectedReturnDate = new Date(3000, 0, 3);

	@SuppressWarnings("deprecation")
	private static final Date expectedReturnDate = new Date(3000, 0, 10);
	private static final int expectedFine = 20;

	@Test
	public void testGetItem() {
		FineReceipt receipt = new FineReceipt(expectedItem, expectedEmployee,
				expectedBorrowDate, expectedExpectedReturnDate,
				expectedReturnDate, expectedFine);
		Item actualItem = receipt.getItem();

		// Equality at reference level!
		assert (expectedItem == actualItem);
	}

	@Test
	public void testGetItemNull() {
		try {
			new FineReceipt(null, expectedEmployee, expectedBorrowDate,
					expectedExpectedReturnDate, expectedReturnDate,
					expectedFine);
			fail("Receipt should not have been created with null item.");
		} catch (IllegalArgumentException iae) {
			// Perfect.
		}
	}

	// Other tests going in the same direction...
}
