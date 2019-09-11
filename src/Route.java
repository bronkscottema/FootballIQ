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
    int numTimes = 0;
    int numA = 0;
    int numB = 0;
    int numF = 0;
    int numT = 0;
    int numU = 0;
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

            if (clicks == 0 && SwingUtilities.isLeftMouseButton(e)) {
                line = new Line();
                line.setP1(new Point(x, y));
                LinkedList<GameObject> jags = handler.object;
                for (GameObject player : jags) {
                    if (mouseOver(e.getX(), e.getY(), player.getX(), player.getY(), 24, 24)) {
                        clicks++;
                        line.setId(player.getID());
                    } else {
                        clicks++;
                    }
                }
            } else {
                line.setP2(new Point(x, y));
                list.add(line);
                clicks = 0;
            }
        }
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
                if (mouseOver(e.getX(),e.getY(),player.getX(),player.getY(),24,24)) {
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
                    if (input.equals("A") && numA < 1) {
                        player.setID(ID.A);
                        numA++;
                    }
                    if (input.equals("B") && numB < 1) {
                        player.setID(ID.B);
                        numB++;
                    }
                    if (input.equals("F") && numF < 1) {
                        player.setID(ID.F);
                        numF++;
                    }
                    if (input.equals("T") && numT < 1) {
                        player.setID(ID.T);
                        numT++;
                    }
                    if (input.equals("U") && numU < 1) {
                        player.setID(ID.U);
                        numU++;
                    }
                    if (input.equals("")) {
                        player.setID(ID.N);
                        if (numTimes == 0) {
                        } else if (numTimes == 1) {
                            player.setID(ID.N1);
                        } else if (numTimes == 2) {
                            player.setID(ID.N2);
                        } else if (numTimes == 3) {
                            player.setID(ID.N3);
                        } else if (numTimes == 4) {
                            player.setID(ID.N4);
                        } else if (numTimes == 5) {
                            player.setID(ID.N5);
                        } else if (numTimes == 6) {
                            player.setID(ID.N6);
                        }
                        numTimes++;
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
        reload();
        LinkedList<GameObject> players = handler.object;
        Iterator<GameObject> jag = players.iterator();
        while (jag.hasNext()) {
           GameObject player = jag.next();
           if (!list.isEmpty()) {
               int size = list.size() - 1;
               list.remove(size);
           }
        }
    }

    public void reload() {
        LinkedList<GameObject> jags = handler.object;
        for (GameObject player : jags) {
            if (!list.isEmpty()) {
                for (int l = 0; l < list.size(); l++) {
                    Line currLine;
                    currLine = (Line) (list.get(l));
                    if (currLine.getId() == player.getID()) {
                        player.setX(currLine.getP1().getX()-12);
                        player.setY(currLine.getP1().getY()-12);
                    }
                }
            }
        }
    }

    public void play() {
        LinkedList<GameObject> jags = handler.object;
        for (GameObject player : jags) {
            for (int l = 0; l < list.size(); l++) {
                if (mouseOver(((Line) list.get(l)).getP1().getX(), ((Line) list.get(l)).getP1().getY(), player.getX(), player.getY(), 24, 24)) {
                    int p2x = ((Line) list.get(l)).getP2().getX()-12;
                    int p2y = ((Line) list.get(l)).getP2().getY()-12;
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
    private ID id;

    public Line() {
    }

    public Line(Point p1, Point p2, Point p3, ID id) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.id = id;
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

    public ID getId() { return id; }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public void setId(ID id) {
        this.id = id;
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