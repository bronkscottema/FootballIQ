import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;

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
        offense.add(ID.WR);
        offense.add(ID.WRH);
        offense.add(ID.WRX);
        offense.add(ID.WRZ);
        offense.add(ID.WRY);
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
        if (offense.contains(getID())) {
            g.setColor(Color.white);
            g.fillOval(x, y, 16, 16);
        } else {
            g.setColor(Color.black);
            g.fillRect(x,y,16,16);
        }
    }
}