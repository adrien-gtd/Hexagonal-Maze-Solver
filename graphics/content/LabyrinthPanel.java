package graphics.content;

import javax.swing.JPanel;

import graphics.LabyrinthWindow;
import graphics.model.Hexagon;
import graphics.model.LabyrinthModel;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class LabyrinthPanel extends JPanel implements MouseListener {
    private final LabyrinthWindow window;
    private final LabyrinthModel model;
    


    public LabyrinthPanel (LabyrinthWindow window) {
        this.window = window;
        model = window.getLabyrinthModel();
        setPreferredSize(new Dimension(500,500));                                                  //Add a size width et width * ratio
        setBackground(Color.WHITE);
        addMouseListener(this);
    }

    public void update() {
        repaint();
    }


    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawGrid(graphics);
    }

    private void drawGrid(Graphics graphics) {
        for(Hexagon hexagon : model.getHexagonList().getList())
            drawHexagon(hexagon, graphics);
    }

    private void drawHexagon (Hexagon hexagon, Graphics graphics) {
        graphics.setColor(hexagon.getColor());
        graphics.fillPolygon(hexagon.getPolygon());
        graphics.setColor(Color.BLACK);
        graphics.drawPolygon(hexagon.getPolygon());
        return;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        model.clicked(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
