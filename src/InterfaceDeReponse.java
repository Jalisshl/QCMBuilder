import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

public class InterfaceDeReponse extends JFrame implements ActionListener /** l'interface qui permet de repondre aux questions en tant qu'etudiant*/{
    Epreuve ep;
    Etudiant et;
    Question cq;
    Score s;
    private boolean byClient= false;
    private Client client;
    private ArrayList<Float> score;
    private JButton valider;
    private JButton ok;

    Stack<Question> Qrestantes = new Stack<Question>();

    ArrayList<JCheckBox> multiChoice = new ArrayList<>();
    ArrayList<JRadioButton> unichoice = new ArrayList<>();
    public InterfaceDeReponse(Epreuve epreuve, Etudiant etudiant){

        super("interface de reponse");
        s = new Score(etudiant,epreuve);
        Qrestantes.addAll(epreuve.getListeQ());
        ep = epreuve;
        et = etudiant;
        Question currentQ = Qrestantes.pop();
        cq  = currentQ;
        JLabel enonce = new JLabel(currentQ.getEnnonce());
        if(currentQ.getBonneRep().size()>1){
            for (String s: currentQ.getReponses()
                 ) {
                multiChoice.add(new JCheckBox(s));
            }
        }
        else{
            for(String s: currentQ.getReponses()){
                unichoice.add(new JRadioButton(s));
            }
        }
        this.setVisible(true);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       JPanel j = (JPanel) this.getContentPane();
       j.setLayout(new FlowLayout());
       j.add(enonce);
       if(currentQ.getBonneRep().size()>1){
           for (JCheckBox jcb: multiChoice
                ) {
               j.add(jcb);
           }
       }
       else{
           for (JRadioButton jrb: unichoice
                ) {
               j.add(jrb);
           }
       }
       JButton valid = new JButton("Valider");
       valider = valid;
       valid.addActionListener(this);
       j.add(valid);


    }
    public InterfaceDeReponse(Epreuve epreuve, Etudiant etudiant,Client cl){

        super("interface de reponse");
        s = new Score(etudiant,epreuve);
        Qrestantes.addAll(epreuve.getListeQ());
        ep = epreuve;
        et = etudiant;
        score = new ArrayList<>();
        Question currentQ = Qrestantes.pop();
        cq  = currentQ;
        JLabel enonce = new JLabel(currentQ.getEnnonce());
        if(currentQ.getBonneRep().size()>1){
            for (String s: currentQ.getReponses()
            ) {
                multiChoice.add(new JCheckBox(s));
            }
        }
        else{
            for(String s: currentQ.getReponses()){
                unichoice.add(new JRadioButton(s));
            }
        }
        this.setVisible(true);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel j = (JPanel) this.getContentPane();
        j.setLayout(new FlowLayout());
        j.add(enonce);
        if(currentQ.getBonneRep().size()>1){
            for (JCheckBox jcb: multiChoice
            ) {
                j.add(jcb);
            }
        }
        else{
            for (JRadioButton jrb: unichoice
            ) {
                j.add(jrb);
            }
        }
        JButton valid = new JButton("Valider");
        valid.addActionListener(this);
        j.add(valid);
        this.byClient = true;
        this.client = cl;


    }
    public void actualisation(){
        Question currentQ = Qrestantes.pop();
        cq = currentQ;
        multiChoice = new ArrayList<>();
        unichoice = new ArrayList<>();
        JLabel enonce = new JLabel(currentQ.getEnnonce());
        JPanel j = (JPanel) this.getContentPane();
        j.removeAll();
        if(currentQ.getBonneRep().size()>1){
            for (String s: currentQ.getReponses()
            ) {
                multiChoice.add(new JCheckBox(s));
            }
        }
        else{
            for(String s: currentQ.getReponses()){
                unichoice.add(new JRadioButton(s));
            }
        }
        j.add(enonce);
        if(currentQ.getBonneRep().size()>1){
            for (JCheckBox jcb: multiChoice
            ) {
                j.add(jcb);
            }
        }
        else{
            for (JRadioButton jrb: unichoice
            ) {
                j.add(jrb);
            }
        }
        JButton valid = new JButton("Valider");
        valid.addActionListener(this);
        j.add(valid);
        this.revalidate();






    }

