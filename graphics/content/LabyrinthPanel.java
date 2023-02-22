package graphics.content;

import javax.swing.JPanel;
import graphics.*;
import java.awt.*;
import maze.*;
import java.lang.Math;

@SuppressWarnings("serial")
public class LabyrinthPanel extends JPanel{
    private final LabyrinthWindow window;
    private Maze maze;
    private int hexagonSize;
    private int panelSizeX;
    private int panelSizeY;
    private int offset;

    private final Color emptyColor = Color.GRAY;
    private final Color wallColor = Color.BLACK;
    private final Color startColor = Color.GREEN;
    private final Color endColor = Color.RED;
    private final Color pathColor = Color.CYAN;
    


    public LabyrinthPanel (LabyrinthWindow window) {
        this.window = window;
        this.maze = window.getMaze();

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(300,300));
    }

    public void update() {
        maze = window.getMaze();
        panelSizeX = (int) this.getSize().getWidth();
        panelSizeY = (int) this.getSize().getHeight();
        hexagonSize = setHexagonSize();
        offset = 2 * hexagonSize;
        repaint();
    }

    public void drawGrid(Graphics graphics) {
        MazeBox[][] grid = maze.getGrid();
        int xIndex = 0;
        int currX;
        int currY = hexagonSize;

        for (MazeBox[] line : grid) {
            if(xIndex % 2 == 0) {
                currX = 0;
            }
            else { 
                currX = (int) ((Math.sqrt(3) / 2) * hexagonSize);
            }

            for (MazeBox box : line) {
                drawHexagon(currX, currY, graphics, box);
                currX += ((Math.sqrt(3) / 2) * hexagonSize) * 2;            //leave no gap between the hexagons
                }
            currY += 1.5 * hexagonSize;
            xIndex ++;
        }
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        drawGrid(graphics);
    }



    private void drawHexagon (int x, int y, Graphics g, MazeBox box) {
        Hexagon hexagon = new Hexagon(x + offset, y + offset, hexagonSize);
        switch (box.getType()) {
            case 'E':
                g.setColor(emptyColor);
                break;
            case 'W':
                g.setColor(wallColor);
                break;
            case 'A':
                g.setColor(endColor);
                break;
            default:
                g.setColor(startColor);
                break;
        }
        
        g.fillPolygon(hexagon.getPolygon());
        g.setColor(Color.BLACK);
        g.drawPolygon(hexagon.getPolygon());
        return;
    }

    private int setHexagonSize() {
        return 30;
    }
}
