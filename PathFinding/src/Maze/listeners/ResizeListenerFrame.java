package Maze.listeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ResizeListenerFrame extends ComponentAdapter {
    private JFrame frame;

    public ResizeListenerFrame(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void componentResized(ComponentEvent event) {
        Rectangle currentWindow = event.getComponent().getBounds();
        frame.setSize(new Dimension(currentWindow.width, currentWindow.height));
        frame.repaint();
       // System.out.println(currentWindow.width+" "+ currentWindow.height);
    }
}
