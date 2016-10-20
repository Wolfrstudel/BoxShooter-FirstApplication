
package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;

public class Game extends JFrame {
    Color color;
    slider slider;
    ColorchooserFrame colorChooser;
    JPopupMenu popup;
    
    boolean running = true;
    boolean starting = false;
    int fps = 60;
    int frameWidth = 500;
    int frameHeight = 500;
    
    Insets insets;
    BufferedImage backBuffer;
    InputHandler input;
    
    //Sets up the player and the enemy
    Player player = new Player(frameWidth/2,frameHeight-40,20,20);
    int playerSpeed = 5;
    Enemy enemy = new Enemy();
    
    //Sets up bullets
    public playerBullet[] bullets;
    final int bulletSpeed = -15;
    
    //Sets up enemies
    public EnemyGenerator[] enemyGen;
    final int enemySpeed = -1;
    int enemyNum = 6;
    
    int Score = 0;
    
    Container contentPane;
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
       
    public void run(){
        initialize();
        //Sets up the starting frame
        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        JLabel label = new JLabel("Go to file to start the game. Arrow Keys to move, spacebar to shoot.");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.PLAIN, 14));
        contentPane.add(label, BorderLayout.CENTER);
        makeMenuBar();
        while(!starting){
            
        }
        remove(contentPane);
        
        while(running){
            //The current time in milliseconds
            long time = System.currentTimeMillis();
            //Update the current frame
            update();
            draw();
            
            if(slider != null){
                playerSpeed = slider.getPlayerSpeed();
            }
            
            if(bullets[0] != null){
                collision();
            }
            //Removes a bullet if it reaches the bottom of the frame
            for(int i = 0;i < bullets.length;i++){
                if(bullets[i]!= null)
                {
                    bullets[i].movement(bulletSpeed);
                    if(bullets[i].getposy()<0){
                        bullets[i] = null;
                    }
                }
            }
            //Removes an enemy if they reach the bottom of the frame
            for(int i = 0;i < enemyGen.length;i++){
                if(enemyGen[i]!= null)
                {
                    enemyGen[i].enemySpeed(enemySpeed);
                    if(enemyGen[i].getposy()> frameHeight-( 20+ enemyGen[i].height)){
                        running = false;
                    }
                }
            }
            //Generates enemies
            for(int i = 0; i < enemyGen.length;i++){
            if(enemyGen[i] == null){
               enemyGen[i] = enemy.generateEnemy();
               break;
            }
          }
            time = (1000/fps) - (System.currentTimeMillis()- time);
            
            if(time>0)
            {
                try{
                    Thread.sleep(time);
                }
                catch(Exception e){}
            }
        }
        setVisible(false);
        
        gameOverGui gameover = new gameOverGui();
        gameover.makeFrame();
        
        highscoreGui highscore = new highscoreGui(Score);
        highscore.makeFrame();

    }
    
        
    public void initialize(){
        setTitle("Box Shooter");
        setSize(frameWidth, frameHeight);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(d.width/2 - frameWidth/2, d.height/2 - frameHeight/2);
        
        //Insets are the title bar and the frame around the window.
        //This bit of code must be placed after set visible because set visible decides what the insets are
        insets = getInsets();
        setSize(insets.left + frameWidth + insets.right, insets.top + frameHeight + insets.bottom);
        
        //Creates an image that is left behind when frames are constantly changing
        backBuffer = new BufferedImage(frameWidth, frameHeight,BufferedImage.TYPE_INT_RGB);
        //Sets up the input handler
        input = new InputHandler(this);
        
        //Amount of enemies that can be on screen
        enemyGen = new EnemyGenerator[enemyNum];
        
        //Amount of bullets that can be on screen
        bullets = new playerBullet[1];
        
    }
    public void update(){
        //Moves the player to the right with the right arrow key
        if(input.isKeyDown(KeyEvent.VK_RIGHT) && player.posx < frameWidth - (20 + insets.right)){
            player.posx += playerSpeed;
        }
        //Moves the player to the left with the left arrow key
        if(input.isKeyDown(KeyEvent.VK_LEFT) && player.posx > 0){
            player.posx -= playerSpeed;
        }
        //Moves the player up with the up arrow key
        if(input.isKeyDown(KeyEvent.VK_UP) && player.posy > 0){
            player.posy -= playerSpeed;
        }
        //Moves the player down with the down arrow key
        if(input.isKeyDown(KeyEvent.VK_DOWN) && player.posy < frameHeight - (40)){
            player.posy += playerSpeed;
        }
        //Shoots a bullet if space bar is pressed
        if(input.isKeyDown(KeyEvent.VK_SPACE)){
            for(int i = 0; i < bullets.length;i++){
                if(bullets[i] == null){
                    bullets[i] = player.shoot();
                    break;
                    
                    }  
                }
            }
        }
    public void draw(){
        //Sets up double buffering
        Graphics g = getGraphics();
        Graphics bbg = backBuffer.getGraphics();
        //Sets color of the background
        bbg.setColor(Color.BLACK);
        bbg.fillRect(0,0,frameWidth,frameHeight);
        //Sets the color of the player
        if(colorChooser == null){
            color = Color.red;
        }else{
           color = colorChooser.getColor(); 
        }
        
        bbg.setColor(color);
        bbg.fillRect(player.posx,player.posy,player.width,player.height);
        
        //Draws the bullets
        for(int i = 0; i< bullets.length;i++){
            if(bullets[i]!= null){
                bullets[i].draw(bbg);
            }
        }
        //Draws the enemies
        for(int i = 0; i < enemyGen.length;i++){
            if(enemyGen[i]!= null){
                enemyGen[i].draw(bbg);
            }
        }    
        bbg.setColor(Color.WHITE);
        bbg.fillRect(0,frameHeight-20,frameWidth,20);
        bbg.setColor(Color.BLACK);
        bbg.drawString("Score: " + Score,0,frameHeight);
        //image,location,location, ImageObserver
        g.drawImage(backBuffer, insets.left, insets.top, this);
    }
    public void collision(){
        int i = 0;
        while(i < enemyGen.length){
        Rectangle rectangle1 = bullets[0].boundary();
        Rectangle rectangle2 = enemyGen[i].boundary();
        
        if(rectangle1.intersects(rectangle2)){
        bullets[0] = null;
        enemyGen[i] = null;
        Score = Score + 100;
        return;
        }
        i++;
      }
    }
    public void makeMenuBar(){
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu ("File");
        
        JMenuItem startItem = new JMenuItem("Start");
        startItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                starting = true;
            }
        });
        
        file.add(startItem);
        menubar.add(file);
        
        JMenu options = new JMenu("Options");
        JMenuItem color = new JMenuItem("Choose Player Color");
        color.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                colorChooser = new ColorchooserFrame();
                colorChooser.makeFrame();
            }
        });
        options.add(color);
        JMenuItem enemynumber = new JMenuItem("Choose Player Speed");
        enemynumber.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                slider = new slider();
                slider.makeFrame();
            }
        });
        options.add(enemynumber);
        menubar.add(options);
        
        
        setJMenuBar(menubar);
        
    }


}
