import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class GameFrame extends JFrame{
    private JButton newGameButton;
    private JButton demoSortedButton;
    private JButton exitGameButton;
    private JLabel gameInfoLabel;
    private FifteenGame game;
    private GamePanel gamePanel;

    /** Skapar fönstret för spelet
     * Sätter storlek, titel, bakgrundsfärg och vad som händer när man stänger fönstret
     */

    public GameFrame(){
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.GRAY);
        setTitle("15-spelet");

        // Skapar ett nytt FifteenGame-objekt med storleken 4*4
        // Skapar sedan en GamePanel som ritar ut spelets brickor och kopplar det till logiken
        game = new FifteenGame(4);
        gamePanel = new GamePanel(game);

        // Titel överst (NORTH)
        JLabel title = new JLabel("15 - Spelet", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD,40));
        title.setBorder(new EmptyBorder(20, 0, 20, 0));
        title.setForeground(new Color(245, 245, 245));


        // Spelbrädet (CENTER)
        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setBackground(Color.GRAY);

        // Titel överst (NORTH)
        centerWrapper.add(title, BorderLayout.NORTH);
        centerWrapper.add(gamePanel, BorderLayout.CENTER);
        add(centerWrapper, BorderLayout.CENTER);

        // Sidopanel med knappar (EAST)
        JPanel sidePanelEast = new JPanel();
        sidePanelEast.setLayout(new GridLayout(3, 1, 10, 10));
        sidePanelEast.setBorder(new EmptyBorder(60, 20, 60, 20));
        sidePanelEast.setBackground(Color.GRAY);
        sidePanelEast.setPreferredSize(new Dimension(200,0));

        newGameButton = new JButton("Nytt spel");
        newGameButton.setFont(new Font("Segoe UI", Font.BOLD,16));
        newGameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        demoSortedButton = new JButton("Demo: Vinst");
        demoSortedButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        demoSortedButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        exitGameButton = new JButton("Avsluta spel");
        exitGameButton.setFont(new Font("Segoe UI", Font.BOLD,16));
        exitGameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        sidePanelEast.add(newGameButton);
        sidePanelEast.add(demoSortedButton);
        sidePanelEast.add(exitGameButton);
        add(sidePanelEast, BorderLayout.EAST);

        newGameButton.addActionListener(e -> {
            game.newGame();
            gamePanel.refresh();
        });

        demoSortedButton.addActionListener(e -> {
            game.reset();
            gamePanel.refresh();
        });

        exitGameButton.addActionListener(e -> System.exit(0));

        // Spelbeskrivning (SOUTH)
        gameInfoLabel = new JLabel("Flytta brickorna tills de ligger i nummerordning", SwingConstants.CENTER);
        gameInfoLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gameInfoLabel.setBorder(new EmptyBorder(10,0,30,0));
        gameInfoLabel.setForeground(new Color(245, 245, 245));
        centerWrapper.add(gameInfoLabel, BorderLayout.SOUTH);

        // Sidopanel som är tom, används för visuell balans(WEST)
        JPanel sidePanelWest = new JPanel();
        sidePanelWest.setBorder(new EmptyBorder(60, 20, 60, 20));
        sidePanelWest.setBackground(Color.GRAY);
        add(sidePanelWest, BorderLayout.WEST);

        game.newGame();
        gamePanel.refresh();
        setVisible(true);
    }


}

