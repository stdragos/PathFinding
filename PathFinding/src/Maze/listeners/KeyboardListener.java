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
            if(!panel.paths.isEmpty()) {

                for (int i = 0; i < panel.paths.get(panel.whichPath).size() - 1; ++i) {
                    panel.maze.editCell(panel.paths.get(panel.whichPath).get(i), new Color(255, 255, 255));
                }

                this.panel.whichPath = (panel.whichPath - 1);
                if (this.panel.whichPath < 0)
                    this.panel.whichPath = panel.paths.size() - 1;

                for (int i = 0; i < panel.paths.get(panel.whichPath).size() - 1; ++i) {
                    panel.maze.editCell(panel.paths.get(panel.whichPath).get(i), new Color(0, 255, 255));
                }

                panel.repaint();
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(!panel.paths.isEmpty()) {
                for (int i = 0; i < panel.paths.get(panel.whichPath).size() - 1; ++i) {

                    panel.maze.editCell(panel.paths.get(panel.whichPath).get(i), new Color(255, 255, 255));
                }
                this.panel.whichPath = (panel.whichPath + 1) % panel.paths.size();
                for (int i = 0; i < panel.paths.get(panel.whichPath).size() - 1; ++i) {
                    panel.maze.editCell(panel.paths.get(panel.whichPath).get(i), new Color(0, 255, 255));
                }

                panel.repaint();
            }
        }
    }
}
