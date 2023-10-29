package Maze.algorithms;

import Maze.utils.Graph;
import Maze.utils.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS {
    private final Graph graph;

    public BFS(Graph graph) {
        this.graph = graph;
    }

    public void pathsFindingBFS() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(graph.nodes.get(graph.getStartingNodeNo() - 1));
        Set<Integer> visited = new HashSet<Integer>();

        while(!queue.isEmpty()) {
            Node current = queue.element();
            queue.remove();
            for(var element : current.getNeighbours()) {
                if(graph.nodes.get(element - 1).getPrevious() == null && !visited.contains(element)) {
                    graph.nodes.get(element - 1).setPrevious(current);
                    visited.add(element);
                    queue.add(graph.nodes.get(element - 1));
                }
            }
        }

    }
}
