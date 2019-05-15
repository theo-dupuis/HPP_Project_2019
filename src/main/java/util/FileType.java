package util;

public enum FileType {
	Friendship("friendship"), Comment("comment"), Like("like");

	private String name = "";

	FileType(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
