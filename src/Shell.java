import javax.swing.*;
import java.awt.*;

public class Shell {
    PhoneBook phoneBook;
    JFrame window;
    JPanel panel;

    Shell(){

    }

    public void init(){
        window = new JFrame();
        panel = new JPanel();
        window.add(panel);

        window.setSize(new Dimension(800, 600));

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // window.setUndecorated(false);
        // window.setBackground(new Color(1, 1, 1, 0));

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void run(){

    }
    public void close(){


    }
}
