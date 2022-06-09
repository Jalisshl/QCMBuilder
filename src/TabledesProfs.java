import java.util.ArrayList;
import java.util.Hashtable;

public class TabledesProfs /** l'annuaire des professeurs*/ {
    private Hashtable<Integer, ArrayList<String>> table;
    public TabledesProfs(){
        table = new Hashtable<>();
    }
    public TabledesProfs(Hashtable<Integer,ArrayList<String>>  t){
        table = t;
    }
    public void addProf(Proffesseur p ){
        table.computeIfAbsent(p.getId(), k -> p.getMatiereEns());
    }
    public ArrayList<String> getMatiereById(int id){
        return table.get(id);
    }
    public void deleteMatiereById(String st){
        for (int i: table.keySet()
             ) {
            ArrayList<String> array = new ArrayList<>();
            for (String s: table.get(i)
                 ) {
                if(!s.equalsIgnoreCase(st)){
                    array.add(s);
                }
            }
            table.put(i,array);

        }
    }
    public void AddMatiere(int id,Matiere m){
        ArrayList<String> j = new ArrayList<>();
        j.add(m.getName());
        table.put(id,j);
    }
}
