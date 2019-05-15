package util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import p2019.Comment;
import p2019.MyApp;

public class Rank {

	private Comparator<Comment> comparator = new Comparator<>()
	{
		@Override
        public int compare(Comment comm1, Comment comm2)
        {
			if (comm1.getRange()==comm2.getRange())
			{
				return comm1.getContent()> comm2.getContent();
			}
			else
				return comm1.getRange()>comm2.getRange();
        }
	}
	
	private List<Comment> comments;
	public Rank() {
		comments = new ArrayList<>(MyApp.k);
	}

	public void changeRange(Comment c)
	{
		if (comments.size()==MyApp.k)
		{
			if (c.getRange()<comments.get(0).getRange())
				return;
			comments.remove(0);
		}
		comments.add(c);
		comments.sort(comparator);
		output(c.getLastUpdateTimeStamp());
	}
	
	public void output(Date timeStamp)
	{
		StringBuilder str = new StringBuilder();
		str.append(timeStamp.toString());
		for(int i=comments.size()-1;i>=0;i--)
		{
			str.append(","+comments.get(i).getContent());
		}
		for(int i= comments.size();i<MyApp.k;i++)
		{
			str.append(",-");
		}
	}
}
