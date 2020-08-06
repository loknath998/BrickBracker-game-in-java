import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JPanel;
import javax.swing.JFrame;

class PDemo extends JPanel implements ActionListener , KeyListener
{
	ImageIcon ic1,ic2,ic3;
	Image ball,brick,brick2;
	int height;
	int width;
	int time;
	int height_of_box = 20;
	int width_of_box = 70;
	int x,y,xspeed,yspeed;
	int x1,y1,xspeed1,yspeed1;
	int x2,y2,xspeed2,yspeed2;
	int startx = 70;
	int starty = 40;
	int boxx[] = new int[50];
	int boxy[] = new int[50];
	int boxes =22;
	int hits = 0;
	int distx =120;
	int disty =0;
	int xb; // x position of brick
	int yb ;	// y position of brick 
	int wb ;  // width of brick
	int hb ;  // height of brick
	int size_of_ball;
	Timer timer;
	boolean start_game;
	
	PDemo( int width, int height)
	{
		this.height = height;
		this.width = width;
		// ic1 = new ImageIcon("black_ball.png");
		// ic2 = new ImageIcon("brick.png");
		// ic3 = new ImageIcon("brick2.png");
		// ball = ic1.getImage();
		// brick = ic2.getImage();
		// brick2 = ic3.getImage();
		start_game = false;
		x = 200;
		y = 300;
		xspeed = 10;
		yspeed = 10;
		time = 33;
		xb = width/2 -60 ; // x position of brick
		yb = height -70;	// y position of brick 
		wb = 120;  // width of brick
		hb = 30;  // height of brick
		size_of_ball =30;

		Font f = new Font("stencil",Font.BOLD, 55);
		setFont(f);
		// setBackground(Color.white);
		setBackground(Color.black);
		timer = new Timer(time,this);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
		
		int k =0;
		for(int i =0; i< boxes; i++)
		{
			
			boxx[i] = startx + k*distx;
			boxy[i] = starty + disty;
			k++;
			if( i==5 || i == 16)
			{
				disty +=70;
				startx = 130;
				k=0;
			}
			else if(i==10 )
			{	
				disty +=70;
				startx = 70;
				k=0;
			}
		}
		
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.green);
		g.fillRect(xb,yb,wb,hb);
		// g.drawImage(brick,xb,yb,this);
		// int k=0,i1;
		g.setColor(Color.red);
		g.fillOval(x,y,size_of_ball,size_of_ball);
		// g.drawImage(ball,x,y,this);
		// System.out.println("repaint");
		g.setColor(Color.gray);
		for(int i = 0; i< boxes; i++)
		g.fillRect(boxx[i],boxy[i],width_of_box,height_of_box);
		g.setColor(Color.white);
		if(hits == 22)
		{
			timer.stop();
			g.drawString("You Win ....",250,400);
			g.drawString("abhi maja aaya na beedu",10,500);
		}			
		else if(y> height -40 )
			{
				timer.stop();
				g.drawString("sed life, you loose bro ",50,400);
				
			}
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		
			
		if(start_game)
		{
			x += xspeed;
			if(y< height )
			y += yspeed;
			if( y< 5 )
				yspeed *= -1;
			if(x> width -size_of_ball - 25 || x< 5)
				xspeed *= -1;
			
		// if( (y == yb - size_of_ball || y == yb + hb ) && ( x >= xb - size_of_ball && x<= xb + wb )) 
		if( (y == yb - size_of_ball ) && ( x >= xb - size_of_ball && x<= xb + wb )) 
			{
				System.out.println("condition 1  \tx: "+x+"\ty: "+y);
				yspeed *= -1;
			}
			
			
			for(int i = 0; i< boxes; i++)
			{
				if( (x == boxx[i]- size_of_ball  || x== boxx[i] + width_of_box ) && (y >= boxy[i] -size_of_ball && y <= boxy[i] +height_of_box ))
				{	
					if( x - xspeed  < boxx[i]- size_of_ball || x - xspeed >  boxx[i] + width_of_box )
					{	
				xspeed *= -1;
				hits++;
					boxx[i]= 900;
					}	
				System.out.println("condition a  \tx: "+x+"\ty: "+y+" box x:"+boxx[i]+"  box y:"+boxy[i]);
				}
				if(( y == boxy[i]- size_of_ball || y== boxy[i] +height_of_box ) && ( x>= boxx[i]-size_of_ball && x<=boxx[i]+width_of_box ))
				{
					yspeed *= -1;
					hits++;
				System.out.println("condition b  \tx: "+x+"\ty: "+y+" box x:"+boxx[i]+"  box y:"+boxy[i]);
					boxy[i]= 900;

				}
				
			}
			
	
		}
			repaint();
	}
	
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			// start_game = !start_game;
			start_game = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if(xb + wb < width -40)
			{
				xb += 40;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if(xb > 0)
			{
				xb -= 40;
			}
		}
		
	}
	public void keyReleased(KeyEvent e)
	{
	
	}
	
	public void keyTyped(KeyEvent e)
	{
	}
}

class FDemo extends JFrame
{
	PDemo p;
	FDemo(int width, int height)
	{
		
		p = new PDemo(width,height);
		add(p);
	}
}
class BrickBreaker
{
	public static void main(String []arfdaf)
	{
		int height = 700;
		int width = 800;
		FDemo f = new FDemo(width,height);
		f.setVisible(true);
		//the screen will show 17 pixcel less in right x direction 
		//the screen will show 40 pixcel less in down y direction 
		f.setBounds(0,0,width,height);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setResizable(false);
		
	}
}