import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Route extends MouseAdapter {

    private int x,y;
    private int velX,velY;

    private Handler handler;
    private Player player;
    private Game game;
    private Point p1 = new Point();
    private Point p2 = new Point();
    private Point p3 = new Point();
    private ArrayList list = new ArrayList();
    private Line line;
    private int clicks = 0;
    boolean isDragging;

    public Route( Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            int x = e.getX();
            int y = e.getY();

            if (clicks == 0) {
                line = new Line();
                line.setP1(new Point(x, y));
                clicks++;
            } else {
                line.setP2(new Point(x, y));
                list.add(line);
                clicks = 0;
            }
        }
        game.repaint();
    }

    public void mousePressed(MouseEvent e){
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
            defense.add(ID.C);
            defense.add(ID.LG);
            defense.add(ID.LT);
            defense.add(ID.RT);
            defense.add(ID.RG);
        }
            LinkedList<GameObject> jags = handler.object;
            for (GameObject player : jags) {
                if (mouseOver(e.getX(),e.getY(),player.getX(),player.getY(),16,16)) {
                    if (defense.contains(player.getID())) {
                        break;
                    }
                    if (SwingUtilities.isRightMouseButton(e)) {
                        String[] choices = {"A", "B", "F", "T","U", ""};
                        String input = (String) JOptionPane.showInputDialog(null, null,
                                "Change Position", JOptionPane.QUESTION_MESSAGE, null, // Use
                                choices, // Array of choices
                                choices[1]); // Initial choice
                        System.out.println(input);
                    if (input.equals("A")) {
                        player.setID(ID.A);
                    }
                    if (input.equals("B")) {
                            player.setID(ID.B);
                    }
                    if (input.equals("F")) {
                        player.setID(ID.F);
                    }
                    if (input.equals("T")) {
                        player.setID(ID.T);
                    }
                    if (input.equals("U")) {
                        player.setID(ID.U);
                    }
                    if (input.equals("")) {
                        player.setID(ID.N);
                    }
                }
            }
        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {
    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
    }


    public void mouseMoved(MouseEvent e) {
    }

    public void tick() {
    }

    public void reset() {
        LinkedList<GameObject> players = handler.object;
        Iterator<GameObject> jag = players.iterator();
        while (jag.hasNext()) {
           GameObject player = jag.next();
           jag.remove();
           if (!list.isEmpty()) {
               int size = list.size() - 1;
               list.remove(size);
           }
        }
        handler.startGame();
    }

    public void play() {
        LinkedList<GameObject> jags = handler.object;
        for (GameObject player : jags) {
            for (int l = 0; l < list.size(); l++) {
                if (mouseOver(((Line) list.get(l)).getP1().getX(), ((Line) list.get(l)).getP1().getY(), player.getX(), player.getY(), 16, 16)) {
                    int p2x = ((Line) list.get(l)).getP2().getX()-8;
                    int p2y = ((Line) list.get(l)).getP2().getY()-8;
                    int p1x = ((Line) list.get(l)).getP1().getX()-8;
                    int p1y = ((Line) list.get(l)).getP1().getY()-8;
//                    player.setVelX(p2x-p1x);
//                    player.setVelY(p2y-p1y);
//                    player.setVelY(0);
//                    player.setVelX(0);
                    player.setX(p2x);
                    player.setY(p2y);
                }
            }
        }
    }

    public void undo() {
        if (!list.isEmpty()) {
            int size = list.size() - 1;
            list.remove(size);
            game.repaint();
        }
    }


    public void render(Graphics g) {
        g.setColor(Color.red);
        Graphics2D g2 = (Graphics2D) g;
        Line currLine;
        for (int i = 0; i < list.size(); i++) {
            currLine = (Line) (list.get(i));
            g2.setStroke(new BasicStroke(2));
            g2.draw(new Line2D.Float(currLine.getP1().getX(), currLine.getP1().getY(),currLine.getP2().getX(), currLine.getP2().getY()));
        }
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else return false;
        } else return false;

    }

}
class Line {

    private Point p1;
    private Point p2;
    private Point p3;

    public Line() {
    }

    public Line(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public Point getP3() {
        return p3;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public void setP3(Point p3) {
        this.p3 = p3;
    }
}
class Point {

    private int x;
    private int y;

    public Point() {
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}