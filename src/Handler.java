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
        final int WIDTH = 1100, HEIGHT = WIDTH / 12 * 9;
        this.addObject(new Player(WIDTH/2, HEIGHT/2, ID.C));
        this.addObject(new Player((WIDTH/2)+35, HEIGHT/2, ID.RG));
        this.addObject(new Player((WIDTH/2)+70, HEIGHT/2, ID.RT));
        this.addObject(new Player((WIDTH/2)-35, HEIGHT/2, ID.LG));
        this.addObject(new Player((WIDTH/2)-70, HEIGHT/2, ID.LT));
        this.addObject(new Player(WIDTH/2, (HEIGHT/2)+75, ID.QB));
        this.addObject(new Player((WIDTH/2)-35, (HEIGHT/2)+85, ID.RB));
        this.addObject(new Player((WIDTH/2)+105, HEIGHT/2, ID.TE));
        this.addObject(new Player((WIDTH/2)+450, (HEIGHT/2)+25, ID.WRZ));
        this.addObject(new Player((WIDTH/2)-450, (HEIGHT/2), ID.WRX));
        this.addObject(new Player((WIDTH/2)-225, (HEIGHT/2)+15, ID.WRH));
        this.addObject(new Player((WIDTH/2)+115, (HEIGHT/2)-30, ID.SDE));
        this.addObject(new Player((WIDTH/2)-75, HEIGHT/2-30, ID.WDE));
        this.addObject(new Player((WIDTH/2)-10, (HEIGHT/2)-30, ID.WDT));
        this.addObject(new Player((WIDTH/2)+40, (HEIGHT/2)-30, ID.SDT));
        this.addObject(new Player((WIDTH/2)+80, (HEIGHT/2)-70, ID.SOSLB));
        this.addObject(new Player((WIDTH/2)-45, (HEIGHT/2)-70, ID.WOSLB));
        this.addObject(new Player((WIDTH/2)+20, (HEIGHT/2)-75, ID.SISLB));
        this.addObject(new Player(WIDTH/2, (HEIGHT/2)-150, ID.FS));
        this.addObject(new Player((WIDTH/2)-225, (HEIGHT/2)-70, ID.SS));
        this.addObject(new Player((WIDTH/2)+450, (HEIGHT/2)-105, ID.SCB));
        this.addObject(new Player((WIDTH/2)-450, (HEIGHT/2)-105, ID.WCB));
    }

}