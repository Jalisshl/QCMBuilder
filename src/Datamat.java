import java.util.ArrayList;

public class Datamat /** bd qui contient matieres et quest*/{
   private ArrayList<Matiere> matieres;
    public Datamat(){
        matieres = new ArrayList<Matiere>();
    }
    public Datamat(ArrayList<Matiere> m){
        matieres = m;

    }
    public void addMat(Matiere m){
        for (Matiere mm: matieres
             ) {
            if(mm.getName().equals(m.getName())){
                throw new RuntimeException("matiere deja présente");
            }
        }
        matieres.add(m);
    }
    public void addMat(ArrayList<Matiere> m){
        for( Matiere mm: m
             ) {
            this.addMat(mm);
        }
    }
    public Matiere getMatiereByName(String name){
        for (Matiere m: matieres
             ) {
            if(m.getName().toUpperCase().equals(name.toUpperCase())){
                return m;
            }

        }
        return null;
    }

    public ArrayList<Matiere> getMatieres() {
        return matieres;
    }
    public Epreuve getEpreuveByName(String na){
        for (Matiere m: matieres
             ) {
            for (Epreuve e: m.getEpreuves()
                 ) {
                if (e.getName().equalsIgnoreCase(na)){
                    return e;
                }
            }
        }
        return null;
    }
    public void deleteMatierebyID(String s){
        ArrayList<Matiere> cop = new ArrayList<>();
        for (Matiere m: matieres
             ) {
            if(!m.getName().equalsIgnoreCase(s)){
                cop.add(m);
            }
        }
        matieres = cop;
    }
}
