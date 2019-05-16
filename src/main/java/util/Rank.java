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

	private File file;
	private Writer writer;
	
	private Comparator<Comment> comparator = new Comparator<Comment>()
	{
		@Override
        public int compare(Comment comm1, Comment comm2)
        {
			if (comm1.getRange()==comm2.getRange())
			{
				return comm1.getContent().compareTo(comm2.getContent());
			}
			else
			{
				if (comm1.getRange()>comm2.getRange())
					return 1;
				else return -1;
			}
        }
	};
	
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
			comments.remove(0);
		}
		comments.add(c);
		comments.sort(comparator);
		output(c.getLastUpdateTimeStamp());
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
