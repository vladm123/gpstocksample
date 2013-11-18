package dk.itu.gp2013.homework7.exercise1.room.feature;
/**
 * Exception class for item unavailable.
 * 
 * @author Vlad Manea (vlam@itu.dk)
 */
public class ItemUnavailableException extends Exception {
	private static final long serialVersionUID = 1L;
	private Item item;

	/**
	 * Creates an item unavailable exception.
	 * 
	 * @param item
	 *            The unavailable item.
	 */
	public ItemUnavailableException(Item item) {
		super();
		
		if (item == null) {
			throw new IllegalArgumentException("Item is null.");
		}

		this.item = item;
	}

	/**
	 * Gets the unavailable item.
	 * 
	 * @return The unavailable item.
	 */
	public Item getItem() {
		return this.item;
	}
}