import java.util.ArrayList;
import java.util.Collection;
import java.util.*;
import java.util.GregorianCalendar;

public class Epreuve /** permet de renseigner les infos sur une épreuve*/ {
    private int salle;
    private String date;
    private String horraire;
    private int nbEtudiant;
    private String[] nomSurv;
    private int nbQuestion;
    private float pourcentQfacile;
    private float pourcentQstand;
    private float pourcentQdiff;
    private float pourcentQpiege;
    private ArrayList<Question> listeQ;
    private int id;
    private String name;
    private HashMap<Integer,Score> score; //hashtable qui contient une la clé qui est lid de l'etud et sa valeur est le score



    public Epreuve(int sa, String da,String hor, int nbe,String[] nomS,
                   int nbQ,float pqf,float pqs, float pqd,float pqp,int id,ArrayList<Question> lq,String na) {


        this.id = id;
        salle = sa;
        date = da;
        horraire = hor;
        nbEtudiant = nbe;
        nomSurv = nomS;
        nbQuestion = nbQ;
        pourcentQfacile = pqf;
        pourcentQstand = pqs;
        pourcentQdiff = pqd;
        pourcentQpiege = pqp;
        this.name = na;
        score = new HashMap<>();
        listeQ = new ArrayList<Question>(nbQuestion);
        Collections.shuffle(lq);

        int apqf = 0;
        int apqs = 0;
        int apqd = 0;
        int apqp = 0;
        int dejaadd = 0;
        int explorer = 0;
        ArrayList<Question> facile = new ArrayList<>();
        ArrayList<Question> standard = new ArrayList<>();
        ArrayList<Question> difficile = new ArrayList<>();
        ArrayList<Question> piege = new ArrayList<>();
        for (Question q : lq
        ) {
            if (q.getD().equals( Difficulté.Facile)) {
                facile.add(q);
            }
            if (q.getD().equals( Difficulté.Standard)) {
                standard.add(q);
            }
            if (q.getD().equals(Difficulté.Difficile)) {
                difficile.add(q);
            }
            if (q.getD().equals(Difficulté.Piège)) {
                piege.add(q);
            }

        }
        float nbfacile =  ((pqf*nbQuestion));
        if(facile.size()<nbfacile){
            throw new RuntimeException("revoir les pourcentages: il n'y a pas assez de questions faciles");
        }
        float nbDifficile = ((pqd*nbQuestion));
        if(difficile.size()<nbDifficile){
            throw new RuntimeException("revoir les pourcentages: il n'y a pas assez de questions difficiles");
        }
        float nbPiege =  ((pqp*nbQuestion));
        if(piege.size()<nbPiege){
            throw new RuntimeException("revoir les pourcentages: il n'y a pas assez de questions piege");
        }
        System.out.println(pqf*nbQuestion+"  "+ piege.size());

        int temp=0;
        for(int i = 0; i< nbfacile;i++){
            System.out.println(nbfacile);
            listeQ.add(facile.get(i));
            temp++;

        }
        for(int i = 0; i<nbDifficile;i++){
            listeQ.add(difficile.get(i));
            temp++;
        }
        for(int i = 0; i< nbPiege;i++){
            listeQ.add(piege.get(i));
            temp++;
        }
        while(temp<nbQuestion){
            int i = 0;
            listeQ.add(standard.get(i));
            i++;
            temp++;
        }
        Collections.shuffle(listeQ);

    }
    public Epreuve(){
        this.id = 0;
        salle = 0;
        date = "";
        horraire = "";
        nbEtudiant = 0;
        nomSurv = new String[]{};
        nbQuestion = 0;
        pourcentQfacile = 0;
        pourcentQstand = 0;
        pourcentQdiff = 0;
        pourcentQpiege = 0;
        this.name = "";
        listeQ = new ArrayList<Question>(nbQuestion);
    }
    public void incr(){
        nbQuestion++;
    }

    public ArrayList<Question> getListeQ() {
        return listeQ;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public void addScore(Etudiant e,Score s){
        score.put(e.getId(),s);
    }
    public void addQuestion(Question q){
        listeQ.add(q);
        nbQuestion++;
    }
    public Question getQuestionByEnnoncé(String enonce){
        for (Question q: listeQ
             ) {
            if(q.getEnnonce().equalsIgnoreCase(enonce)){
                return q;
            }
        }
        return null;
    }
public Epreuve(int nbQuestion,float pourcentQfacile, float pourcentQstand, float pourcentQdiff,float pourcentQpiege,ArrayList<Question> quest,String name){

        this.salle = 0;
        this.date = "";
        this.horraire = "";
        this.nomSurv = new String[]{};
        this.nbQuestion = nbQuestion;
        this.pourcentQfacile = pourcentQfacile;
        this.pourcentQstand = pourcentQstand;
        this.pourcentQdiff = pourcentQdiff;
        this.pourcentQpiege = pourcentQpiege;
        this.id = 0;
        this.listeQ = new ArrayList<>();
        this.name = name;


    score = new HashMap<>();
    listeQ = new ArrayList<Question>(nbQuestion);
    Collections.shuffle(quest);

    int apqf = 0;
    int apqs = 0;
    int apqd = 0;
    int apqp = 0;
    int dejaadd = 0;
    int explorer = 0;
    ArrayList<Question> facile = new ArrayList<>();
    ArrayList<Question> standard = new ArrayList<>();
    ArrayList<Question> difficile = new ArrayList<>();
    ArrayList<Question> piege = new ArrayList<>();
    for (Question q : quest
    ) {
        if (q.getD().equals( Difficulté.Facile)) {
            facile.add(q);
        }
        if (q.getD().equals( Difficulté.Standard)) {
            standard.add(q);
        }
        if (q.getD().equals(Difficulté.Difficile)) {
            difficile.add(q);
        }
        if (q.getD().equals(Difficulté.Piège)) {
            piege.add(q);
        }

    }
    float nbfacile =  ((pourcentQfacile*nbQuestion));
    if(facile.size()<nbfacile){
        throw new RuntimeException("revoir les pourcentages: il n'y a pas assez de questions faciles");
    }
    float nbDifficile = ((pourcentQdiff*nbQuestion));
    if(difficile.size()<nbDifficile){
        throw new RuntimeException("revoir les pourcentages: il n'y a pas assez de questions difficiles");
    }
    float nbPiege =  ((pourcentQpiege*nbQuestion));
    if(piege.size()<nbPiege){
        throw new RuntimeException("revoir les pourcentages: il n'y a pas assez de questions piege");
    }
    System.out.println(pourcentQpiege*nbQuestion+"  "+ piege.size());

    int temp=0;
    for(int i = 0; i< nbfacile;i++){
        System.out.println(nbfacile);
        listeQ.add(facile.get(i));
        temp++;

    }
    for(int i = 0; i<nbDifficile;i++){
        listeQ.add(difficile.get(i));
        temp++;
    }
    for(int i = 0; i< nbPiege;i++){
        listeQ.add(piege.get(i));
        temp++;
    }
    while(temp<nbQuestion){
        int i = 0;
        listeQ.add(standard.get(i));
        i++;
        temp++;
    }
    Collections.shuffle(listeQ);


}
public void setListeQ(ArrayList<Question> q){
        listeQ = q;
}


public Score getScoreByid(int id){
        try{
   return score.get(id);}
        catch(Exception e){
            return null;
        }
}
public HashMap<Integer,Score> getScore(){
        return this.score;
}

}
