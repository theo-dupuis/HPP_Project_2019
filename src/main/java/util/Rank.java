package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import p2019.Comment;
import p2019.MyApp;

public class Rank {

	
	private Comparator<Comment> comparator = new Comparator<Comment>()
	{
		@Override
        public int compare(Comment comm1, Comment comm2)
        {
			if (comm1.getRange()==comm2.getRange())
			{
				return comm2.getContent().compareTo(comm1.getContent());
			}
			else
			{
				if (comm1.getRange()>comm2.getRange())
					return 1;
				else return -1;
			}
        }
	};
	
	private Comparator<Comment> comparatorByTime = new Comparator<Comment>()
	{
		@Override
        public int compare(Comment comm1, Comment comm2)
        {
			return comm1.getCreationTimeStamp().compareTo(comm2.getCreationTimeStamp());
        }
	};
	
	private File file;
	private Writer writer;
	private long olderTimeStamp;
	
	private List<Comment> comments;
	public Rank() {
		comments = new ArrayList<>(MyApp.k);
		file  = new File("Output.txt");
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Error while creating output file");
		}
		
		writer = new Writer(file);
	}

	public void changeRange(Comment c)
	{
		if (comments.size()==MyApp.k)
		{
			if (c.getRange()<comments.get(0).getRange())
				return;
			if (comments.contains(c))
			{
				int i=comments.indexOf(c);
				if (i == comments.size()-1 || comparator.compare(c, comments.get(i+1))<0)
					return;
				else
					comments.remove(c);
			}
			else
				comments.remove(0);
		}
		else
		{
			if (comments.contains(c))
				return;
		}
		comments.add(c);
		comments.sort(comparator);
		output(c.getLastUpdateTimeStamp());
	}
	
	public void checkOutdatedComment(Date timeStamp)
	{
		if (timeStamp.getTime()>olderTimeStamp+MyApp.duration*1000)
		{
			comments.sort(comparatorByTime.thenComparing(comparator));
			int i=0;
			List<Comment> drops=new ArrayList<>();
			while (comments.size()>i && comments.get(i).getCreationTimeStamp().getTime()<=timeStamp.getTime())
			{
				while(comments.size()>i+1 && comparatorByTime.compare(comments.get(i), comments.get(i+1))==0)
				{
					comments.remove(i);
				}
				drops.add(comments.get(i));
				i++;
			}
			comments.sort(comparator);
			for(Comment c: drops)
			{
				dropComment(c);
			}
		}
	}
	
	public void dropComment(Comment c)
	{
		if (comments.contains(c))
		{
			comments.remove(c);
			
			output(new Date(c.getCreationTimeStamp().getTime()+MyApp.duration*1000));
		}
	}
	
	public void output(Date timeStamp)
	{
		StringBuilder str = new StringBuilder();
		str.append(MyApp.dateFormat.format(timeStamp));
		for(int i=comments.size()-1;i>=0;i--)
		{
			str.append(","+comments.get(i).getContent());
		}
		for(int i= comments.size();i<MyApp.k;i++)
		{
			str.append(",-");
		}
		
		writer.write(str.toString());
	}
}
