import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class GameFrame extends JFrame{
    private JButton newGameButton;
    private JButton exitGameButton;
    private JLabel gameInfoLabel;
    private GamePanel gamePanel;


    public GameFrame(){
        setLayout(new BorderLayout());
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.GRAY);


        // Titel överst (NORTH)
        JLabel title = new JLabel("15 - Spelet", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD,40));
        title.setBorder(new EmptyBorder(20, 0, 20, 0));
        title.setForeground(new Color(245, 245, 245));
        add(title, BorderLayout.NORTH);

        // Spelbrädet (CENTER)
        gamePanel = new GamePanel();
        add(gamePanel, BorderLayout.CENTER);

        // Sidopanel med knappar (EAST)
        JPanel sidePanelEast = new JPanel();
        sidePanelEast.setLayout(new GridLayout(2, 1, 10, 10));
        sidePanelEast.setBorder(new EmptyBorder(40, 20, 40, 20));
        sidePanelEast.setBackground(Color.GRAY);

        newGameButton = new JButton("Nytt spel");
        newGameButton.setFont(new Font("Segoe UI", Font.BOLD,16));
        newGameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitGameButton = new JButton("Avsluta spel");
        exitGameButton.setFont(new Font("Segoe UI", Font.BOLD,16));
        exitGameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        sidePanelEast.add(newGameButton);
        sidePanelEast.add(exitGameButton);
        add(sidePanelEast, BorderLayout.EAST);

        // Spelbeskrivning (SOUTH)
        gameInfoLabel = new JLabel("Flytta brickorna tills de ligger i nummerordning", SwingConstants.CENTER);
        gameInfoLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gameInfoLabel.setBorder(new EmptyBorder(40,20,40,20));
        gameInfoLabel.setForeground(new Color(245, 245, 245));
        add(gameInfoLabel, BorderLayout.SOUTH);

        // Sidopanel med tom marginal (WEST)
        JPanel sidePanelWest = new JPanel();
        sidePanelWest.setBorder(new EmptyBorder(40, 35, 40, 35));
        sidePanelWest.setBackground(Color.GRAY);
        add(sidePanelWest, BorderLayout.WEST);

        setVisible(true);
    }

    void main(){

    }
}

