package dk.itu.gp2013.homework7.exercise1.room.feature;
/**
 * Employee class (immutable state).
 * 
 * @author Vlad Manea (vlam@itu.dk).
 */
public class Employee {
	private String name;

	/**
	 * Creates this employee.
	 * 
	 * @param name
	 *            The name.
	 */
	public Employee(String name) {
		this.name = name;
	}

	/**
	 * Gets the name.
	 * 
	 * @return The name.
	 */
	public String getName() {
		return this.name;
	}
}