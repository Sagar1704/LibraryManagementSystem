package sagar.databasedesign.enums;

public enum SearchBy {
	ISBN("ISBN"), BORROWER("BORROWER");

	private String searchBy;

	public String getSearchBy() {
		return searchBy;
	}

	private SearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

}
