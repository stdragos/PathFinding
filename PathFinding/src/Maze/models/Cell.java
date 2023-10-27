package Maze.models;

import java.awt.*;

public class Cell {
    private Color cellColor;
    private Point position;
    private int length;

    public void setPosition(Point point) {
        position = point;
    }

    public Point getPosition() {
        return position;
    }

    public Color getCellColor() {
        return cellColor;
    }

    public Cell(Color cellColor, int length) {
        this.cellColor = cellColor;
        this.length = length;
    }

    public void setCellColor(Color color) {
        this.cellColor = color;
    }

    public void drawCell(Graphics g) {
        g.setColor(cellColor);
        g.fillRect(this.position.x, this.position.y,  length,  length);
        g.setColor(new Color(0,0,0));
        g.drawRect(this.position.x,this.position.y,  length,  length);
    }

}
