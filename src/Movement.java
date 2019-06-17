import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class Movement extends MouseAdapter {
    private int x,y;

    private Handler handler;
    private Player player;
    private Game game;

    public Movement( Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e){}

    @Override
    public void mouseReleased(MouseEvent e) {}


    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public void mouseDragged(MouseEvent e) {
        LinkedList<GameObject> jag = handler.object;
        for (GameObject player : jag) {
            if (mouseOver(e.getX(),e.getY(),player.getX(),player.getY(),16,16)) {
                player.setX(e.getX() - 8);
                player.setY(e.getY() - 8);
                break;
            }
        }
    }

    public void mouseMoved(MouseEvent e) {}

    public void tick() {

    }

    public void render(Graphics g) {}

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else return false;
        } else return false;
    }
}