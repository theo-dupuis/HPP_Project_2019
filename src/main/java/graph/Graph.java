package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
	private Map<User, List<User>> friendship;
	
	public Graph() {
		friendship = new HashMap<>();
	}
	
	public void addUser(String id) {
		friendship.putIfAbsent(new User(id), new ArrayList<>());
	}
	
	public void addFriendship(String id1, String id2) {
		User a = new User(id1);
		User b = new User(id2);
		
		addUser(id1);
		addUser(id2);
		friendship.get(a).add(b);
		friendship.get(b).add(a);
	}
	
	public List<User> findBuddies(String id){
		return friendship.get(new User(id));
	}
	
}
