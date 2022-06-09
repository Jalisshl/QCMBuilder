import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Question /** implemente question de l'exam*/ {
private Difficulté d;
private float nbPoints;
private String ennonce;
private ArrayList<String> reponses;
private ArrayList<Integer> bonneRep ;
private static int global = 0; //incrementer

public Question (){
    global++;
    double i = Math.random();
    if(i<0.25){
    d = Difficulté.Facile;}
    if(i>=0.25 && i<0.5){
        d = Difficulté.Standard;
    }
    if(i>=0.5 && i<0.75){
        d = Difficulté.Difficile;

    }
    if(i>= 0.75){
        d = Difficulté.Piège;
    }
    nbPoints = 1;
    ennonce = String.valueOf(global);
    reponses = new ArrayList<String>();
    reponses.add(String.valueOf(Math.random()));
    reponses.add(String.valueOf(Math.random()));
    reponses.add(String.valueOf(Math.random()));
    reponses.add(String.valueOf(Math.random()));
    bonneRep = new ArrayList<Integer>();
    bonneRep.add(0);
    bonneRep.add(1);

}
public Question(Difficulté dif, float nb, String ennonce, ArrayList<String> reponses, ArrayList<Integer> bonrep){
    d = dif; nbPoints = nb; this.ennonce = ennonce; this.reponses = reponses; bonneRep = bonrep;
}
/* ####################GETTERS##############"*/
    public Difficulté getD() {
        return d;
    }

    public float getNbPoints() {
        return nbPoints;
    }

    public String getEnnonce() {
        return ennonce;
    }

    public ArrayList<String> getReponses() {
        return reponses;
    }
    /* ######################SETTERS#########*/

    public void setD(Difficulté d) {
        this.d = d;
    }

    public void setEnnonce(String ennonce) {
        this.ennonce = ennonce;
    }

    public void setNbPoints(float nbPoints) {
        this.nbPoints = nbPoints;
    }

    public void setReponses(ArrayList<String> reponses) {
        this.reponses = reponses;
    }
    public String toString(){
        String s = "QUESTION"+global+"\n";
        s+= "Enoncé :" +this.ennonce+"\n";
        s+= "Difficulté :"+this.d.toString()+"\n";
        s+= "NotéSur : "+this.nbPoints+"\n";
        for(int i =0; i< this.reponses.size();i++){
            s+= reponses.get(i)+"\n";
        }
        return s;

    }
    public ArrayList<Integer> getBonneRep(){
        return bonneRep;
    }

}
