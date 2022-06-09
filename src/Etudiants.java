import java.util.Hashtable;

public class Etudiants /** BD des étudiants*/{
    private Hashtable<Integer,String> studenttoPromo;
    public Etudiants(){
        studenttoPromo = new Hashtable<>();
    }
    public Etudiants(Hashtable<Integer,String> prom){
        studenttoPromo = prom;
    }
    public void addEtudiant(Etudiant e){
        studenttoPromo.put(e.getId(),e.getPromo());

    }
    public String getPromo(int id){
        return studenttoPromo.get(id);
    }

}
