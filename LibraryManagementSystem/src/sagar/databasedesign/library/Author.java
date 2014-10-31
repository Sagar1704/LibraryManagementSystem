package sagar.databasedesign.library;

import sagar.databasedesign.enums.Role;

public class Author {
	private String name;
	private int type;
	private Role role;

	public Author(String name, int type, Role role) {
		super();
		this.name = name;
		this.type = type;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Author() {
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
