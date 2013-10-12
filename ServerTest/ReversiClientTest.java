import java.net.Socket;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;
        
public class ReversiClientTest {
    private Socket socket;
    
    public ReversiClientTest() throws IOException {
        String serverAddress = JOptionPane.showInputDialog("Enter IP Address of a machine running the service on port 9090");
        socket = new Socket(serverAddress, 9090);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String answer = input.readLine();
        JOptionPane.showMessageDialog(null, answer);
    }
    
    public static void main(String[] args) throws IOException {
        new ReversiClientTest();
    }
}
