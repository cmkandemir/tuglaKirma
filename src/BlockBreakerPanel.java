import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BlockBreakerPanel extends JPanel implements KeyListener{
	ArrayList<Block> blocks=new ArrayList<Block>();
	ArrayList<Block> ball=new ArrayList<Block>();
	Block paddle;
	Thread thread;
	Animate animate;
	int size=25; //size of the ball
	BlockBreakerPanel(){
		paddle=new Block(175, 480, 150,25, "images/black.png");
		for (int i=0; i<8; i++){
			blocks.add(new Block((i*60+2),0,60,25, "images/blue.png"));
		}
		for (int i=0; i<8; i++){
			blocks.add(new Block((i*60+2),25,60,25, "images/red.png"));
		}
		for (int i=0; i<8; i++){
			blocks.add(new Block((i*60+2),50,60,25, "images/green.png"));
		}
		for (int i=0; i<8; i++){
			blocks.add(new Block((i*60+2),75,60,25, "images/yellow.png"));
		}
		ball.add(new Block(237,437,25,25,"images/ball.png")); //first Ball
		addKeyListener(this); //without this you won't read the keys
		setFocusable(true);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g); //erase the screen and repaint
		for (Block b : blocks)
			b.draw(g, this);
		for (Block b : ball)
			b.draw(g, this);
		paddle.draw(g, this);
	}
	//update thread
	public void update(){ 
		for (Block ba:ball){
			ba.x+=ba.dx;
			if(ba.x>(getWidth()-size)&&ba.dx>0||ba.x<0)
				ba.dx*=-1;
			if (ba.y<0||ba.intersects(paddle))
				ba.dy*=-1;
			ba.y+=ba.dy;
			for (Block b: blocks){
				if(ba.intersects(b)&&!b.destroyed){
					b.destroyed=true;
					ba.dy*=-1; //if this value =-1 ???
				}
			}
		}
		repaint();
	}

	//Add unimplemented methods
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER){
			animate=new Animate(this);
			thread=new Thread(animate);
			thread.start();
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT && paddle.x>0){
			paddle.x-=15;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT && paddle.x<(getWidth()-paddle.width)){
			paddle.x+=15;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
