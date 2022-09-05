import javax.swing.*;
import java.awt.*;

public class PaintArea extends JPanel {

    public Turtle turtle;

    public PaintArea() {
        this.turtle = new Turtle(0,0,180);
        this.setPreferredSize(new Dimension(1060,720));
        this.setBackground(Color.WHITE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.turtle.paint(g);
        repaint();
    }

    public Turtle getTurtle() {
        return turtle;
    }

}
