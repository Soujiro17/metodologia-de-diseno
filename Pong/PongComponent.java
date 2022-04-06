import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PongComponent extends JComponent implements KeyListener, Runnable
{
	private PongEnvironment environment;
	private boolean pressingUp = false;
	private boolean pressingDown = false;
	private boolean pressingShift = false;

	public PongComponent()
	{
		super();
		environment = new PongEnvironment();
		environment.setComputer(false, true);
 		setPreferredSize(new Dimension(environment.WIDTH, environment.HEIGHT));

 		addKeyListener(this);
 		Thread run = new Thread(this);
 		run.start();
	}

	public void run()
	{
		while(true)
		{
			try
			{
				Thread.sleep(20);
			}
			catch(Exception ex)
			{
			}
			requestFocus();
			update();
			repaint();
		}
	}

	public void paint(Graphics g)
	{
		synchronized(g)
		{
			environment.draw(g);
		}
	}

	public void update()
	{
		environment.update();

		// Se envía como parámetro si se está presionando el shift, para activar el boost de velocidad.
		if(pressingUp)
			environment.getLeft().moveUp(pressingShift);
		else if(pressingDown)
			environment.getLeft().moveDown(pressingShift);
	}

	public void keyPressed(KeyEvent ke)
	{
		// Se comprueba que se mantiene presionado el shift en un condicional aparte,
		// ya que si se evalúa en el mismo que la keyUp y keyDown, solo se podrá mantener
		// presionado una de las tres, no dos al mismo tiempo (shift + (keyDown | keyUp)).
		if(ke.getKeyCode() == KeyEvent.VK_SHIFT)
			pressingShift = true;

		if(ke.getKeyCode() == KeyEvent.VK_UP)
			pressingUp = true;
		else if(ke.getKeyCode() == KeyEvent.VK_DOWN)
			pressingDown = true;

	}

	public void keyReleased(KeyEvent ke)
	{
		// Cuando se deje de presionar el shift, que pressingShift sea false.
		// para actualizar el estado del paddle y su velocidad.

		if(ke.getKeyCode() == KeyEvent.VK_SHIFT)
			pressingShift = false;

		if(ke.getKeyCode() == KeyEvent.VK_UP)
			pressingUp = false;
		else if(ke.getKeyCode() == KeyEvent.VK_DOWN)
			pressingDown = false;
	}

	public void keyTyped(KeyEvent ke)
	{
	}
}