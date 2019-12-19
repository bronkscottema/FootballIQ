import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class Handler {
    Font font = new Font("Courrier New", Font.PLAIN, 16);
    Game game;
    JFrame frame;

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
        LinkedList<GameObject> players = object;
        Iterator<GameObject> jag = players.iterator();
        while (jag.hasNext()) {
            GameObject player = jag.next();
            jag.remove();
        }
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
    public void punt() {
        LinkedList<GameObject> players = object;
        Iterator<GameObject> jag = players.iterator();
        while (jag.hasNext()) {
            GameObject player = jag.next();
            jag.remove();
        }
        final int WIDTH = 1100, HEIGHT = WIDTH / 12 * 9;
        this.addObject(new Player(WIDTH/2, (HEIGHT/2)+200, ID.P));
        this.addObject(new Player(WIDTH/2, HEIGHT/2, ID.L1));
        this.addObject(new Player((WIDTH/2)+100, HEIGHT/2, ID.L2));
        this.addObject(new Player((WIDTH/2)+200, HEIGHT/2, ID.L3));
        this.addObject(new Player((WIDTH/2)+300, HEIGHT/2, ID.L4));
        this.addObject(new Player((WIDTH/2)-100, HEIGHT/2, ID.L5));
        this.addObject(new Player((WIDTH/2)-200, HEIGHT/2, ID.R1));
        this.addObject(new Player((WIDTH/2)-300, HEIGHT/2, ID.R2));
        this.addObject(new Player((WIDTH/2)-50, (HEIGHT/2)+80, ID.R3));
        this.addObject(new Player((WIDTH/2)+50, (HEIGHT/2)+80, ID.R4));
        this.addObject(new Player((WIDTH/2)+50, (HEIGHT/2)+110, ID.R5));
        this.addObject(new Player((WIDTH/2)+160, (HEIGHT/2)-30, ID.SDE));
        this.addObject(new Player((WIDTH/2)-100, HEIGHT/2-30, ID.WDE));
        this.addObject(new Player((WIDTH/2)-40, (HEIGHT/2)-30, ID.WDT));
        this.addObject(new Player((WIDTH/2)+40, (HEIGHT/2)-30, ID.SDT));
        this.addObject(new Player((WIDTH/2)+100, (HEIGHT/2)-30, ID.SOSLB));
        this.addObject(new Player((WIDTH/2)-160, (HEIGHT/2)-30, ID.WOSLB));
        this.addObject(new Player((WIDTH/2)+200, (HEIGHT/2)-35, ID.SISLB));
        this.addObject(new Player(WIDTH/2, (HEIGHT/2)-350, ID.FS));
        this.addObject(new Player((WIDTH/2)-200, (HEIGHT/2)-30, ID.SS));
        this.addObject(new Player((WIDTH/2)+260, (HEIGHT/2)-30, ID.SCB));
        this.addObject(new Player((WIDTH/2)-260, (HEIGHT/2)-30, ID.WCB));
    }
    public void KO() {
        LinkedList<GameObject> players = object;
        Iterator<GameObject> jag = players.iterator();
        while (jag.hasNext()) {
            GameObject player = jag.next();
            jag.remove();
        }
        final int WIDTH = 1100, HEIGHT = WIDTH / 12 * 9;
        this.addObject(new Player((WIDTH/2)+50, 100, ID.K));
        this.addObject(new Player((WIDTH/2)+500, 150, ID.L1));
        this.addObject(new Player((WIDTH/2)+400, 150, ID.L2));
        this.addObject(new Player((WIDTH/2)+300, 150, ID.L3));
        this.addObject(new Player((WIDTH/2)+200, 150, ID.L4));
        this.addObject(new Player((WIDTH/2)+100, 150, ID.L5));
        this.addObject(new Player((WIDTH/2)-500, 150, ID.R1));
        this.addObject(new Player((WIDTH/2)-400, 150, ID.R2));
        this.addObject(new Player((WIDTH/2)-300, 150, ID.R3));
        this.addObject(new Player((WIDTH/2)-200, 150, ID.R4));
        this.addObject(new Player((WIDTH/2)-100, 150, ID.R5));
        this.addObject(new Player((WIDTH/2)+50, 600, ID.SDE));
        this.addObject(new Player((WIDTH/2)-50, 600, ID.WDE));
        this.addObject(new Player((WIDTH/2)-305, 450, ID.WDT));
        this.addObject(new Player((WIDTH/2)+105, 450, ID.SDT));
        this.addObject(new Player((WIDTH/2)+305, 450, ID.SOSLB));
        this.addObject(new Player((WIDTH/2)-105, 450, ID.WOSLB));
        this.addObject(new Player((WIDTH/2)+225, (HEIGHT/2)-105, ID.SISLB));
        this.addObject(new Player(WIDTH/2, (HEIGHT/2)-105, ID.FS));
        this.addObject(new Player((WIDTH/2)-225, (HEIGHT/2)-105, ID.SS));
        this.addObject(new Player((WIDTH/2)+450, (HEIGHT/2)-105, ID.SCB));
        this.addObject(new Player((WIDTH/2)-450, (HEIGHT/2)-105, ID.WCB));
    }
    public void FG() {
        LinkedList<GameObject> players = object;
        Iterator<GameObject> jag = players.iterator();
        while (jag.hasNext()) {
            GameObject player = jag.next();
            jag.remove();
        }
        final int WIDTH = 1100, HEIGHT = WIDTH / 12 * 9;
        this.addObject(new Player((WIDTH/2)-25, (HEIGHT/2)+105, ID.K));
        this.addObject(new Player(WIDTH/2, HEIGHT/2, ID.C));
        this.addObject(new Player((WIDTH/2)+35, HEIGHT/2, ID.RG));
        this.addObject(new Player((WIDTH/2)+70, HEIGHT/2, ID.RT));
        this.addObject(new Player((WIDTH/2)-35, HEIGHT/2, ID.LG));
        this.addObject(new Player((WIDTH/2)-70, HEIGHT/2, ID.LT));
        this.addObject(new Player((WIDTH/2)+15, (HEIGHT/2)+75, ID.N));
        this.addObject(new Player((WIDTH/2)-105, HEIGHT/2, ID.N1));
        this.addObject(new Player((WIDTH/2)+105, HEIGHT/2, ID.N2));
        this.addObject(new Player((WIDTH/2)-125, (HEIGHT/2)+30, ID.N3));
        this.addObject(new Player((WIDTH/2)+125, (HEIGHT/2)+30, ID.N4));

        this.addObject(new Player((WIDTH/2)-20, (HEIGHT/2)-30, ID.WDT));
        this.addObject(new Player((WIDTH/2)-50, HEIGHT/2-30, ID.WDE));
        this.addObject(new Player((WIDTH/2)-80, (HEIGHT/2)-30, ID.WOSLB));
        this.addObject(new Player((WIDTH/2)-110, (HEIGHT/2)-30, ID.SS));
        this.addObject(new Player((WIDTH/2)-140, (HEIGHT/2)-30, ID.WCB));
        this.addObject(new Player((WIDTH/2)+20, (HEIGHT/2)-30, ID.SDT));
        this.addObject(new Player((WIDTH/2)+50, (HEIGHT/2)-30, ID.SDE));
        this.addObject(new Player((WIDTH/2)+80, (HEIGHT/2)-30, ID.SOSLB));
        this.addObject(new Player((WIDTH/2)+110, (HEIGHT/2)-30, ID.SISLB));
        this.addObject(new Player((WIDTH/2)+140, (HEIGHT/2)-30, ID.SCB));
        this.addObject(new Player(WIDTH/2, (HEIGHT/2)-55, ID.FS));

    }

    public void playbook() {
        LinkedList<GameObject> players = object;
        Iterator<GameObject> jag = players.iterator();
        while (jag.hasNext()) {
            GameObject player = jag.next();
            jag.remove();
        }
        final int WIDTH2 = 1100, HEIGHT = WIDTH2 / 12 * 9, WIDTH = HEIGHT;
        this.addObject(new Player(WIDTH/2, HEIGHT/2-150, ID.C));
        this.addObject(new Player((WIDTH/2)+35, HEIGHT/2-150, ID.RG));
        this.addObject(new Player((WIDTH/2)+70, HEIGHT/2-150, ID.RT));
        this.addObject(new Player((WIDTH/2)-35, HEIGHT/2-150, ID.LG));
        this.addObject(new Player((WIDTH/2)-70, HEIGHT/2-150, ID.LT));
        this.addObject(new Player(WIDTH/2, (HEIGHT/2)-95, ID.QB));
        this.addObject(new Player((WIDTH/2)-35, (HEIGHT/2)-85, ID.RB));
        this.addObject(new Player((WIDTH/2)+105, HEIGHT/2-150, ID.TE));
        this.addObject(new Player((WIDTH/2)+350, (HEIGHT/2)-125, ID.WRZ));
        this.addObject(new Player((WIDTH/2)-350, (HEIGHT/2)-150, ID.WRX));
        this.addObject(new Player((WIDTH/2)-225, (HEIGHT/2)-125, ID.WRH));
        this.addObject(new Player((WIDTH/2)+115, (HEIGHT/2)-180, ID.SDE));
        this.addObject(new Player((WIDTH/2)-75, HEIGHT/2-180, ID.WDE));
        this.addObject(new Player((WIDTH/2)-10, (HEIGHT/2)-180, ID.WDT));
        this.addObject(new Player((WIDTH/2)+40, (HEIGHT/2)-180, ID.SDT));
        this.addObject(new Player((WIDTH/2)+80, (HEIGHT/2)-225, ID.SOSLB));
        this.addObject(new Player((WIDTH/2)-45, (HEIGHT/2)-225, ID.WOSLB));
        this.addObject(new Player((WIDTH/2)+20, (HEIGHT/2)-225, ID.SISLB));
        this.addObject(new Player(WIDTH/2, (HEIGHT/2)-300, ID.FS));
        this.addObject(new Player((WIDTH/2)-225, (HEIGHT/2)-220, ID.SS));
        this.addObject(new Player((WIDTH/2)+325, (HEIGHT/2)-255, ID.SCB));
        this.addObject(new Player((WIDTH/2)-325, (HEIGHT/2)-255, ID.WCB));
    }
}