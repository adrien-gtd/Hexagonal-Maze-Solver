package graphics.content;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import graphics.LabyrinthWindow;
import graphics.model.Hexagon;
import graphics.model.LabyrinthModel;

import java.awt.*;
import java.awt.event.*;

/**
 * This panel controls the diplaying of the maze grid. 
 * No data is manipulated here, only the animations and the
 * display of the data realated to the maze grid is handled here.
 */
@SuppressWarnings("serial")
public class LabyrinthPanel extends JPanel implements MouseInputListener, ComponentListener {
    private final LabyrinthModel model;
    private Boolean isDraggedEnd = false;
    private Boolean isDraggedStart = false;
    private Hexagon hexagonDragged;
    private Point hexagonCenter;
    private Point previousPoint;
    


    public LabyrinthPanel (LabyrinthWindow window) {
        model = window.getLabyrinthModel();
        setPreferredSize(new Dimension(500,500));       //Default size of the window
        setBackground(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);
        addComponentListener(this);
    }

    /**
     * This method is called when the model requests to update the interface.
     */
    public void update() {
        repaint();
    }

    /**
     * This method is called every time the update method is called (see above)
     * It implies its execution every time the data chages in the model (new maze, new path, ...)
     * But also on certain events (dragging a box, resizing the window, ..)
     * Implemented to correctly display the current state of the maze. 
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if(model.getHexagonList() != null) {
            drawGrid(graphics);
        }
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

    /**
     * When called, this method gets the current grid from the model and draws it on the interface.
     */
    private void drawGrid(Graphics graphics) {
        for(Hexagon hexagon : model.getHexagonList().getList())
            drawHexagon(hexagon, graphics);
    }


    /**
     * Method used to draw a given hexagon on the screen.
     * @param hexagon   hexagon to draw
    */
    private void drawHexagon (Hexagon hexagon, Graphics graphics) {
        graphics.setColor(hexagon.getColor());
        graphics.fillPolygon(hexagon.getPolygon());
        graphics.setColor(Color.BLACK);
        graphics.drawPolygon(hexagon.getPolygon());
        return;
    }

    /**
     * On the press of a click, there are three possiblities:
     * -the user clicked on a empty / wall box -> handled by the model (changing the type)
     * -the user did not clicked on any box -> nothing
     * -the user click on the start / end box -> starting the drag animation
     */
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

    /**
     * When the user releases the mouse, two possibilities :
     * -If he was not dragging the end / start box -> nothing happenes. 
     * -If he was dragging the end / start box -> the model handles the modifications if necessary.
     * End of the dragging animantion.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if(isDraggedEnd || isDraggedStart) {
            model.dropped(e.getPoint(), hexagonDragged.isStart());
        }
        isDraggedEnd = false;
        isDraggedStart = false;
        repaint();
    }


    /**
     * If the user clicked on the starting box or the end box, one of the 
     * booleans isDragged is set to true. As long as he doesn't realese the click, 
     * an hexagon (drawn in the paintComponent method) follows the cursor of the user.
     */
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


    /**
     * When the window is resized, the model is notified. 
     * Used to center the maze and update the size of the 
     * boxes to take most of the available space on the screen.
     */
    @Override
    public void componentResized(ComponentEvent e) {
        model.setWindowSize(getSize());
    }




    /* -------------- unused -------------- */

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) {}

    @Override
    public void componentHidden(ComponentEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
