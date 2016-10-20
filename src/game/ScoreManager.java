
package game;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class ScoreManager {
    
    ArrayList<Score> scores;
    
    static final String HIGHSCORE_FILE = "Highscores.dat";
    
    ObjectOutputStream output = null;
    ObjectInputStream input = null;
    
    public ScoreManager(){
        scores = new ArrayList<Score>();
    }
    
    public ArrayList<Score> getScores(){
        loadScoreFile();
        sort();
        return scores;
    }
    public void sort(){
        Comparer comparer = new Comparer();
        Collections.sort(scores, comparer);
    }
    public void addScore(String name, int score){
        loadScoreFile();
        scores.add(new Score(name,score));
        updateScoreFile();
    }
    public void loadScoreFile(){
        try{
            input = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            scores = (ArrayList<Score>) input.readObject();
            }
        catch(FileNotFoundException e)
        {
            System.out.println(e);
        }   
        catch(IOException e){
        System.out.println(e);
    }catch(ClassNotFoundException e){
        System.out.println(e);
    }finally{
            try{
                if(output!= null){
                    output.flush();
                    output.close();
                }
            }catch(IOException e){
                System.out.println(e);
            }
        }
    }
    public void updateScoreFile(){
        try{
            output = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
            output.writeObject(scores);
        }catch(FileNotFoundException e){
        System.out.println(e);
        }catch(IOException e){
        System.out.println(e);
     }finally{
            try{
                if(output != null){
                    output.flush();
                    output.close();
                }
            }catch(IOException e){
                System.out.println(e);
                
            }
        }
    }
    public String getHighscore(){
        String highscore = "";
        int maximum = 10;
        
        ArrayList<Score> scores;
        scores = getScores();
        
        int i = 0;
        int x = scores.size();
        if(x > maximum){
            x = maximum;
        }
        while (i < x){
            highscore += (i + 1) + ".\t" + scores.get(i).getName() + "\t\t" + scores.get(i).getScore()+ "\n";
            i++;
        }
        return highscore;
    }
    
    
}
