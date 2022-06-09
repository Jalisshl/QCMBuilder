import java.io.*;
import java.net.Socket;
/** service va ecouter le canal de com entre client et serveur et va diriger ttous les requetes vers la fonctionnalité*/
public class Service extends Thread{
    private Socket socket;
    private int numcli;
    public Service (Socket s, int numcli){
        super();
        socket = s;
        this.numcli = numcli;

    }
    public String getKw(String s){
        return s.split("::")[0];
    }
    public void run(){
        try {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os,true);
            System.out.println("le client "+numcli+ " est co");

            while(true){
                String req = br.readLine();
                System.out.println(req);

                if(getKw(req).equalsIgnoreCase("LOG")){
                    String s = new ParserServ().parseLogServ(req);
                    pw.println(s);
                }
                if(getKw(req).equalsIgnoreCase("NEWQ")){
                    new ParserServ().parseNewQ(req);
                    System.out.println(req);
                }
                if(getKw(req).equalsIgnoreCase("NEWE")){
                    new ParserServ().parseNewe(req);

                }
                if(getKw(req).equalsIgnoreCase("NOMEXAM")){

                    String reponse  = new ParserServ().parseNomExam(req);
                    System.out.println(reponse);

                    pw.println(reponse);
                }
                if(getKw(req).equalsIgnoreCase("GETEPREUVE")){
                    String reponse = new ParserServ().parseGETEPREUVE(req);
                    System.out.println(reponse);
                    pw.println(reponse);
                }
                if(getKw(req).equalsIgnoreCase("SCORE")){
                    new ParserServ().parseSCORE(req);

                }
                if(getKw(req).equalsIgnoreCase("NEWUS")){
                    String s = new ParserServ().parseNEWUS(req);
                    pw.println(s);
                }
                if(getKw(req).equalsIgnoreCase("RECUPMAT")){
                    String s = new ParserServ().parseRECUPMAT(req);
                    pw.println(s);
                }
                if(getKw(req).equalsIgnoreCase("DELMAT")){
                    new ParserServ().parseDELMAT(req);

                }
                if(getKw(req).equalsIgnoreCase("ADDMAT")){
                    new ParserServ().parseADDMAT(req);

                }
                if(getKw(req).equalsIgnoreCase("GETALLEP")){
                   String s=  new ParserServ().parseGETALLEP(req);
                    pw.println(s);
                }
                if(getKw(req).equalsIgnoreCase("RESULT")){
                    String s = new ParserServ().parseRESULT(req);
                    pw.println(s);
                }



            }

            }

     catch (Exception e) {
            e.printStackTrace();
        }


    }
}
