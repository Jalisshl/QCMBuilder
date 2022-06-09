import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ConsultationStat extends JFrame implements ActionListener /** permet de consulter les stats*/{
    Client c;
    Proffesseur p;
    JComboBox<String> monChoix;
    public ConsultationStat(Proffesseur p, Client c) throws IOException {
        super("consultation stat");
        this.setVisible(true);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel j = (JPanel) this.getContentPane();
        j.setLayout(new FlowLayout());
        ArrayList<String> nomEpEnCours = new ArrayList<>();
        String req = "GETALLEP::";
        for (String s :p.getMatiereEns()
             ) {
            req+=s+";;";
        }
        c.getPw().println(req);
        String rep = c.getBr().readLine();

        String[] fc = rep.split("::");
        String[] sc = fc[1].split(";;");
        ArrayList<String> epreuves = new ArrayList<>();
        for (String ss: sc
             ) {
            epreuves.add(ss);
        }


        JComboBox<String> choixMat = new JComboBox<String>(epreuves.toArray(new String[0]));
        monChoix = choixMat;
        j.add(choixMat);
        this.c = c;
        this.p = p;
        JButton valid = new JButton("Valider");
        valid.addActionListener(this);
        j.add(valid);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(((JButton)e.getSource()).getText().equals("Valider"));{
            c.getPw().println("RESULT::"+monChoix.getItemAt(monChoix.getSelectedIndex()));
            try {
                String s =c.getBr().readLine();
                String[] fc = s.split("::");
                String[] sc = fc[1].split(";;");
                ArrayList a = new ArrayList<>();
                for (String sss:sc
                     ) {
                    a.add("id: "+sss.split("%!%")[0]+ "notte: "+sss.split("%!%")[1]);
                }
                JList<String> j = new JList<String>((String[]) a.toArray(new String[0]));
                this.getContentPane().add(j);
                this.revalidate();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
