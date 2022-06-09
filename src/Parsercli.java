import java.util.ArrayList;
import java.util.List;

public class Parsercli /** permet de comprendre les requetes que le serveur envoie au client*/{
public void parserLogcli(String s,Client c){
    String[] first = s.split("::");
    if(first[0].equals("PROF")){
        String[] secnd = first[1].split(";;");
        String nom = secnd[0];
        String prenom = secnd[1];
        String id = secnd[2];
        ArrayList<String> matiere = new ArrayList<>();
        for(int i = 3;i< secnd.length;i++){
            matiere.add(secnd[i]);
        }
        new ProfessorInterface(new Proffesseur(nom,prenom,id,matiere),c);

    }
    if(first[0].equals("ETUDIANT")){
        String[] secnd = first[1].split(";;");
        String nom = secnd[0];
        String prenom = secnd[1];
        String promo = secnd[2];
        new EtudiantInterface(new Etudiant(nom,prenom,"0",promo),c);
    }
    if(first[0].equalsIgnoreCase("ADMIN")){
        String[] secnd = first[1].split(";;");
        String nom = secnd[0];
        String prenom = secnd[1];
        new AdminInterface(c);

    }
    }
    public ArrayList<String> parseNomExam(String s){
    ArrayList<String>toret = new ArrayList<>();
    String[] fc = s.split("::");
    if(fc[0].equalsIgnoreCase("NOMEXAM")){
        if(fc.length >1 ){
        toret.addAll(List.of(fc[1].split(";;")));}
    }
    return toret;
    }
    public ArrayList<Question> parseEPREUVE(String s){

    ArrayList<String> toret = new ArrayList<>();
    String[] fc = s.split("::");
    if(fc[0].equalsIgnoreCase("EPREUVE")){
        String[] questionsrep = fc[1].split(";;");//questions avec rep et brep
        ArrayList<Question> ret = new ArrayList<>();
        for (String sss: questionsrep
             ) {
            Difficulté diff = Difficulté.Standard;
            String[] cut = sss.split("§§");//indice 0 tout indice 1 le nombre de pts
            String[] cut2 = cut[0].split("##");//l'indice 0 tout l'indice autre les bonne rep
            String[]cut3 = cut2[0].split("%!%");//l'indice 0 l'énoncé l'indice autr tout le reste
            ArrayList<String> bonnerep = new ArrayList<>();
            ArrayList<Integer> indBonnerep = new ArrayList<>();
            for(int i = 1; i< cut3.length;i++){
                bonnerep.add(cut3[i]);
            }
            for(int i = 1; i< cut2.length;i++){
                indBonnerep.add(Integer.parseInt(cut2[i]));
            }
            Question q = new Question(diff,Float.parseFloat(cut[1]),cut3[0],bonnerep,indBonnerep);
            ret.add(q);

        }

        return ret;
    }
    return null;
    }
}
