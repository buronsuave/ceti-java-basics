package hw06;

import javax.swing.JFrame;

public class SimpleWindow extends JFrame {
    public SimpleWindow() {
        super("Simple Window");
        setSize(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleWindow();
    }
}
