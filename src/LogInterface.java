import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;

public class LogInterface extends JFrame implements ActionListener /** gere l'interface et permet à l'utilisateur de so connecter*/{
    private JPanel jPanel;
    private JTextField id;
    private JPasswordField pass;
    private boolean byClient = false;
    private Client c = null;

    public LogInterface(){
        super("log");
        this.setVisible(true);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jPanel = (JPanel)this.getContentPane();
        jPanel.setLayout(new FlowLayout());
        JTextField ident = new JTextField("identifiant");
        ident.setPreferredSize(new Dimension(120,30));
        JPasswordField password = new JPasswordField("mot de passe");
        password.setPreferredSize(new Dimension(120,30));
        id = ident;
        pass = password;
        jPanel.add(id);
        jPanel.add(pass);
        JButton valid = new JButton("valider");
        valid.addActionListener(this);
        jPanel.add(valid);


    }
    public LogInterface(Client c){
        super("log");
        this.setVisible(true);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jPanel = (JPanel)this.getContentPane();
        jPanel.setLayout(new FlowLayout());
        JTextField ident = new JTextField("identifiant");
        ident.setPreferredSize(new Dimension(120,30));
        JPasswordField password = new JPasswordField("mot de passe");
        password.setPreferredSize(new Dimension(120,30));
        id = ident;
        pass = password;
        jPanel.add(id);
        jPanel.add(pass);
        JButton valid = new JButton("valider");
        valid.addActionListener(this);
        jPanel.add(valid);
        byClient = true;
        this.c = c;
    }

    @Override
    public void actionPerformed(ActionEvent e){ //methode de l'interface actionlistener
        if(!byClient){
        Humain user = Main.users.getUserById(Integer.parseInt(id.getText()));
       int hash = user.getHashpass();
       if (hash == pass.getText().hashCode()){
           if(user.getR()==Role.PROF){
               new ProfessorInterface((Proffesseur) user);
               this.dispose();
           }
           if(user.getR() == Role.ETUDIANT){
               new EtudiantInterface((Etudiant) user);
               this.dispose();
           }
       }
    }
        if(byClient){

            PrintWriter p = new PrintWriter(c.getOs(),true);

            p.println("LOG::"+id.getText()+";;"+pass.getText().hashCode());


        }
    }
}
