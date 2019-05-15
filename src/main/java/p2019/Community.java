package p2019;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import graph.User;

public class Community {

	private int size;
	private Set<User> users = new HashSet<>();
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
		c.getUsers().stream().forEach(u->users.add(u));
		size = users.size();
	}

	
}
