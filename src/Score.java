import java.util.ArrayList;

public class Score /** gerer le score d'un etudiant*/{
    private ArrayList<Float> scoreParQuestion;
   private Etudiant e;
   private Epreuve ep;
    public Score(){
        scoreParQuestion = new ArrayList<>();
        e = new Etudiant();
        ep = new Epreuve();
    }
    public Score(Etudiant et,Epreuve epr){
        scoreParQuestion = new ArrayList<>();
        e = et;
        ep = epr;
    }
    public void addPoint(float f){
        scoreParQuestion.add(f);
    }

    public ArrayList<Float> getScoreParQuestion() {
        return scoreParQuestion;
    }
    public void addScore(ArrayList<Float> sc){
        scoreParQuestion.addAll(sc);
    }
}
