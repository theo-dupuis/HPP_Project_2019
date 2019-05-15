package p2019;

import java.util.HashSet;
import java.util.Set;

public class Community {

	private int size;
	private Set<User> users = new HashSet<>();
	
	public Community(User user) {
		users.add(user);
	}
	public Community() {
		
	}
	public int getSize() {
		return size;
	}
	
	public Set<User> getUsers() {
		return users;
	}
	
	public void addUser(User u)
	{
		users.add(u);
		size+=1;
	}
	
	public void merge(Community c)
	{
		users.addAll(c.getUsers());
		size = users.size();
	}
}
