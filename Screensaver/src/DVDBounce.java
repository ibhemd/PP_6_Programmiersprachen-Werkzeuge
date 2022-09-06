import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DVDBounce {

    private final int startX;
    private final int startY;
    private int x;
    private int y;
    private int xVelo;
    private int yVelo;
    private final int maxX;
    private final int maxY;
    ArrayList<Line> lines;
    ArrayList<Integer[]> collisions;

    public DVDBounce(int x, int y, int xVelo, int yVelo, int maxX, int maxY) {
        this.x = x;
        this.startX = this.x;
        this.y = y;
        this.startY = this.y;
        this.xVelo = xVelo;
        this.yVelo = yVelo;
        this.maxX = maxX;
        this.maxY = maxY;
        this.lines = new ArrayList<>();
        this.collisions = new ArrayList<>();
    }

    public void setRandomPosition() {
        Random random = new Random();
        this.x = random.nextInt(this.maxX);
        this.y = random.nextInt(this.maxY);
        //System.out.println(this.x + " " + this.y);
    }

    public void setRandomDirection() {
        int[] arr = {-1,1};
        Random random = new Random();
        this.xVelo = arr[random.nextInt(arr.length)];
        this.yVelo = arr[random.nextInt(arr.length)];
    }

    private enum CollisionDirection{
        NONE,
        HORIZONTAL,
        VERTICAL,
        BOTH
    }

    private CollisionDirection checkCollision() {
        if ((this.x == 0 && this.y == 0) // oben links
            || (this.x == this.maxX && this.y == this.maxY) // unten rechts
            || (this.x == 0 && this.y == this.maxY) // unten links
            || (this.x == this.maxX && this.y == 0)) { // oben rechts
            return CollisionDirection.BOTH;
        } else if (this.x == 0 || this.x == maxX) {
            return CollisionDirection.HORIZONTAL;
        } else if (this.y == 0 || this.y == maxY) {
            return CollisionDirection.VERTICAL;
        } else {
            return CollisionDirection.NONE;
        }
    }

    private void move() {
        this.x = Math.min(this.x + xVelo, maxX);
        this.y = Math.min(this.y + yVelo, maxY);
        //writeCoordinates();
    }

    private void changeDirection() {
        String s = this.xVelo + " " + this.yVelo;
        switch (s) {
            case "1 1" -> {
                this.xVelo = -1;
                this.yVelo = -1;
            }
            case "1 -1" -> {
                this.xVelo = -1;
                this.yVelo = 1;
            }
            case "-1 1" -> {
                this.x = 1;
                this.y = -1;
            }
            case "-1 -1" -> {
                this.xVelo = 1;
                this.yVelo = 1;
            }
        }
    }

    private void logCollision() {
        if (lines.size() == 0) {
            // wenn Liste leer, füge Line vom Start bis Kollision hinzu
            lines.add(new Line(startX,startY, this.x, this.y));
        } else {
            // füge Linie zwischen Endpunkt der letzten Kollision und aktueller Position hinzu
            Line lastLine = lines.get(lines.size()-1);
            lines.add(new Line(lastLine.getEndX(), lastLine.getEndY(), this.x, this.y));
        }
    }

    private double getShortestDistance() {
        double res = Double.MAX_VALUE;
        for (Line l : this.lines) {
            double d = Line.getDistance(new int[]{l.getStartX(), l.getStartY()}, new int[]{l.getEndX(), l.getEndY()});
            if (d < res) res = d;
            //System.out.println("\t" + d);
        }
        return res;
    }

    private double getTotalDistance() {
        double res = 0;
        for (Line l : lines) {
            res += Line.getDistance(new int[]{l.getStartX(), l.getStartY()}, new int[]{l.getEndX(), l.getEndY()});
        }
        return res;
    }

    private boolean checkRepeat() {
        // return true, wenn sich Linienmuster wiederholen
        if (lines.size() > 4) {
            List<String> stringList = new ArrayList<>();
            for (int i = 4; i < lines.size(); i++) {

                // erstelle String aus den letzten 4 Koordinaten
                StringBuilder b = new StringBuilder();
                for (int j = 1; j <= 4; j++) {
                    b.append(lines.get(i - j).toString());
                    b.append("\n");
                }
                String s = b.toString();

                if (stringList.contains(s)) {
                    // wenn String schon in Liste vorhanden ist return true
                    return true;
                } else {
                    // füge String der Liste hinzu (wenn er noch nicht drin ist)
                    stringList.add(s);
                }
            }
            // kein String ist in Liste
            return false;
        } else {
            // noch nicht genug Lines, um Wiederholungen haben zu können
            return false;
        }
    }

    public void frameTick() {
        while (checkCollision() != CollisionDirection.BOTH && !checkRepeat()) {
            move();
            if (checkCollision() == CollisionDirection.VERTICAL
                    || checkCollision() == CollisionDirection.HORIZONTAL) {
                // bei Kollision füge letzte Line der Liste hinzu
                logCollision();
                // bei Kollision ändere Richtung
                changeDirection();
            }
        }
        if (checkCollision() == CollisionDirection.BOTH) logCollision();
        System.out.print("\nEcke erreicht: ");
            if (this.x == 0 && this.y == 0) System.out.println("oben links");
            else if (this.x == 0 && this.y == maxY) System.out.println("unten links");
            else if (this.x == maxX && this.y == 0) System.out.println("oben rechts");
            else if (this.x == maxX && this.y == maxY) System.out.println("unten rechts");
            else System.out.println("keine -> Schleife");
        System.out.println("kürzeste Strecke: " + getShortestDistance());
        System.out.println("Anzahl Wegabschnitte: " + lines.size());
            /*for (Line l : lines) {
                System.out.println("\t" + l.toString());
            }*/
        System.out.println("Strecke insgesamt: " + getTotalDistance());
        //return true;

        // erzeuge .JAR
        System.out.println();
        Main.exportToJAR();

        // erzeuge Kommentare zur Verbesserung
        System.out.println();
        Main.writePerformanceComments();
    }

    private void writeCoordinates() {
        System.out.println("x: " + this.x + " || y: " + y);
    }
}
