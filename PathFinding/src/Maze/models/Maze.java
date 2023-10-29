package Maze.models;
import Maze.Panel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Maze {
    private final List<List<Cell>> cellMaze = new ArrayList<>();
    private List<List<Integer>> intMaze;
    private int startingPointX;
    private int startingPointY;
    private Panel panel;

    public Point startingCell;

    public List<List<Cell>> getCellMaze() {
        return cellMaze;
    }

    public void setStartingPoint(Dimension panelSize) {
        this.startingPointX = panelSize.width / 2 - intMaze.get(0).size() * (panel.getCellSize() / 2);
        this.startingPointY = panelSize.height / 2 - intMaze.size() * (panel.getCellSize() / 2);
    }

    public int getStartingPointX() {
        return startingPointX;
    }

    public int getStartingPointY() {
        return startingPointY;
    }

    public Maze(List<List<Integer>> maze, Color blockedCell, Color freeCell, Color startCell, Panel panel) {
        this.intMaze = maze;
        this.panel = panel;
        for(int i = 0; i < maze.size(); ++i) {
            List<Cell> temp = new ArrayList<>();
            for(int j = 0; j < maze.get(i).size(); ++j) {
                switch (maze.get(i).get(j)){
                    case 0 -> temp.add(new Cell(blockedCell, panel.getCellSize()));//blocked cell

                    case 1 -> temp.add(new Cell(freeCell, panel.getCellSize()));

                    case 2-> {
                        temp.add(new Cell(startCell, panel.getCellSize()));
                        startingCell = new Point(i,j);
                    }
                }

            }
            cellMaze.add(temp);
        }

    }

    public void editCell(Point pos, Color color) {
        this.cellMaze.get(pos.x).get(pos.y).setCellColor(color);
    }

    public List<List<Integer>> getIntMaze() {
        return intMaze;
    }

    public void drawMaze(Graphics g) {
        for(int i = 0; i < cellMaze.size(); ++i) {
            for (int j = 0; j < cellMaze.get(i).size(); ++j) {
                cellMaze.get(i).get(j).setPosition(new Point(startingPointX + panel.getCellSize() * j, startingPointY + panel.getCellSize() * i));
            }
        }

        for(int i = 0; i < cellMaze.size(); ++i)
            for(int j = 0; j < cellMaze.get(i).size(); ++j) {
                cellMaze.get(i).get(j).drawCell(g);
            }
    }
}
