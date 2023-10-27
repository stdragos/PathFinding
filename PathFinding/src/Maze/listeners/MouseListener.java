package Maze.listeners;

import Maze.Panel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Objects;

public class MouseListener implements javax.swing.event.MouseInputListener {
    private final Panel panel;

    public MouseListener(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point badPos = new Point(-1,-1);
        Point position = badPos;

        for(int i = 0; i < panel.maze.cellMaze.size() && position == badPos; ++i) {
            for(int j = 0; j < panel.maze.cellMaze.get(i).size() && position == badPos; ++j) {
                Point cellCoords = panel.maze.cellMaze.get(i).get(j).getPosition();
                cellCoords.x += 30;
                cellCoords.y += 30;
                if(Point2D.distance(cellCoords.x, cellCoords.y, e.getX(), e.getY()) <= 30){
                    position = new Point(i,j);
                }
            }
        }
        if(position != badPos){//refill cells with basic colors
            if(panel.maze.intMaze.get(position.x).get(position.y) != 0) {
            for(int i = 0; i < panel.maze.cellMaze.size(); ++i) {
                for (int j = 0; j < panel.maze.cellMaze.get(i).size(); ++j) {
                    if(Objects.equals(panel.maze.cellMaze.get(i).get(j).getCellColor(), new Color(0, 255, 255)))
                        panel.maze.cellMaze.get(i).get(j).setCellColor(new Color(255,255,255));
                    if(Objects.equals(panel.maze.cellMaze.get(i).get(j).getCellColor(), new Color(0, 255, 0)))
                        panel.maze.cellMaze.get(i).get(j).setCellColor(new Color(255,255,255));
                    if(Objects.equals(panel.maze.cellMaze.get(i).get(j).getCellColor(), new Color(255, 0,0)))
                        panel.maze.cellMaze.get(i).get(j).setCellColor(new Color(255,255,255));
                    if(panel.maze.intMaze.get(i).get(j) != 0)
                        panel.maze.intMaze.get(i).set(j,1);
                }
            }
                panel.whichPath = 0;
                panel.recalculatePath(position);

            /*for(int i = 0; i < panel.paths.get(panel.whichPath).size(); ++i) {

                panel.maze.editCell(panel.paths.get(panel.whichPath).get(i), new Color(255, 255, 255));
            }*/



            }
        }
        panel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
