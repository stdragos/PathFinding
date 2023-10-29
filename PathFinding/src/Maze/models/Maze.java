package Maze.models;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Maze {
    public List<List<Cell>> cellMaze = new ArrayList<>();
    public List<List<Integer>> intMaze;
    private int startingPointX;
    private int startingPointY;

    public Point startingCell;

    public void setStartingPoint(Dimension panelSize) {
        this.startingPointX = panelSize.width / 2 - intMaze.get(0).size() * 30;
        this.startingPointY = panelSize.height / 2 - intMaze.size() * 30;
    }

    public Maze(List<List<Integer>> maze, Color blockedCell, Color freeCell, Color startCell) {
        this.intMaze = maze;

        for(int i = 0; i < maze.size(); ++i) {
            List<Cell> temp = new ArrayList<>();
            for(int j = 0; j < maze.get(i).size(); ++j) {
                switch (maze.get(i).get(j)){
                    case 0 -> temp.add(new Cell(blockedCell, 60));//blocked cell

                    case 1 -> temp.add(new Cell(freeCell, 60));

                    case 2-> {
                        temp.add(new Cell(startCell, 60));
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
        //System.out.println(cellMaze.size() + " " + cellMaze.size());
        for(int i = 0; i < cellMaze.size(); ++i) {
            for (int j = 0; j < cellMaze.get(i).size(); ++j) {
                cellMaze.get(i).get(j).setPosition(new Point(startingPointX + 60 * j, startingPointY + 60 * i));
            }
        }
        for(int i = 0; i < cellMaze.size(); ++i)
            for(int j = 0; j < cellMaze.get(i).size(); ++j) {
                cellMaze.get(i).get(j).drawCell(g);
            }
    }
}
