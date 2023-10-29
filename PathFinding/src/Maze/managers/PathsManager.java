package Maze.managers;

import Maze.utils.Node;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class PathsManager {
    private List<List<Point>> paths = new ArrayList<>();
    private int whichPath = 0;
    private Dimension initialPanelDim;

    public int getWhichPath() {
        return whichPath;
    }

    public void setInitialPanelDim(Dimension initialPanelDim) {
        this.initialPanelDim = initialPanelDim;
    }

    public void setWhichPath(int whichPath) {
        this.whichPath = whichPath;
    }

    public void setPaths(List<List<Point>> paths) {
        this.paths = paths;
    }

    public List<List<Point>> getPaths() {
        return paths;
    }

    public PathsManager() {
    }

    public void paintPath(Graphics g, Dimension newPanelDim, Color pathColor, Color arrowColor, int cellSize) {
        if(!paths.isEmpty()) {
            for (int i = 0; i < paths.get(whichPath).size() - 1; ++i)
            {
                int panelDifferenceW = newPanelDim.width - this.initialPanelDim.width;
                int panelDifferenceH = newPanelDim.height - this.initialPanelDim.height;

                Point A = paths.get(whichPath).get(i);
                Point B = paths.get(whichPath).get(i+1);

                Graphics2D g2d = (Graphics2D) g;
                AffineTransform initAT = g2d.getTransform();

                double angle = Math.atan2(B.y - A.y, B.x - A.x);

                AffineTransform affineTransform = AffineTransform.getTranslateInstance(B.x + (int)(cellSize / 2) + panelDifferenceW, B.y + (int)(cellSize / 2) + panelDifferenceH);
                affineTransform.concatenate(AffineTransform.getRotateInstance( angle - Math.PI));
                g2d.setTransform(affineTransform);

                Polygon arrowHead = new Polygon();


                g2d.setColor(pathColor);
                g2d.setStroke(new BasicStroke(7));
                g2d.drawLine(0, 0, cellSize, 0);

                g2d.setColor(arrowColor);
                arrowHead.addPoint( 10 + cellSize,0);
                arrowHead.addPoint( -10 + cellSize,-10);
                arrowHead.addPoint( -10 + cellSize,10);
                g2d.fill(arrowHead);

                g2d.setTransform(initAT);
            }
        }
    }
}
