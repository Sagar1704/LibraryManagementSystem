package sagar.databasedesign.exceptions;

/**
 * Throw exception if the file is empty
 * 
 * @author Sagar
 * 
 */
public class FileEmptyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -403571992724920569L;

	public FileEmptyException(String message) {
		super(message);
	}

}
