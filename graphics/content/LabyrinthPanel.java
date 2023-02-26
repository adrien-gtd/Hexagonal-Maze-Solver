package graphics.content;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import graphics.LabyrinthWindow;
import graphics.model.Hexagon;
import graphics.model.LabyrinthModel;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class LabyrinthPanel extends JPanel implements MouseInputListener {
    private final LabyrinthWindow window;
    private final LabyrinthModel model;
    private Boolean isDraggedEnd = false;
    private Boolean isDraggedStart = false;
    private Hexagon hexagonDragged;
    private Point hexagonCenter;
    private Point previousPoint;
    


    public LabyrinthPanel (LabyrinthWindow window) {
        this.window = window;
        model = window.getLabyrinthModel();
        setPreferredSize(new Dimension(500,500));                                                  //Add a size width et width * ratio
        setBackground(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void update() {
        repaint();
    }


    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if(model.getHexagonList() != null)
            drawGrid(graphics);
        if (isDraggedEnd || isDraggedStart) {
            drawHexagon(
                new Hexagon(
                    hexagonDragged.getX(),
                    hexagonDragged.getY(),
                    hexagonDragged.getSize(),
                    Color.YELLOW
                ), 
                graphics);

            drawHexagon(
                new Hexagon(
                    hexagonCenter.x, 
                    hexagonCenter.y, 
                    hexagonDragged.getSize(), 
                    hexagonDragged.getColor()
                ), 
                graphics);  
        } 

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
        Hexagon answer = model.clicked(e.getPoint());
        if (answer == null)
            return;
        if (answer.isEnd() || answer.isStart()) {
            isDraggedEnd = answer.isEnd();
            isDraggedStart = answer.isStart();
            hexagonDragged = answer;
            previousPoint = e.getPoint();
            hexagonCenter = new Point(answer.getX(), answer.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(isDraggedEnd || isDraggedStart) {
            model.dropped(e.getPoint(), hexagonDragged.isStart());
        }
        isDraggedEnd = false;
        isDraggedStart = false;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        if(isDraggedEnd || isDraggedStart) {
            Point currentPt = e.getPoint();
                hexagonCenter.translate(
                    (int) (currentPt.getX() - previousPoint.getX()),
                    (int) (currentPt.getY() - previousPoint.getY())
                );
                previousPoint = currentPt;
                repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
