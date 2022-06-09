import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AdminInterface extends JFrame implements ActionListener /** implemente l'interface d'admin*/{
    Client c;
    JTextField prenom;
    JTextField nom;
    JTextField mdp;
    JComboBox<String> role;
    JButton ok;
    JTextField promo;
    JTextField matiere;
    JButton valid;



    public AdminInterface(Client c){

        super("admin interface");
        this.setVisible(true);
        this.c = c;
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        JLabel jlab = new JLabel("entrez les données du nv utilisateur");
        this.setVisible(true);
        JPanel jp = (JPanel) this.getContentPane();
        jp.setLayout(new FlowLayout());
        JTextField p = new JTextField("entrez prenom");
        JTextField n = new JTextField("entrez nom");
        JTextField mdp = new JTextField("entrez mot de passe");
        prenom = p;
        nom = n;
        this.mdp = mdp;
        jp.add(p);jp.add(n); jp.add(mdp);
        JComboBox<String> r = new JComboBox<>(new String[] {Role.ETUDIANT.toString(),Role.PROF.toString(),Role.ADMIN.toString()});
        role= r;
        jp.add(r);
        JButton jb = new JButton("ok");
        ok = jb;
        ok.addActionListener(this);
        JButton gestionMatiere = new JButton("Gerer les matieres");
        gestionMatiere.addActionListener(this);
        jp.add(ok);
        jp.add(gestionMatiere);


    }

    public void actualise(){
        JPanel njp = new JPanel();
        njp.setLayout(new FlowLayout());

        if(role.getItemAt(role.getSelectedIndex()).equalsIgnoreCase("ETUDIANT")){
            JLabel jlab = new JLabel("entrez une promo");
            JTextField jtf = new JTextField("promo");
            promo = jtf;
            njp.add(jlab); njp.add(jtf);
        }

        if(role.getItemAt(role.getSelectedIndex()).equalsIgnoreCase("PROF")){
            JLabel jlab = new JLabel("entrez matieres enseignee separées par ;;");
        }

        if(role.getItemAt(role.getSelectedIndex()).equalsIgnoreCase("ADMIN")){


        }
        JButton j = new JButton("valider");

        j.addActionListener(this);
        njp.add(j);
        this.setContentPane(njp);
        this.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if( (((JButton)e.getSource()).getText().equals("ok"))){
        this.actualise();
        this.revalidate();}
       if((((JButton)e.getSource()).getText().equalsIgnoreCase("Valider"))){

           String s = "NEWUS::"+ prenom.getText()+";;"+nom.getText()+";;"+mdp.getText()+";;";
           if(role.getItemAt(role.getSelectedIndex()).equalsIgnoreCase("ETUDIANT")){
               s+="ETUDIANT;;"+promo.getText();;
           }
           if(role.getItemAt(role.getSelectedIndex()).equalsIgnoreCase("ADMIN")){
               s+= "ADMIN";
           }
           if(role.getItemAt(role.getSelectedIndex()).equalsIgnoreCase("PROF")){
               s+="PROF;;";
               for (String str: matiere.getText().split(";;")
                    ) {
                   s+= str+"%!%";
               }
           }
           c.getPw().println(s);
           JPanel j = new JPanel();
           j.setLayout(new FlowLayout());
           try {
               String rep =c.getBr().readLine();
               String[] fc = rep.split("::");
               String nom = fc[1].split(";;")[0];
               String mdp = fc[1].split(";;")[1];


               JLabel jl = new JLabel("utilisateur créer: identifiant:"+nom+ "mdp :"+mdp);
               j.add(jl);
               JButton jb = new JButton("quitter");
               jb.addActionListener(this);
               j.add(jb);
               this.setContentPane(j);
               this.revalidate();



           } catch (IOException ex) {
               ex.printStackTrace();
           }



       }
       if((((JButton)e.getSource()).getText().equalsIgnoreCase("quitter"))){
           new AdminInterface(c);
           this.dispose();
       }
       if((((JButton)e.getSource()).getText().equalsIgnoreCase("Gerer les matieres"))){
           new GestionMat(c);
           this.dispose();

       }


    }
}
