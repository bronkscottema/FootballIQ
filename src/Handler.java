import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }

    public void startGame() {
        final int WIDTH = 720, HEIGHT = WIDTH / 12 * 9;
        this.addObject(new Player(WIDTH/2, HEIGHT/2, ID.C));
        this.addObject(new Player((WIDTH/2)+25, HEIGHT/2, ID.RG));
        this.addObject(new Player((WIDTH/2)+50, HEIGHT/2, ID.RT));
        this.addObject(new Player((WIDTH/2)-25, HEIGHT/2, ID.LG));
        this.addObject(new Player((WIDTH/2)-50, HEIGHT/2, ID.LT));
        this.addObject(new Player(WIDTH/2, (HEIGHT/2)+50, ID.QB));
        this.addObject(new Player((WIDTH/2)-25, (HEIGHT/2)+55, ID.RB));
        this.addObject(new Player((WIDTH/2)+75, HEIGHT/2, ID.TE));
        this.addObject(new Player((WIDTH/2)+300, (HEIGHT/2)+15, ID.WRZ));
        this.addObject(new Player((WIDTH/2)-300, (HEIGHT/2), ID.WRX));
        this.addObject(new Player((WIDTH/2)-175, (HEIGHT/2)+15, ID.WRH));
        this.addObject(new Player((WIDTH/2)+85, (HEIGHT/2)-20, ID.SDE));
        this.addObject(new Player((WIDTH/2)-55, HEIGHT/2-20, ID.WDE));
        this.addObject(new Player((WIDTH/2)-5, (HEIGHT/2)-20, ID.WDT));
        this.addObject(new Player((WIDTH/2)+30, (HEIGHT/2)-20, ID.SDT));
        this.addObject(new Player((WIDTH/2)+50, (HEIGHT/2)-50, ID.SOSLB));
        this.addObject(new Player((WIDTH/2)-35, (HEIGHT/2)-50, ID.WOSLB));
        this.addObject(new Player((WIDTH/2)+10, (HEIGHT/2)-55, ID.SISLB));
        this.addObject(new Player(WIDTH/2, (HEIGHT/2)-150, ID.FS));
        this.addObject(new Player((WIDTH/2)-175, (HEIGHT/2)-50, ID.SS));
        this.addObject(new Player((WIDTH/2)+300, (HEIGHT/2)-85, ID.SCB));
        this.addObject(new Player((WIDTH/2)-300, (HEIGHT/2)-85, ID.WCB));
    }

}