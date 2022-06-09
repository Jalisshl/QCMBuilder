public class Admin extends Humain{ /** implementer l'admin*/
    public Admin(){
        super();
        this.setR(Role.ADMIN);
    }
    public Admin(String n,String p, String mdp){
        super(n,p,mdp,Role.ADMIN);

    }

}
