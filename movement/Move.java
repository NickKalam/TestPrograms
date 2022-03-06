package movement;

public class Move
{
	private int r,c;
	private char Pl='T';
	public Move ()
	{
		this.r=1;
		this.c=1;
	}

	public int getR()
	{
		return this.r;
	}

	public int getC()
	{
		return this.c;
	}

	public  char getPl()
	{
		return this.Pl;
	}
	public void mov(char move)
	{
		if(move=='W')
		{
			this.r--;
		}
		else if(move=='S')
		{
			this.r++;
		}
		else if(move=='A')
		{
			this.c--;
		}
		else if(move=='D')
		{
			this.c++;
		}
	}
}
