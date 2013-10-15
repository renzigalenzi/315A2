import java.io.PrintWriter;
import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.*;

public class ReversiServer {
    private ServerSocket serversocket;
    private Socket clientsocket;
    private PrintWriter out;
    private BufferedReader in;
    
    public static String hostname;
	public static String portnumber;
	
	public Reversi reversi;
	
    //TODO:
    //process input method
    //    will call stuff on reversi
    
    //probably give this constructor arguments for hostname and port number
    public ReversiServer(String host, String port) throws IOException {
        hostname = host;
		
		portnumber = port;
        //set up server socket
        int portint = Integer.parseInt(portnumber);
		InetAddress addr = InetAddress.getByName(hostname);
		
        try {
            //use constructor with IP address and port
            serversocket = new ServerSocket(portint, 1, addr);
            hostname = serversocket.getInetAddress().getHostName(); 
            System.out.println("Hostname: " + hostname);
            System.out.println("Port: " + portnumber);
            if (serversocket.isBound())
                System.out.println("Socket serversocket is bound");
        } catch (IOException e) {
            System.err.println("Couldn't listen on port " + portnumber);
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
        
		reversi = new Reversi();
		
        //output = process input
        out.println("Testing, do you get this client?");
        while ((input = in.readLine()) != null) {
            output = processinput(input);
            out.println(output);
//            if (output.equalsIgnoreCase("whatever the exit command is"))
//                break;
        }
        //close stuff, obviously
        out.close();
        in.close();
        clientsocket.close();
        serversocket.close();
        
    }
	
	public String processinput(String input) {
		return "not yet implemented";
	}
	
	public static void main(String args[]) throws Exception {
		hostname = JOptionPane.showInputDialog(null, "Enter hostname", 1);
		portnumber = JOptionPane.showInputDialog(null, "Enter the port number", 1);
		ReversiServer server = new ReversiServer(hostname, portnumber);
	}
}