import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreationByDB extends JFrame implements ActionListener /** permet de creer une BD à partir de rien*/ {
    Epreuve ep = new Epreuve();
    JTextField pourcentFa;
    JTextField pourcentSt;
    JTextField pourcentDi;
    JTextField pourcentPi;
    JTextField nbquesti;
    JTextField nomEpreuve;
    JComboBox<String> matiere;
    boolean byClient = false;
    Client c;
    public CreationByDB(Proffesseur p ){
        super("creation by database");
        this.setVisible(true);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JTextField nE = new JTextField("nom de l'epreuve");
        JTextField pourcentF = new JTextField("pourcentage questions faciles");
        JTextField pourcentS = new JTextField("pourcentage questions standard");
        JTextField pourcentD = new JTextField("pourcentage questions difficiles");
        JTextField pourcentP = new JTextField("pourcentage questions piege");
        JTextField nbQuest = new JTextField("nombre de questions");

        JComboBox matiere = new JComboBox(p.getMatiereEns().toArray(new String[0]));
        JButton valider = new JButton("valider");

        JPanel j = (JPanel) this.getContentPane();
        j.setLayout(new FlowLayout());
        j.add( nE);
        j.add(pourcentF);
        j.add(pourcentS);
        j.add(pourcentD);
        j.add(pourcentP);
        j.add(nbQuest);
        j.add(matiere);
        j.add(valider);

        valider.addActionListener(this);
        nomEpreuve = nE;
        pourcentFa = pourcentF;
        pourcentSt = pourcentS;
        pourcentDi = pourcentD;
        pourcentPi = pourcentP;
        nbquesti = nbQuest;
        this.matiere = matiere;
    }
    public CreationByDB(Proffesseur p,Client c){
        super("creation by database");
        this.setVisible(true);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JTextField nE = new JTextField("nom de l'epreuve");
        JTextField pourcentF = new JTextField("pourcentage questions faciles");
        JTextField pourcentS = new JTextField("pourcentage questions standard");
        JTextField pourcentD = new JTextField("pourcentage questions difficiles");
        JTextField pourcentP = new JTextField("pourcentage questions piege");
        JTextField nbQuest = new JTextField("nombre de questions");

        JComboBox matiere = new JComboBox(p.getMatiereEns().toArray(new String[0]));
        JButton valider = new JButton("valider");

        JPanel j = (JPanel) this.getContentPane();
        j.setLayout(new FlowLayout());
        j.add( nE);
        j.add(pourcentF);
        j.add(pourcentS);
        j.add(pourcentD);
        j.add(pourcentP);
        j.add(nbQuest);
        j.add(matiere);
        j.add(valider);

        valider.addActionListener(this);
        nomEpreuve = nE;
        pourcentFa = pourcentF;
        pourcentSt = pourcentS;
        pourcentDi = pourcentD;
        pourcentPi = pourcentP;
        nbquesti = nbQuest;
        this.matiere = matiere;
        this.byClient = true;
        this.c = c;
    }
    public String fus(ArrayList<Question> list){
        String str = "";
        for (Question s: list
             ) {
            str+=s.getEnnonce()+"%!%";
        }
        return str;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!byClient){
        ep = new Epreuve(0,"","",0,new String[]{},Integer.parseInt(nbquesti.getText()),Float.parseFloat(pourcentFa.getText()),Float.parseFloat(pourcentSt.getText()),Float.parseFloat(pourcentDi.getText()),Float.parseFloat(pourcentPi.getText()),0,Main.tabledesMatieres.getMatiereByName(matiere.getItemAt(matiere.getSelectedIndex())).getQuestions(),nomEpreuve.getText());
        Main.tabledesMatieres.getMatiereByName(matiere.getItemAt(matiere.getSelectedIndex())).addEpreuve(ep);
        for (Question q:Main.tabledesMatieres.getMatiereByName(matiere.getItemAt(matiere.getSelectedIndex())).getEpreuveByName(ep.getName()).getListeQ()
             ) {
            System.out.println(q.toString());
        }}
        if(byClient){
            String nom = nomEpreuve.getText();
            System.out.println(Serveur.tabledesMatieres.getMatieres().size());
            c.getPw().println("TABLEQ::"+matiere.getItemAt(matiere.getSelectedIndex()));
            String s = "NEWE::"+ nbquesti.getText()+";;"+pourcentFa.getText()+";;"+pourcentSt.getText()+";;"+pourcentDi.getText()+";;"+pourcentPi.getText()+";;"+nomEpreuve.getText()+";;"+matiere.getItemAt(matiere.getSelectedIndex());
            c.getPw().println(s);
        }
    }
}
