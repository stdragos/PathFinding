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
            if(!panel.getPaths().isEmpty()) {

                for (int i = 0; i < panel.getPaths().get(panel.getWhichPath()).size() - 1; ++i) {
                    panel.getMaze().editCell(panel.getPaths().get(panel.getWhichPath()).get(i), new Color(255, 255, 255));
                }

                this.panel.setWhichPath(panel.getWhichPath() - 1);
                if (this.panel.getWhichPath() < 0)
                    this.panel.setWhichPath(panel.getPaths().size() - 1);

                for (int i = 0; i < panel.getPaths().get(panel.getWhichPath()).size() - 1; ++i) {
                    panel.getMaze().editCell(panel.getPaths().get(panel.getWhichPath()).get(i), new Color(0, 255, 255));
                }

                panel.repaint();
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(!panel.getPaths().isEmpty()) {
                for (int i = 0; i < panel.getPaths().get(panel.getWhichPath()).size() - 1; ++i) {

                    panel.getMaze().editCell(panel.getPaths().get(panel.getWhichPath()).get(i), new Color(255, 255, 255));
                }
                this.panel.setWhichPath((panel.getWhichPath() + 1) % panel.getPaths().size());
                for (int i = 0; i < panel.getPaths().get(panel.getWhichPath()).size() - 1; ++i) {
                    panel.getMaze().editCell(panel.getPaths().get(panel.getWhichPath()).get(i), new Color(0, 255, 255));
                }

                panel.repaint();
            }
        }
    }
}
