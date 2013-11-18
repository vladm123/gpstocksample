package dk.itu.gp2013.homework7.exercise1.room.feature;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Room class<br>
 * Used for managing the employees and items.
 * 
 * @author Vlad Manea (vlam@itu.dk)
 */
public class Room {

	// The registered employees. All objects referenced below should also exist
	// here.
	private List<Employee> employees = new ArrayList<>();

	// The registered items. All objects referenced below should also exist
	// here.
	private List<Item> items = new ArrayList<>();

	// The list of reservations.
	private List<Reservation> reservations = new ArrayList<>();

	// The list of payments.
	private List<Payment> payments = new ArrayList<>();

	/**
	 * Registers an item.<br>
	 * If the item is already registered, it will not be registered twice.
	 * 
	 * @param item
	 *            The item to be registered (must not be null).
	 * @see Item.
	 */
	public void registerItem(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("Item is null.");
		}

		// First check that the item does not exist.
		for (Item existingItem : items) {
			if (existingItem.equals(item)) {
				return;
			}
		}

		items.add(item);
	}

	/**
	 * Registers an employee.<br>
	 * If the employee is already registered, it will not be registered twice.
	 * 
	 * @param employee
	 *            The employee to be registered (must not be null).
	 * @see Employee.
	 */
	public void registerEmployee(Employee employee) {
		if (employee == null) {
			throw new IllegalArgumentException("Employee is null.");
		}

		// First check that the employee does not exist.
		for (Employee existingEmployee : employees) {
			if (existingEmployee.equals(employee)) {
				return;
			}
		}

		employees.add(employee);
	}

	/**
	 * Reserves an item for an employee.<br>
	 * If the reservation is already done, it will not happen twice.
	 * Reservations are not binding any obligations to the employee.
	 * 
	 * @param employee
	 *            The employee to whom the reservation is done (must not be
	 *            null).
	 * @param item
	 *            The reserved item (must not be null).
	 * @see Employee.
	 * @see Item.
	 */
	public void reserve(Employee employee, Item item) {
		if (employee == null) {
			throw new IllegalArgumentException("Employee is null.");
		}

		if (item == null) {
			throw new IllegalArgumentException("Item is null.");
		}

		// Short circuit the reservation, if the item exists.
		if (isItemAvailable(item)) {
			announce(employee, item);
			return;
		}

		// First check that the reservation does not exist.
		for (Reservation reservation : reservations) {
			if (reservation.getEmployee().equals(employee)
					&& reservation.getItem().equals(item)) {
				return;
			}
		}

		reservations.add(new Reservation(employee, item));
	}

	/**
	 * Rents an item by an employee.
	 * 
	 * @param employee
	 *            The employee who rents (must not be null).
	 * @param item
	 *            The rented item (must not be null).
	 * @param fromDate
	 *            The date from which the rent starts (must not be null).
	 * @param forDays
	 *            The amount of days for which the rental is planned (must be
	 *            positive).
	 * @throws ItemUnavailableException
	 *             If the item is already in use.
	 * 
	 * @see Employee.
	 * @see Item.
	 * @see Date.
	 */
	public void rent(Employee employee, Item item, Date fromDate, int forDays)
			throws ItemUnavailableException {
		if (employee == null) {
			throw new IllegalArgumentException("Employee is null.");
		}

		if (item == null) {
			throw new IllegalArgumentException("Item is null.");
		}

		if (fromDate == null) {
			throw new IllegalArgumentException("From date is null.");
		}

		if (forDays <= 0) {
			throw new IllegalArgumentException("For days is not positive.");
		}

		// First check that the item is not used.
		if (!isItemAvailable(item)) {
			throw new ItemUnavailableException(item);
		}

		// Calculate the expected return date based on the from date.
		Date expectedReturnDate = addDays(fromDate, forDays);

		// Usage is kept inside the payment, as it is related to the payment.
		// Note that the usage itself does not know much about payment info.
		Usage usage = new Usage(employee, item, fromDate, expectedReturnDate);
		Payment payment = new Payment(usage, item.getValue());
		payments.add(payment);
	}

	/**
	 * Brings back an item.
	 * 
	 * @param item
	 *            The brought item (must not be null).
	 * @param atDate
	 *            The date at which the item is brought back (must not be null).
	 * @see Item.
	 */
	public void bring(Item item, Date atDate) {
		if (item == null) {
			throw new IllegalArgumentException("Item is null.");
		}

		if (atDate == null) {
			throw new IllegalArgumentException("At date is null.");
		}

		Payment payment = null;

		// First see if the item is not used.
		for (Payment registeredPayment : payments) {
			Usage usage = registeredPayment.getUsage();

			if (usage.getItem().equals(item) && !usage.isReturned()) {
				payment = registeredPayment;
				break;
			}
		}

		if (payment == null) {
			// The item was not found in the inventory.
			// Did the employee take it from some other repository?
			return;
		}

		// Mark the usage as finished.
		payment.getUsage().setReturnDate(atDate);

		// Notify the first reservation of this item.
		notifyFirstReservation(payment.getUsage().getItem());
	}

	/**
	 * Gets the fine receipts for a particular employee.
	 * 
	 * @param employee
	 *            The employee (must not be null).
	 * @param forDate
	 *            The date for which the fine receipts are generated (must not
	 *            be null).
	 * @return The fine receipts.
	 * @see List.
	 * @see FineReceipt.
	 * @see Employee.
	 */
	public List<FineReceipt> getFineReceipts(Employee employee, Date forDate) {
		if (employee == null) {
			throw new IllegalArgumentException("Employee is null.");
		}

		if (forDate == null) {
			throw new IllegalArgumentException("For date is null.");
		}

		List<FineReceipt> receipts = new ArrayList<>();

		for (Payment payment : payments) {
			int fine = payment.getFine(forDate);

			// Any payment that has a fine is added to the receipt.
			if (fine > 0) {
				Usage usage = payment.getUsage();
				FineReceipt receipt = new FineReceipt(usage.getItem(),
						usage.getEmployee(), usage.getBorrowDate(),
						usage.getExpectedReturnDate(), usage.getReturnDate(),
						fine);
				receipts.add(receipt);
			}
		}

		return receipts;
	}

	/**
	 * Makes a payment.
	 * 
	 * @param receipt
	 *            The receipt (must not be null).
	 * @param offer
	 *            The offered money.
	 * @throws If
	 *             the offer does not correspond to the true payment.
	 */
	public void makePayment(FineReceipt receipt, int offer)
			throws InexactPaymentException {
		if (receipt == null) {
			throw new IllegalArgumentException("Receipt is null.");
		}

		for (Payment payment : payments) {
			Usage usage = payment.getUsage();

			if (!payment.isMade()
					&& usage.getEmployee().equals(receipt.getEmployee())
					&& usage.getItem().equals(receipt.getItem())) {

				// Simply attempt to pay now.
				payment.make(offer);
				return;
			}
		}
	}

	private boolean isItemAvailable(Item item) {
		for (Payment payment : payments) {
			Usage usage = payment.getUsage();

			// Note how there is always at most one pending usage for the item.
			if (usage.getItem().equals(item) && !usage.isReturned()) {
				return false;
			}
		}

		return true;
	}

	private void notifyFirstReservation(Item item) {
		Reservation reservation = null;

		for (Reservation registeredReservation : reservations) {
			if (registeredReservation.getItem().equals(item)) {
				reservation = registeredReservation;
			}
		}

		if (reservation == null) {
			// There were no reservations for this item.
			return;
		}

		// Announce the employee.
		announce(reservation.getEmployee(), reservation.getItem());

		// Done with this reservation, excellent!
		reservations.remove(reservation);
	}

	private void announce(Employee employee, Item item) {
		System.out.printf("Dear %s, item %s is now available!",
				employee.getName(), item.getName());
		System.out.println();
	}

	private Date addDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
}
