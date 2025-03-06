package hw10;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.Image;
import java.awt.Toolkit;

public class ImageViewer extends JFrame {
    private JScrollPane panel;
    private ImageViewerScreen screen;

    public ImageViewer(String file) {
        super("Image Viewer");

        // Load the image
        Image image = Toolkit.getDefaultToolkit().getImage(file);
        screen = new ImageViewerScreen(image);

        // Create JScrollPane with both vertical and horizontal scroll bars always visible
        panel = new JScrollPane(screen, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // Add JScrollPane to JFrame
        getContentPane().add(panel);

        // Basic JFrame setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);  // You can adjust this as needed
        setVisible(true);
    }

    public static void main(String[] args) {
        String path = "./image.png";
        new ImageViewer(path);
    }
}
