import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Serveur extends Thread /** reserve le port, gere les threads, ecoute les connections*/ {
    private int nbClient = 0 ;
    public static Datamat tabledesMatieres = new Datamat();
    public static Promos tabledesPromos = new Promos();
    public static Users users;//liste des utilisateurs
    public static TabledesProfs tabledesProfs = new TabledesProfs();
    public static Etudiants tabledesEtudiants = new Etudiants();


    public void run(){
        try {
            ServerSocket ss = new ServerSocket(5555);
            while(true){
                Socket s = ss.accept();
                new Service(s,nbClient).start();
                nbClient++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void main(String[] args) throws IOException, InterruptedException {
        users = new Users(new ArrayList<>());

        Matiere es = new Matiere("Espagnol", new ArrayList<Epreuve>());
        Matiere hi = new Matiere("Histoire", new ArrayList<Epreuve>());
        tabledesMatieres.addMat(es);
        tabledesMatieres.addMat(hi);
        ArrayList<Question> quest = new ArrayList<>(5000);
        for(int i = 0; i<5000; i++){
            quest.add(new Question());
            tabledesMatieres.getMatiereByName("Histoire").addQuestionOutOfEpreuve(new Question());
        }

        Epreuve ep = new Epreuve(0,"","",0,new String[]{},3, (float) 0.3, (float) 0.2, (float) 0.3, (float) 0.2,0,quest,"EXAMEN");
        tabledesMatieres.getMatiereByName("Histoire").addEpreuve(ep);
        ArrayList<Matiere> mat = new ArrayList<Matiere>();
        mat.add(tabledesMatieres.getMatiereByName("Histoire"));
        Promo promo = new Promo(new ArrayList<>(),mat,"L3Miage");
        Etudiant etudiant = new Etudiant("Nom","Prenom","0","L3Miage");
        tabledesEtudiants.addEtudiant(etudiant);
        ArrayList<String> matt = new ArrayList<>();
        matt.add("Histoire");
        Proffesseur p = new Proffesseur("Nom","Prenom","0",matt);
        Admin a = new Admin("moi","lui","0");

        tabledesProfs.addProf(p);
        users.addUser(p);


        users.addUser(etudiant);
        users.addUser(a);

        tabledesPromos.addPromo(promo);

        Serveur s = new Serveur();
        s.start();
    }

}
