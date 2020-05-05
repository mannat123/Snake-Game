import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener,ActionListener
{

	// arrays for mentioning the position of snake 
	private int snakeXLength[]=new int[750];
	private int snakeYLength[]=new int[750];
	
	//for keeping the track of direction of snake
	private boolean left=false;
	private boolean right=false;
	private boolean up=false;
	private boolean down=false;
	
	// for the mouth of snake in different directions 
	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	
	private int  lengthofsnake=3; 
	private int moves=0;
	
	//for controlling the speed of snake 
	private Timer timer; 
	private int delay=120;
	
	private ImageIcon snakeimage; //for body of snake
	
	private ImageIcon titleimage; // for the title image that we have given 'SNAKE' 
	
	// default enemy positions 
	private int[] enemyxpos= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,
			400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	private int[] enemyypos= {75,100,125,150,175,200,225,250,275,300,325,350,375,
			400,425,450,475,500,525,550,575,600,625};
	
	private ImageIcon enemyimage;
	
	private Random random=new Random();
	 
	private int xpos=random.nextInt(34);
	private int ypos=random.nextInt(23);
	
	
	private int score=0;
	
	public Gameplay()
	{
		addKeyListener(this); // the parameter is the object of the class implementing KeyListener interface.
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer =new Timer(delay, this);
		timer.start();
		
		
		
	}
	public void paint(Graphics g)
	{
		/* This will check if the game has already started or not.
		 * so this is to check the moves initially 
		 */
		if(moves==0)
		{
			snakeXLength[2]=50;
			snakeXLength[1]=75;
			snakeXLength[0]=100;
			
			snakeYLength[2]=100;
			snakeYLength[1]=100;
			snakeYLength[0]=100;
			
		}
		
		// draw title image border
		g.setColor(Color.WHITE);
		g.drawRect(24, 10, 851,55);
		
		// draw title image 
		titleimage=new ImageIcon("snaketitle.jpg");
		titleimage.paintIcon(this, g, 25, 11);
		
		// draw border for gameplay or playing area
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 577);
		
		// draw background for the gameplay 
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		//draw scores
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Score: "+score, 780, 30);
		
		//draw length
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial",Font.PLAIN,14));
				g.drawString("Length: "+lengthofsnake, 780, 50);
				
		
		 
		//giving default position of snake and default length 
		rightmouth=new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this, g,snakeXLength[0], snakeYLength[0]);
		
		//loop for detecting the direction of snake 
		for(int i=0;i<lengthofsnake;i++)
		{
			/*here i=0 means the starting of snake as snakeXLength[] and snakeYLength[] arrays have head of
			 * snake at 0th index and then the body of snake and right==true or others define the 
			 * direction in which it is present already. 
			 */
			if(i==0 && right==true)
			{
				rightmouth=new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g,snakeXLength[i], snakeYLength[i]);
			}

			if(i==0 && left==true)
			{
				leftmouth=new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g,snakeXLength[i], snakeYLength[i]);
			}

			if(i==0 && up==true)
			{
				upmouth=new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g,snakeXLength[i], snakeYLength[i]);
			}

			if(i==0 && down==true)
			{
				downmouth=new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g,snakeXLength[i], snakeYLength[i]);
			}

			// Now if the snake's head is built we will create the body using this 
			 if(i!=0)
			 {
				snakeimage=new ImageIcon("snakeimage.png");
				snakeimage.paintIcon(this, g,snakeXLength[i], snakeYLength[i]);
			 }
		}
		
		enemyimage=new ImageIcon("enemy.png"); // for displaying the enemy 
		
		// now we have to check if the head of the snake is colliding with the enemy position 
		if((enemyxpos[xpos]==snakeXLength[0]) && (enemyypos[ypos]==snakeYLength[0]))
		{
			score++;
			lengthofsnake++;
			xpos=random.nextInt(34);
			ypos=random.nextInt(23);
			
		}
		 
		enemyimage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);
		
		//to check the collision of head with its body
		for( int b=1;b<lengthofsnake;b++)
		{
			if(snakeXLength[b]==snakeXLength[0]&& snakeYLength[b]==snakeYLength[0])
			{
				right=false;
				left=false;
				up=false;
				down=false;
				
				g.setColor(Color.white);
				g.setFont(new Font("arial",Font.BOLD,50));
				g.drawString("Game Over", 300, 300);
				
				g.setFont(new Font("arial",Font.BOLD,20));
				g.drawString("Press SPACE to restart", 350, 340);
				
				
			}
		}
		
		g.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(right)
		{
			for(int r=lengthofsnake-1;r>=0;r--)
			{
				snakeYLength[r+1]=snakeYLength[r];
			}
			for(int r=lengthofsnake;r>=0;r--)
			{
				if(r==0)
				{
					snakeXLength[r]=snakeXLength[r]+25;
					
				}
				else
				{
				snakeXLength[r]=snakeXLength[r-1];
				}
				if(snakeXLength[r]>850)
				{
					snakeXLength[r]=25;
				}
				
			}
			repaint(); // will call paint method automatically 
				
		}
		if(left)
		{
			for(int r=lengthofsnake-1;r>=0;r--)
			{
				snakeYLength[r+1]=snakeYLength[r];
			}
			for(int r=lengthofsnake;r>=0;r--)
			{
				if(r==0)
				{
					snakeXLength[r]=snakeXLength[r]-25;
					
				}
				else
				{
				snakeXLength[r]=snakeXLength[r-1];
				}
				if(snakeXLength[r]<25)
				{
					snakeXLength[r]=850;
				}
				
			}
			repaint();
		}
		if(up)
		{

			for(int r=lengthofsnake-1;r>=0;r--)
			{
				snakeXLength[r+1]=snakeXLength[r];
			}
			for(int r=lengthofsnake;r>=0;r--)
			{
				if(r==0)
				{
					snakeYLength[r]=snakeYLength[r]-25;
					
				}
				else
				{
				snakeYLength[r]=snakeYLength[r-1];
				}
				if(snakeYLength[r]<75)
				{
					snakeYLength[r]=625;
				}
				
			}
			repaint();
		}
		if(down)
		{

			for(int r=lengthofsnake-1;r>=0;r--)
			{
				snakeXLength[r+1]=snakeXLength[r];
			}
			for(int r=lengthofsnake;r>=0;r--)
			{
				if(r==0)
				{
					snakeYLength[r]=snakeYLength[r]+25;
					
				}
				else
				{
				snakeYLength[r]=snakeYLength[r-1];
				}
				if(snakeYLength[r]>625)
				{
					snakeYLength[r]=75;
				}
				
			}
			repaint();
			
		}
		
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	// function for detection of keys on the keyboard and how we will use it 
	public void keyPressed(KeyEvent e) {
		
		// if space key is pressed then the game starts again 
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			moves=0;
			score=0;
			lengthofsnake=3;
			repaint();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			moves++;
			right=true;
			/* this is to check that if the snake 
			 * is already moving in left and right arrow key is pressed acc to the above if
			 * then it should not turn to right suddenly 
			 * bcoz then it will attack itself only 
			 * same for up and down 
			 */
			if(!left)
			{
				right=true;
			}
//			//if left is true means it's moving towards left and we have pressed right but we applied that
//			in else that right needs to stay false so that it can't turn right suddenly and not attack itself.
			else
			{
				right=false;
				left=true;
				
			}
			up=false;
			down=false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			moves++;
			left=true;
			if(!right)
			{
				left=true;
			}
			else
			{
				left=false;
				right=true;
				
			}
			up=false;
			down=false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			moves++;
			up=true;
			if(!down)
			{
				up=true;
			}
			else
			{
				up=false;
				down=true;
				
			}
			left=false;
			right=false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			moves++;
			down=true;
			if(!up)
			{
				down=true;
			}
			else
			{
				down=false;
				up=true;
				
			}
			left=false;
			right=false;
		}
		
		
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
