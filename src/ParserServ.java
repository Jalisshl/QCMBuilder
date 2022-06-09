import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class ParserServ {
    /** permet de comprendre les requetes que le client envoie au serveur*/

    public String  parseLogServ(String request){
        String[] firstCut = request.split("::");
        String keyWord = firstCut[0];
        if(keyWord.equalsIgnoreCase("LOG")){
            String[] secndCut = firstCut[1].split(";;");
            int hash = Integer.parseInt(secndCut[1]);
            int ident =Integer.parseInt(secndCut[0]);
            Humain h = Serveur.users.getUserById(ident);
            int thash = h.getHashpass();
            if(hash == thash){
                if(h.getR()==Role.PROF){

                    String sss= "";
                    for(String ss: Serveur.tabledesProfs.getMatiereById(h.getId())){
                        sss+=";;"+ss;
                    }
                    System.out.println("mon nom est"+h.getNom());

                    String s =  "PROF::"+h.getNom()+";;"+h.getPrenom()+";;"+h.getId()+sss;
                    return s;
                }
                if(h.getR()==Role.ETUDIANT){
                    String sss = "ETUDIANT::"+h.getNom()+";;"+h.getPrenom()+";;"+Serveur.tabledesEtudiants.getPromo(h.getId())  ;
                    System.out.println(h.toString());
                    return sss;
                }
                if(h.getR()==Role.ADMIN){
                    String sss = "ADMIN::"+h.getNom()+";;"+h.getPrenom();
                    System.out.println(sss);
                    return sss;
                }


            }
        }
        return "mauvais id";
    }
    public String parseNomExam(String s){
        System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjj");
        String[] fc = s.split("::");
        if(fc[0].equalsIgnoreCase("NOMEXAM")){
            String [] sc = fc[1].split(";;");

            String prom = sc[0];
            int id = Integer.parseInt(sc[1]);
             ArrayList<Matiere> mat = Serveur.tabledesPromos.getPromoById(prom).getMatieresSuivies();
             ArrayList<String> ret = new ArrayList<>();
             String toret = "NOMEXAM::";
            for (Matiere m: mat
                 ) {
                for (Epreuve e: m.getEpreuves()
                     ) {
                    if(e.getScoreByid(id)==null){
                    ret.add(e.getName()+"%!%"+m.getName());}
                }
            }
            for (String ss: ret
                 ) {
                toret+=ss+";;";
            }
            return toret;
        }
        return null;

    }
    public String parseNewQ(String s){
        String[] fc = s.split("::");
        Difficulté d = Difficulté.Standard;
        float points;
        if(fc[0].equalsIgnoreCase("NEWQ")){
            String[] sc = fc[1].split(";;");
            if(sc[0].equalsIgnoreCase("FACILE")){
                d = Difficulté.Facile;
            }
            if(sc[0].equalsIgnoreCase("STANDARD")){
                d = Difficulté.Standard;
            }
            if(sc[0].equalsIgnoreCase("DIFFICILE")){
                d = Difficulté.Difficile;
            }
            if(sc[0].equalsIgnoreCase("PIEGE")){
                d = Difficulté.Piège;
            }
            points = Float.parseFloat(sc[1]);
            String question = sc[2];
            ArrayList<String> rep = new ArrayList<>();
            ArrayList<Integer> brep = new ArrayList<>();
            String matiere = sc[5];
            for (String s1: sc[3].split("%!%")
                 ) {
                rep.add(s1);
            }
            for (String s2:sc[4].split("%!%")
                 ) {
                brep.add(Integer.parseInt(s2));
            }
            String name = sc[6];
            int nbq = Integer.parseInt(sc[7]);
            if(Serveur.tabledesMatieres.getMatiereByName(matiere).getEpreuveByName(name)== null){
                Serveur.tabledesMatieres.getMatiereByName(matiere).addEpreuve(new Epreuve(0,"","",0,new String[]{},nbq,0,0,0,0,0,new ArrayList<>(),name));
            }
            Serveur.tabledesMatieres.getMatiereByName(matiere).getEpreuveByName(name).addQuestion(new Question(d,points,question,rep,brep));
            for (Question q: Serveur.tabledesMatieres.getMatiereByName(matiere).getEpreuveByName(name).getListeQ()
                 ) {
             System.out.println(q);
            }


        }
        return "";
    }

    public void parseNewe(String s){
        String[] fc = s.split("::");
        if(fc[0].equalsIgnoreCase("NEWE")){
            String[] sc = fc[1].split(";;");
           Epreuve ep= new Epreuve(Integer.parseInt(sc[0]),Float.parseFloat(sc[1]),Float.parseFloat(sc[2]),Float.parseFloat(sc[3]),Float.parseFloat(sc[4]),Serveur.tabledesMatieres.getMatiereByName(sc[6]).getQuestions(),sc[5]);
           Serveur.tabledesMatieres.getMatiereByName(sc[6]).addEpreuve(ep);
            for (Question q: Serveur.tabledesMatieres.getMatiereByName(sc[6]).getEpreuveByName(sc[5]).getListeQ()
                 ) {
                System.out.println(q.toString());

            }
        }
    }
    public String parseGETEPREUVE(String s){
        String[] fc = s.split("::");
        if(fc[0].equalsIgnoreCase("GETEPREUVE")){
            String[] sc = fc[1].split(";;");
            String nom = sc[0];
            String matiere = sc[1];

           Epreuve e = Serveur.tabledesMatieres.getMatiereByName(matiere).getEpreuveByName(nom);
           String ret = "EPREUVE::";
            for (Question q: e.getListeQ()
                 ) {
                ret = ret+q.getEnnonce();
                for (String str: q.getReponses()
                     ) {
                    ret+= "%!%"+str;
                }
                for(Integer i : q.getBonneRep()){
                    ret+= "##"+i;
                }
                ret+= "§§"+q.getNbPoints();
                ret+=";;";

            }
            return ret;
        }
        return null;

    }
    public void parseSCORE(String s){
        String[] fc = s.split("::");
        if(fc[0].equalsIgnoreCase("SCORE")){
            System.out.println("fc lqmjdflqjsdf"+ fc[0]);
            String [] sc = fc[1].split("##");//indice 1 le nom de l'etudiant
            String [] tc = sc[0].split("§§");//l'indice 1 le nom de l'epreuve
            String[] qc = tc[0].split(";;");//contiens les nom des questions + le score
            Etudiant e = (Etudiant) Serveur.users.getUserbyId(Integer.parseInt(sc[1]));
            Epreuve ep = Serveur.tabledesMatieres.getEpreuveByName(tc[1]);
            ArrayList<Float> score= new ArrayList<>();
            for (String str: qc
                 ) {
                System.out.println(str);
                String[] c5 = str.split("%!%");
                String ennonce = c5[0];
                float barem = Float.parseFloat(c5[1]);
                score.add(barem);

            }
            Score score1 = new Score(e,ep);
            score1.addScore(score);
            ep.addScore(e,score1);
            float sum = 0;
            for (float f: score
                 ) {
                sum+= f;
            }

            System.out.println("le score est"+ sum);





            }
        }
        public String parseNEWUS(String s){
        String[] fc = s.split("::");
        if(fc[0].equalsIgnoreCase("NEWUS")){
            String[] sc =  fc[1].split(";;");
            String prenom = sc[0];
            String nom = sc[1];
            String mdp = sc[2];
            String deter = sc[3];
            if(deter.equalsIgnoreCase("ETUDIANT")){
                Etudiant et = new Etudiant(nom,prenom,mdp,sc[4]);
                Serveur.users.addUser(et);
                Serveur.tabledesEtudiants.addEtudiant(et);
                return "NEWUS::"+et.getId()+";;"+mdp;
            }
            if(deter.equalsIgnoreCase("PROF")){
                String[] maten = sc[4].split("%!%");
                ArrayList<String> matens = new ArrayList<>();
                matens.addAll(List.of(maten));
                Proffesseur p = new Proffesseur(nom,prenom,mdp,matens);
                Serveur.users.addUser(p);
                Serveur.tabledesProfs.addProf(p);
                return "NEWUS::"+p.getId()+";;"+mdp;

            }
            if(deter.equalsIgnoreCase("ADMIN")){
                Admin ad = new Admin(nom,prenom,mdp);
                Serveur.users.addUser(ad);
                return "NEWUS::"+ad.getId()+";;"+mdp;



            }






        }
        return null;
        }
        public String parseRECUPMAT(String s){
        if(s.equalsIgnoreCase("RECUPMAT")){
            String sp = "RECUPMAT::";
            for (Matiere m: Serveur.tabledesMatieres.getMatieres()
                 ) {
                sp+= m.getName()+";;";
            }
            return sp;
        }
        return null;
        }
        public void parseDELMAT(String s){
        String[] fc = s.split("::");
        if(fc[0].equalsIgnoreCase("DELMAT")){
            Serveur.tabledesMatieres.deleteMatierebyID(fc[1]);
            Serveur.tabledesPromos.deleteMatInAllPromo(fc[1]);
            Serveur.tabledesProfs.deleteMatiereById(fc[1]);

        }
        }
        public void parseADDMAT(String s){
        String[] fc = s.split("::");
        if(fc[0].equalsIgnoreCase("ADDMAT")){
            String[] sc = fc[1].split(";;");
            String nommat = sc[0];
            String nomProf = sc[1];
            ArrayList<String> promo = new ArrayList<>();
            promo.addAll(List.of(sc[2].split("%")));

            Serveur.tabledesMatieres.addMat(new Matiere(nommat,new ArrayList<>()));
            Serveur.tabledesPromos.addMatToPromo(promo,new Matiere(nommat,new ArrayList<>()));
            Serveur.tabledesProfs.AddMatiere(Integer.parseInt(nomProf),new Matiere(nommat,new ArrayList<>()));

        }
        }
        public String parseGETALLEP(String s){
        String[] fc = s.split("::");
        if(fc[0].equalsIgnoreCase("GETALLEP")){
            String[] matieres = fc[1].split(";;");
            String rep = "GETALLEP::";
            for (String ss:matieres
                 ) {

                for (Epreuve e: Serveur.tabledesMatieres.getMatiereByName(ss).getEpreuves()
                     ) {
                    rep += e.getName()+";;";
                }

            }
            return rep;
        }
        return null;
        }

        public String parseRESULT(String s){
        String[] fc = s.split("::");
        if(fc[0].equalsIgnoreCase("RESULT")){
            String ret = "RESULT::";
           HashMap<Integer,Score> score =  Serveur.tabledesMatieres.getEpreuveByName(fc[1]).getScore();
            for (Integer k: score.keySet()
                 ) {
                float sum = 0;
                for (Float f:  score.get(k).getScoreParQuestion()
                     ) {
                    sum+=f;
                }
                ret+=k+"%!%"+sum+";;";
            }
            return ret;
        }
      return null;
        }
    }



