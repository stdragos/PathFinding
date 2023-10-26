package Maze.utils;

import Maze.models.Maze;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Node> nodes = new ArrayList<>();
    private Node startingNode;

    Graph(List<Node> nodes, Node startingNode) {
        this.nodes = nodes;
        this.startingNode = startingNode;
    }

    Graph(Maze maze, Point startingNode) {
        constructGraphFromMaze(maze, startingNode);
    }

    public void constructGraphFromMaze(Maze maze, Point starting) {
        List<List<Integer>> matrix = maze.getIntMaze();
        int number = 1;
        for(var line : matrix)
            for(var element : line) {
                element = number;
                ++number;
            }
    }
}
