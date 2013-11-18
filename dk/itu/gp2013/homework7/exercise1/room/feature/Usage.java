package dk.itu.gp2013.homework7.exercise1.room.feature;

import java.util.Date;

/**
 * Usage class.<br>
 * A usage is created, by specifying the return contract.<br>
 * A return is registered for the date when the item is returned.<br>
 * 
 * @author Vlad Manea (vlam@itu.dk)
 */
public class Usage {
	private Employee employee;
	private Item item;

	private Date borrowDate;
	private Date returnDate;
	private Date expectedReturnDate;

	/**
	 * Creates this reservation.
	 * 
	 * @param employee
	 *            The employee reserving the item (must not be null).
	 * @param item
	 *            The item reserved by the employee (must not be null).
	 * @param borrowDate
	 *            The date when the item is borrowed (must not be null).
	 * @param expectedReturnDate
	 *            The date before which the item is expected to be returned
	 *            (must not be null).
	 */
	Usage(Employee employee, Item item, Date borrowDate, Date expectedReturnDate) {
		if (employee == null) {
			throw new IllegalArgumentException("Employee is null.");
		}

		if (item == null) {
			throw new IllegalArgumentException("Item is null.");
		}

		if (borrowDate == null) {
			throw new IllegalArgumentException("Borrow date is null.");
		}

		if (expectedReturnDate == null) {
			throw new IllegalArgumentException("Expected return date is null.");
		}

		this.employee = employee;
		this.item = item;
		this.borrowDate = borrowDate;
		this.expectedReturnDate = expectedReturnDate;
	}

	/**
	 * Gets the employee.
	 * 
	 * @return The employee.
	 */
	Employee getEmployee() {
		return employee;
	}

	/**
	 * Gets the item.
	 * 
	 * @return The item.
	 */
	Item getItem() {
		return item;
	}

	/**
	 * Gets the borrow date.
	 * 
	 * @return The borrow date.
	 */
	Date getBorrowDate() {
		return borrowDate;
	}

	/**
	 * Gets the expected return date.
	 * 
	 * @return The expected return date.
	 */
	Date getExpectedReturnDate() {
		return expectedReturnDate;
	}

	/**
	 * Sets the return date.
	 * 
	 * @param returnDate
	 *            The return date (must not be null).
	 */
	void setReturnDate(Date returnDate) {
		if (returnDate == null) {
			throw new IllegalArgumentException("Return date is null.");
		}

		this.returnDate = returnDate;
	}

	/**
	 * Gets the return date.
	 * 
	 * @return The return date (nullable).
	 */
	Date getReturnDate() {
		return this.returnDate;
	}

	/**
	 * Decides if the item is returned.
	 * 
	 * @return The returned status.
	 */
	boolean isReturned() {
		return returnDate != null;
	}

	/**
	 * Decides if the return is overdue.<br>
	 * If the item was not returned yet, but the date is overdue, the return is
	 * considered overdue.<br>
	 * If the item was not returned yet, and the date is not overdue, the return
	 * is not overdue yet.
	 * 
	 * @param forDate
	 *            The date for which the fine is computed (must not be null).
	 * 
	 * @return The return overdue status.
	 */
	boolean isReturnOverdue(Date forDate) {
		if (forDate == null) {
			throw new IllegalArgumentException("For date is null.");
		}

		if (this.isReturned() && expectedReturnDate.compareTo(returnDate) < 0) {
			// Return date is past the expected return date.
			return true;
		}

		if (!this.isReturned() && expectedReturnDate.compareTo(forDate) < 0) {
			// Item was not returned, but it is past the expected return date.
			return true;
		}

		return false;
	}
}
