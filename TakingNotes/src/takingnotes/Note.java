package takingnotes;

public class Note {
	private String title;
	private final String password;
	private String body;
	
	Note()
	{
		this.title = "";
		this.body = "";
		this.password = "";
	}
	Note(String title , String body)
	{
		this.title = title;
		this.password = "";
		this.body = body;
	}
	Note(String title , String password , String body)
	{
		this.title = title;
		this.password = password;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getTitle()
	{
		return title;
	}
	public String getPassword()
	{
		return password;
	}
	public String getBody()
	{
		return body;
	}
	public boolean isEmpty()
        {
            if(title.isEmpty() && body.isEmpty())
                return true;
            return false;
        }
}
