package Maze;

import Maze.listeners.ResizeListenerFrame;

import javax.swing.SwingUtilities;
import javax.swing.*;

public class Main {
    static JFrame frame;
    static Panel panel;

    private static void initUI() {
        frame = new JFrame("Graph Paint");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addComponentListener(new ResizeListenerFrame(frame));
        frame.setSize(800,800);
        frame.add(panel = new Panel(frame.getSize()));
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initUI();
            }

        });
    }
}