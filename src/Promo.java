import java.util.ArrayList;

public class Promo /** implemente une promo d'etudiant ex ajt etudiant, le supp...*/{
    private ArrayList<Integer> etudiants;
    private ArrayList<Matiere> matieresSuivies;
    private String id;
    public Promo(){
        etudiants = new ArrayList<>();
        matieresSuivies = new ArrayList<>();
        id = "";
    }
    public Promo(ArrayList<Integer> et,ArrayList<Matiere> ms, String i){
        etudiants = et; id = i; matieresSuivies = ms;
    }
    public String getId(){
        return id;
    }

    public ArrayList<Matiere> getMatieresSuivies() {
        return matieresSuivies;
    }
    public String toString(){
        String s = "Promo: "+ id + " "+matieresSuivies.toString();
        return s;
    }
    public void setMatieresSuivies(ArrayList<Matiere> mat){
        this.matieresSuivies = mat;
    }
    public void addMatiere(Matiere m){
        matieresSuivies.add(m);
    }
}