    public JPanel correction(Question q){
        //si on donne la bonne reponse
        JPanel j = new JPanel();
        j.setLayout(new FlowLayout(FlowLayout.CENTER));
        boolean b = true;
        if (multiChoice.size()>0){

            for (int i : cq.getBonneRep()
            ) {
                b = b&& multiChoice.get(i).isSelected();
            }
        }
        if (unichoice.size()>0){

            for (int i : cq.getBonneRep()
            ) {
                b = b&& unichoice.get(i).isSelected();
            }
        }
        if(b){
            JLabel jlab = new JLabel("BONNE REPONSE");
            jlab.setForeground(new Color(0,255,0));
            j.add(jlab);
            JButton k = new JButton("ok");
            ok = k;
            k.addActionListener(this);
            j.add(k);
        }
        if(!b){
            JLabel jlab = new JLabel("Mauvaise reponse");
            jlab.setForeground(new Color(255,0,0));
            String s = "les bonnes réponsess etaient : \n";
            for (int i :cq.getBonneRep()
                 ) {
                s+= cq.getReponses().get(i)+"\n";

            }
            JButton k = new JButton("ok");
            k.addActionListener(this);

            ok = k;
            j.add(jlab);
            j.add(new JLabel(s));
            j.add(k);
        }
        return j;
    }
    public JPanel resultat(float score){
        JPanel j = new JPanel();
        j.setLayout(new FlowLayout());
        j.add(new JLabel("votre score est de :"+String.valueOf(score)));
        ok = new JButton("retour");
        ok.addActionListener(this);
        j.add(ok);
        return j;


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(!byClient){
        System.out.println("################"+Qrestantes);

        boolean b = true;
        if (multiChoice.size()>0){

            for (int i : cq.getBonneRep()
                 ) {
                b = b&& multiChoice.get(i).isSelected();
            }
        }
        if (unichoice.size()>0){

            for (int i : cq.getBonneRep()
            ) {
                b = b&& unichoice.get(i).isSelected();
            }
        }
        if(b){
            s.addPoint(cq.getNbPoints());

        }
        else{
            s.addPoint(0);
        }



        if(!(Qrestantes.isEmpty())){actualisation();}
        else{
            ep.addScore(et,s);
            float sum = 0;
            for (float ss: s.getScoreParQuestion()
                 ) {
                sum+=ss;
            }
            System.out.println(sum);
        }}




        if(byClient){
            System.out.println( ((JButton)e.getSource()).getText());
           if (((JButton)e.getSource()).getText().equalsIgnoreCase("ok")){
               System.out.println("ok");
            boolean b = true;
            if (multiChoice.size()>0){

                for (int i : cq.getBonneRep()
                ) {
                    b = b&& multiChoice.get(i).isSelected();
                }
            }
            if (unichoice.size()>0){

                for (int i : cq.getBonneRep()
                ) {
                    b = b&& unichoice.get(i).isSelected();
                }
            }
            if(b){
                score.add(cq.getNbPoints());
            }
            if(!b){
                score.add((float) 0);
            }
            if(!(Qrestantes.isEmpty())){actualisation();}
            else{
                String s = "SCORE::"+ep.getListeQ().get(0).getEnnonce()+"%!%"+score.get(0);
                for(int i = 1; i<ep.getListeQ().size();i++){
                    s+= ";;"+ep.getListeQ().get(i).getEnnonce()+"%!%"+score.get(i);
                }
                s+="§§"+ep.getName();
                s+="##"+et.getId();
                client.getPw().println(s);
                float sum = 0;
                for (float f: score
                     ) {
                    sum+= f;
                }
                this.setContentPane(resultat(sum));
                this.revalidate();
            }
        }
           if(((JButton)e.getSource()).getText().equalsIgnoreCase("valider")){
            System.out.println("valider");
          this.setContentPane(correction(cq));
          this.revalidate();}
        }
        if(((JButton)e.getSource()).getText().equalsIgnoreCase("retour")){
            new EtudiantInterface(et,client);
            this.dispose();
        }






    }
}
