package movement;
import javax.swing.JOptionPane;

class movementTest
{
    public static void main(String[] args)
    {
        String input;
        char direction;
        int i, j, time,distance;
        Move move=new Move();

        for(time=0;time<50;time++)
        {
            for (i=0;i<=10;i++){
                if(i==0 || i==10){
                    System.out.print(" ");
                    for(j=1;j<9;j++){
                        System.out.print("*");
                    }
                    System.out.println(" ");
                }
                else{
                    System.out.print("*");
                    for(j=1;j<9;j++){
                        if(i==move.getR() && j==move.getC()){
                            System.out.print(move.getPl());
                        }
                        else{
                            System.out.print(" ");
                        }
                    }
                    System.out.println("*");
                }
            }
            input = "";// to avoid "The local variable input may not have been initialized"
            try{
                do{
                    input = JOptionPane.showInputDialog( "Make your move \nW : go up \nS : go down \nD : go right \nA : go left\nE: to exit");
                }while((!input.equalsIgnoreCase("W")) && (!input.equalsIgnoreCase("S")) && (!input.equalsIgnoreCase("D")) && (!input.equalsIgnoreCase("A")) && (!input.equalsIgnoreCase("E")));
            }catch(NullPointerException e){
                System.exit(0);//quit the program
            }
            if(input.equalsIgnoreCase("E")){
                break;
            }
            direction=input.charAt(0);
            do{
                    input =JOptionPane.showInputDialog( "How far do you want to go (number of tiles ) : ");
            }while(Integer.parseInt(input)<=0 || input==null);
            distance=Integer.parseInt(input);
            move.mov(direction,distance);
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
        System.exit(0);
    }
}
