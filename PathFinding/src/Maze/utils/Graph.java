package Maze.utils;

import Maze.models.Maze;

import java.util.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    private List<Node> nodes = new ArrayList<>(); // kept in order by number
    private Point startingNode;
    private int startingNodeNo;
    List<List<Integer>> matrix = new ArrayList<>();
    Maze maze;

    private boolean insideMatrix(int n, int m, int newX, int newY) {
        return newX >= 0 && newX < n && newY < m && newY >= 0;
    }

    private List<List<Integer>> generateNeighbours(Maze maze, List<List<Integer>> matrix, int noFreeCells) {
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

    public Graph(Maze maze, Point startingNode) {
        this.startingNode = startingNode;
        constructGraphFromMaze(maze);
        this.maze = maze;

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




        List<List<Integer>> neighbours = this.generateNeighbours(maze, matrix, number);

        for(int i = 0; i < neighbours.size(); ++i) {

            //search the pos of the current node
            Point pos = null;
            for(int j = 0; j < this.matrix.size() && pos == null; ++j)
                for(int q = 0; q < this.matrix.get(0).size() && pos == null; ++q)
                    if(this.matrix.get(j).get(q) == i + 1)
                        pos = new Point(j, q);

            this.nodes.add(new Node(i + 1, neighbours.get(i), pos));
        }

        this.startingNodeNo = matrix.get(startingNode.x).get(startingNode.y);

    }

    private void pathsFindingBFS() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(this.nodes.get(startingNodeNo - 1));
        Set<Integer> visited = new HashSet<Integer>();

        while(!queue.isEmpty()) {
            Node current = queue.element();
            queue.remove();
            for(var element : current.getNeighbours()) {
                if(this.nodes.get(element - 1).getPrevious() == null && !visited.contains(element)) {
                    this.nodes.get(element - 1).setPrevious(current);
                    visited.add(element);
                    queue.add(this.nodes.get(element - 1));
                }
            }
        }

    }

    private List<Point> reconstructPath(Node destinationNode) {
        List<Point> path = new ArrayList<>();
        Node currentNode = destinationNode;
        path.add(currentNode.getPos());

        while(currentNode.getNumber() != startingNodeNo && currentNode.getPrevious() != null ) {
            currentNode = currentNode.getPrevious();
            path.add(currentNode.getPos());
        }

        return path;
    }

    public List<List<Point>> reconstructAllPaths() {
        pathsFindingBFS();

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
                        maze.cellMaze.get(i).get(0).setCellColor(new Color(255, 0, 0));
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
                        maze.cellMaze.get(n-1).get(j).setCellColor(new Color(255, 0, 0));
                }
            }
        }
        for(int i = n - 1; i >= 0; --i) {
            if (this.matrix.get(i).get(m - 1) != 0) {
                if (this.nodes.get(this.matrix.get(i).get(m - 1) - 1).getPrevious() != null) {
                    paths.add(reconstructPath(this.nodes.get(this.matrix.get(i).get(m - 1) - 1)));
                } else {
                    if (this.matrix.get(i).get(m - 1) != 0)
                        maze.cellMaze.get(i).get(m - 1).setCellColor(new Color(255, 0, 0));
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
                        maze.cellMaze.get(0).get(j).setCellColor(new Color(255, 0, 0));
                }
            }
        }



        return paths;
    }
}
