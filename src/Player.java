import java.awt.Graphics;
import java.awt.Color;


public class Player extends GameObject {

    public Player(int x, int y, ID id) {

        super(x, y, id);
    }

    public void tick() {
        //route methods go here
        x += velX;
        y += velY;

    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, 15, 15);
    }


}
