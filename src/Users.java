import java.util.ArrayList;
import java.util.HashMap;

public class Users /** BD des utilisateurs*/{
    private ArrayList<Humain> users;

    public Users(ArrayList<Humain> u){
        users = u;
    }

    public ArrayList<Humain> getUsers() {
        return users;
    }
    public Humain getUserById(int id){
        for (Humain h: users
             ) {
            if(h.getId()==id){
                return h;
            }
        }
        return null;
    }
    public void addUser(Humain h){
        users.add(h);
    }
    public Humain getUserbyId(int id){
        for (Humain h: users
             ) {
            if(h.getId()==id){
                return h;
            }
        }
        return null;
    }

}
