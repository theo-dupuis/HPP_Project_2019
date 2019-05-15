package p2019;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class Comment {
	
	private String id;
	
	private Date creationTimeStamp;
	private Date lastUpdateTimeStamp;
	
	private int range;
	private String content;
	private Map<User, Community> communities;
	
	public Comment(String ts, String commentId, String comment)
	{
		try {
			creationTimeStamp = MyApp.dateFormat.parse(ts);
			lastUpdateTimeStamp = creationTimeStamp;
		} catch (ParseException e) {
			System.out.println("Error while formatting timestamp");
		}
		
		id = commentId;
		content = comment;
		range =0;
		communities = new HashMap<>();
	}
	
	public int getRange() { return range;}
	
	public void updateTimeStamp(String ts) {
		try {
			lastUpdateTimeStamp = MyApp.dateFormat.parse(ts);
		} catch (ParseException e) {
			System.out.println("Error while formatting timestamp");
		}
	}

	public Date getLastUpdateTimeStamp() {
		return lastUpdateTimeStamp;
	}

	public void updateTimeStamp(Date lastUpdateTimeStamp) {
		this.lastUpdateTimeStamp = lastUpdateTimeStamp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}
	
	public Map<User, Community> getCommunities() {
		return communities;
	}

	public Date getCreationTimeStamp() {
		return creationTimeStamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public void communityMerge(User user1, User user2)
	{
		Community comm = communities.get(user1);
		comm.merge(communities.get(user2));
		communities.put(user2, comm);
		sizeHasChanged(comm);
	}
	
	public void sizeHasChanged(Community c)
	{
		if (c.getSize() > range)
		{
			range = c.getSize();
		}
	}
}
