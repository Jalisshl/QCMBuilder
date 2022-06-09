import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class CreationQCM extends JFrame implements ActionListener /** permet de creer un qcm à partir de rien*/{
    private JTextField nom = new JTextField();
    private JTextField question = new JTextField();
    private JTextField reponses = new JTextField();
    private JComboBox<String> matiere = new JComboBox<String>();
    private JTextField points = new JTextField();
    private JTextField bonneRep = new JTextField();
    private JComboBox<String> diff = new JComboBox<String>();
    private Epreuve ep= new Epreuve();
    private boolean byClient = false;
    private Client client;


    Proffesseur p;
    private JButton validation;

    public CreationQCM(Proffesseur p){

        super("creationQCM");
        this.p = p;
        JComboBox<String> editMater = new JComboBox<String>(p.getMatiereEns().toArray(new String[0]));
        JTextField editQuestion = new JTextField("entrer questions");
        JTextField nomEpreuve = new JTextField("entrer nom epreuve");
        JTextField editRep = new JTextField("entrer reponses séparées par ;;");
        JTextField editBonneRep = new JTextField("entrer le rang des bonnes réponses séparés par ;;(commence à 0)");
        JTextField editPoints = new JTextField("entrer le bareme");
        JComboBox<String> dif = new JComboBox<String>(new String[]{"Facile","Standard","Difficile","Piège"});

        reponses.add(editRep);
        JButton valid = new JButton("Ajouter nouvelle question");
        JButton questionSuivante = new JButton("Valider epreuve");
        questionSuivante.addActionListener(this);
        valid.addActionListener(this);
        questionSuivante.setVisible(false);
        validation = questionSuivante;
        editQuestion.setPreferredSize(new Dimension(120,30));
        editRep.setPreferredSize(new Dimension(400,30));
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        JPanel conteneur = (JPanel)this.getContentPane();
        conteneur.setLayout(new FlowLayout());
        nom = nomEpreuve;
        matiere = editMater;
        question = editQuestion;
        reponses = editRep;
        points = editPoints;
        diff = dif;
        bonneRep = editBonneRep;

        conteneur.add(editMater);
        conteneur.add(nomEpreuve);
        conteneur.add(editQuestion);
        conteneur.add(editRep);
        conteneur.add(editBonneRep);
        conteneur.add(editPoints);

        conteneur.add(dif);

        conteneur.add(valid);
        conteneur.add(questionSuivante);


    }

    public CreationQCM(Proffesseur p,Client c){

        super("creationQCM");
        this.p = p;
        JComboBox<String> editMater = new JComboBox<String>(p.getMatiereEns().toArray(new String[0]));
        JTextField editQuestion = new JTextField("entrer questions");
        JTextField nomEpreuve = new JTextField("entrer nom epreuve");
        JTextField editRep = new JTextField("entrer reponses séparées par ;;");
        JTextField editBonneRep = new JTextField("entrer le rang des bonnes réponses séparés par ;;(commence à 0)");
        JTextField editPoints = new JTextField("entrer le bareme");
        JComboBox<String> dif = new JComboBox<String>(new String[]{"Facile","Standard","Difficile","Piège"});

        reponses.add(editRep);
        JButton valid = new JButton("Ajouter nouvelle question");
        JButton questionSuivante = new JButton("Valider epreuve");
        questionSuivante.addActionListener(this);
        valid.addActionListener(this);
        questionSuivante.setVisible(false);
        validation = questionSuivante;
        editQuestion.setPreferredSize(new Dimension(120,30));
        editRep.setPreferredSize(new Dimension(400,30));
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        JPanel conteneur = (JPanel)this.getContentPane();
        conteneur.setLayout(new FlowLayout());
        nom = nomEpreuve;
        matiere = editMater;
        question = editQuestion;
        reponses = editRep;
        points = editPoints;
        diff = dif;
        bonneRep = editBonneRep;

        conteneur.add(editMater);
        conteneur.add(nomEpreuve);
        conteneur.add(editQuestion);
        conteneur.add(editRep);
        conteneur.add(editBonneRep);
        conteneur.add(editPoints);

        conteneur.add(dif);

        conteneur.add(valid);
        conteneur.add(questionSuivante);
        this.byClient = true;
        this.client = c;

    }

    public ArrayList<String> parse(String qu){
        ArrayList<String> questi = new ArrayList<>();
        questi.addAll(Arrays.asList(qu.split(";;")));
        return questi;
    }
    public ArrayList<Integer> parsrep (String rep){
        ArrayList<Integer> ret = new ArrayList<Integer>();
        for (String s: rep.split(";;")
             ) {
            ret.add(Integer.parseInt(s));
        }
        return ret;
    }
    public String fusrep(ArrayList<Integer> al){
        String s="";
        for (int i: al
             ) {
            s+= i+"%!%";
        }
        return s;
    }

    public String fus(ArrayList<String> s){
        String str = "";
        for (String st: s
             ) {
            str+=st+"%!%";
        }
        return str;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!byClient){
        if(((JButton)e.getSource()).getText().equals("Ajouter nouvelle question")){
            if(nom.isVisible()){
                ep.setName(nom.getText());
            }
            nom.setVisible(false);
            matiere.setVisible(false);
            validation.setVisible(true);
            String difficulty = this.diff.getItemAt(diff.getSelectedIndex());
            String matiere = this.matiere.getItemAt(this.matiere.getSelectedIndex());
            if (difficulty.toUpperCase().equals(Difficulté.Facile.toString().toUpperCase())){
                ep.getListeQ().add(new Question(Difficulté.Facile,Float.parseFloat(points.getText()),question.getText(),parse(reponses.getText()),parsrep(bonneRep.getText())));
            }
            if (difficulty.toUpperCase().equals(Difficulté.Standard.toString().toUpperCase())){
                ep.getListeQ().add(new Question(Difficulté.Standard,Float.parseFloat(points.getText()),question.getText(),parse(reponses.getText()),parsrep(bonneRep.getText())));
            }
            if (difficulty.toUpperCase().equals(Difficulté.Difficile.toString().toUpperCase())){
                ep.getListeQ().add(new Question(Difficulté.Difficile,Float.parseFloat(points.getText()),question.getText(),parse(reponses.getText()),parsrep(bonneRep.getText())));
            }
            if (difficulty.toUpperCase().equals(Difficulté.Piège.toString().toUpperCase())){
                ep.getListeQ().add(new Question(Difficulté.Piège,Float.parseFloat(points.getText()),question.getText(),parse(reponses.getText()),parsrep(bonneRep.getText())));
            }
            ep.incr();

            for (Question q: ep.getListeQ()
                 ) {
                System.out.println(q.toString()); //faire en sorte que les zones de texte se réinitialisent

            }


        }
        if(((JButton)e.getSource()).getText().equals("Valider epreuve")){

            String matier = this.matiere.getItemAt(this.matiere.getSelectedIndex());
            Main.tabledesMatieres.getMatiereByName(matier).addEpreuve(ep);


            for (Question q: Main.tabledesMatieres.getMatiereByName(matier).getEpreuveByName(ep.getName()).getListeQ()
                 ) {
                System.out.println(q.toString());

            }
        }}else{
            String s = "";
            if(((JButton)e.getSource()).getText().equals("Ajouter nouvelle question")){
                if(nom.isVisible()){
                    ep.setName(nom.getText());
                }
                nom.setVisible(false);
                matiere.setVisible(false);
                validation.setVisible(true);
                String difficulty = this.diff.getItemAt(diff.getSelectedIndex());
                String matiere = this.matiere.getItemAt(this.matiere.getSelectedIndex());
                String matier = this.matiere.getItemAt(this.matiere.getSelectedIndex());
                if (difficulty.toUpperCase().equals(Difficulté.Facile.toString().toUpperCase())){




                            s+="NEWQ::"+"FACILE;;"+points.getText()+";;"+question.getText()+";;"+fus(parse(reponses.getText()))+";;"+fusrep(parsrep(bonneRep.getText()))+";;"+matier
                            +";;"+ep.getName()+";;"+ep.getListeQ().size();
                }
                if (difficulty.toUpperCase().equals(Difficulté.Standard.toString().toUpperCase())){




                    s+="NEWQ::"+"STANDARD;;"+points.getText()+";;"+question.getText()+";;"+fus(parse(reponses.getText()))+";;"+fusrep(parsrep(bonneRep.getText()))+";;"+matier
                            +";;"+ep.getName()+";;"+ep.getListeQ().size();
                }
                if (difficulty.toUpperCase().equals(Difficulté.Difficile.toString().toUpperCase())){




                    s+="NEWQ::"+"DIFFICILE;;"+points.getText()+";;"+question.getText()+";;"+fus(parse(reponses.getText()))+";;"+fusrep(parsrep(bonneRep.getText()))+";;"+matier
                            +";;"+ep.getName()+";;"+ep.getListeQ().size();
                }
                if (difficulty.toUpperCase().equals(Difficulté.Piège.toString().toUpperCase())){




                    s+="NEWQ::"+"PIEGE;;"+points.getText()+";;"+question.getText()+";;"+fus(parse(reponses.getText()))+";;"+fusrep(parsrep(bonneRep.getText()))+";;"+matier
                            +";;"+ep.getName()+";;"+ep.getListeQ().size();
                }

                System.out.println(s);
                PrintWriter p = new PrintWriter(client.getOs(),true);
                p.println(s);



        }
            if(((JButton)e.getSource()).getText().equals("Valider epreuve")){

                s+="NEWQ::"+"PIEGE;;"+points.getText()+";;"+question.getText()+";;"+fus(parse(reponses.getText()))+";;"+fusrep(parsrep(bonneRep.getText()))+";;"+this.matiere.getItemAt(this.matiere.getSelectedIndex())
                        +";;"+ep.getName()+";;"+ep.getListeQ().size();
                client.getPw().println(s);
                new ProfessorInterface(p,client);
                this.dispose();

                }

        }







    /* public void actionPerformed(ActionEvent e) {
       String difficulty = this.diff.getItemAt(diff.getSelectedIndex());
       String matiere = this.matiere.getItemAt(this.matiere.getSelectedIndex());
       if (difficulty.toUpperCase().equals(Difficulté.Facile.toString().toUpperCase())){
           Main.database.setQuestion(matiere, new Question(Difficulté.Facile,Float.parseFloat(points.getText()),question.getText(),parse(reponses.getText()),parsrep(bonneRep.getText())));
       }
       System.out.println(Main.database.getQuestion(matiere));
        if (difficulty.toUpperCase().equals(Difficulté.Standard.toString().toUpperCase())){
            Main.database.setQuestion(matiere, new Question(Difficulté.Standard,Float.parseFloat(points.getText()),question.getText(),parse(reponses.getText()),parsrep(bonneRep.getText())));
        }
        System.out.println(Main.database.getQuestion(matiere));
        if (difficulty.toUpperCase().equals(Difficulté.Difficile.toString().toUpperCase())){
            Main.database.setQuestion(matiere, new Question(Difficulté.Difficile,Float.parseFloat(points.getText()),question.getText(),parse(reponses.getText()),parsrep(bonneRep.getText())));
        }
        System.out.println(Main.database.getQuestion(matiere));
        if (difficulty.toUpperCase().equals(Difficulté.Piège.toString().toUpperCase())){
            Main.database.setQuestion(matiere, new Question(Difficulté.Piège,Float.parseFloat(points.getText()),question.getText(),parse(reponses.getText()),parsrep(bonneRep.getText())));
        }
        System.out.println(Main.database.getQuestion(matiere));
    }*/

}}
