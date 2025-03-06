package hw13;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MiniPaint extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
    private ButtonGroup modes;
    private JPanel area;
    private JLabel status;
    private Image buffer;
    private Image temporal;

    private final int POINTS = 1;
    private final int LINES = 2;
    private final int RECTANGLES = 3;
    private final int CIRCLES = 4;
    private int mode;
    private int x, y;

    public MiniPaint() {
        super("MiniPaint 1.0");
        JMenuBar barMenu = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        // New file option
        JMenuItem newOption = new JMenuItem("New", 'N');
        newOption.addActionListener(this);
        newOption.setActionCommand("New");
        fileMenu.add(newOption);
        fileMenu.addSeparator();
        // Quit app option
        JMenuItem quitOption = new JMenuItem("Quit", 'Q');
        quitOption.addActionListener(this);
        quitOption.setActionCommand("Quit");
        fileMenu.add(quitOption);
        // Add both to menu bar
        barMenu.add(fileMenu);

        modes = new ButtonGroup();
        JMenu modeMenu = new JMenu("Mode");

        // Points mode
        JRadioButtonMenuItem pointsOption = new JRadioButtonMenuItem("Points", true);
        pointsOption.addActionListener(this);
        pointsOption.setActionCommand("Points");
        modeMenu.add(pointsOption);
        modes.add(pointsOption);
        // Lines mode
        JRadioButtonMenuItem linesOption = new JRadioButtonMenuItem("Lines");
        linesOption.addActionListener(this);
        linesOption.setActionCommand("Lines");
        modeMenu.add(linesOption);
        modes.add(linesOption);
        // Rectangles mode
        JRadioButtonMenuItem rectanglesOption = new JRadioButtonMenuItem("Rectangles");
        rectanglesOption.addActionListener(this);
        rectanglesOption.setActionCommand("Rectangles");
        modeMenu.add(rectanglesOption);
        modes.add(rectanglesOption);
        // Circles mode
        JRadioButtonMenuItem circlesOption = new JRadioButtonMenuItem("Circles");
        circlesOption.addActionListener(this);
        circlesOption.setActionCommand("Circles");
        modeMenu.add(circlesOption);
        modes.add(circlesOption);
        // Add modes menu into bar menu
        barMenu.add(modeMenu);

        area = new JPanel();
        area.addMouseListener(this);
        area.addMouseMotionListener(this);
        status = new JLabel("Status", JLabel.LEFT);
        // Assign menu bar
        setJMenuBar(barMenu);
        // Add graphic zone
        getContentPane().add(area, BorderLayout.CENTER);
        // Add status bar
        getContentPane().add(status, BorderLayout.SOUTH);

        mode = POINTS;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
        setResizable(false);

        buffer = area.createImage(area.getWidth(), area.getHeight());
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {}

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        x = mouseEvent.getX();
        y = mouseEvent.getY();
        temporal = area.createImage(area.getWidth(), area.getHeight());
        temporal.getGraphics().drawImage(buffer, 0, 0, this);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        buffer.getGraphics().drawImage(temporal, 0, 0, this);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        setCursor(Cursor.getDefaultCursor());
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        Graphics g = temporal.getGraphics();
        switch (mode) {
            case POINTS:
                g.fillOval(mouseEvent.getX(), mouseEvent.getY(), 5, 5);
                area.getGraphics().drawImage(temporal, 0, 0, this);
                break;
            case LINES:
                g.drawImage(buffer, 0, 0, area);
                g.drawLine(x, y, mouseEvent.getX(), mouseEvent.getY());
                area.getGraphics().drawImage(temporal, 0, 0, this);
                break;
            case RECTANGLES:
                g.drawImage(buffer, 0, 0, area);
                g.drawRect(x, y, mouseEvent.getX()-x, mouseEvent.getY()-y);
                area.getGraphics().drawImage(temporal, 0, 0, this);
                break;
            case CIRCLES:
                g.drawImage(buffer, 0, 0, area);
                g.drawOval(x, y, mouseEvent.getX()-x, mouseEvent.getY()-y);
                area.getGraphics().drawImage(temporal, 0, 0, this);
                break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        status.setText("x=" + mouseEvent.getX() + ",y=" + mouseEvent.getY());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();
        if (command.equals("New")) {
            area.getGraphics().clearRect(0, 0, area.getWidth(), area.getHeight());
        } else {
            if (command.equals("Quit")) {
                if (JOptionPane.showConfirmDialog(this,
                        "Are you sure of leaving now?", "Confirmation", JOptionPane.YES_NO_OPTION)
                                == JOptionPane.YES_OPTION)
                {
                    dispose();
                    System.exit(0);
                }
            } else if (command.equals("Points")) {
                mode = POINTS;
            } else if (command.equals("Lines")) {
                mode = LINES;
            } else if (command.equals("Rectangles")) {
                mode = RECTANGLES;
            } else if (command.equals("Circles")) {
                mode = CIRCLES;
            }
        }
    }

    public static void main(String[] args) {
        new MiniPaint();
    }
}
