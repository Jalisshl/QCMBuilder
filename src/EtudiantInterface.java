import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EtudiantInterface extends JFrame implements ActionListener /** implementE l'interface de l'etudiant*/{
    JButton historique = new JButton("consulter l'historique");
    JButton epreuve = new JButton("faire epreuve");
    private Etudiant etu;
    private Client c;
    private boolean byClient = false;

    public EtudiantInterface(Etudiant e){
        this.setVisible(true);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel j = (JPanel)this.getContentPane();
        j.setLayout(new FlowLayout());
        j.add(historique);
        historique.addActionListener(this);
        j.add(epreuve);
        epreuve.addActionListener(this);
        etu = e;

    }
    public EtudiantInterface(Etudiant e,Client c){
        this.setVisible(true);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel j = (JPanel)this.getContentPane();
        j.setLayout(new FlowLayout());
        j.add(historique);
        historique.addActionListener(this);
        j.add(epreuve);
        epreuve.addActionListener(this);
        etu = e;
        byClient = true;
        this.c = c;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!byClient){
        new InterfaceEpreuve(etu);}
        if(byClient){
            try {
                new InterfaceEpreuve(etu, c);
                this.dispose();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    }
}
