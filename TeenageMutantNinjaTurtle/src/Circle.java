import java.awt.*;

public class Circle {

    int x;
    int y;
    int radius;
    Color color;

    public Circle(int x, int y, int r, Color color) {
        this.x = x;
        this.y = y;
        this.radius = r;
        this.color = color;
    }

    public void paint(Graphics g) {
        g.setColor(this.color);
        g.drawOval(this.x-radius, this.y-radius, this.radius*2, this.radius*2);
    }

}
