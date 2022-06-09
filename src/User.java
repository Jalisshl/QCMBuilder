public class User /** implemente un utilisateur*/ {
    int id; int mdp; Role role; Humain h;
 public User(int i, int m , Role r,Humain hh){
    id = i; mdp = m ; role = r; h = hh;


}

    public int getId() {
        return id;
    }

    public int getMdp() {
        return mdp;
    }

    public Role getRole() {
        return role;
    }


}

