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
    
    //TODO:
    //process method
    //  will call stuff on reversi
    
    //probably give this constructor arguments for hostname and port number
    public ReversiServer() throws IOException {
        //server socket
        //TODO: port number needs to be inputted
        try {
            serversocket = new ServerSocket(4444);
            String serveraddress = serversocket.getInetAddress().getHostName(); 
            System.out.println(serveraddress); //shows hostname
        } catch (IOException e) {
            System.err.println("Couldn't listen on port 4444");
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