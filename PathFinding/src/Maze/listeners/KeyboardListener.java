package Maze.listeners;

import Maze.Panel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardListener implements KeyListener {
    final Panel panel;
    public KeyboardListener(Panel panel) {
        this.panel = panel;
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(!panel.getPathsManager().getPaths().isEmpty()) {

                panel.getPathsManager().setWhichPath(panel.getPathsManager().getWhichPath() - 1);
                if (panel.getPathsManager().getWhichPath() < 0)
                    panel.getPathsManager().setWhichPath(panel.getPathsManager().getPaths().size() - 1);
                panel.repaint();
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(!panel.getPathsManager().getPaths().isEmpty()) {
                panel.getPathsManager().setWhichPath((panel.getPathsManager().getWhichPath() + 1) % panel.getPathsManager().getPaths().size());
                panel.repaint();
            }
        }
    }
}
