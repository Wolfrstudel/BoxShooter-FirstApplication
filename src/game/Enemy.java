
package game;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends Rectangles{

    public Enemy(){
        super();

    }
    public EnemyGenerator generateEnemy(){
        EnemyGenerator enemy = new EnemyGenerator(0,30,30);
        return enemy;
    }
}
