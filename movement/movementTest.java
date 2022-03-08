package movement;
import javax.swing.JOptionPane;

class movementTest
{
    public static void main(String[] args){
        String input;
        Area move = new Area();

        while(true){
            move.printArea();
            input = "";// to avoid "The local variable input may not have been initialized"
            try{
                do{
                    input = JOptionPane.showInputDialog( "Make your move \nW : go up \nS : go down \nD : go right \nA : go left");
                }while((!input.equalsIgnoreCase("W")) && (!input.equalsIgnoreCase("S")) && (!input.equalsIgnoreCase("D")) && (!input.equalsIgnoreCase("A")));            
            }
            catch(NullPointerException e){
                System.exit(0);//quit the program
            }

            char direction = input.charAt(0);
            int numOfTiles = 1;// to avoid "The local variable numOfTiles may not have been initialized"
            do{
                input = JOptionPane.showInputDialog( "How far do you want to go (number of tiles ) : ", numOfTiles);
                if(input == null){break;}
                try{
                    numOfTiles = Integer.parseInt(input);
                    move.mov(direction, numOfTiles);
                }
                catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                    numOfTiles = -1;
                }
            }while(numOfTiles <= 0);
        }
    }
}
