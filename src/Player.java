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
        offense.add(ID.N1);
        offense.add(ID.N2);
        offense.add(ID.N3);
        offense.add(ID.N4);
        offense.add(ID.N5);
        offense.add(ID.N6);
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
        y = Game.clamp(y, 0, Game.HEIGHT - 37);

    }

    public void render(Graphics g) {
        Font font = new Font("Courrier New", Font.PLAIN, 16);
        Graphics2D g2 = (Graphics2D) g;
        if (offense.contains(getID())) {
            g2.drawOval(x, y, 24, 24);
            g2.setStroke(new BasicStroke(3));
            g2.setColor(Color.black);
            if (getID() == ID.A) {
                g2.setColor(Color.black);
                g2.setFont(font);
                g2.drawString("A", x + 7, y + 15);
            }
            if (getID() == ID.B) {
                g.setColor(Color.black);
                g2.setFont(font);
                g.drawString("B", x + 7, y + 17);
            }
            if (getID() == ID.F) {
                g.setColor(Color.black);
                g2.setFont(font);
                g.drawString("F", x + 7, y + 17);
            }
            if (getID() == ID.WRH) {
                g2.setColor(Color.black);
                g2.setFont(font);
                g2.drawString("H", x + 6, y + 17);
            }
            if (getID() == ID.QB) {
                g.setColor(Color.black);
                g2.setFont(font);
                g.drawString("Q", x + 6, y + 17);
            }
            if (getID() == ID.RB) {
                g.setColor(Color.black);
                g2.setFont(font);
                g.drawString("F", x + 7, y + 17);
            }
            if (getID() == ID.T) {
                g.setColor(Color.black);
                g2.setFont(font);
                g.drawString("T", x + 7, y + 17);
            }
            if (getID() == ID.TE) {
                g.setColor(Color.black);
                g2.setFont(font);
                g.drawString("Y", x + 7, y + 17);
            }
            if (getID() == ID.U) {
                g.setColor(Color.black);
                g2.setFont(font);
                g.drawString("U", x + 7, y + 17);
            }
            if (getID() == ID.WRX) {
                g2.setColor(Color.black);
                g2.setFont(font);
                g2.drawString("X", x + 7, y + 17);
            }
            if (getID() == ID.TE) {
                g2.setColor(Color.black);
                g2.setFont(font);
                g.drawString("Y", x + 7, y + 17);
            }
            if (getID() == ID.WRZ) {
                g2.setColor(Color.black);
                g2.setFont(font);
                g2.drawString("Z", x + 7, y + 17);
            }
            if (getID() == ID.N) {
                g2.setColor(Color.black);
            }
        } else if (defense.contains(getID())) {
            g.setColor(Color.black);
            g.fillRect(x, y, 24, 24);
        }
    }
}