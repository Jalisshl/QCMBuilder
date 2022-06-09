import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client /** etablie lien entre client et serveur*/{
    private InputStream is;
    private InputStreamReader isr;
    private OutputStream os;
    private static PrintWriter pw;
    private static BufferedReader br;
    private Socket s;
    public Client() throws IOException {
         s = new Socket("localhost",5555);
        is = s.getInputStream();
        isr = new InputStreamReader(is);
        br = new BufferedReader(isr);
        os = s.getOutputStream();
        pw = new PrintWriter(os,true);
        new LogInterface(this);

    }
    public Socket getSocket(){
        return s;
    }
    public PrintWriter getPw(){
        return pw;
    }

    public OutputStream getOs(){
        return os;
    }

    public BufferedReader getBr() {
        return br;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
       Client c =  new Client();
        String req = br.readLine();
        System.out.println(req);
        Parsercli p = new Parsercli();
        p.parserLogcli(req,c);




        /*Socket s = new Socket("localhost",5555);
        InputStream is = s.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        OutputStream os = s.getOutputStream();
        PrintWriter pw = new PrintWriter(os,true);*/
        //Scanner clavier = new Scanner(System.in);



        /*while(true){
            System.out.println("nom");
            String str = clavier.next();
            pw.println(str);//envoyer string au serv
            String res = br.readLine();
            System.out.println(res);
        }*/

    }
}
