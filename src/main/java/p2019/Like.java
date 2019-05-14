package p2019;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Like {

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS+0000");
	private Date timeStamp_;
	private String commentId_;
	private String userId_;
	
	public Like(String ts, String commentId, String userId) {
		try {
			timeStamp_ = format.parse(ts);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		commentId_ = commentId;
		userId_ = userId;
	}

	public String getComment()
	{
		return commentId_;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId_ == null) ? 0 : userId_.hashCode());
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
		Like other = (Like) obj;
		if (userId_ == null) {
			if (other.userId_ != null)
				return false;
		} else if (!userId_.equals(other.userId_))
			return false;
		return true;
	}
	
	

}
