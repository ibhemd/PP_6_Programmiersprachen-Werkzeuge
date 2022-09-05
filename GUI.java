import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    public GUI () {
        // create JFrame
        this.setTitle("Teenage Mutant Ninja Turtle");
        this.setSize(1280,720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // set Layout
        this.setLayout(new BorderLayout());

        // add PaintArea
        PaintArea paintArea = new PaintArea();
        this.add(paintArea, BorderLayout.WEST);

        // create JPanel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setSize(200,720);

        // add JTextArea to panel
        JTextArea textArea = new JTextArea();
        panel.add(textArea);
        textArea.setSize(200,680);

        // add JButton to panel
        JButton button = new JButton();
        button.setText("Send");
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = textArea.getText();
                paintArea.getTurtle().interpret(s);
            }
        };
        button.addActionListener(actionListener);
        panel.add(button);

        // add Penal to Frame
        this.add(panel, BorderLayout.EAST);

        // make panel visible
        this.setVisible(true);

        /*
        // test
        paintArea.turtle.setPenDown(true);
        paintArea.turtle.goForward(20);
        paintArea.turtle.moveTo(105,505);
        paintArea.turtle.moveTo(200,250);
        */

    }

}
