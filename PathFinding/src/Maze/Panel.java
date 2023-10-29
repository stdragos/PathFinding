package Maze;

import Maze.listeners.*;
import Maze.managers.PathsManager;
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
    private List<List<Integer>> intMaze = new ArrayList<>();
    private Maze maze;
    private Point startingPoint = new Point();
    public Color blockedCell = new Color(15,14,14);
    public Color freeCell = new Color(234, 224, 204);
    public Color startCell = new Color(63, 97, 45);
    public Color impossibleCell = new Color(250, 0, 63);
    public Color pathColor = new Color(34, 124, 157);
    public Color arrowColor = new Color(45, 48, 71);
    private int whichPath = 0;
    private int cellSize = 60;
    private List<List<Point>> paths = new ArrayList<>();
    MouseListener mouseListener;


    public int getCellSize() {
        return cellSize;
    }

    private PathsManager pathsManager = new PathsManager();

    public PathsManager getPathsManager() {
        return pathsManager;
    }

    public int getWhichPath() {
        return whichPath;
    }

    public void setWhichPath(int pathNo) {
        this.whichPath = pathNo;
    }

    public Maze getMaze() {
        return maze;
    }

    public List<List<Point>> getPaths() {
        return paths;
    }

    public void setStartingPoint(Point startingPoint) {
        this.startingPoint = startingPoint;
    }

    private void readMatrix() {
        try {
            File myFile = new File("matrix.in");
            Scanner myReader = new Scanner(myFile);
            while(myReader.hasNextLine()) {
                String line = myReader.nextLine();
                List<Integer> temp = new ArrayList<>();
                for(int i = 0; i < line.length(); ++i)
                    temp.add(Integer.parseInt(String.valueOf(line.charAt(i))));
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
        this.addKeyListener(new KeyboardListener(this));
        addMouseListener(mouseListener = new MouseListener(this));
        this.setFocusable(true);
        this.requestFocusInWindow();

        maze = new Maze(intMaze, blockedCell, freeCell, startCell, this);

        Panel.this.repaint();
    }

    public void recalculatePath(Point startingPoint) {
        this.startingPoint = startingPoint;
        maze.getCellMaze().get(startingPoint.x).get(startingPoint.y).setCellColor(startCell);

        Graph graph = new Graph(maze, startingPoint, this);
        graph.reconstructAllPaths(pathsManager);
        pathsManager.setInitialPanelDim(new Dimension(maze.getStartingPointX(), maze.getStartingPointY()));
        pathsManager.setWhichPath(0);

        Panel.this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        maze.setStartingPoint(this.getSize());
        this.maze.drawMaze(g);
        pathsManager.paintPath(g, new Dimension(maze.getStartingPointX(), maze.getStartingPointY()), pathColor, arrowColor, cellSize);
    }
}
