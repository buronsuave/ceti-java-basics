package hw11;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseListenerTest extends JFrame {

    public MouseListenerTest() {
        super("Mouse Listener Test");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();

        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.out.println("Mouse clicked! x: " + mouseEvent.getX() + ", y: " + mouseEvent.getY());
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                System.out.println("Mouse pressed! x: " + mouseEvent.getX() + ", y: " + mouseEvent.getY());
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                System.out.println("Mouse released! x: " + mouseEvent.getX() + ", y: " + mouseEvent.getY());
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                System.out.println("Mouse entered! x: " + mouseEvent.getX() + ", y: " + mouseEvent.getY());
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                System.out.println("Mouse exited! x: " + mouseEvent.getX() + ", y: " + mouseEvent.getY());
            }
        });

        panel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                System.out.println("Mouse dragged! x: " + mouseEvent.getX() + ", y: " + mouseEvent.getY());
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                System.out.println("Mouse moved! x: " + mouseEvent.getX() + ", y: " + mouseEvent.getY());
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MouseListenerTest();
    }
}
