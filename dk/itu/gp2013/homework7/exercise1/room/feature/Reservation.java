package dk.itu.gp2013.homework7.exercise1.room.feature;
/**
 * Reservation class (immutable state). Used to retain employees who reserved
 * particular items.
 * 
 * @author Vlad Manea (vlam@itu.dk)
 */
public class Reservation {
	private Employee employee;
	private Item item;

	/**
	 * Creates this reservation.
	 * 
	 * @param employee
	 *            The employee reserving the item (must not be null).
	 * @param item
	 *            The item reserved by the employee (must not be null).
	 */
	Reservation(Employee employee, Item item) {
		if (employee == null) {
			throw new IllegalArgumentException("Employee is null.");
		}
		
		if (item == null) {
			throw new IllegalArgumentException("Item is null.");
		}
		
		this.employee = employee;
		this.item = item;
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
}
