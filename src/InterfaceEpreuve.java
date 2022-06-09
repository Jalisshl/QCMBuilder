import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class InterfaceEpreuve extends JFrame implements ActionListener /** permet de select une epreuve*/{
    private JComboBox<String> selecEpreuve;
    private ArrayList<Epreuve> epreuvespossibles = new ArrayList<>();
    private Etudiant et;
    private boolean byClient= false;
    private Client client;
    private ArrayList<String> mat;
    public InterfaceEpreuve(Etudiant e){

        super("interface epreuve");
        et = e;
        this.setVisible(true);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel j = (JPanel)this.getContentPane();
        j.setLayout(new FlowLayout());
        Promo myPromo = Main.tabledesPromos.getPromoById(e.getPromo());
        System.out.println(myPromo.toString());
        ArrayList<Matiere> myMat = myPromo.getMatieresSuivies();
        for (Matiere m: myMat
             ) {
            epreuvespossibles.addAll(m.getEpreuves());

        }
        ArrayList<String> mapos = new ArrayList<>();
        for (Epreuve ep: epreuvespossibles
             ) {
            mapos.add(ep.getName());
        }
        JComboBox<String> se = new JComboBox<String>(mapos.toArray(new String[0]));
        JButton valider = new JButton();
        selecEpreuve = se;
        valider.addActionListener(this);

        j.add(se);
        j.add(valider);

    }
    public InterfaceEpreuve(Etudiant e,Client c) throws IOException {

        super("interface epreuve");
        System.out.println("aqui esta");
        et = e;
        this.setVisible(true);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel j = (JPanel)this.getContentPane();
        j.setLayout(new FlowLayout());

        String request = "NOMEXAM::"+e.getPromo()+";;"+e.getId();

        c.getPw().println(request);

        String rep = c.getBr().readLine();
        System.out.println("apres la requette"+request);
        ArrayList<String> ouf = new Parsercli().parseNomExam(rep);//separe en couple matiere epreuve
        ArrayList<String> matire = new ArrayList<>();
        ArrayList<String> exam = new ArrayList<>();
        for (String sss: ouf
             ) {
            matire.add(sss.split("%!%")[1]);
            exam.add(sss.split("%!%")[0]);

        }
        mat = matire;



        JComboBox<String> se = new JComboBox<String>(exam.toArray(new String[0]));
        JButton valider = new JButton();
        selecEpreuve = se;
        valider.addActionListener(this);

        j.add(se);
        j.add(valider);
        byClient = true;
        client = c;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!byClient){
        for (Epreuve ep:epreuvespossibles
             ) {
            if(ep.getName().equalsIgnoreCase(selecEpreuve.getItemAt(selecEpreuve.getSelectedIndex()))){
                new InterfaceDeReponse(ep,et);
            }
        }}
        if(byClient){
           String nomEpreuve = selecEpreuve.getItemAt(selecEpreuve.getSelectedIndex());
           client.getPw().println("GETEPREUVE::"+nomEpreuve+";;"+mat.get(selecEpreuve.getSelectedIndex())+";;"+et.getId() );
           String s="";
            try {
                s = client.getBr().readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            ArrayList<Question> quest = new Parsercli().parseEPREUVE(s);

            Epreuve a = new Epreuve(quest.size(),0,0,0,0,quest,nomEpreuve);

            a.setListeQ(quest);
            new InterfaceDeReponse(a,et,client);
            this.dispose();

        }
    }
}
