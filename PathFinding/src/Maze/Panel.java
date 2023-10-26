package Maze;

import Maze.listeners.ResizeListenerPanel;
import Maze.models.*;

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

    public Panel(Dimension frameSize) {
        readMatrix();
        setBackground(new Color(255, 255, 255));
        this.addComponentListener(new ResizeListenerPanel(this));
        this.setSize(frameSize);
        //System.out.println(this.getSize());
        maze = new Maze(this , intMaze, new Color(0,0,0), new Color(255,255,255), new Color(255, 0, 0));
        Panel.this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        maze.setStartingPoint(this.getSize());
        this.maze.drawMaze(g);
        //System.out.println(this.getSize());
    }
}
