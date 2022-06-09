import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main /** utilisée pour faire les tests*/ {


    public static int nbCreer = 0;
    public static Users users;//liste des utilisateurs
    public static Datamat tabledesMatieres = new Datamat();//liste des questions par matieres
    public static Promos tabledesPromos = new Promos();
    public static void main(String[] agrs) {
        users = new Users(new ArrayList<>());

        Matiere es = new Matiere("Espagnol", new ArrayList<Epreuve>());
        Matiere hi = new Matiere("Histoire", new ArrayList<Epreuve>());
        tabledesMatieres.addMat(es);
        tabledesMatieres.addMat(hi);
        ArrayList<Question> quest = new ArrayList<>(5000);
        for(int i = 0; i<5000; i++){
            quest.add(new Question());
        }
        Epreuve ep = new Epreuve(0,"","",0,new String[]{},5, (float) 0.3, (float) 0.2, (float) 0.3, (float) 0.2,0,quest,"EXAMEN");
        tabledesMatieres.getMatiereByName("Histoire").addEpreuve(ep);
        ArrayList<Matiere> mat = new ArrayList<Matiere>();
        mat.add(tabledesMatieres.getMatiereByName("Histoire"));
        Promo promo = new Promo(new ArrayList<>(),mat,"L3Miage");
        Etudiant etudiant = new Etudiant("Nom","Prenom","0","L3Miage");

        users.addUser(etudiant);

        tabledesPromos.addPromo(promo);
        new LogInterface();

    }

}
