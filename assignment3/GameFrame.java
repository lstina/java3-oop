import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class GameFrame extends JFrame{
    public GameFrame(){
        setSize(800,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        getContentPane().setBackground(Color.GRAY);


        JLabel title = new JLabel("15 - Spelet", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD,40));
        title.setBorder(new EmptyBorder(20, 0, 20, 0));
        title.setForeground(new Color(245, 245, 245));
        add(title, BorderLayout.NORTH);


        add(new GamePanel(), BorderLayout.CENTER);
        setVisible(true);
    }

    void main(){

    }
}

