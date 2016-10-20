package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class playerBullet extends Rectangles {
    
    public playerBullet(int posx, int posy, int width, int height){
        super();
        this.posx = posx;
        this.posy = posy;
        this.width = width;
        this.height = height;
    }
    public int getposy(){
        return posy;
    }
    public int getposx(){
        return posx;
    }
    public void draw(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillOval(posx,posy,width,height);
        
    }
    public void movement(int speed){
        posy += speed;
    }
}
