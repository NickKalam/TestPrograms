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
	public void mov(char move,int distance)
	{
		if(move=='W' || move=='w')
		{
			this.r-=distance;
		}
		else if(move=='S' || move=='s')
		{
			this.r+=distance;
		}
		else if(move=='A' || move=='a')
		{
			this.c-=distance;
		}
		else if(move=='D' || move=='d')
		{
			this.c+distance;
		}
	}
}
