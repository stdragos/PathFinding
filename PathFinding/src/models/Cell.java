package models;

import java.awt.*;
import java.util.List;

public class Cell {
    private Color cellColor;
    private Point centerPosition;
    private int diameter;

    public Cell(Color cellColor, Point centerPosition, int diameter) {
        this.cellColor = cellColor;
        this.centerPosition = centerPosition;
        this.diameter = diameter;
    }

    public void drawCell(Graphics g) {
        g.setColor(cellColor);
        g.fillRect(this.centerPosition.x - diameter,this.centerPosition.y - diameter,  diameter,  diameter);
        g.setColor(new Color(0,0,0));
        g.drawRect(this.centerPosition.x - diameter,this.centerPosition.y - diameter,  diameter,  diameter);
    }
}
