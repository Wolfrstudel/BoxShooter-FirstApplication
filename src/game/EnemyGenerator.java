package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class EnemyGenerator extends Rectangles{
    Random random = new Random();
    
    public EnemyGenerator(int posy,int width, int height){
        super();
        posx = 20 + random.nextInt(450);
        this.posy = posy;
        this.width = width;
        this.height = height;
    }
    
    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.drawRect(posx,posy,width,height);
        g.setColor(Color.GREEN);
        g.fillRect(posx,posy,width,height);
    }
    public int getposy(){
        return posy;
    }
    public void enemySpeed(int speed){
        posy -= speed;
    }
}
