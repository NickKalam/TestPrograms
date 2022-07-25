package editor;

import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
//import 
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.nio.file.FileSystems;
import java.time.LocalTime;
import java.time.ZoneId;
import java.io.FileWriter;  
import java.io.IOException;
import javax.swing.Box;

import javax.swing.JColorChooser;
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


public class Editor extends JFrame implements ActionListener
{
	
	
	private JPanel panel;
	private JLabel time;
	private  String fontFamily=Font.SERIF;
	private  int fontSize=11;
	private  int fontType=Font.PLAIN;
	private  Font mainFont=new Font(fontFamily,fontType,fontSize);
	
    private  JFileChooser chooseFile;
	private  String programNameEdit;
	private  boolean editting=false;
	private  FileWriter program=null;
	private  TextArea text=new TextArea("",50,125);
	private  JTextField title=new JTextField();
	
	JMenuItem saveButton=new JMenuItem("Save ");
	JMenuItem editButton=new JMenuItem("Open");
	JMenuItem exitButton=new JMenuItem("Exit");
	JMenuItem colorButton=new JMenuItem("Text Color");
	JMenuItem backgroundButton=new JMenuItem("Background Color");
	JMenuItem textBackground=new JMenuItem("Text area background");
	
	JComboBox<String> family;
	
	JComboBox<String> style;
	JComboBox<Integer> sizes;
	
	public Editor()
	{
		
		super("Editor");
		time=new JLabel();
		
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
						JOptionPane.showMessageDialog(null,"There is no text to be saved","Warning",JOptionPane.WARNING_MESSAGE);
					}
					else 
					{
						saveFile();
					}
				}
			}
		});
		
		
		saveButton=new JMenuItem("Save ");
		editButton=new JMenuItem("Open");
		exitButton=new JMenuItem("Exit");
		colorButton=new JMenuItem("Text Color");
		backgroundButton=new JMenuItem("Background Color");
		textBackground=new JMenuItem("Text area background");
		
		family =new JComboBox<String>(fontFamilies);
		family.setBackground(Color.LIGHT_GRAY);
		
		style=new JComboBox<String>(fontChoices);
		style.setBackground(Color.LIGHT_GRAY);
		
		sizes=new JComboBox<Integer>(sizeChoices);
		sizes.setBackground(Color.LIGHT_GRAY);
		
		sizes.setPreferredSize(new Dimension(5,5));
		panel=new JPanel();
		

		Box root=Box.createVerticalBox();
		
		JMenu menu=new JMenu("File");
		
		
		menu.add(saveButton);
		menu.add(editButton);
		
		
		menu.add(exitButton);
		
		saveButton.addActionListener(this);
		
		editButton.addActionListener(this);
		
		exitButton.addActionListener(this);
		
		style.addActionListener(this);
		
		sizes.addActionListener(this);
		
		family.addActionListener(this);
		
		colorButton.setBackground(Color.GRAY);
		colorButton.addActionListener(this);
		
		backgroundButton.setBackground(Color.GRAY);
		backgroundButton.addActionListener(this);
		
		textBackground.setBackground(Color.GRAY);
		textBackground.addActionListener(this);
		
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
		
		this.add(panel);
		this.setJMenuBar(menuBar);
		this.setTitle("EDITOR");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1200, 720);
		this.setVisible(true);
		

	}
	
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource()==saveButton)
		{
			if(text.getText().equals(""))//if the text area is empty
			{
				JOptionPane.showMessageDialog(null,"There is no text to be saved","Warning",JOptionPane.WARNING_MESSAGE);
			}
			else 
			{
				saveFile();
			}
		}
		else if(event.getSource()==editButton)
		{
			if(text.getText().equals(""))//if the text area is empty
			{
				openFile();//open a file for editing
			}
			else if(JOptionPane.showConfirmDialog(null, "Are you sure you want to discard the written text ?")==JOptionPane.YES_OPTION)
			{
				openFile();
			}
		}
		else if(event.getSource()==exitButton)
		{
			if(JOptionPane.showConfirmDialog(null, "Are you sure you want to exit ?")==JOptionPane.YES_OPTION)
			{
				System.exit(0);
			}
		}
		else if(event.getSource()==style)
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
		else if(event.getSource()==sizes)
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
		else if(event.getSource()==family)
		{
			fontFamily=(String) family.getSelectedItem();
			text.setFont(new Font(fontFamily,fontType,fontSize));
		}
		else if(event.getSource()==colorButton)
		{
			Color color=JColorChooser.showDialog(null,"Choose a color ",text.getForeground());
			if(color==null)
			{
				color=text.getForeground();
			}
			
			text.setForeground(color);
		}
		else if(event.getSource()==backgroundButton)
		{
			Color color=JColorChooser.showDialog(null,"Choose a color ",panel.getBackground());
			if(color==null)
			{
				color=panel.getBackground();
			}
		
			//text.setBackground(color);
			panel.setBackground(color);
		}
		else if(textBackground==event.getSource())
		{
			Color color=JColorChooser.showDialog(null,"Choose a color ",text.getBackground());
			if(color==null)
			{
				color=text.getBackground();
			}
			
			text.setBackground(color);
		}
	}
	private  void saveFile()
	{
		if(editting==true)
		{
			try {
				program=new FileWriter(programNameEdit);
				program.write(text.getText());
				program.close();
				
				text.setText("");
				JOptionPane.showMessageDialog(null,"File successfully editted .","SAVED",JOptionPane.INFORMATION_MESSAGE);
				title.setText("");
				editting=false;
				title.setVisible(editting);
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Contents weren't saved .","ERROR",JOptionPane.ERROR_MESSAGE);
			}

		}
		else
		{
			try {

				
				chooseFile.showSaveDialog(null);
				program=new FileWriter(chooseFile.getSelectedFile().getAbsoluteFile());
				program.write(text.getText());
				program.close();
				JOptionPane.showMessageDialog(null,"File successfully saved .","SAVED",JOptionPane.INFORMATION_MESSAGE);
				text.setText("");
			}
			catch (IOException | NullPointerException e)
			{
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "File didn't open","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			
			
		}
	}
	
	private  void openFile()
	{

		try {
			
			chooseFile.showOpenDialog(null);
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
			JOptionPane.showMessageDialog(null, "File didn't open","ERROR",JOptionPane.ERROR_MESSAGE);
		}
		
		catch (NullPointerException e)
		{
			JOptionPane.showMessageDialog(null, "No file was picked .","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}

	public JLabel Time()
	{
		return time;
	}

	public  String setTime()
	{
		LocalTime current=LocalTime.now( ZoneId.systemDefault() );
		
		return String.format("%02d:%02d:%02d %s", (current.getHour()==12?0:current.getHour()%12),current.getMinute(),current.getSecond(),(( current.getHour()<12 && current.getHour()>0)?" AM":" PM"));
	}
}

