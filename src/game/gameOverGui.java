package game;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class gameOverGui{
    int frameWidth = 500;
    int frameHeight = 500;
    JFrame frame;
    JLabel label = new JLabel("GAME OVER");
 
    public void makeFrame(){
        frame = new JFrame();
        frame.setSize(frameWidth,frameHeight);
        Container contentPane = frame.getContentPane();
        
        //Sets the words Game over in the center of the screen
        label.setFont(new Font("Serif",Font.BOLD,40));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setOpaque(true);
        label.setBackground(Color.BLACK);
        
        contentPane.add(label);
        
        frame.setResizable(false);
        frame.setVisible(true);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        //Pauses the frame for 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(gameOverGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Removes the frame
        frame.dispose();
    }
        
}

    