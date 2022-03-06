import javax.swing.JOptionPane;
import movement.Move;
class movementTest
{

    public static void main(String[] args)
    {
        String input;
        int i,j,time;
        Move move=new Move();
        for(time=0;time<50;time++)
        {
            for (i=0;i<=10;i++)
            {
                if(i==0 || i==10)
                {
                    System.out.print(" ");
                    for(j=1;j<9;j++)
                    {
                        System.out.print("*");
                    }
                    System.out.println(" ");
                }
                else
                {
                    System.out.print("*");
                    for(j=1;j<9;j++)
                    {
                        if(i==move.getR() && j==move.getC())
                        {
                            System.out.print(move.getPl());
                        }
                        else
                        {
                            System.out.print(" ");
                        }
                    }
                    System.out.println("*");
                }
            }
            do
            {
                input=JOptionPane.showInputDialog( "Make your move \nW : go up \nS : go down \nD : go right \nA : go left ");
            }while(input.charAt(0)!='W' && input.charAt(0)!='S' && input.charAt(0)!='D' && input.charAt(0)!='A');
            move.mov(input.charAt(0));
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
        System.exit(0);
    }
}
