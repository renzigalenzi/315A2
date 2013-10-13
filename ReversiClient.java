import java.io.PrintWriter;
import java.io.BufferedReader;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

public class ReversiClient {
    private Socket localsocket;
    private PrintWriter out;
    private BufferedReader in;
    private BufferedReader cmdin;
    
    //probably give this constructor arguments for hostname and port number
    public ReversiClient(String hostname, String port) throws IOException {
        int portint = Integer.parseInt(port);
        //setup shtuff
        try {
            localsocket = new Socket(hostname, portint);
            if (localsocket.isBound())
                System.out.println("Socket localsocket is bound to " + hostname + " and port " + port);
            else System.out.println("localsocket not bound?...");
            out = new PrintWriter(localsocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(localsocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Client could not find that host...");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("IO connection failed clientside");
            System.exit(1);
        }
        
        //for input from user command line?
        cmdin = new BufferedReader(new InputStreamReader(System.in));
        String fromserver, fromcmd;
        
        while ((fromserver = in.readLine()) != null) {
            System.out.println("Server says: " + fromserver);
            //if fromserver.equals("exit command")
            //break
            
            fromcmd = cmdin.readLine();
            if (fromcmd != null) {
                System.out.println("Client: " + fromcmd);
                out.println(fromcmd);
            }
        }
        
        out.close();
        in.close();
        cmdin.close();
        localsocket.close();
    }
    
}