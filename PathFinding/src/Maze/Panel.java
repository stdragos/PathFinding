package Maze;

import Maze.listeners.MouseListener;
import Maze.listeners.NextBttnActionListener;
import Maze.listeners.PrevBttnActionListener;
import Maze.listeners.ResizeListenerPanel;
import Maze.models.*;
import Maze.utils.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Panel extends JPanel {
    public List<List<Integer>> intMaze = new ArrayList<>();
    public Maze maze;
    private Point startingPoint = new Point();
    public Color blockedCell = new Color(0,0,0);
    public Color freeCell = new Color(255,255,255);
    public Color startCell = new Color(255, 0, 0);
    public int whichPath = 0;
    public List<List<Point>> paths = new ArrayList<>();
    MouseListener mouseListener;

    public void setStartingPoint(Point startingPoint) {
        this.startingPoint = startingPoint;
    }

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

        addMouseListener(mouseListener = new MouseListener(this));
        JButton previousBttn = new JButton("<<");
        previousBttn.setBounds(200,100,95,30);
        previousBttn.setFont(new Font("Arial", Font.PLAIN, 20));
        this.add(previousBttn);
        previousBttn.addActionListener(new PrevBttnActionListener(this));

        JButton nextBttn = new JButton(">>");
        nextBttn.setBounds(200,100,95,30);
        nextBttn.setFont(new Font("Arial", Font.PLAIN, 20));
        this.add(nextBttn);
        nextBttn.addActionListener(new NextBttnActionListener(this));

        maze = new Maze(this , intMaze, blockedCell, freeCell, startCell);

        Panel.this.repaint();
    }

    public void recalculatePath(Point startingPoint) {
        this.startingPoint = startingPoint;
        maze.cellMaze.get(startingPoint.x).get(startingPoint.y).setCellColor(new Color(0,255,0));

        Graph graph = new Graph(maze, startingPoint);
        paths = graph.reconstructAllPaths();

        if(!paths.isEmpty()) {
            for (int i = 0; i < paths.get(0).size() - 1; ++i)
                maze.editCell(paths.get(0).get(i), new Color(0, 255, 255));
        }

        Panel.this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        maze.setStartingPoint(this.getSize());
        this.maze.drawMaze(g);
    }
}
