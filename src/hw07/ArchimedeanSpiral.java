package hw07;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ArchimedeanSpiral extends JPanel {

    // Initial configuration
    private static final double R0 = 0;       // Initial radius
    private static final double A0 = 0;        // Initial angle (radians)
    private static final int T0 = 0;        // Initial time (ms)
    private static final int P0 = 0;        // Initial p parameter
    private static final double VR = 1;   // Radial velocity
    private static final double VA = 0.1;   // Angular velocity (radians)

    // Animation parameters
    private static final int T = 10_000;    // Animation duration (in ms)
    private static final Color COLOR1 = Color.BLACK;
    private static final Color COLOR2 = Color.RED;
    private static final int SIZE = 10;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final double ORIGIN_X = (double) WIDTH / 2;
    private static final double ORIGIN_Y = (double) HEIGHT / 2;

    // Actual values
    private double r;                          // Actual radius
    private double a;                          // Actual angle
    private int t;                             // Actual time
    private double p;                          // Actual p parameter

    public double getR() { return this.r; }
    public int getT() { return this.t; }
    public double getP() { return this.p; }

    public void setR(double r) { this.r = r; }
    public void setA(double a) { this.a = a; }
    public void setT(int t) { this.t = t; }
    public void setP(double p) { this.p = p; }

    public ArchimedeanSpiral() {
        this.r = R0;
        this.a = A0;
        this.t = T0;
        this.p = P0;
    }

    // Paint particle in its current position
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(COLOR1);
        int x = (int) (ORIGIN_X + this.r * Math.cos(this.a));
        int y = (int) (ORIGIN_Y - this.r * Math.sin(this.a));
        g.fillOval(x, y, SIZE, SIZE);

        g.setColor(COLOR2);
        x = (int) (ORIGIN_X - this.r * Math.cos(this.a));
        y = (int) (ORIGIN_Y + this.r * Math.sin(this.a));
        g.fillOval(x, y, SIZE, SIZE);
    }

    public static void main(String[] args) {
        ArchimedeanSpiral as = new ArchimedeanSpiral();
        JFrame frame = new JFrame("Archimedean Spiral");
        frame.add(as);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            if (as.getT() < T) {
                as.setT(as.getT() + 1);
                double dp = ((double) SIZE / 2) / Math.sqrt(Math.pow(VR, 2) + Math.pow(as.getR() * VA, 2));
                as.setP(as.getP() + dp);
                as.setA(A0 + VA * as.getP());
                as.setR(R0 + VR * as.getP());
                as.repaint();
            } else {
                scheduler.shutdown();
            }
        };

        scheduler.scheduleAtFixedRate(task, 0, 100, TimeUnit.MILLISECONDS);
    }
}