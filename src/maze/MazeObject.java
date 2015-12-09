/**
 * MazeObject.java
 * Author: Corbin Staaben
 * Description: MazeObject is the interface used by all objects in the maze that the user can interact with, or
 * contains information about the maze or MazePlayer object
 */
package maze;

import java.io.Serializable;

public interface MazeObject<T> extends Serializable {

	/**
	 * Provides all objects in the maze the ability to represent themselves in a String
	 * @return - the String representation of the object
	 */
	public String display();
}
