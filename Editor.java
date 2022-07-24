package editor;

import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.JColorChooser;

import java.time.LocalTime;
import java.time.ZoneId;

import java.io.FileWriter;  
import java.io.IOException;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;


public class Editor 
{
	
	
	private static JFrame frame=new JFrame();
	private static String fontFamily=Font.SERIF;
	private static int fontSize=11;
	private static int fontType=Font.PLAIN;
	private static Font mainFont=new Font(fontFamily,fontType,fontSize);
	
    private static JFileChooser chooseFile;
	private static String programNameEdit;
	private static boolean editting=false;
	private static FileWriter program=null;
	private static TextArea text=new TextArea("",50,125);
	private static JTextField title=new JTextField();
	
	public static void main(String [] args)
	{
		
		JLabel time=new JLabel();
		
		 chooseFile=new JFileChooser();
		 String [] fontChoices= {"PLAIN","BOLD","ITALIC"};
		 String [] fontFamilies= {"Serif","Monospaced","SansSerif","Dialog","DialogInput","Ink Free"};
		 Integer [] sizeChoices= {11,12,13,14,15,16,17,18,19,20,21};
		
		title.setEditable(false);
		title.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
		title.setVisible(false);
		text.setFont(mainFont);//initially it's plain 
		text.setColumns(125);
		text.setRows(50);
		text.setForeground(Color.WHITE);
		text.addKeyListener(new KeyAdapter() 
		{
			
			public void keyPressed(KeyEvent event)
			{
				if(event.isControlDown() && event.getKeyCode()==KeyEvent.VK_S)
				{
					if(text.getText().equals(""))//if the text area is empty
					{
						JOptionPane.showMessageDialog(frame,"There is no text to be saved","Warning",JOptionPane.WARNING_MESSAGE);
					}
					else 
					{
						saveFile();
					}
				}
			}
		});
		
		//The JMenuItems  and JComboBoxes for the the JMenBar of the frame
		JMenuItem saveButton=new JMenuItem("Save ");
		JMenuItem editButton=new JMenuItem("Open");
		JMenuItem exitButton=new JMenuItem("Exit");
		JMenuItem colorButton=new JMenuItem("Text Color");
		JMenuItem backgroundButton=new JMenuItem("Background Color");
		JMenuItem textBackground=new JMenuItem("Text area background");
		
		JComboBox<String> family =new JComboBox<String>(fontFamilies);
		family.setBackground(Color.LIGHT_GRAY);
		
		JComboBox<String> style=new JComboBox<String>(fontChoices);
		style.setBackground(Color.LIGHT_GRAY);
		
		JComboBox<Integer> sizes=new JComboBox<Integer>(sizeChoices);
		sizes.setBackground(Color.LIGHT_GRAY);
		sizes.setPreferredSize(new Dimension(5,5));
		
		JPanel panel=new JPanel();
		

		Box root=Box.createVerticalBox();
		
		JMenu menu=new JMenu("File");
		
		
		menu.add(saveButton);
		menu.add(editButton);
		
		
		menu.add(exitButton);
		
		saveButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						if(text.getText().equals(""))//if the text area is empty
						{
							JOptionPane.showMessageDialog(frame,"There is no text to be saved","Warning",JOptionPane.WARNING_MESSAGE);
						}
						else 
						{
							saveFile();
						}
					}
				});
		
		editButton.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent event)
			{
				if(text.getText().equals(""))//if the text area is empty
				{
					openFile();//open a file for editing
				}
				else if(JOptionPane.showConfirmDialog(frame, "Are you sure you want to discard the written text ?")==JOptionPane.YES_OPTION)
				{
					openFile();
				}
			
			}
				}
		);
		
		exitButton.addActionListener(new ActionListener()
					{
			
						public void actionPerformed(ActionEvent e)
						{
							if(JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit ?")==JOptionPane.YES_OPTION)
							{
								System.exit(0);
							}
						}

					}
		);
		
		style.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				if(style.getSelectedItem().equals("PLAIN"))
				{
					fontType=Font.PLAIN;
				}
				else if(style.getSelectedItem().equals("BOLD"))
				{
					fontType=Font.BOLD;
				}
				else if(style.getSelectedItem().equals("ITALIC"))
				{
					fontType=Font.ITALIC;
				}
				
				text.setFont(new Font(fontFamily,fontType,fontSize));
				
			}
		});
		
		sizes.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				 if((int)sizes.getSelectedItem()>12)
				 {
					 text.setColumns(110);
					 text.setRows(43);
				 }
				 if((int)sizes.getSelectedItem()>13)
				 {
					 text.setColumns(105);
					 text.setRows(38);
				 }
				 else if((int)sizes.getSelectedItem()>14)
				 {
					 text.setColumns(100);
					 text.setRows(35);
				 }
				 else if((int)sizes.getSelectedItem()>15)
				 {
					 text.setColumns(90);
					 text.setRows(30);
				 }
				 else if((int)sizes.getSelectedItem()>16)
				 {
					 text.setColumns(20);
					 text.setRows(2);
				 }
				 else if((int)sizes.getSelectedItem()==21)
				 {
					 text.setColumns(20);
					 text.setRows(0);
				 }
				 else
				 {
					 text.setColumns(125);
					 text.setRows(50);
				 }
				fontSize=(int)sizes.getSelectedItem();
				
				text.setFont(new Font(fontFamily,fontType,fontSize));
				
				
				
			}
		});
		
		
		family.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					fontFamily=(String) family.getSelectedItem();
					text.setFont(new Font(fontFamily,fontType,fontSize));
				}
		});
		
		colorButton.setBackground(Color.GRAY);
		colorButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Color color=JColorChooser.showDialog(frame,"Choose a color ",text.getForeground());
						if(color==null)
						{
							color=text.getForeground();
						}
						
						text.setForeground(color);
					}
			
				});
		
		backgroundButton.setBackground(Color.GRAY);
		backgroundButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color color=JColorChooser.showDialog(frame,"Choose a color ",panel.getBackground());
				if(color==null)
				{
					color=panel.getBackground();
				}
				
				//text.setBackground(color);
				panel.setBackground(color);
			}
	
		});
		
		textBackground.setBackground(Color.GRAY);
		textBackground.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color color=JColorChooser.showDialog(frame,"Choose a color ",text.getBackground());
				if(color==null)
				{
					color=text.getBackground();
				}
				
				text.setBackground(color);
			}
	
		});
		
		text.setBackground(Color.DARK_GRAY);
		root.add(title);
		root.add(text);
		panel.add(root);
		panel.setBackground(Color.DARK_GRAY);
		JMenuBar menuBar=new JMenuBar();
		menuBar.setSize(100, 10);
		menuBar.add(time);
		menuBar.add(menu);
		menuBar.add(new JLabel("|		   Font family : "));
		menuBar.add(family);
		menuBar.add(new JLabel("|     Style : "));
		menuBar.add(style);
		menuBar.add(new JLabel("|    Text size :"));
		menuBar.add(sizes);
		menuBar.add(new JLabel("|   "));
		menuBar.add(colorButton);
		menuBar.add(new JLabel("|   "));
		menuBar.add(backgroundButton);
		menuBar.add(new JLabel("|   "));
		menuBar.add(textBackground);
		menuBar.setBackground(Color.LIGHT_GRAY);
		frame.add(panel);
		frame.setJMenuBar(menuBar);
		frame.setTitle("EDITOR");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 720);
		frame.setVisible(true);
		
		//this loop will update the timer approximately every second 
		while(true)
		{
			time.setText( setTime()+" |" );
			
			try {
				Thread.sleep(800);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	
	/*
	 * Save's a file,either by creating a new one or by saving a previously opened one 
	 */
	private static void saveFile()
	{
		if(editting==true)
		{
			try {
				program=new FileWriter(programNameEdit);
				program.write(text.getText());
				program.close();
				
				text.setText("");
				JOptionPane.showMessageDialog(frame,"File successfully editted .","SAVED",JOptionPane.INFORMATION_MESSAGE);
				title.setText("");
				editting=false;
				title.setVisible(editting);
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(frame, "Contents weren't saved .","ERROR",JOptionPane.ERROR_MESSAGE);
			}

		}
		else
		{
			try {

				
				chooseFile.showSaveDialog(frame);
				program=new FileWriter(chooseFile.getSelectedFile().getAbsoluteFile());
				program.write(text.getText());
				program.close();
				JOptionPane.showMessageDialog(frame,"File successfully saved .","SAVED",JOptionPane.INFORMATION_MESSAGE);
				text.setText("");
			}
			catch (IOException | NullPointerException e)
			{
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(frame, "File didn't open","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			
			
		}
	}
	
	/*
	 * Open's a  file for editing
	 */
	private static void openFile()
	{

		try {
			
			chooseFile.showOpenDialog(frame);
			Scanner file=new Scanner(chooseFile.getSelectedFile());

			programNameEdit=chooseFile.getSelectedFile().getAbsolutePath();
			String program="";
			
			while(file.hasNext())
			{
				program+=file.nextLine()+"\n";
			}
			
			text.setText(program);
			
			title.setText(chooseFile.getSelectedFile().getName());
			editting=true;
			title.setVisible(editting);
			file.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(frame, "File didn't open","ERROR",JOptionPane.ERROR_MESSAGE);
		}
		
		catch (NullPointerException e)
		{
			JOptionPane.showMessageDialog(frame, "No file was picked .","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/*
	 * Sets the time to the current time
	 */
	private static String setTime()
	{
		LocalTime current=LocalTime.now( ZoneId.systemDefault() );
		
		return String.format("%02d:%02d:%02d %s", (current.getHour()==12?0:current.getHour()%12),current.getMinute(),current.getSecond(),(( current.getHour()<12 && current.getHour()>0)?" AM":" PM"));
	}
}