import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class GamePanel extends JPanel {
    private final FifteenGame game;
    private final JButton[] buttons;

    public GamePanel(FifteenGame game){
        this.game = game;
        int size = game.getSize();
        this.buttons = new JButton[size * size];

        setLayout(new GridLayout(size,size, 5,5));
        setBorder(new EmptyBorder(30, 30, 30, 30));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(Color.GRAY);

        ActionListener listener = e -> {
            JButton b = (JButton) e.getSource();
            int tileIndex = (int) b.getClientProperty("index");
            if (game.move(tileIndex)) {
                refresh();
                if (game.gameSolved()){
                    JOptionPane.showMessageDialog(this,  "Grattis, du vann!");
                }
            }
        };

        for (int i = 0;  i < buttons.length; i++){
            JButton btn = new JButton();
            btn.setFont(new Font("SansSerif", Font.BOLD, 30));
            btn.putClientProperty("index", i);
            btn.addActionListener(listener);
            buttons[i] = btn;
            add(btn);
        }

        refresh();
    }

    public void refresh(){
        int[] tiles = game.getTiles();
        for (int i = 0; i < tiles.length; i++){
            if (tiles[i] == 0) {
                buttons[i].setText("");
                buttons[i].setEnabled(false);
            } else{
                buttons[i].setText(Integer.toString(tiles[i]));
                buttons[i].setEnabled(true);
            }
        }
    }
}
