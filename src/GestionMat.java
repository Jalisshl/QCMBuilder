import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestionMat extends JFrame implements ActionListener /** gestionnaire des matieres*/{
    Client c;
    JComboBox<String> mati;
    JTextField nvmat;
    JTextField jtf;
    JTextField prof;
    JTextField prom;
;

    public GestionMat(Client c){
        super("gestion des matieres");
        this.c = c;
        this.setVisible(true);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel jPanel = (JPanel) this.getContentPane();
        jPanel.setLayout(new FlowLayout());

        JButton ajoutMATIERE = new JButton("ajouter matiere");
        ajoutMATIERE.addActionListener(this);
        JButton suppression = new JButton("supprimer matiere");
        suppression.addActionListener(this);
        jPanel.add(ajoutMATIERE);
        jPanel.add(suppression);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel j = new JPanel();j.setLayout(new FlowLayout());
        if((((JButton)e.getSource()).getText().equalsIgnoreCase("ajouter matiere"))){

            j.add(new JLabel("entrez le nom de la matiere"));
            JTextField jtf = new JTextField();
            jtf.setPreferredSize(new Dimension(120,30));
            nvmat = jtf;
            JButton va = new JButton("valider");
            va.addActionListener(this);
            JTextField prof = new JTextField("entrer id du prof");
            JTextField prom = new JTextField("entrer la liste des promos suivant le cours séparé par %");;
            j.add(jtf);
            j.add(prof);
            j.add(prom);
            j.add(va);
            this.jtf = jtf;
            this.prof = prof;
            this.prom = prom;
            this.setContentPane(j);
            this.revalidate();




        }
        if(((JButton)e.getSource()).getText().equalsIgnoreCase("supprimer matiere")){

            String s = "RECUPMAT";
            c.getPw().println(s);
            try {
                String ret = c.getBr().readLine();
                JComboBox<String> mati = new JComboBox<String>(ret.split("::")[1].split(";;"));
                this.mati = mati;
                j.add(mati);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            JButton ok = new JButton("ok");
            ok.addActionListener(this);
            j.add(ok);
            this.setContentPane(j);
            this.revalidate();

        }
        if((((JButton)e.getSource()).getText().equalsIgnoreCase("ok"))){
            String s = "DELMAT::";
            s+= mati.getItemAt(mati.getSelectedIndex());
            c.getPw().println(s);

            new GestionMat(c);
            this.dispose();


        }
        if((((JButton)e.getSource()).getText().equalsIgnoreCase("valider"))){

            c.getPw().println("ADDMAT::"+jtf.getText()+";;"+prof.getText()+";;"+prom.getText());
            new GestionMat(c);
            this.dispose();
        }




    }
}
