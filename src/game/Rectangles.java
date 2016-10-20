package game;

import java.awt.Rectangle;

abstract public class Rectangles {
    int posx,posy,width,height;
    
    public Rectangles(){
    
    }
    
    public Rectangle boundary(){
    return(new Rectangle(posx,posy,width,height));
    }
    
}
