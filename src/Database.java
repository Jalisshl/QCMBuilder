import java.util.ArrayList;
import java.util.Collection;

public class Database /** table des matieres*/ {
    private ArrayList<String> matiere;
    private ArrayList<ArrayList<Question>> data;
    public Database(ArrayList<String> mat) {
        matiere = mat;
        data = new ArrayList<>(matiere.size());
        for (String m:matiere
             ) {
            data.add(new ArrayList<Question>());
        }
    }
    public ArrayList<Question> getQuestion(String matiere){
        ArrayList<Question> qmat= new ArrayList<Question>();
        for(int i = 0; i<this.matiere.size();i++){

            if (matiere.toUpperCase().equals(this.matiere.get(i).toUpperCase())){
                qmat.addAll(data.get(i));
                return qmat;
            }
        }
        throw new RuntimeException("matiere inconnue");
    }
    public void setQuestion(String matiere,Question q){
        for(int i = 0; i<this.matiere.size();i++){
            if (matiere.toUpperCase().equals(this.matiere.get(i).toUpperCase())){
                data.get(i).add(q);
            }
        }
    }
    public void setQuestion(String matiere,ArrayList<Question> newQuest){
        for(int i = 0; i<this.matiere.size();i++){
            if (matiere.toUpperCase().equals(this.matiere.get(i).toUpperCase())){
                data.get(i).addAll(newQuest);
            }
            }
    }
    public void addMatiere(String matiere){
        for (String om: this.matiere
             ) {
            if(om.toUpperCase().equals(matiere.toUpperCase())){
                throw new RuntimeException("matiere deja ajoutée");
            }

        }
        this.matiere.add(matiere);
        this.data.add(new ArrayList<Question>());
    }

}
