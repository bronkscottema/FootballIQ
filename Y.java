import java.awt.*;


public class Y extends GameObject {

    public Y(int x, int y, ID ID) {
        super(x, y, ID);
    }

    public void tick() {

    }
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 32, 32);
    }


}
