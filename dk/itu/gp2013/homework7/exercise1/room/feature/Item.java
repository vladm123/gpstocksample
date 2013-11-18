package dk.itu.gp2013.homework7.exercise1.room.feature;

/**
 * Item class (immutable state).
 * 
 * @author Vlad Manea (vlam@itu.dk).
 */
public class Item {
	private String name;
	private String type;
	private int value;

	/**
	 * Creates this item.
	 * 
	 * @param name
	 *            The name.
	 * @param type
	 *            The type.
	 * @paam value
	 *            The value (must not be negative).
	 */
	public Item(String name, String type, int value) {
		if (value < 0) {
			throw new IllegalArgumentException("Value is negative.");
		}

		this.name = name;
		this.type = type;
		this.value = value;
	}

	/**
	 * Gets the name.
	 * 
	 * @return The name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the type.
	 * 
	 * @return The type.
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Gets the value.
	 * 
	 * @return The value.
	 */
	public int getValue() {
		return this.value;
	}
}
