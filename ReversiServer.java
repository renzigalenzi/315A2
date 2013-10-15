import java.io.*;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.*;
import java.applet.*;
import java.awt.*;
import java.util.*;
import java.util.Random;
import java.awt.event.*;
import java.lang.Math;
import java.awt.image.*;

/** 
 *	Lance Elliott
 *  Ross Hudgins
	Edgardo Angel
 **/

public class ReversiServer {
    private ServerSocket serversocket;
    private Socket clientsocket;
    private PrintWriter out;
    private BufferedReader in;
	private BufferedReader cmdin;
    
    public static String hostname;
	public static String portnumber;
	
	public static Reversi reversi;
	public static Applet reversiapplet;
	
	public static final int apwidth =800; //unchanging values for window size
    public static final int apheight =600;
	
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
            System.out.println("Connected to client");
        } catch (IOException e) {
            System.err.println("Couldn't accept connection");
            System.exit(1);
        }
        //in and out
        out = new PrintWriter(clientsocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
        cmdin = new BufferedReader(new InputStreamReader(System.in));
		String fromclient, output, fromcmd;
        
		System.out.println("made in and out");
		
        //output = process input
        out.println("Testing, do you get this client?");
        while ((fromclient = in.readLine()) != null) {
            System.out.println("fromclient: " + fromclient);
			output = processinput(fromclient);
            out.println(output);
//            if (output.equalsIgnoreCase("whatever the exit command is"))
//                break;

			fromcmd = cmdin.readLine();
            if (fromcmd != null) {
                System.out.println("Server: " + fromcmd);
                out.println(fromcmd);
            }
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
		
		//Frame frame = new Frame();
        Frame frame = new Frame();
        //Applet applet = new Reversi();
		reversiapplet = new Reversi();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }	
        });

        frame.add(reversiapplet);
        frame.setSize(apwidth,apheight);
        frame.show();
		
		ReversiServer server = new ReversiServer(hostname, portnumber);
	}
}