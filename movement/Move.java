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
		if(move=='w' || move=='W')
		{
			this.r--;
		}
		else if(move=='s' || move=='S')
		{
			this.r++;
		}
		else if(move=='a' || move=='A')
		{
			this.c--;
		}
		else if(move=='d' || move=='D')
		{
			this.c++;
		}
	}
}
