import java.io.*;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.net.Socket;
import java.net.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import javax.swing.*;
import java.applet.*;
import java.awt.*;
import java.util.*;
import java.util.Random;
import java.awt.event.*;
import java.lang.Math;
import java.awt.image.*;



public class ReversiClient {
    private Socket localsocket;
    private PrintWriter out;
    private BufferedReader in;
    private BufferedReader cmdin;
	
	public static String hostname;
	public static String portnumber;
	
	public static Reversi reversi;
	
	public static final int apwidth =800; //unchanging values for window size
    public static final int apheight =600;
    
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
        String fromserver, fromcmd, output;
        
        while ((fromserver = in.readLine()) != null) {
            System.out.println("Server says: " + fromserver);
            //if fromserver.equals("exit command")
            //break
			
			output = processinput(fromserver);
            out.println(output);
			
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
	
	public String processinput(String input) {
		return "not implemented yet";
	}
	
	public static void main(String args[]) throws Exception {
		hostname = JOptionPane.showInputDialog(null, "Enter hostname", 1);
		portnumber = JOptionPane.showInputDialog(null, "Enter the port number", 1);
		
		//Frame frame = new Frame();
        Frame frame = new Frame();
        Applet applet = new Reversi();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }	
        });

        frame.add(applet);
        frame.setSize(apwidth,apheight);
        frame.show();
		
		ReversiClient client = new ReversiClient(hostname, portnumber);
	}
    
}