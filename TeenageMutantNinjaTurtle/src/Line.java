import java.awt.*;

public class Line {

    int startX;
    int startY;
    int endX;
    int endY;
    Color color;

    public Line(int startX, int startY, int endX, int endY, Color color) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.color = color;
    }

    public void paint(Graphics g) {
        g.setColor(this.color);
        g.drawLine(startX, startY, endX, endY);
    }
}
