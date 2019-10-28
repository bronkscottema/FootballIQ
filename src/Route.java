import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Route extends MouseAdapter {

    private Handler handler;
    private Game game;
    private ArrayList list = new ArrayList();
    private Line line;
    private int clicks = 0;
    private Long startTime;
    private long playTime = 2000;
    private ArrayList<Point> pOTL = new ArrayList<Point>();
    private ArrayList<Point> pOTLs = new ArrayList<Point>();
    private Point startPoint, midpoint, endPoint;
    private Point pointOnTimeLine, pointOnTimeLines;
    private double pointInTime;

    public Route( Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e){
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
                    }
                }
            } else if (clicks == 1) {
                line.setP2(new Point(x,y));
                clicks++;
            } else {
                line.setP3(new Point(x, y));
                list.add(line);
                clicks = 0;
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
        for (int p = 0; p < pOTL.size(); p++) {
            pOTL.remove(p);
        }

    }

    public void play() {
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<GameObject> jags = handler.object;
                for (GameObject player : jags) {
                    for (int l = 0; l < list.size(); l++) {
                        if (mouseOver(((Line) list.get(l)).getP1().getX(), ((Line) list.get(l)).getP1().getY(), player.getX(), player.getY(), 24, 24)) {
                            int p2x = ((Line) list.get(l)).getP3().getX() - 12;
                            int p2y = ((Line) list.get(l)).getP3().getY() - 12;
                            player.setX(p2x);
                            player.setY(p2y);
                            //TODO figure out animation
//                            startPoint = new Point(((Line) list.get(l)).getP1().getX(), ((Line) list.get(l)).getP1().getY());
//                            midpoint = new Point(((Line) list.get(l)).getP2().getX(), ((Line) list.get(l)).getP2().getY());
//                            endPoint = new Point(((Line) list.get(l)).getP3().getX(), ((Line) list.get(l)).getP3().getY());
//                            pointOnTimeLine = new Point(player.getX(), player.getY());
//                            pointOnTimeLines = new Point(player.getX(), player.getY());
//
//                            if (startTime == null) {
//                                startTime = System.currentTimeMillis();
//                            }
//                            long now = System.currentTimeMillis();
//                            long diff = now - startTime;
//                            if (diff >= playTime) {
//                                diff = playTime;
//                                ((Timer) e.getSource()).stop();
//                                pOTL.clear();
//                                pOTLs.clear();
//                            }
//                            double i = (double) diff / (double) playTime;
//                            pointInTime = i;
//
//                            pointOnTimeLine.x = (int) (startPoint.x + ((midpoint.x - startPoint.x) * i));
//                            pointOnTimeLine.y = (int) (startPoint.y + ((midpoint.y - startPoint.y) * i));
//                            Point p3 = new Point(pointOnTimeLine.x, pointOnTimeLine.y);
//                            pOTL.add(p3);
//
//                            pointOnTimeLines.x = (int) (midpoint.x + ((endPoint.x - midpoint.x) * i));
//                            pointOnTimeLines.y = (int) (midpoint.y + ((endPoint.y - midpoint.y) * i));
//                            Point p4 = new Point(pointOnTimeLines.x, pointOnTimeLines.y);
//                            pOTLs.add(p4);
                        }

                    }
                }

            }
        });
        timer.start();
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
            g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 10.0f, null, 0.0f));
            g2.drawPolyline(new int[] {currLine.getP1().getX(),currLine.getP2().getX(), currLine.getP3().getX()},
                            new int[] {currLine.getP1().getY(),currLine.getP2().getY(), currLine.getP3().getY()}, 3);
        }
        //TODO figure out more animation
//        if (pointOnTimeLine != null) {
//            for (int i = 0; i < pOTL.size(); i++) {
//                Point currPolt;
//                currPolt = (Point) pOTL.get(i);
//                g2.setColor(Color.black);
//                Ellipse2D shape = new Ellipse2D.Float(currPolt.x-12, currPolt.y-12, 24, 24);
//                g2.draw(shape);
//                remove();
//            }
//            for (int s = 0; s < pOTLs.size(); s++) {
//                Point currPolts;
//                currPolts = (Point) pOTLs.get(s);
//                g2.setColor(Color.black);
//                Ellipse2D shapes = new Ellipse2D.Float(currPolts.x - 12, currPolts.y - 12, 24, 24);
//                g2.draw(shapes);
//                remove2nd();
//            }
//        }
    }

    //TODO figure out more animation
//    public void remove() {
//        Timer time = new Timer(100, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int n = list.size();
//                if (pOTL.size() > n) {
//                    pOTL.remove(0);
//                }
//            }
//        });
//        time.start();
//    }
//
//    public void remove2nd() {
//        Timer time = new Timer(100, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int n = list.size();
//                if (pOTLs.size() > n) {
//                    pOTLs.remove(0);
//                }
//            }
//        });
//        time.start();
//    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else return false;
        } else return false;

    }

    public static boolean inLine(Point A, Point B, Point C) {
        // if AC is vertical
        if (A.getX() == C.getX()) return B.getX() == C.getX();
        // if AC is horizontal
        if (A.getY() == C.getY()) return B.getY() == C.getY();
        // match the gradients
        return (A.getX() - C.getX())*(A.getY() - C.getY()) == (C.getX() - B.getX())*(C.getY() - B.getY());
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

    public void setP3(Point p3) {
        this.p3 = p3;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
class Point {

    public int x;
    public int y;

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