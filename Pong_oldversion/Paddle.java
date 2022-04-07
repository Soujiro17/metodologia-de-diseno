import java.awt.*;

public class Paddle
{
	public static final int WIDTH = 10;
	public static final int HEIGHT = 50;
	public static final int MAX_SPEED = 10;

	private int x, y;

	public Paddle(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void moveTo(int y)
	{
		if(Math.abs(this.y-y) > MAX_SPEED)
		{
			if(this.y > y)
				this.y -= MAX_SPEED;
			else
				this.y += MAX_SPEED;
		}
		else
			this.y = y;
	}

	public void move(int dy)
	{
		this.y+=dy;
		if(y < 0)
			y = 0;
		if(y > PongEnvironment.HEIGHT)
			y = PongEnvironment.HEIGHT;
	}

	// moveUp y moveDown reciben como parámetro la variable pressingShift para comprobar si se está presionando el shift para el boost de velocidad.
	// Si se está presionando, claramente la velocidad aumentará, sino, se mantiene normal.

	public void moveUp(boolean pressingShift)
	{
		if(pressingShift)
			move(-Paddle.MAX_SPEED - 15);
		else
			move(-Paddle.MAX_SPEED);
	}

	public void moveDown(boolean pressingShift)
	{
		if(pressingShift)
			move(Paddle.MAX_SPEED + 15);
		else
			move(Paddle.MAX_SPEED);

	}

	public boolean contains(Ball b)
	{
		Rectangle paddle = new Rectangle(x-WIDTH/2, y-HEIGHT/2, WIDTH, HEIGHT);
		Rectangle ball = new Rectangle((int)(b.getLocation().getX() - Ball.RADIUS),(int)(b.getLocation().getY() - Ball.RADIUS), Ball.RADIUS*2, Ball.RADIUS*2);

		return paddle.intersects(ball);
	}

	public void draw(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(x-WIDTH/2, y-HEIGHT/2, WIDTH, HEIGHT);
	}
}