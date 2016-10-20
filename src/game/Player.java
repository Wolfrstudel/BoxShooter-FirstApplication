package game;

import java.awt.Rectangle;

public class Player extends Rectangles{
    
    public Player(int posx, int posy, int width, int height){
        super();
        this.posx = posx;
        this.posy = posy;
        this.width = width;
        this.height = height;
        
    }
    public playerBullet shoot(){
        playerBullet bullet = new playerBullet(posx+width/3,posy,5,10);
        return bullet;
    }
    public int getPosX(){
        return posx;
    }
    public int getPosY(){
        return posy;
    }
}