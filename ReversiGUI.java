import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
 
public class ReversiGUI extends JFrame{
    //Window Components
    public ReversiGUI() {
        initComponents();
    }
    String hostval, portval;
    
    
    String[] connection = {"Client", "Server"};
    Color light = new Color(26, 188, 156);
    Color dark = new Color(22, 160, 133);
    
    //Panels
    JPanel intro = new JPanel();
    JPanel game_mode = new JPanel();
    JPanel ai_mode = new JPanel();
    JPanel connect = new JPanel();
    JPanel network = new JPanel();
    JPanel details = new JPanel();
    
    //Text Labels
    JLabel title = new JLabel("WELCOME TO THE GAME OF REVERSI");
    JLabel inst = new JLabel("SELECT A FOLLOWING GAME MODE");
    JLabel ai1_l = new JLabel("1st AI DIFFICULTY LEVEL: ");
    JLabel ai2_l = new JLabel("2nd AI DIFFICULTY LEVEL: ");
    JLabel hostl = new JLabel("HOST: ");
    JLabel portl = new JLabel("PORT: ");
    JLabel creds1 = new JLabel("CSCE 315 - Project #2 - Reversi Game");
    JLabel creds2 = new JLabel("Made by Lance Elliot, Ross Hudgins & Edgardo Angel");
    
    //Text Fields
    JTextField hostf = new JTextField(4);
    JTextField portf = new JTextField(10);
    
    //Buttons
    JButton game = new JButton("START GAME");
    
    
    //Toggle Buttons
    ButtonGroup ai1 = new ButtonGroup();
    ButtonGroup ai2 = new ButtonGroup();
    ButtonGroup gmode = new ButtonGroup();
    JToggleButton ai1_e = new JToggleButton("EASY");
    JToggleButton ai1_m = new JToggleButton("MEDIUM");
    JToggleButton ai1_h = new JToggleButton("HARD");
    JToggleButton ai2_e = new JToggleButton("EASY");
    JToggleButton ai2_m = new JToggleButton("MEDIUM");
    JToggleButton ai2_h = new JToggleButton("HARD");
    JToggleButton u_vs_ai = new JToggleButton("User vs AI");
    JToggleButton u_vs_u = new JToggleButton("User vs User");
    JToggleButton ai_vs_ai = new JToggleButton("AI vs AI");
    
    //Toggle Radio Buttons
    JComboBox engine = new JComboBox(connection);
    
      
    //Drawing Main window
    public void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        //setPreferredSize(new Dimension(480,320));
        setMaximumSize(new Dimension(480,500));
        setTitle("Reversi");
        GridLayout gLayout1 = new GridLayout(0,1);
        GridLayout gLayout2 = new GridLayout(2,1);
        FlowLayout fLayout = new FlowLayout(FlowLayout.CENTER,10,17);
        
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setFont(new Font("Tahoma", 0, 18));
        inst.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        intro.setBackground(dark);
        intro.setLayout(gLayout1);
        intro.add(title);
        intro.add(inst);
        
        game_mode.setLayout(fLayout);
        game_mode.setBackground(light);
        u_vs_ai.setPreferredSize(new Dimension(100,23));
        u_vs_u.setPreferredSize(new Dimension(100,23));
        ai_vs_ai.setPreferredSize(new Dimension(100,23));
        
        game_mode.add(u_vs_ai);
        game_mode.add(u_vs_u);
        game_mode.add(ai_vs_ai);
        
        ai_mode.setBackground(dark);
        ai_mode.setLayout(gLayout2);
        ai_mode.add(ai1_l);
        ai_mode.add(ai1_e);
        ai_mode.add(ai1_m);
        ai_mode.add(ai1_h);
        ai1.add(ai1_e);
        ai1.add(ai1_m);
        ai1.add(ai1_h);
        ai_mode.add(ai2_l);
        ai_mode.add(ai2_e);
        ai_mode.add(ai2_m);
        ai_mode.add(ai2_h);
        ai2.add(ai2_e);
        ai2.add(ai2_m);
        ai2.add(ai2_h);
        ai2_l.setEnabled(false);
        ai2_e.setEnabled(false);
        ai2_m.setEnabled(false);
        ai2_h.setEnabled(false);
        
        network.setBackground(light);
        network.setLayout(fLayout);
        network.add(engine);
        network.add(hostl);
        network.add(hostf);
        network.add(portl);
        network.add(portf);
        network.add(game);
        
        details.setBackground(dark);
        details.setLayout(gLayout1);
        details.add(creds1);
        details.add(creds2);
        creds1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        creds2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        
        getContentPane().setLayout(gLayout1);
        getContentPane().add(intro);
        getContentPane().add(game_mode);
        getContentPane().add(ai_mode);
        getContentPane().add(network);
        getContentPane().add(details);
        pack();

       u_vs_u.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ai1_l.setEnabled(false);
                ai1_e.setEnabled(false);
                ai1_m.setEnabled(false);
                ai1_h.setEnabled(false);
                ai2_l.setEnabled(false);
                ai2_e.setEnabled(false);
                ai2_m.setEnabled(false);
                ai2_h.setEnabled(false);
                //whiteplayer = human;
                //blackplayer = human;
            }
        });
        
        u_vs_ai.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ai1_l.setEnabled(true);
                ai1_e.setEnabled(true);
                ai1_m.setEnabled(true);
                ai1_h.setEnabled(true);
                ai2_l.setEnabled(false);
                ai2_e.setEnabled(false);
                ai2_m.setEnabled(false);
                ai2_h.setEnabled(false);
                //AiPresent=true;
                //whiteplayer = Ai;
                //blackplayer = human;
               
            }
        });
        
        ai_vs_ai.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ai1_l.setEnabled(true);
                ai1_e.setEnabled(true);
                ai1_m.setEnabled(true);
                ai1_h.setEnabled(true);
                ai2_l.setEnabled(true);
                ai2_e.setEnabled(true);
                ai2_m.setEnabled(true);
                ai2_h.setEnabled(true);
                //ai2_e.setSelected(true); //By Default in case the user continues without selecting 2nd AI
                //BothAi=true;
                //whiteplayer = Ai;
                //blackplayer = Ai;
                
               
            }
        });
        
        
        ai1_e.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //whitedifficulty=easy;
            }
        });
                
        ai1_m.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //whitedifficulty=medium;
            }
        });
                        
        ai1_h.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //whitedifficulty=hard;
            }
        });
        
        ai2_e.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Second AI set to Easy
                //blackdifficulty=easy;
            }
        });
                
        ai2_m.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Second AI set to Medium
               getContentPane().add(network);
                //blackdifficulty=medium;
            }
        });
                        
        ai2_h.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Second AI set to Hard
                //blackdifficulty=hard;
            }
        });
        
        game.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                hostval = hostf.getText();
                portval = portf.getText();
                JOptionPane.showMessageDialog(null,
                    "Host #: " + hostval + "Port #: " + portval,
                    "PARAMETERS",
                    JOptionPane.PLAIN_MESSAGE);
                JFrame board = new JFrame("Gui made window");
                board.setVisible(true);
                board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                dispose();
                //p=2;
                board.setLocationRelativeTo(null); // Draws in the middle of the screen
            }
        });
        setLocationRelativeTo(null); // Draws in the middle of the screen
    }
    
    //Running Main program
    public static void main(String[] args) {
        //Makes the program look better - take out if giving error compiling on local and on server
        /*try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException ex) {
        }*/

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ReversiGUI().setVisible(true);
            }
        });
    }
}
