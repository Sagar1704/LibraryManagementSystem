package sagar.databasedesign.enums;

public enum Role {
	AUTHOR("Author"), EDITOR("Editor"), ILLUSTRATOR("Illustrator");
	
	private String role;

	public String getRole() {
		return role;
	}

	private Role(String role) {
		this.role = role;
	}
	
}
