package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGame extends JLabel {
    JLabel label1;
    JButton button1,button2;
    JPanel panel;
    
    boolean starting = false;
    public void makeLabel(){
         setLayout(new BorderLayout());
        
         label1 = new JLabel("Title");
         label1.setFont(new Font("Serif",Font.BOLD,60));
         label1.setHorizontalAlignment(SwingConstants.CENTER);
         add(label1,BorderLayout.NORTH);
         
         panel = new JPanel();
         panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
         button1 = new JButton("Start");
         button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                starting = true;
            }
        });
        button1.setMaximumSize(new Dimension(200,50));
        button2 = new JButton("Highscores");
        button2.setMaximumSize(new Dimension(200,50));
        
        panel.add(Box.createRigidArea(new Dimension(0,200)));
        panel.add(button1);
        button1.setAlignmentX(panel.CENTER_ALIGNMENT);
        panel.add(button2);
        button2.setAlignmentX(panel.CENTER_ALIGNMENT);
              
        add(panel,BorderLayout.CENTER);
         
         
         
        
    }
    public boolean getStarted(){
        return starting;
    }
    
}
