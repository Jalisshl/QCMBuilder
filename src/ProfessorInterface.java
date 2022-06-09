import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class ProfessorInterface extends JFrame implements ActionListener /** interface du professeur*/ {
    private JButton consultationsStat;
    private JButton creationEpreuveData;
    private JButton creationEpreuveRien;
    private Proffesseur p;
    private boolean byClient= false;
    private Client c;
    public ProfessorInterface(Proffesseur p){
        super("profinterface");
        JPanel j = (JPanel) this.getContentPane();
        j.setLayout(new FlowLayout());
        JButton cs = new JButton("consultation stat");
        JButton ced = new JButton("Creation epreuve à partir d'une dataBase");
        JButton cer = new JButton("completer database");
        cer.addActionListener(this);
        ced.addActionListener(this);
        this.setVisible(true);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        j.add(cs); j.add(ced);j.add(cer);
        consultationsStat = cs; creationEpreuveData = ced; creationEpreuveRien = cer;
        this.p = p;




    }
    public ProfessorInterface(Proffesseur p , Client c){
        super("profinterface");
        JPanel j = (JPanel) this.getContentPane();
        j.setLayout(new FlowLayout());
        JButton cs = new JButton("consultation stat");
        JButton ced = new JButton("Creation epreuve à partir d'une dataBase");
        JButton cer = new JButton("completer database");
        cer.addActionListener(this);
        ced.addActionListener(this);
        cs.addActionListener(this);
        this.setVisible(true);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        j.add(cs); j.add(ced);j.add(cer);
        consultationsStat = cs; creationEpreuveData = ced; creationEpreuveRien = cer;
        this.p = p;
        byClient = true;
        this.c = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!byClient){
        if(((JButton)e.getSource()).getText().equals("completer database")){
        new CreationQCM(p);
        this.dispose();}
        if(((JButton)e.getSource()).getText().equals("Creation epreuve à partir d'une dataBase")){
            new CreationByDB(p);
            this.dispose();
        }


        }
        else{
            if(((JButton)e.getSource()).getText().equals("completer database")){
                new CreationQCM(p,this.c);
                this.dispose();}
            if(((JButton)e.getSource()).getText().equals("Creation epreuve à partir d'une dataBase")){
                new CreationByDB(p,this.c);
                this.dispose();
        }
            if(((JButton)e.getSource()).getText().equals("consultation stat")){
                try {
                    new ConsultationStat(p,this.c);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
    }
}
}
