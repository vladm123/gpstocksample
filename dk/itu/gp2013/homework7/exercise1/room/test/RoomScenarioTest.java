/**
 * 
 */
package dk.itu.gp2013.homework7.exercise1.room.test;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dk.itu.gp2013.homework7.exercise1.room.feature.Employee;
import dk.itu.gp2013.homework7.exercise1.room.feature.FineReceipt;
import dk.itu.gp2013.homework7.exercise1.room.feature.InexactPaymentException;
import dk.itu.gp2013.homework7.exercise1.room.feature.Item;
import dk.itu.gp2013.homework7.exercise1.room.feature.ItemUnavailableException;
import dk.itu.gp2013.homework7.exercise1.room.feature.Room;

/**
 * Room scenario test.
 * 
 * @author Vlad Manea (vlam@itu.dk).
 */
public class RoomScenarioTest {
	private final Room jabba = new Room();

	private final Employee luke = new Employee("Luke");
	private final Employee darth = new Employee("Darth");

	private final Item helmet = new Item("Helmet", "3000X", 10);
	private final Item c3po = new Item("C3PO", "annoying robot", 5);

	/**
	 * Sets up by being run before each scenario test. Here, we register the two
	 * employees and items to the room.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		jabba.registerEmployee(luke);
		jabba.registerEmployee(darth);

		jabba.registerItem(helmet);
		jabba.registerItem(c3po);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void test() throws InexactPaymentException, ItemUnavailableException {

		// Luke rents a helmet for 3 days starting 1 Jan 3000.
		jabba.rent(luke, helmet, new Date(1100, 0, 1), 3);

		// Darth tries without success to rent the helmet.
		try {
			jabba.rent(darth, helmet, new Date(1100, 0, 2), 4);
			fail("Darth should have not been able to rent.");
		} catch (ItemUnavailableException iue) {
			// Nothing has to be done here.
		}

		// Darth registers for the robot and gets notified instantly.
		jabba.reserve(darth, c3po);

		// Luke rents the robot now, for roughly more than 3000 years.
		jabba.rent(luke, c3po, new Date(1100, 0, 3), 3000 * 366);

		// Darth registers for the robot again, but does not get notified.
		jabba.reserve(darth, c3po);

		// Luke forgot to bring both items. A fine is applied.
		List<FineReceipt> receipts = jabba.getFineReceipts(luke, new Date(1100,
				0, 8));

		for (FineReceipt receipt : receipts) {
			Item item = receipt.getItem();

			// There is only one fine, applied to the helmet.
			// Reference equality assertion.
			assert (helmet == item);

			// Try to pay more money than necessary without success.
			try {
				jabba.makePayment(receipt, receipt.getFine() + 1);
				fail("Too much money were paid.");
			} catch (InexactPaymentException ipe) {
				// Nothing has to be done here.
			}

			// Try to pay less money than necessary without success.
			try {
				jabba.makePayment(receipt, receipt.getFine() - 1);
				fail("Too little money were paid.");
			} catch (InexactPaymentException ipe) {
				// Nothing has to be done here.
			}

			// Pay the exact required amount of money.
			jabba.makePayment(receipt, receipt.getFine());
		}

		// Luke returns the robot on time, and Darth should be notified.
		jabba.bring(c3po, new Date(3100, 0, 1));

		// Luke has no debt to Jabba.
		if (jabba.getFineReceipts(luke, new Date(3100, 0, 1)).size() > 0) {
			fail("Luke should have no debt.");
		}
	}
}
