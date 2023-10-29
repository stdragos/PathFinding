package Maze.utils;

import java.awt.*;
import java.util.List;

public class Node {
    private final int number;
    private final List<Integer> neighbours;
    private Node previous = null;
    private final Point pos;

    public int getNumber() {
        return this.number;
    }

    public Node getPrevious() {
        return this.previous;
    }
    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public List<Integer> getNeighbours() {
        return this.neighbours;
    }

    public Point getPos() {
        return this.pos;
    }

    Node(int number, List<Integer> neighbours, Point pos) {
        this.number = number;
        this.neighbours = neighbours;
        this.pos = pos;
    }
}
