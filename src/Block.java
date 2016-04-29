import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Block extends Rectangle{
	Image pic;
	int dx=3; //ball movement
	int dy=-3;
	boolean destroyed=false;
	Block(int a, int b, int w, int h, String s){
		x=a;
		y=b;
		width=w;
		height=h;
		pic=Toolkit.getDefaultToolkit().getImage(s);
	}
	
	public void draw(Graphics g, Component c){
		if (!destroyed)
			g.drawImage(pic, x, y, width, height, c);		
	}
	

}
