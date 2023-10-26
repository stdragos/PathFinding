package Maze.utils;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private int number;
    private List<Node> neighbours = new ArrayList<>();
    private Node previous;

    public int getNumber() {
        return this.number;
    }

    public Node getPrevious() {
        return this.previous;
    }

    Node(int number, List<Node> neighbours) {
        this.number = number;
        this.neighbours = neighbours;
    }
}
