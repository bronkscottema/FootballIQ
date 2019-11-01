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
    private ArrayList routeList = new ArrayList();
    private ArrayList motionList = new ArrayList();
    private ArrayList zoneList = new ArrayList();
    private ArrayList blockList = new ArrayList();
    private Line line;
    private int clicks = 0;
    private Long startTime;
    private long playTime = 2000;
    private ArrayList<Point> pOTL = new ArrayList<Point>();
    private ArrayList<Point> pOTLs = new ArrayList<Point>();
    private Point startPoint, midpoint, endPoint;
    private Point pointOnTimeLine, pointOnTimeLines;
    private double pointInTime;


    public Route(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e) && game.routeline.isSelected()) {
            int x = e.getX();
            int y = e.getY();
            Point eventPoint = new Point(x, y);

            if (clicks == 0 && SwingUtilities.isLeftMouseButton(e)) {
                line = new Line();
                line.setP1(new Point(x, y));
                LinkedList<GameObject> jags = handler.object;
                for (GameObject player : jags) {
                    if (mouseOver(e.getX(), e.getY(), player.getX(), player.getY(), 24, 24)) {
                        clicks++;
                        line.setId(player.getID());
                    } else if (!mouseOver(e.getX(), e.getY(), player.getX(), player.getY(), 24, 24)) {
                        for (int i = 0; i < motionList.size(); i++) {
                            Line currLine;
                            currLine = (Line) (motionList.get(i));
                            if (player.getID().equals(currLine.getId()) && currLine.getP2().getX() - line.getP1().getX() <= 10 || currLine.getP2().getX() - line.getP1().getX() <= -10) {
                                clicks++;
                                line.setId(player.getID());
                                return;
                            }
                        }
                    }
                }
            } else if (clicks == 1) {
                line.setP2(new Point(x, y));
                clicks++;
            } else {
                line.setP3(new Point(x, y));
                routeList.add(line);
                clicks = 0;
            }
        }
        if (SwingUtilities.isLeftMouseButton(e) && game.motion.isSelected()) {
            int x = e.getX();
            int y = e.getY();

            if (clicks == 0) {
                LinkedList<GameObject> jags = handler.object;
                for (GameObject player : jags) {
                    if (mouseOver(e.getX(), e.getY(), player.getX(), player.getY(), 24, 24)) {
                    line = new Line();
                    line.setP1(new Point(x, y));
                    line.setId(player.getID());
                    clicks++;
                    }
                }
            } else {
                line.setP2(new Point(x, y));
                motionList.add(line);
                clicks = 0;
            }
        }
        if (SwingUtilities.isLeftMouseButton(e) && game.zone.isSelected()) {
            int x = e.getX();
            int y = e.getY();

            if (clicks == 0) {
                LinkedList<GameObject> jags = handler.object;
                for (GameObject player : jags) {
                    if (mouseOver(e.getX(), e.getY(), player.getX(), player.getY(), 24, 24)) {
                        line = new Line();
                        line.setP1(new Point(x, y));
                        line.setId(player.getID());
                        clicks++;
                    }
                }
            } else {
                line.setP2(new Point(x, y));
                zoneList.add(line);
                clicks = 0;
            }
        }
        if (SwingUtilities.isLeftMouseButton(e) && game.block.isSelected()) {
            int x = e.getX();
            int y = e.getY();

            if (clicks == 0) {
                LinkedList<GameObject> jags = handler.object;
                for (GameObject player : jags) {
                    if (mouseOver(e.getX(), e.getY(), player.getX(), player.getY(), 24, 24)) {
                        line = new Line();
                        line.setP1(new Point(x, y));
                        line.setId(player.getID());
                        clicks++;
                    }
                }
            } else {
                line.setP2(new Point(x, y));
                blockList.add(line);
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
        clicks = 0;
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
            if (!routeList.isEmpty()) {
                int size = routeList.size() - 1;
                routeList.remove(size);
            }
            if (!motionList.isEmpty()) {
                int size = motionList.size() - 1;
                motionList.remove(size);
            }
            if (!zoneList.isEmpty()) {
                int size = zoneList.size() - 1;
                zoneList.remove(size);
            }
            if (!blockList.isEmpty()) {
                int size = blockList.size() - 1;
                blockList.remove(size);
            }
        }
    }

    public void reload() {
        LinkedList<GameObject> jags = handler.object;
        for (GameObject player : jags) {
            for (int l = 0; l < routeList.size(); l++) {
                Line currLine;
                currLine = (Line) (routeList.get(l));
                if (currLine.getId() == player.getID()) {
                    player.setX(currLine.getP1().getX() - 12);
                    player.setY(currLine.getP1().getY() - 12);

                }
            }
            for (int i = 0; i < motionList.size(); i++) {
                Line currLine;
                currLine = (Line) (motionList.get(i));
                if (currLine.getId() == player.getID() &&  player.getID() == currLine.getId()) {
                    player.setX(currLine.getP1().getX()-12);
                    player.setY(currLine.getP1().getY()-12);
                }
            }
            for (int z = 0; z < zoneList.size(); z++) {
                Line currLine;
                currLine = (Line) (zoneList.get(z));
                if (currLine.getId() == player.getID()) {
                    player.setX(currLine.getP1().getX() - 12);
                    player.setY(currLine.getP1().getY() - 12);
                }
            }
            for (int z = 0; z < blockList.size(); z++) {
                Line currLine;
                currLine = (Line) (blockList.get(z));
                if (currLine.getId() == player.getID()) {
                    player.setX(currLine.getP1().getX() - 12);
                    player.setY(currLine.getP1().getY() - 12);
                }
            }

        }
//        for (int p = 0; p < pOTL.size(); p++) {
//            pOTL.remove(p);
//        }

    }

    public void play() {
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<GameObject> jags = handler.object;
                for (GameObject player : jags) {
                    for (int m = 0; m < motionList.size(); m++) {
                        if (mouseOver(((Line) motionList.get(m)).getP1().getX(), ((Line) routeList.get(m)).getP1().getY(), player.getX(), player.getY(), 24, 24)) {
                            int p2x = ((Line) motionList.get(m)).getP2().getX() - 12;
                            int p2y = ((Line) motionList.get(m)).getP2().getY() - 12;
                            player.setX(p2x);
                            player.setY(p2y);
                            ((Timer) e.getSource()).stop();
                        }

                    }
                }

            }
        });
        timer.start();
        Timer timer2 = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<GameObject> jags = handler.object;
                for (GameObject player : jags) {
                    for (int l = 0; l < routeList.size(); l++) {
                        if (mouseOver(((Line) routeList.get(l)).getP1().getX(), ((Line) routeList.get(l)).getP1().getY(), player.getX(), player.getY(), 24, 24)) {
                            int p2x = ((Line) routeList.get(l)).getP3().getX() - 12;
                            int p2y = ((Line) routeList.get(l)).getP3().getY() - 12;
                            player.setX(p2x);
                            player.setY(p2y);
                            ((Timer) e.getSource()).stop();
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
                   for (int l = 0; l < zoneList.size(); l++) {
                       if (mouseOver(((Line) zoneList.get(l)).getP1().getX(), ((Line) zoneList.get(l)).getP1().getY(), player.getX(), player.getY(), 24, 24)) {
                           int p2x = ((Line) zoneList.get(l)).getP2().getX() - 12;
                           int p2y = ((Line) zoneList.get(l)).getP2().getY() - 12;
                           player.setX(p2x);
                           player.setY(p2y);
                       }
                   }
                   for (int l = 0; l < blockList.size(); l++) {
                       if (mouseOver(((Line) blockList.get(l)).getP1().getX(), ((Line) blockList.get(l)).getP1().getY(), player.getX(), player.getY(), 24, 24)) {
                           int p2x = ((Line) blockList.get(l)).getP2().getX() - 12;
                           int p2y = ((Line) blockList.get(l)).getP2().getY() - 12;
                           player.setX(p2x);
                           player.setY(p2y);
                       }
                   }
                }

            }
        });
        timer2.start();
    }

    public void undo() {

        if (!routeList.isEmpty() && game.routeline.isSelected()) {
            int size = routeList.size() - 1;
            routeList.remove(size);
        }
        if (!motionList.isEmpty() && game.motion.isSelected()) {
            int size = motionList.size() - 1;
            motionList.remove(size);
        }
        if (!zoneList.isEmpty() && game.zone.isSelected()) {
            int size = zoneList.size() - 1;
            zoneList.remove(size);
        }
        if (!blockList.isEmpty() && game.block.isSelected()) {
            int size = blockList.size() - 1;
            blockList.remove(size);
        }

    }

    public void render(Graphics g) {

        g.setColor(Color.red);
        Graphics2D g2 = (Graphics2D) g;
        Line currLine;
        for (int i = 0; i < routeList.size(); i++) {
            currLine = (Line) (routeList.get(i));
            g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 10.0f, null, 0.0f));
             g2.drawPolyline(new int[]{currLine.getP1().getX(), currLine.getP2().getX(), currLine.getP3().getX()},
                    new int[]{currLine.getP1().getY(), currLine.getP2().getY(), currLine.getP3().getY()}, 3);
        }
        for (int m = 0; m < motionList.size(); m++) {
            currLine = (Line) (motionList.get(m));
            g2.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
            g2.drawLine(currLine.getP1().getX(), currLine.getP1().getY(), currLine.getP2().getX(), currLine.getP2().getY());
        }
        for (int m = 0; m < zoneList.size(); m++) {
            currLine = (Line) (zoneList.get(m));
            g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 10.0f, null, 0.0f));
            g2.drawLine(currLine.getP1().getX(), currLine.getP1().getY(), currLine.getP2().getX(), currLine.getP2().getY());
            if (game.defensivePlay.getText().contains("cov 3") || game.defensivePlay.getText().contains("cover 3")) {
                if (currLine.getId().toString().equals("SCB") || currLine.getId().toString().equals("WCB") || currLine.getId().toString().equals("FS")) {
                g2.drawRect(currLine.getP2().getX() - 100, currLine.getP2().getY() - 75, 200, 150);
                } else {
                    g2.drawRect(currLine.getP2().getX() - 50, currLine.getP2().getY() - 50, 100, 100);
                }
            } else if (game.defensivePlay.getText().contains("cov 2") || game.defensivePlay.getText().contains("cover 2")) {
                if (currLine.getId().toString().equals("SS") || currLine.getId().toString().equals("FS")) {
                    g2.drawRect(currLine.getP2().getX() - 100, currLine.getP2().getY() - 75, 200, 150);
                } else {
                    g2.drawRect(currLine.getP2().getX() - 50, currLine.getP2().getY() - 50, 100, 100);
                }
            } else if (game.defensivePlay.getText().contains("cov 4") || game.defensivePlay.getText().contains("cover 4")) {
                if (currLine.getId().toString().equals("SCB") || currLine.getId().toString().equals("WCB") || currLine.getId().toString().equals("SS") || currLine.getId().toString().equals("FS")) {
                    g2.drawRect(currLine.getP2().getX() - 100, currLine.getP2().getY() - 75, 200, 150);
                } else {
                    g2.drawRect(currLine.getP2().getX() - 50, currLine.getP2().getY() - 50, 100, 100);
                }
            } else if (game.defensivePlay.getText().contains("cov 6") || game.defensivePlay.getText().contains("cover 6")) {
                if (currLine.getId().toString().equals("WCB") || currLine.getId().toString().equals("SS")|| currLine.getId().toString().equals("FS")) {
                    g2.drawRect(currLine.getP2().getX() - 100, currLine.getP2().getY() - 75, 200, 150);
                } else {
                    g2.drawRect(currLine.getP2().getX() - 50, currLine.getP2().getY() - 50, 100, 100);
                }
            } else if (game.defensivePlay.getText().isBlank()) {
                g2.drawRect(currLine.getP2().getX() - 50, currLine.getP2().getY() - 50, 100, 100);
            } else {
                g2.drawRect(currLine.getP2().getX() - 50, currLine.getP2().getY() - 50, 100, 100);

            }
        }
        for (int m = 0; m < blockList.size(); m++) {
            currLine = (Line) (blockList.get(m));
            g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 10.0f, null, 0.0f));
            g2.drawLine(currLine.getP1().getX(), currLine.getP1().getY(), currLine.getP2().getX(), currLine.getP2().getY());
            g2.drawArc(currLine.getP2().x-12, currLine.getP2().y-20, 25, 20, 180, 180);
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