package Maze.listeners;

import Maze.Panel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrevBttnActionListener implements ActionListener {
    private final Panel panel;

    public PrevBttnActionListener(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < panel.paths.get(panel.whichPath).size() - 1; ++i) {
            panel.maze.editCell(panel.paths.get(panel.whichPath).get(i), new Color(255, 255, 255));
        }

        this.panel.whichPath = (panel.whichPath - 1);
        if(this.panel.whichPath < 0)
            this.panel.whichPath = panel.paths.size() - 1;

        for(int i = 0; i<panel.paths.get(panel.whichPath).size() - 1; ++i) {
            panel.maze.editCell(panel.paths.get(panel.whichPath).get(i), new Color(0, 255, 255));
        }

        panel.repaint();
    }
}