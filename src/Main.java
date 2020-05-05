import java.awt.Color;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
	JFrame obj=new JFrame(); // opens the window where game will run
	
	Gameplay gameplay=new Gameplay();
	
	obj.setBounds(10, 10, 905, 700); // to set the size of the window that will open
	obj.setBackground(Color.DARK_GRAY); // background color of our window 
	obj.setResizable(false); // This is done so that the user may not resize the window of our game.
	obj.setVisible(true);
	obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Means it exits on close by default.
	
	obj.add(gameplay); // adding obj of Gameplay to obj of JFrame
	
	

	}

}
