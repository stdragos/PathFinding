import models.Maze;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Panel extends JPanel {
    private List<List<Integer>> intMaze = new ArrayList<>();
    private Maze maze;

    private void readMatrix() {
        try {
            File myFile = new File("matrix.in");
            Scanner myReader = new Scanner(myFile);
            int n = myReader.nextInt();
            int m = myReader.nextInt();

            for(int i = 0; i < n; ++i) {
                List<Integer> temp = new ArrayList<>();
                for(int j = 0; j < m; ++j)
                    temp.add(myReader.nextInt());
                intMaze.add(temp);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public Panel() {
        readMatrix();
        setBackground(new Color(255, 255, 255));
        maze = new Maze(intMaze, new Color(0,0,0), new Color(255,255,255), new Color(255, 0, 0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Cell cell = new Cell(new Color(234, 185, 185), new Point(100,100), 50);
        //cell.drawCell(g);
        this.maze.drawMaze(g);
    }
}
