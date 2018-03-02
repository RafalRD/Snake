package snake;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;


public class Snake implements ActionListener, KeyListener
{

	public static Snake snake;
	private static Dimension dim;
	

	private JFrame jframe;
	private RenderPanel renderPanel;
	private Timer timer = new Timer(20,this);
	
	public ArrayList<Point> snakeParts = new ArrayList<Point>();

	private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	public static final int SCALE = 15;

	public int score;
	public int tailLength;
	public int ticks;
	public int speed;
	private int  direction;
	public Point head, cherry;
	private Random random;
	public boolean over, pause;
	
	public Snake()
	{
		dim = Toolkit.getDefaultToolkit().getScreenSize();

		pause =true;
		over = false;

		ticks = 1;
		score=0;
		speed=6;
		tailLength = 0;

		jframe = new JFrame("Snake");
		jframe.setVisible(true);
		jframe.setSize(800, 695);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setResizable(false);
		jframe.setLocation(dim.width /2 - jframe.getWidth() /2, dim.height /2 - jframe.getHeight() /2);
		jframe.add(renderPanel = new RenderPanel());
		jframe.addKeyListener(this);

		timer.start();
		startGame();

	}
	private void startGame()
	{
		over = false;
		direction = DOWN;
		head = new Point(0,0);
		random = new Random();
		snakeParts.clear();
		cherry = new Point(random.nextInt(52), random.nextInt(43));
		for (int i = 0; i> tailLength; i++)
		{
			snakeParts.add(new Point(head.x,head.y));
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		renderPanel.repaint();
		ticks++;

		if(ticks % speed == 0 && head != null && !over && !pause)
		{
			snakeParts.add(new Point(head.x,head.y));
			
			if(direction == UP)
			{
				if(head.y -1 >= 0 )
					head = new Point(head.x, head.y -1);
				else
					over = true;
			}
			if(direction == DOWN)
			{
				if(head.y + 1 < 44 )
					head = new Point(head.x, head.y +1);
				else 
					over = true;
			}
			if(direction == LEFT)
			{
				if(head.x -1 >= 0 )
					head = new Point(head.x - 1, head.y);
				else
					over = true;
			}
			if(direction == RIGHT)
			{
				if(head.x + 1 < 53 )
					head = new Point(head.x + 1, head.y);
				else
					over = true;
			}
			if(snakeParts.size() > tailLength)
				snakeParts.remove(0);
			
			if(cherry != null)
			{
				if(head.equals(cherry))
				{
					score += 10;
					tailLength++;
					cherry.setLocation(random.nextInt(52), random.nextInt(43));
				}
			}
		}
	}

	// main
	public static void main(String[] args)
	{
		snake = new Snake();
	}

	// handle input
	@Override
	public void keyPressed(KeyEvent arg0) 
	{
		int i = arg0.getKeyCode();  
		int X = 0;
		int Y = 0;

		if (i == KeyEvent.VK_LEFT && direction != RIGHT)
		{
			direction = LEFT;
		}
		if (i == KeyEvent.VK_RIGHT && direction != LEFT)
		{
			direction = RIGHT; 
		}
		if (i== KeyEvent.VK_DOWN && direction != UP)
		{
			direction = DOWN;
		}
		if (i == KeyEvent.VK_UP && direction != DOWN && (X < head.x | X > head.x | Y < head.y))
		{
			direction = UP;
		}
		if (i == KeyEvent.VK_SPACE )
		{
			if (over)
			{
				startGame();
				ticks = 0;
			}
			else
				pause =! pause;
		}

		if (i == KeyEvent.VK_L && pause)
		{
			speed ++;
			if(speed > 6)
			{
				speed = 6;
			}
	}
		if (i == KeyEvent.VK_H && pause)
		{
			speed --;
			
			if(speed < 1)
			{
				speed = 1;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) 
	{

	}

	@Override
	public void keyTyped(KeyEvent arg0) 
	{

	}

}
