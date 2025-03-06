package hw09;

import javax.swing.JFrame;
import java.awt.Graphics;

public class Monito extends JFrame {
    public Monito() {
        super("Graphics usage");
        setSize(200, 300);
        setVisible(true);
    }

    public void paint(Graphics g) {
        g.drawString("Graphics demo", 10, 50);

        // Face
        g.drawArc(50, 60, 50, 50, 0, 360);
        g.drawArc(60, 70, 30, 30, 180, 180);
        g.fillOval(65, 75, 5, 5);
        g.fillOval(80, 75, 5, 5);

        // Body
        g.drawLine(75, 110, 75, 200);

        // Arms
        g.drawLine(75, 120, 45, 160);
        g.drawLine(75, 120, 105, 160);

        // Legs
        g.drawLine(75, 200, 45, 240);
        g.drawLine(75, 200, 105, 240);
    }

    public static void main(String[] args) {
        new Monito();
    }
}
