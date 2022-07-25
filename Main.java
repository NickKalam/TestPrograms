package editor;

public class Main {
	public static void main(String [] args)
	{
		Editor editor=new Editor();
		
		while(true)
		{
			editor.Time().setText( editor.setTime()+" |" );
			
			try {
				Thread.sleep(800);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
}
