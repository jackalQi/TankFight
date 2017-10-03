import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {

    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 800, 600);
        Font myFont = new Font("TIMES NEW ROMAN", Font.BOLD, 30);
        g.setColor(Color.YELLOW);
        g.setFont(myFont);
        g.drawString("STAGE : 1", 400, 300);
    }
}