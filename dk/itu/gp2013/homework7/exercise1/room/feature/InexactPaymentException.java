package dk.itu.gp2013.homework7.exercise1.room.feature;
/**
 * Exception class for inexact payment.
 * 
 * @author Vlad Manea (vlam@itu.dk)
 */
public class InexactPaymentException extends Exception {
	private static final long serialVersionUID = 1L;
	private int expectedPayment;
	private int actualPayment;

	/**
	 * Creates an inexact payment exception.
	 * 
	 * @param expectedPayment
	 *            The expected payment.
	 * @param actualPayment
	 *            The actual payment.
	 */
	public InexactPaymentException(int expectedPayment, int actualPayment) {
		super();

		this.expectedPayment = expectedPayment;
		this.actualPayment = actualPayment;
	}

	/**
	 * Gets the expected payment.
	 * 
	 * @return The expected payment.
	 */
	public int getExpectedPayment() {
		return this.expectedPayment;
	}

	/**
	 * Gets the actual payment.
	 * 
	 * @return The actual payment.
	 */
	public int getActualPayment() {
		return this.actualPayment;
	}
}