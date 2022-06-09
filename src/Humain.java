public abstract class Humain /** classe abstraite qui implemente les étudiants*/{
    private String nom;
    private String prenom;
    private int id;
    private int hashpass;
    private Role r;
    public Humain(){
        nom = "";
        prenom = "";
        id = Main.nbCreer;
        hashpass = 0;
        Main.nbCreer++;
        r = null;

    }
    public Humain(String n, String p , String mdp,Role rr){
        nom = n; prenom = p; this.id = Main.nbCreer; hashpass = mdp.hashCode();r = rr;
        Main.nbCreer ++;
    }

    public int getId() {
        return id;
    }

    public int getHashpass() {
        return hashpass;
    }

    public Role getR() {
        return r;
    }

    public void setR(Role r) {
        this.r = r;
    }
    public String getNom(){
        return nom;
    }
    public String getPrenom(){
        return prenom;
    }
    public Role getRole(){
        return r;
    }
    public void setRole(Role r){
        this.r = r;
    }
}
