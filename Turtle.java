
import java.awt.*;
import java.util.ArrayList;

public class Turtle {

    private int x;
    private int y;
    private double angle;
    private boolean penDown;
    private Color color;
    private ArrayList<Line> lineList;

    public Turtle(int x, int y, int angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.color = Color.BLACK;
        this.penDown = true;
        this.lineList = new ArrayList<>();
    }

    public void setPenDown(boolean penDown) {
        this.penDown = penDown;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void turn(double delta, boolean right) {
        if (right) this.angle -= delta;
        else this.angle += delta;
    }

    public void paint(Graphics g) {
        for (Line l : lineList) {
            l.paint(g);
        }
    }

    public void moveTo(int x, int y) {
        if (penDown) {
            lineList.add(new Line(this.x, this.y, x, y, this.color));
        }
        this.x = x;
        this.y = y;
    }

    public void goForward(int step) {
        /*int newX = 0, newY = 0;
        if (viewWine%360 == 0) {
            newY = this.y + step;
        }
        if (viewWine%360 == 45) {
            newX = this.x + step;
            newY = this.y + step;
        }
        if (viewWine%360 == 90) {
            newX = this.x + step;
        }
        if (viewWine%360 == 135) {
            newX = this.x + step;
            newY = this.y - step;
        }
        if (viewWine%360 == 180) {
            newY = this.y - step;
        }
        if (viewWine%360 == 225) {
            newX = this.x - step;
            newY = this.y - step;
        }
        if (viewWine%360 == 270) {
            newX = this.x - step;
        }
        if (viewWine%360 == 315) {
            newX = this.x - step;
            newY = this.y + step;
        }*/

        double angleInRadian = angle * (Math.PI /180 );
        int newX = (int) (x + (step * Math.cos(angleInRadian)));
        int newY = (int) (y + (step * Math.sin(angleInRadian)));

        if (penDown) {
            lineList.add(new Line(this.x, this.y, newX, newY, this.color));
        }
        this.x = newX;
        this.y = newY;
    }

    public void interpret(String commands) {
        String[] z = commands.split("\n");
        for (String str : z) {
            if (str.equals("pen up")) this.penDown = false;
            if (str.equals("pen down")) this.penDown = true;
            if (str.startsWith("move")) {
                String[] s = str.split(" ");
                moveTo(Integer.parseInt(s[1]), Integer.parseInt(s[2]));
            }
            if (str.startsWith("go")) {
                String[] s = str.split(" ");
                goForward(Integer.parseInt(s[1]));
            }
            if (str.startsWith("turn")) {
                String[] s = str.split(" ");
                if (s[1].equals("left")) {
                    turn(Integer.parseInt(s[2]), false);
                }
                if (s[1].equals("right")) {
                    turn(Integer.parseInt(s[2]), true);
                }
            }
            if (str.startsWith("color")) {
                String[] s = str.split(" ");
                System.out.println(s[1]);
                setColor(Color.decode(s[1]));
            }
        }
    }

}
