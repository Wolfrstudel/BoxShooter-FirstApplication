package game;

import java.util.Comparator;

public class Comparer implements Comparator<Score> {
    
    public int compare(Score score1, Score score2){
        int scores1 = score1.getScore();
        int scores2 = score2.getScore();
        
        if(scores1 > scores2){
            
            return -1;
        }
        else if (scores1 < scores2){
            return +1;
        }
        else{
            return 0;
        }
        
    }
    
}
