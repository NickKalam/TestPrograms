package movement;

public class Area{
	private int r, c;
	private char Pl = 'T';
	private static final int ROWS = 15;
	private static final int COLUMNS = 9;

	public Area (){
		r = 0;
		c = 0;
	}

	public void mov(char move, int distance){
		if(move=='W' || move=='w'){
			r -= distance;
		}
		else if(move=='S' || move=='s'){
			r += distance;
		}
		else if(move=='A' || move=='a'){
			c -= distance;
		}
		else if(move=='D' || move=='d'){
			c += distance;
		}
		if(r < 0){
			r = 0;
		}
		else if(r >= ROWS){
			r = ROWS - 1;
		}
		if(c < 0){
			c = 0;
		}
		else if(c >= COLUMNS){
			c = COLUMNS - 1;
		}
	}
	public void printArea(){
		System.out.print("\033[H\033[2J");
        System.out.flush();
		printBorder();
		System.out.print('|');
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLUMNS; j++){
				System.out.print(' ');
				if(r == i && c == j){
					System.out.print(Pl);
				}
				else{
					System.out.print(' ');
				}
				System.out.print(" |");
			}System.out.println();
			if(i < (ROWS-1)){
				printCellBorder();
				System.out.print('|');
			}
		}
		printBorder();
	}
	private void printBorder(){
		for(int i = 0; i < (4*COLUMNS + 1); i++){
			System.out.print('*');
		}System.out.println();
	}
	private void printCellBorder(){
		for(int i = 0; i < (4*COLUMNS + 1); i++){
			System.out.print('-');
		}System.out.println();
	}
}


/*

*********************************
| T |   |   |   |   |   |   |   |




*/