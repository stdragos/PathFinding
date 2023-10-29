package Maze.listeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ResizeListenerPanel extends ComponentAdapter {
    private JPanel panel;

    public ResizeListenerPanel(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void componentResized(ComponentEvent event) {
        Rectangle currentWindow = event.getComponent().getBounds();
        panel.setSize(new Dimension(currentWindow.width, currentWindow.height));
        panel.repaint();
    }
}