package snake;

import javax.swing.*;
import java.awt.*;

public class RenderPanel extends JPanel
{

	private static Color green = new Color(39219);
	private static Color purple = new Color(16711783);
	private static Color blue = new Color(3342591);
	
	
	@Override
	protected void paintComponent(Graphics g2) 
	{
		Graphics2D g =  (Graphics2D) g2;

		g.setColor (green);
		g.fillRect(0, 0, 800, 700);
		                            
		g.setColor(Color.RED);
		Snake snake = Snake.snake;
				
		for(Point point : snake.snakeParts)
		{
			g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		}

		g.setColor(Color.BLUE);
		g.fillRect(snake.head.x* Snake.SCALE, snake.head.y *Snake.SCALE, Snake.SCALE, Snake.SCALE);
		g.setColor(purple);
		g.fillRect(snake.cherry.x *Snake.SCALE, snake.cherry.y * Snake.SCALE, Snake.SCALE,Snake.SCALE);
		
		g.setColor(Color.cyan);
		g.setFont(new Font("Courier New", Font.BOLD, 18));
		String text = "Score = " + snake.score + "   Length = " + snake.tailLength + "  Time = " + snake.ticks / 50 + "  Speed = " + snake.speed;
		String welcomeText =  "Collect 60 cherries!" ;
		g.drawString(text, 150, 15);
		
		if (snake.tailLength < 1)
		{
			g.setFont(new Font("Courier New", Font.HANGING_BASELINE, 25));
			g.setColor(Color.BLACK);
			g.drawString( welcomeText, 20, 60);
		}
		
		String notYet =  "oh no 10 more !!!!" ;
		if (snake.tailLength > 59 &&  60 <= snake.tailLength && snake.tailLength < 62)
		{
			g.setFont(new Font("Courier New", Font.HANGING_BASELINE, 25));
			g.setColor(Color.BLACK);
			g.drawString( notYet, 20, 60);
		}
		
		g.setColor(blue);
		g.setFont(new Font("Courier New", Font.BOLD, 30));
		String victory = "YOU WIN ;)!!!";
		
		if(snake.score == 700 )
		{
			g.drawString(victory, 290, 250);
			snake.over = true;
			snake.ticks--;
			snake.cherry.setLocation(-1,-1);
		}
		if (snake.pause == true )
		{
			snake.ticks --;
			g.setFont(new Font ("Courier New", Font.BOLD, 17));
			g.setColor(Color.black);
			g.drawString("Press:" , 20,100);
			g.drawString("- H to speed snake, L to slow the snake ", 20 , 130);
			g.drawString("- space to pause" , 20, 150);
		}
		if (snake.over == true)
		{
			snake.ticks--;
			g.setFont(new Font ("Courier New", Font.BOLD, 22));
			g.setColor(Color.black);
			g.drawString("GAME OVER!!!" , 300,250);
			
		}
		
	}

	
}
