package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class highscoreGui {
    
    int frameWidth = 500;
    int frameHeight = 500;
    JFrame frame = new JFrame();;
    JPanel panel;
    JButton button;
    JTextArea text1, text2;
    
    int score;
    String name;
    
    public highscoreGui(int score){
        this.score = score;
    }
    
    public void makeFrame(){
        
        frame.setTitle("Type in your name.");
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        makeMenuBar();

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        text1 = new JTextArea();
        text1.setEditable(false);
        text1.setFont(new Font("Serif",Font.BOLD,20));
        contentPane.add(text1, BorderLayout.CENTER);
        
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.BLACK);
        text2 = new JTextArea(1,20);
        
        //Creates a button that submits your name in the score after you type in your name
        button = new JButton("Submit");
        button.setBackground(Color.white);
        button.addActionListener(new ActionListener(){        
        public void actionPerformed(ActionEvent e){
            try{
                if(text2.getText().trim().length() == 0){
                    throw new NoNameException();
                }
                name = text2.getText();
                text2.setText("");
                text2.setEditable(false);
                button.setEnabled(false);
                makeHighscores();
            }catch(NoNameException e1){
                JOptionPane.showMessageDialog(null,"Must enter a name in the text box");
            }
        }
    });
        
        panel.add(text2);
        panel.add(button);
        contentPane.add(panel,BorderLayout.NORTH);
        frame.setVisible(true);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
    }
    public void makeHighscores(){
        //adds the score to the file if its within 10 scores
        ScoreManager manager = new ScoreManager();
        manager.addScore(name,score);
        text1.append(manager.getHighscore());
    }
    public void makeMenuBar(){
        //Creates a menubar so that you can exit the gui. For some odd reason
        //I wasn't able to use a set default on close operation.
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        file.add(exitItem);
        menubar.add(file);
        frame.setJMenuBar(menubar);
    }
}
