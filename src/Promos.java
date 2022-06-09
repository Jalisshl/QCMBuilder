import java.util.ArrayList;

public class Promos /** BD promo*/{
    private ArrayList<Promo> promo = new ArrayList<>();
    public Promos(){
        promo = new ArrayList<>();
    }
    public Promos(ArrayList<Promo> p){
        promo = p;
    }
    public void addPromo(Promo p){
        for (Promo pp: promo
             ) {
            if(pp.getId().toUpperCase().equals(p.getId().toUpperCase())){
                throw new RuntimeException("promo deja existante");
            }
        }
        promo.add(p);
    }
    public Promo getPromoById(String name){
        for (Promo p: promo
             ) {
            if(name.toUpperCase().equals(p.getId().toUpperCase())){
                return p;
            }
        }
        return null;
    }
    public void deleteMatInAllPromo(String name){
        for (Promo p:promo
             ) {
            ArrayList<Matiere> matieresSuivies = new ArrayList<>();
            for (Matiere m: p.getMatieresSuivies()
                 ) {
                if(!m.getName().equalsIgnoreCase(name)){
                    matieresSuivies.add(m);
                }
                p.setMatieresSuivies(matieresSuivies);
            }
        }
    }
    public void addMatToPromo(ArrayList<String> name,Matiere m){

        for (String s :name
             ) {
            this.getPromoById(s).addMatiere(m);
        }

    }
}
