import java.util.ArrayList;

public class Etudiant extends Humain/** implement les étudiants en héritant de la classe humain*/{

    private String promo;
    public Etudiant(){
        super();

    }
    public Etudiant(String n, String p , String mdp,String promo){
        super(n,p,mdp,Role.ETUDIANT);
        this.promo = promo;
    }
    public String getPromo(){
        return promo;
    }
    public String toString(){
        return this.getPrenom()+"  "+getNom()+"  "+getPromo();
    }
}
