import java.awt.*;
import java.util.ArrayList;

public class Player extends GameObject {

    public Player(int x, int y, ID id) {

        super(x, y, id);
    }

    ArrayList<ID> offense = new ArrayList<ID>();

    {
        offense.add(ID.TE);
        offense.add(ID.LT);
        offense.add(ID.LG);
        offense.add(ID.C);
        offense.add(ID.RG);
        offense.add(ID.RT);
        offense.add(ID.QB);
        offense.add(ID.RB);
        offense.add(ID.FB);
        offense.add(ID.WRH);
        offense.add(ID.WRX);
        offense.add(ID.WRZ);
        offense.add(ID.A);
        offense.add(ID.B);
        offense.add(ID.U);
        offense.add(ID.F);
        offense.add(ID.T);
        offense.add(ID.N);
    }

    ArrayList<ID> defense;

    {
        defense = new ArrayList<ID>();
        defense.add(ID.SDE);
        defense.add(ID.SDT);
        defense.add(ID.WDT);
        defense.add(ID.WDE);
        defense.add(ID.SOSLB);
        defense.add(ID.SISLB);
        defense.add(ID.WOSLB);
        defense.add(ID.WISLB);
        defense.add(ID.FS);
        defense.add(ID.SS);
        defense.add(ID.WCB);
        defense.add(ID.SCB);
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

        Graphics2D g2 = (Graphics2D) g;
        if (offense.contains(getID())) {
            g2.drawOval(x, y, 16, 16);
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.black);
            if (getID() == ID.A) {
                g2.setColor(Color.black);
                g2.drawString("A", x + 4, y + 13);
            }
            if (getID() == ID.B) {
                g.setColor(Color.black);
                g.drawString("B", x + 4, y + 13);
            }
            if (getID() == ID.F) {
                g.setColor(Color.black);
                g.drawString("F", x + 4, y + 13);
            }
            if (getID() == ID.WRH) {
                g2.setColor(Color.black);
                g2.drawString("H", x + 4, y + 13);
            }
            if (getID() == ID.QB) {
                g.setColor(Color.black);
                g.drawString("Q", x + 4, y + 13);
            }
            if (getID() == ID.RB) {
                g.setColor(Color.black);
                g.drawString("F", x + 4, y + 13);
            }
            if (getID() == ID.T) {
                g.setColor(Color.black);
                g.drawString("T", x + 4, y + 13);
            }
            if (getID() == ID.TE) {
                g.setColor(Color.black);
                g.drawString("Y", x + 4, y + 13);
            }
            if (getID() == ID.U) {
                g.setColor(Color.black);
                g.drawString("U", x + 4, y + 13);
            }
            if (getID() == ID.WRX) {
                g2.setColor(Color.black);
                g2.drawString("X", x + 4, y + 13);
            }
            if (getID() == ID.TE) {
                g2.setColor(Color.black);
                g.drawString("Y", x + 4, y + 13);
            }
            if (getID() == ID.WRZ) {
                g2.setColor(Color.black);
                g2.drawString("Z", x + 4, y + 13);
            }
            if (getID() == ID.N) {
                g2.setColor(Color.black);
            }
        } else if (defense.contains(getID())) {
            g.setColor(Color.black);
            g.fillRect(x, y, 16, 16);
        }
    }
}