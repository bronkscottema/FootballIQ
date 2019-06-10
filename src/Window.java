import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.*;


public class Window extends Canvas {
    public Window (int width, int height, String title, Game game) {
        JFrame frame = new JFrame (title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        JTextArea offense = new JTextArea("offense");
        offense.setBounds(100, 32, 50, 25);
        frame.add(offense);
        //top left offense play
        JTextArea offensivePlay = new JTextArea("insert play name");
        offensivePlay.setBounds(25, 62, 200, 25);
        frame.add(offensivePlay);
        //top right defense
        JTextArea defense = new JTextArea("defense");
        defense.setBounds(550, 32, 50, 25);
        frame.add(defense);
        //top left defensive play
        JTextArea defensivePlay = new JTextArea("insert play name");
        defensivePlay.setBounds(475, 62, 200, 25);
        frame.add(defensivePlay);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
}
