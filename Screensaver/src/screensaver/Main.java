package screensaver;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        boolean valideCoordinates = true;
        boolean valideVelocitys = true;
        boolean valideNumberOfArguments = false;
        boolean valideArguments = false;

        Integer[] arguments;

        int v = 0;
        // Scanner für Tastatureingabe zur Testauswahl
        System.out.print("Test Szenario Nr. ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        try {
            v = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            // do nothing
        }
        scanner.close();

        // setze Testauswahl fix
        /*v = 1;*/

        // Testauswahl
        switch (v) {
            default -> {
                arguments = new Integer[0]; // Fehler, da Länge = 0
            }
            case 1 -> {
                arguments = new Integer[6]; // alle Werte werden automatisch gesetzt -> Zufall
            }
            case 2 -> {
                arguments = new Integer[]{50, 10, 1, 1, 500, 500}; // alle Werte gesetzt -> Schleife
            }
            case 3 -> {
                arguments = new Integer[]{50, 50, 1, 1, 500, 500}; // alle Werte gesetzt -> unten rechts (geht einfach diagonal durch)
            }
            case 4 -> {
                arguments = new Integer[]{50, 50, 1, -1, 100, 100}; // alle Werte gesetzt -> oben rechts (geht einfach diagonal durch)
            }
            case 5 -> {
                arguments = new Integer[]{35, 35, -1, -1, 300, 350}; // alle Werte gesetzt -> oben links (geht einfach diaginal durch)
            }
            case 6 -> {
                arguments = new Integer[]{null, null, -1, 1, 400, 650}; // Koordinaten nicht gesetzt -> Zufall
            }
            case 7 -> {
                arguments = new Integer[]{150, 75, null, null, 400, 650}; // Direction nicht gesetzt -> Zufall
            }
            case 8 -> {
                arguments = new Integer[]{50, 50, 0, 1, 100, 100}; // Direction zufällig -> oben links oder oben rechts (geht einfach diagonal durch)
            }
        }

        // prüfe und ersetze ungültige Werte
        if (arguments.length == 6) {
            if (arguments[0] == null || arguments[1] == null
                || arguments[0] < 0 || arguments[1] < 0) {
                valideCoordinates = false;
            }
            if (arguments[2] == null || arguments[3] == null
                || (arguments[2] != 1 && arguments[2] != -1)
                || (arguments[3] != 1 && arguments[3] != -1)) {
                valideVelocitys = false;
            }
            if (arguments[4] == null || arguments[5] == null
                    || arguments[4] < 0 || arguments[5] < 0) {
                // setze zufällige Max-ints, falls diese ≤0 oder null sind
                Random random = new Random();
                arguments[4] = random.nextInt(1080);
                arguments[5] = random.nextInt(720);
            }
            valideNumberOfArguments = true;
        }

        // teste, ob Format der Argumente = int
        try {
            for (int i : arguments) {
                int z = Integer.parseInt(String.valueOf(i));
            }
        } catch (NullPointerException e) {
            // if error, do nothing
        }

        // Ausgabe
        if (valideNumberOfArguments) {
            // erzeuge Instanzen
            if (!valideCoordinates && !valideVelocitys) { // zufällige Direction & Koordinaten
                DVDBounce dvdBounce = new DVDBounce(
                        0, // x
                        0, // y
                        0, // xVelo
                        0, // yVelo
                        arguments[4], // maxX
                        arguments[5]); // maxY
                dvdBounce.setRandomPosition();
                dvdBounce.setRandomDirection();
                dvdBounce.frameTick();
            } else if (!valideCoordinates) { // zufällige Koordinaten
                DVDBounce dvdBounce = new DVDBounce(
                        0, // x
                        0, // y
                        arguments[2], // xVelo
                        arguments[3], // yVelo
                        arguments[4], // maxX
                        arguments[5]); // maxY
                dvdBounce.setRandomPosition();
                dvdBounce.frameTick();
            } else if (!valideVelocitys) { // zufällige Direction
                DVDBounce dvdBounce = new DVDBounce(
                        arguments[0], // x
                        arguments[1], // y
                        0, // xVelo
                        0, // yVelo
                        arguments[4], // maxX
                        arguments[5]); // maxY
                dvdBounce.setRandomDirection();
                dvdBounce.frameTick();
            } else { // alle Parameter gegeben
                DVDBounce dvdBounce = new DVDBounce(
                        arguments[0], // x
                        arguments[1], // y
                        arguments[2], // xVelo
                        arguments[3], // yVelo
                        arguments[4], // maxX
                        arguments[5]); // maxY
                dvdBounce.frameTick();
            }
        } else {
            System.out.println("\ndu bist zu dumm, Zahlen einzugeben\n");
            System.out.println("Koordinaten müssen im Intervall [0,maxX] für x bzw. [0,maxY] für y liegen");
            System.out.println("Direction muss -1 oder 1 sein");
            System.out.println("max-Werte im Intervall [0,1920] für x bzw. [0,1080] für y liegen");
        }
    }

    public static void exportToJAR() {
        System.out.println("[...] exportiere .JAR [...]");
        System.out.println("Erzeuge .class-Dateien...");
        System.out.println("-->\t\t\tjavac Main.java Line.java DVDBounce.java");
        System.out.println("Erzeuge .jar-Datei ...");
        System.out.println("-->\t\t\tjar cfe Screensaver.jar screensaver.Main screensaver");
        System.out.println("Führe .jar-Datei aus ...");
        System.out.println("-->\t\t\tjava -jar Screensaver.jar");
    }

    public static void writePerformanceComments() {
        System.out.println("[...] hier könnten Ihre Kommentare stehen [...]");
        System.out.println("-->\t\tLesbarkeit 1) setRandomPosition und setRandomDirection aus screensaver.DVDBounce auslagern");
        System.out.println("-->\t\tLesbarkeit 2) eigene Prüfmethode für die Validierung der Parameter schreiben, damit die main-Methode nur einen Aufruf hat");
        System.out.println("-->\t\tPerformance 1) shortestDistance und totalDistance beim Erzeugen von Lines über globale Variablen direkt Vergleichen; ohne for-Schleife oder compareTo");
    }

}
