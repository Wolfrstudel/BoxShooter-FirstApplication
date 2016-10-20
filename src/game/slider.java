
package game;

import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;
import javax.swing.*;
import javax.swing.event.*;

public class slider {
    JButton button;
    JSlider slider;
    JFrame frame;
    int max = 10;
    int min = 1;
    int playerSpeed;
    
    public void makeFrame(){
        frame = new JFrame();
        frame.setTitle("Set the number of Enemies.");
        frame.setSize(300,100);
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        // Sets up the slider
        slider = new JSlider(JSlider.HORIZONTAL);
        slider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                playerSpeed = slider.getValue();
            }
        });
        slider.setMinimum(min);
        slider.setMaximum(max);
        slider.setMajorTickSpacing(2);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBackground(Color.GREEN);
        
        //When you click the button it makes the screen magically disappear
        button = new JButton("Submit");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
            }
        });
        button.setBackground(Color.GREEN);
        contentPane.add(button,BorderLayout.SOUTH);
             
        //Creates a hashtable to labe the tick marks of the slider
        Hashtable label = new Hashtable();
        label.put(new Integer(min), new JLabel("Hard"));
        label.put(new Integer((max + min) / 2), new JLabel("Moderate"));
        label.put(new Integer(max), new JLabel("Easy"));
        contentPane.add(slider, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setResizable(false);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        
    }
    //Returns the players variable speed. Used in the game class;
    public int getPlayerSpeed(){
        return playerSpeed;
    }
}
