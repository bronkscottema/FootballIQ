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

        //stop stuff from going past the game screen
        x = game.clamp(x, 0, game.WIDTH - 15);
        y = game.clamp(y, 0, game.HEIGHT - 37);

    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, 15, 15);
    }


}
