public class Line implements Comparable{

    private final int startX;
    private final int startY;
    private final int endX;
    private final int endY;
    public final int length;

    public Line(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;

        int[] p1 = {startX, startY};
        int[] p2 = {endX, endY};
        this.length = (int) getDistance(p1, p2); // ist das so richtig?
    }

    public static double getDistance(int[] p1, int[] p2) {
        return Math.sqrt(Math.pow((p1[0]-p2[0]),2) + Math.pow((p1[1]-p2[1]),2));
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public String toString() {
        return startX + " " + startY + " " + endX + " " + endY;
    }

    @Override
    public int compareTo(Object o) {
        // wenn o keine Line ist return -1
        if (o.getClass() != Line.class) {
            return -1;
        }
        return 0;
    } // noch schreiben
}
