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
        for(int i = 0; i < panel.getMaze().getCellMaze().size() && position == badPos; ++i) {
            for(int j = 0; j < panel.getMaze().getCellMaze().get(i).size() && position == badPos; ++j) {
                Point cellCoords = panel.getMaze().getCellMaze().get(i).get(j).getPosition();

                if(Point2D.distance(cellCoords.x + (int)(panel.getCellSize()/2), cellCoords.y + (int)(panel.getCellSize()/2), e.getX(), e.getY()) <= (int)(panel.getCellSize()/2)){
                    position = new Point(i,j);
                }
            }
        }
        if(position != badPos){//refill cells with basic colors
            if(panel.getMaze().getIntMaze().get(position.x).get(position.y) != 0) {
            for(int i = 0; i < panel.getMaze().getCellMaze().size(); ++i) {
                for (int j = 0; j < panel.getMaze().getCellMaze().get(i).size(); ++j) {
                    if(Objects.equals(panel.getMaze().getCellMaze().get(i).get(j).getCellColor(), panel.startCell))
                        panel.getMaze().getCellMaze().get(i).get(j).setCellColor(panel.freeCell);
                    if(Objects.equals(panel.getMaze().getCellMaze().get(i).get(j).getCellColor(), panel.impossibleCell))
                        panel.getMaze().getCellMaze().get(i).get(j).setCellColor(panel.freeCell);
                    if(panel.getMaze().getIntMaze().get(i).get(j) != 0)
                        panel.getMaze().getIntMaze().get(i).set(j,1);
                }
            }
                panel.setWhichPath(0);
                panel.recalculatePath(position);
            }
        }
        panel.repaint();
        panel.setFocusable(true);
        panel.requestFocusInWindow();
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
