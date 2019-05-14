package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
	private Set<User> friendship;
	
	public Graph() {
		friendship = new HashSet<>();
	}
	
	public void addUser(String id) {
		friendship.add(new User(id));
	}
	
	public void addFriendship(String id1, String id2) {
		User a = new User(id1);
		User b = new User(id2);
		
		addUser(id1);
		addUser(id2);
		a.addFriend(b);
		b.addFriend(a);
	}


	
}
