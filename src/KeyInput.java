import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        System.out.println(key);
        for (int i  = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getID() == ID.WRZ) {
                if (key == KeyEvent.VK_W) tempObject.setVelY(-1);
                if (key == KeyEvent.VK_S) tempObject.setVelY(1);
                if (key == KeyEvent.VK_D) tempObject.setVelX(1);
                if (key == KeyEvent.VK_S) tempObject.setVelX(-1);
            }
            if (tempObject.getID() == ID.WRX) {
                if (key == KeyEvent.VK_UP) tempObject.setVelY(-1);
                if (key == KeyEvent.VK_DOWN) tempObject.setVelY(1);
                if (key == KeyEvent.VK_RIGHT) tempObject.setVelX(1);
                if (key == KeyEvent.VK_LEFT) tempObject.setVelX(-1);
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i  = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getID() == ID.WR) {
                if (key == KeyEvent.VK_W) tempObject.setVelY(0);
                if (key == KeyEvent.VK_S) tempObject.setVelY(0);
                if (key == KeyEvent.VK_D) tempObject.setVelY(0);
                if (key == KeyEvent.VK_S) tempObject.setVelY(0);
            }
            if (tempObject.getID() == ID.WRX) {
                if (key == KeyEvent.VK_UP) tempObject.setVelY(0);
                if (key == KeyEvent.VK_DOWN) tempObject.setVelY(0);
                if (key == KeyEvent.VK_LEFT) tempObject.setVelY(0);
                if (key == KeyEvent.VK_RIGHT) tempObject.setVelY(0);
            }
        }
    }
}
