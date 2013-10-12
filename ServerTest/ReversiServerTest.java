import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;


/**
 *
 * @author Ross
 */
public class ReversiServerTest {

    /**
     * @param args the command line arguments
     */
    private ServerSocket listener;
    private Socket socket;
    
    public ReversiServerTest() throws IOException {
        try {
            listener = new ServerSocket(9090);
            System.out.println("serversocket opened");
            while (true) {
                System.out.println("gonna try to accept now");
                socket = listener.accept();
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("Hey, this was sent from the server");
                }
                finally {
                    socket.close();
                }
            }
        }
//        catch (IOException e) {
//            System.out.println("IOError");
//        }
        finally {
            listener.close();
        }
    }
    
    public static void main(String[] args) throws IOException {
        new ReversiServerTest();
    }
}
