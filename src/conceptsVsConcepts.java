import java.awt.*;
import java.util.Scanner;

public class conceptsVsConcepts {

    //public static String conceptVsConcept

    public void tick() {

    }


    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        Font font = new Font("SansSerif", Font.PLAIN, 20);
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString("offense", 100, 32);
        g.drawString("defense", 500, 32);
        g.drawString("Twins Tight Inside Zone", 100, 50);
        g.drawString("4-3 Over Cover 3", 500, 50);
    }
}
