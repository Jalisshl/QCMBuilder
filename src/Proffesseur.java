import java.util.ArrayList;

public class Proffesseur extends Humain /** implemente le prof*/{
    private ArrayList<String> matiereEns;
    public Proffesseur(){
        super();
        matiereEns = new ArrayList<>();
    }
    public Proffesseur(String n, String p, String mpd, ArrayList<String>matiereEns){
        super(n,p,mpd,Role.PROF);
       this.matiereEns = matiereEns;
    }

    public ArrayList<String> getMatiereEns() {
        return matiereEns;
    }
}
