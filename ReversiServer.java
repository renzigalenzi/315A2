import java.io.PrintWriter;
import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReversiServer {
    private ServerSocket serversocket;
    private Socket clientsocket;
    private PrintWriter out;
    private BufferedReader in;
    
    public String hostname;
    //TODO:
    //process method
    //  will call stuff on reversi
    
    //probably give this constructor arguments for hostname and port number
    public ReversiServer(String port) throws IOException {
        
        //set up server socket
        int portint = Integer.parseInt(port);
        try {
            //use constructor with IP address and port
            serversocket = new ServerSocket(portint);
            hostname = serversocket.getInetAddress().getHostName(); 
            System.out.println("Hostname: " + hostname);
            System.out.println("Port: " + port);
            if (serversocket.isBound())
                System.out.println("Socket serversocket is bound");
        } catch (IOException e) {
            System.err.println("Couldn't listen on port " + port);
            System.exit(1);
        }
        //client socket
        try {
            clientsocket = serversocket.accept();
            
        } catch (IOException e) {
            System.err.println("Couldn't accept connection");
            System.exit(1);
        }
        //in and out
        out = new PrintWriter(clientsocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
        String input, output;
        
        //output = process input
        out.println("Testing, do you get this client?");
        while ((input = in.readLine()) != null) {
            //output = process input
            //out.println(output);
//            if (output.equalsIgnoreCase("whatever the exit command is"))
//                break;
        }
        //close stuff, obviously
        out.close();
        in.close();
        clientsocket.close();
        serversocket.close();
        
    }
}