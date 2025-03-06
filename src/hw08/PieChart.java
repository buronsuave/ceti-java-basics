package hw08;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.BorderLayout;

public class PieChart extends JPanel {
    private int[] angles;
    private static final Color[] colors = {
            Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN, Color.ORANGE
    };

    public PieChart(double[] values) {
        setSize(800, 800);
        buildAngles(values);
        setVisible(true);
    }

    public void buildAngles(double[] values) {
        double total = 0.0;
        for (double value : values)
            total += value;
        int[] angles = new int[values.length];
        for (int i = 0; i < values.length; ++i)
            angles[i] = (int) ((values[i] / total) * 360);

        // Fix last angle
        int left = 360;
        for (int i = 0; i < angles.length-1; ++i) {
            left -= angles[i];
        }
        angles[angles.length - 1] = left;
        this.angles = angles;
    }

    @Override
    public void paintComponent(Graphics g) {
        int currentAngle = 0;
        for (int i = 0; i < angles.length; ++i) {
            g.setColor(colors[i % colors.length]);
            g.fillArc(0, 0, 300, 300, currentAngle, angles[i]);
            currentAngle += angles[i];
        }
    }

    public static void main(String[] args) {
        double[] values = new double[args.length];
        for (int i = 0; i < args.length; ++i)
            values[i] = Double.parseDouble(args[i]);

        JFrame frame = new JFrame("Pie Chart");
        PieChart chart = new PieChart(values);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.add(chart, BorderLayout.CENTER);
        frame.setSize(300, 340);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}