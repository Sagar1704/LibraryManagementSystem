package sagar.databasedesign.enums;

public enum Filter {
	ALL("All"), ISBN("ISBN"), TITLE("Title"), AUTHOR("Author");

	private String filter;

	public String getFilter() {
		return filter;
	}

	private Filter(String filter) {
		this.filter = filter;
	}

}
