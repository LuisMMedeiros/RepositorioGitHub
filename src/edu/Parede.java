package edu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Parede {
	
	
	private static int x, y, width, height;
	private String id;
	public boolean construida, antigaParede;
	private Edu edu;
	private Player player;
	
	public Parede(int x, int y,int width, int height,String id) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
	}
	
	public static int getX() {
		return x;
	}
	public static int getY() {
		return y;
	}
	public static int getWidth() {
		return width;
	}
	public static int getHeight() {
		return height;
	}
	public String getID() {
		return this.id;
	}
	
	
	public void paredeando() {
		if(Player.getX() != x + 10) {
			x = Player.getX() + 50;
		}
		if(Player.getY() != y + 10) {
			y = Player.getY() -25;
		}
	}
	
	public void tick() {
	
		
	}
	
	
	public void render(Graphics g) {
		if(construida) {
			g.setColor(Color.YELLOW);
			g.fillRect(x, y, width, height);
		}
	}
}
