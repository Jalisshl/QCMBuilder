import java.util.ArrayList;

public class Matiere /** implemente les matieres*/ {
    private String name;
    private ArrayList<Epreuve> epreuves;
    private ArrayList<Question> questions;
    public Matiere(String n , ArrayList<Epreuve> ep){
        name = n ; epreuves = ep;questions = new ArrayList<Question>();
        for (Epreuve e: epreuves
             ) {
            questions.addAll(e.getListeQ());
        }

    }
    public String getName(){
        return name;
    }
    public void addEpreuve(Epreuve e){
        for (Epreuve ee: epreuves
             ) {
            if(e.getName().equals(ee.getName())){
                throw new RuntimeException("epreuve deja existante veuillez la completer");
            }


        }
        epreuves.add(e);
        questions.addAll(e.getListeQ());

    }
    public Epreuve getEpreuveByName(String name){
        for (Epreuve e:epreuves
             ) {
            System.out.println(e.getName());
            if(e.getName().toUpperCase().equals(name.toUpperCase())){
                return e;
            }
        }
        return null;
    }
    public void addQuestionOutOfEpreuve(Question q){
        questions.add(q);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
    public ArrayList<Epreuve> getEpreuves(){
        return epreuves;
    }
    public String toString(){
        return name;
    }
    public Question getQuestionByEnnoncé(String enonce){
        for (Question q: questions
        ) {
            if(q.getEnnonce().equalsIgnoreCase(enonce)){
                return q;
            }
        }
        return null;
    }
}
