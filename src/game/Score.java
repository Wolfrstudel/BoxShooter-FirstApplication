package game;

import java.io.Serializable;


public class Score implements Serializable{
    private int score;
    private String name;
    
    public Score(String name, int score){
        this.name = name;
        this.score = score;
    }
    
    public int getScore(){
        return score;
    }
    
    public String getName(){
        return name;
    }
    
}
