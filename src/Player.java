import java.awt.*;


public class Player extends GameObject {

    public Player(int x, int y, ID id) {

        super(x, y, id);
    }

    public void tick() {
        //route methods go here
        x += velX;
        y += velY;

        //stop stuff from going past the Game screen
        x = Game.clamp(x, 0, Game.WIDTH - 16);
        y = Game.clamp(y, 0, Game.HEIGHT - 38);

    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 16, 16);
    }
}