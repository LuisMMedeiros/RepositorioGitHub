package edu;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Edu extends Canvas implements Runnable, KeyListener{
	

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private boolean isRunning = true;
	private Thread thread;
	private final int WIDTH = 160;
	private final int HEIGHT = 120;
	private final int SCALE = 4;
	private final BufferedImage image;
	
	private final Player player;
	private final Parede parede;
	private final Player2 player2;
	private final Parede2 parede2;
	
	public Edu() {
		this.addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		image = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
		//player 1
		player = new Player(10, 10, 50, 50, "Polaro" );
		parede = new Parede(1000, 1000, Player.getWidth()*2, Player.getHeight()*2, player.getID());
		//player 2
		player2 = new Player2(100, 100, 50, 50, "edu");
		parede2 = new Parede2(1200, 1000, Player2.getWidth()*2, Player2.getHeight()*2, player2.getID());
	}
	
	public void initFrame() {
		frame = new JFrame();
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	public synchronized void stop() {
		
	}
	
	public static void main(final String[] agrs) {
		final Edu game = new Edu();
		game.start();
		
	}
	
	public void tick() {
		player.tick();
		parede.tick();
		player2.tick();
		parede2.tick();
	}
	public void render() {
		final BufferStrategy bs  = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		player.render(g);
		parede.render(g);
		player2.render(g);
		parede2.render(g);
		bs.show();
;	}
		
	@Override
	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		final double ns = (1000000000 / amountOfTicks);
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while(isRunning) {
			final long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			if(System.currentTimeMillis() - timer >=1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		}
	}

	@Override
	public void keyPressed(final KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_E) {
			parede.construida = true;
			parede.paredeando();
			
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			 player.right = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_A) {
			 player.left = true;
		}
		 
		if(e.getKeyCode() == KeyEvent.VK_W) {
			 player.up = true;
		}
		 
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			 player.down = true;
		}
		
		
		//player 2		
		
		if(e.getKeyCode() == KeyEvent.VK_P) {
			parede2.construida = true;
			parede2.paredeando();
		}	

		 if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			 player2.right = true;

		 }
		 else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			 player2.left = true;
		 }
		 
		 if(e.getKeyCode() == KeyEvent.VK_UP) {
			 player2.up = true;
		 }
		 
		 else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			 player2.down = true;
		 }
		 
		 
	}

	@Override
	public void keyReleased(final KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
			 
		if(e.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;
		}
			 
		else if(e.getKeyCode() == KeyEvent.VK_S ) {
			player.down = false;
		}
			 
		//player 2
			 
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player2.right = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player2.left = false;
		}
					 
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player2.up = false;
		}
					 
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player2.down = false;
		}
		
	}

	@Override
	public void keyTyped(final KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
	