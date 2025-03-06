package hw14;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerTest extends JFrame {

    public KeyListenerTest() {
        super("Key Listener Test");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                System.out.println("Key typed! key: " + keyEvent.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                System.out.println("Key pressed! key: " + keyEvent.getKeyChar());
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                System.out.println("Key released! key: " + keyEvent.getKeyChar());
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new KeyListenerTest();
    }
}
