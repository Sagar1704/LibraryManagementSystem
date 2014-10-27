package sagar.databasedesign.database;

public final class DatabaseManager {
	private static DatabaseManager databaseManager;
	
	private DatabaseManager() {
		databaseManager = new DatabaseManager();
	}
	
	public static synchronized DatabaseManager getInstane() {
		if(databaseManager == null)
			databaseManager = new DatabaseManager();
		return databaseManager;
	}
	
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
