import java.awt.Color;

/**
 * This class is the blueprint for a Player.
 * 
 * @author Cindy Li
 * @since Wednesday, July 12, 2017
 *
 */
public class Player {

	// fields
	private Color color;
	private String name;
	private int id;
	private boolean checked;
	
	
	// constructor
	public Player(Color c, String n, int i) {
		
		color = c;
		name = n;
		id = i;
	}
	
	
	/**
	 * Returns the player's color.
	 * @return color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Returns the player's id.
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	
	/**
	 * Returns the player's name.
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * Returns if the player is in a checked position or not.
	 * @return checked
	 */
	public boolean getChecked() {
		return checked;
	}
	
	
	/**
	 * Sets whether the player is in a checked position or not.
	 * @param b  True for yes, false for no
	 */
	public void setChecked(boolean b) {
		checked = b;
	}
}
