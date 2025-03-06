package hw10;

import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;

public class ImageViewerScreen extends JPanel {
    private Image image;

    public ImageViewerScreen(Image image) {
        this.image = image;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // Always call the superclass method first

        // Set the size of the panel to be the size of the image
        Dimension size = new Dimension(image.getWidth(this), image.getHeight(this));
        setPreferredSize(size);

        // Draw the image
        g.drawImage(image, 0, 0, this);
    }
}
