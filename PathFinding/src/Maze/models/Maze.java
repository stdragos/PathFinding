package models;
package src;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Maze {
    private List<List<Cell>> cellMaze = new ArrayList<>();
    private List<List<Integer>> intMaze = new ArrayList<>();

    public Maze(Panel panel, List<List<Integer>> maze, Color blockedCell, Color freeCell, Color startCell, Point startingPoint) {
        this.intMaze = maze;

        for(int i = 0; i < maze.size(); ++i) {
            List<Cell> temp = new ArrayList<>();
            for(int j = 0; j < maze.get(i).size(); ++j) {
                switch (maze.get(i).get(j)){
                    case 0 -> temp.add(new Cell(blockedCell, new Point(100 + 60 * j, 100 + 60 * i), 60));//blocked cell

                    case 1 -> temp.add(new Cell(freeCell, new Point(100 + 60 * j, 100 + 60 * i), 60));

                    case 2-> temp.add(new Cell(startCell, new Point(100 + 60 * j, 100 + 60 * i), 60));
                }
                cellMaze.add(temp);
            }
        }

    }

    public void drawMaze(Graphics g) {
        for(var line : this.cellMaze)
            for(var cell : line)
                cell.drawCell(g);
    }
}
