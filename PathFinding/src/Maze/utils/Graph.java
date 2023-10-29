package Maze.utils;

import Maze.algorithms.BFS;
import Maze.managers.PathsManager;
import Maze.models.Maze;

import java.util.*;
import java.awt.*;
import java.util.List;
import Maze.Panel;

public class Graph {
    public List<Node> nodes = new ArrayList<>(); // kept in order by number
    private final Point startingNode;
    private int startingNodeNo;
    List<List<Integer>> matrix = new ArrayList<>();
    Maze maze;
    private Panel panel;
    private final BFS bfs = new BFS(this);


    public int getStartingNodeNo() {
        return startingNodeNo;
    }

    private boolean insideMatrix(int n, int m, int newX, int newY) {
        return newX >= 0 && newX < n && newY < m && newY >= 0;
    }

    private List<List<Integer>> generateNeighbours(List<List<Integer>> matrix, int noFreeCells) {
        int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

        List<List<Integer>> neighbours = new ArrayList<>();
        for(int i = 0; i < noFreeCells - 1; ++i)
            neighbours.add(new ArrayList<>());

        for(int i = 0; i < matrix.size(); ++i) {
            for(int j = 0; j < matrix.get(i).size(); ++j) {
                if(matrix.get(i).get(j) != 0) {
                    for(int q = 0; q < 4; ++q) {
                        int newX = i + dx[q];
                        int newY = j + dy[q];
                        if(insideMatrix(matrix.size(), matrix.get(i).size(), newX, newY)) {
                            if(matrix.get(newX).get(newY) != 0)
                                neighbours.get(matrix.get(i).get(j) - 1).add(matrix.get(newX).get(newY));
                        }
                    }
                }
            }
        }

        return neighbours;
    }

    public Graph(Maze maze, Point startingNode, Panel panel) {
        this.startingNode = startingNode;
        constructGraphFromMaze(maze);
        this.maze = maze;
        this.panel = panel;
    }

    public void constructGraphFromMaze(Maze maze) {
        this.matrix = maze.getIntMaze();

        int number = 1;
        for(int i = 0; i < matrix.size(); ++i) {
            for (int j = 0; j < matrix.get(i).size(); ++j){
                if(this.matrix.get(i).get(j) == 1) {
                    this.matrix.get(i).set(j, number);
                    ++number;
                }
            }
        }

        List<List<Integer>> neighbours = this.generateNeighbours(matrix, number);

        for(int i = 0; i < neighbours.size(); ++i) {

            //search the pos of the current node
            Point pos = null;
            Point realPos = null;
            for(int j = 0; j < this.matrix.size() && pos == null; ++j)
                for(int q = 0; q < this.matrix.get(j).size() && pos == null; ++q)
                    if(this.matrix.get(j).get(q) == i + 1) {
                        pos = new Point(j, q);
                        realPos = maze.getCellMaze().get(j).get(q).getPosition();
                    }

            this.nodes.add(new Node(i + 1, neighbours.get(i), pos, realPos));
        }

        this.startingNodeNo = matrix.get(startingNode.x).get(startingNode.y);
    }

    private List<Point> reconstructPath(Node destinationNode) {
        List<Point> path = new ArrayList<>();
        Node currentNode = destinationNode;
        path.add(currentNode.getRealPos());

        while(currentNode.getNumber() != startingNodeNo && currentNode.getPrevious() != null ) {
            currentNode = currentNode.getPrevious();
            path.add(currentNode.getRealPos());
        }

        return path;
    }

    public void reconstructAllPaths(PathsManager pathsManager) {
        bfs.pathsFindingBFS();

        List<List<Point>> paths = new ArrayList<>();
        int n = this.matrix.size();
        int m = this.matrix.get(0).size();

        for(int i = 0; i < n; ++i) {
            if(this.matrix.get(i).get(0) != 0) {
                if(this.nodes.get(this.matrix.get(i).get(0) - 1).getPrevious() != null) {
                    paths.add(reconstructPath(this.nodes.get(this.matrix.get(i).get(0) - 1)));
                }
                else {
                    if(this.matrix.get(i).get(0) != 0)
                        maze.getCellMaze().get(i).get(0).setCellColor(panel.impossibleCell);
                }

            }
        }
        for(int j = 1; j < m - 1; ++j) {
            if(this.matrix.get(n-1).get(j) != 0) {
                if(this.nodes.get(this.matrix.get(n-1).get(j) - 1).getPrevious() != null) {
                    paths.add(reconstructPath(this.nodes.get(this.matrix.get(n-1).get(j) - 1)));
                }
                else {
                    if(this.matrix.get(n-1).get(j) != 0)
                        maze.getCellMaze().get(n-1).get(j).setCellColor(panel.impossibleCell);
                }
            }
        }
        for(int i = n - 1; i >= 0; --i) {
            if (this.matrix.get(i).get(m - 1) != 0) {
                if (this.nodes.get(this.matrix.get(i).get(m - 1) - 1).getPrevious() != null) {
                    paths.add(reconstructPath(this.nodes.get(this.matrix.get(i).get(m - 1) - 1)));
                } else {
                    if (this.matrix.get(i).get(m - 1) != 0)
                        maze.getCellMaze().get(i).get(m - 1).setCellColor(panel.impossibleCell);
                }

            }
        }
        for(int j = m - 2; j >= 1 ; --j) {
            if(this.matrix.get(0).get(j) != 0) {
                if(this.nodes.get(this.matrix.get(0).get(j) - 1).getPrevious() != null) {
                    paths.add(reconstructPath(this.nodes.get(this.matrix.get(0).get(j) - 1)));
                }
                else {
                    if(this.matrix.get(0).get(j) != 0)
                        maze.getCellMaze().get(0).get(j).setCellColor(panel.impossibleCell);
                }
            }
        }

        pathsManager.setPaths(paths);
    }
}
