
package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ColorchooserFrame {
    Color color;
    JColorChooser colorChooser;
    JButton button;
    JFrame frame;
    public void makeFrame(){
        frame = new JFrame();
        frame.setSize(500,500);
        JLabel label = new JLabel();
        
        label.setLayout(new BorderLayout());
        
        colorChooser = new JColorChooser();
        colorChooser.setBackground(Color.CYAN);
        colorChooser.getSelectionModel().addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
               color = colorChooser.getColor();
            }
        });
        
        label.add(colorChooser, BorderLayout.CENTER);
        
        button = new JButton("Submit Color");
        button.setBackground(Color.CYAN);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
            }
        });
        label.add(button,BorderLayout.SOUTH);
        
        frame.add(label);
        frame.setVisible(true);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
    }
    public Color getColor(){
        return color;
    }
    
}
