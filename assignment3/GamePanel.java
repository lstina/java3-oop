import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class GamePanel extends JPanel {
    private final FifteenGame game;
    private final JButton[] buttons;

    // Tar emot FifteenGame-objekt som innehåller all logik
    // Layouten sätts till ett 4*4 rutnät
    public GamePanel(FifteenGame game){
        this.game = game;
        int size = game.getSize();
        this.buttons = new JButton[size * size];

        setLayout(new GridLayout(size,size, 5,5));
        setBorder(new EmptyBorder(30, 30, 30, 30));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(Color.GRAY);

        // Skapar en ActionListener med lambda som lyssnar på klick
        // Varje  knapp får ett index (1-15) och sparas med putCLientProperty
        // När man klickar på en knapp anropas move, som försöker flytta brickan
        // Efter varje klick görs refresh för att uppdatera text på knapp
        // Om spelet blir löst, JOptionPane med meddelande
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

        // Skapar och kopplar ihop alla knappar med spelet
        for (int i = 0;  i < buttons.length; i++){
            JButton btn = new JButton();
            btn.setFont(new Font("SansSerif", Font.BOLD, 30));
            btn.putClientProperty("index", i);
            btn.addActionListener(listener);
            buttons[i] = btn;
            add(btn);
        }
        // Ritar upp startläge
        refresh();
    }

    /** Uppdaterar gränssnittet så att knapparna matchar spelbrädet
     * Hämtar aktuell tile-array från spelet
     * Loopar igenom alla knappar och sätter deras text till rätt nummer
     * Om värdet är 0 betyder det att rutan är tom, då visas ingen text
     * Annars visas numret och knappen är klickbar
     */
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
