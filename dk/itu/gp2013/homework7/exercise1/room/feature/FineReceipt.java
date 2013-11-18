package dk.itu.gp2013.homework7.exercise1.room.feature;

import java.util.Date;

/**
 * Fine receipt class.
 * 
 * @author Vlad Manea (vlam@itu.dk).
 */
public class FineReceipt {
	private Item item;
	private Employee employee;
	private Date borrowDate;
	private Date expectedReturnDate;
	private Date returnDate;
	private int fine;

	/**
	 * Creates this fine receipt.
	 * 
	 * @param item
	 *            The item for which the fine was made (must not be null).
	 * @param borrowDate
	 *            The date when the item was borrowed (must not be null).
	 * @param expectedReturnDate
	 *            The expected return date (must not be null).
	 * @param returnDate
	 *            The return date (can be null).
	 * @param fine
	 *            The fine to be paid (must not be negative).
	 */
	public FineReceipt(Item item, Employee employee, Date borrowDate,
			Date expectedReturnDate, Date returnDate, int fine) {
		if (item == null) {
			throw new IllegalArgumentException("Item is null.");
		}

		if (employee == null) {
			throw new IllegalArgumentException("Employee is null.");
		}

		if (borrowDate == null) {
			throw new IllegalArgumentException("Borrow date is null.");
		}

		if (expectedReturnDate == null) {
			throw new IllegalArgumentException("Expected return date is null.");
		}
		
		if (fine < 0) {
			throw new IllegalArgumentException("Fine is negative.");
		}

		this.item = item;
		this.employee = employee;
		this.borrowDate = borrowDate;
		this.expectedReturnDate = expectedReturnDate;
		this.returnDate = returnDate;
		this.fine = fine;
	}

	/**
	 * Gets the item.
	 * 
	 * @return The item.
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Gets the employee.
	 * 
	 * @return The employee.
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * Gets the borrow date.
	 * 
	 * @return The borrow date.
	 */
	public Date getBorrowDate() {
		return borrowDate;
	}

	/**
	 * Gets the expected return date.
	 * 
	 * @return The expected return date.
	 */
	public Date getExpectedReturnDate() {
		return expectedReturnDate;
	}

	/**
	 * Gets the return date.
	 * 
	 * @return The return date.
	 */
	public Date getReturnDate() {
		return returnDate;
	}

	/**
	 * Gets the fine.
	 * 
	 * @return The fine.
	 */
	public int getFine() {
		return fine;
	}
}