package dk.itu.gp2013.homework7.exercise1.room.feature;

import java.util.Date;

/**
 * Payment class.<br>
 * Fine and payment tracking for a usage.
 * 
 * @author Vlad Manea (vlam@itu.dk).
 */
class Payment {
	private Usage usage;
	private int fine;
	private boolean made;

	/**
	 * Creates a payment.
	 * 
	 * @param usage
	 *            The usage (must not be null).
	 * @param fine
	 *            The fine (must not be negative).
	 */
	Payment(Usage usage, int fine) {
		if (usage == null) {
			throw new IllegalArgumentException("Usage is null.");
		}
		
		if (fine < 0) {
			throw new IllegalArgumentException("Fine is negative.");
		}

		this.usage = usage;
		this.fine = fine;
	}

	/**
	 * Gets the fine. If the payment was not performed and the usage indicates
	 * an overdue payment, the fine is reported.<br>
	 * Otherwise, no fine is reported.
	 * 
	 * @param forDate
	 *            The date for which the fine is calculated (must not be null).
	 * 
	 * @return The fine, or <code>0</code> if there is no fine to pay.
	 */
	int getFine(Date forDate) {
		if (forDate == null) {
			throw new IllegalArgumentException("For date is null.");
		}

		if (this.usage.isReturnOverdue(forDate) && !this.made) {
			return this.fine;
		}

		// The fine is either paid or the return is not overdue.
		return 0;
	}

	/**
	 * Gets the usage.
	 * 
	 * @return The usage.
	 */
	Usage getUsage() {
		return this.usage;
	}

	/**
	 * Decides if the payment is made.
	 * 
	 * @return The payment made status.
	 */
	boolean isMade() {
		return this.made;
	}

	/**
	 * Makes the payment.
	 * 
	 * @param offer
	 *            The money offer.
	 * @throws InexactPaymentException
	 *             If the offer does not match the fine.
	 */
	void make(int offer) throws InexactPaymentException {

		// This is against all greedy clerks in the world!
		if (this.fine != offer) {
			throw new InexactPaymentException(this.fine, offer);
		}

		made = true;
	}
}
