import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class GamePanel extends JPanel {
    private final JButton[] buttons = new JButton[16];

    public GamePanel(){
        setLayout(new GridLayout(4,4, 5,5));
        setBorder(new EmptyBorder(30, 30, 30, 30));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(Color.LIGHT_GRAY);


        for (int i = 0;  i < 16; i++){
            if (i < 15){
                buttons [i] = new JButton(String.valueOf(i + 1));
            } else{
                buttons [i] = new JButton("");
            }
            buttons[i].setFont(new Font("SansSerif", Font.BOLD, 30));
            add(buttons[i]);

        }

    }
}
