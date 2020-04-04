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
    private ArrayList bubbleList = new ArrayList();
    private Line line;
    private int clicks = 0;
    private String input;
    private Long startTime;
    private long playTime = 2000;
    private ArrayList<Point> pOTL = new ArrayList<Point>();
    private ArrayList<Point> pOTLs = new ArrayList<Point>();
    private Point startPoint, midpoint, endPoint;
    private Point pointOnTimeLine, pointOnTimeLines;
    private double pointInTime;
    private Line savedRoutes;


    public Route(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println(x+","+y);
        ArrayList<ID> defense;
        {
            defense = new ArrayList<ID>();
            defense.add(ID.SOSLB);
            defense.add(ID.SISLB);
            defense.add(ID.WOSLB);
            defense.add(ID.WISLB);
            defense.add(ID.FS);
            defense.add(ID.SS);
            defense.add(ID.WCB);
            defense.add(ID.SCB);
        }

        ArrayList<ID> offensiveLine;
        {
            offensiveLine = new ArrayList<ID>();
            offensiveLine.add(ID.C);
            offensiveLine.add(ID.LG);
            offensiveLine.add(ID.LT);
            offensiveLine.add(ID.RT);
            offensiveLine.add(ID.RG);
        }

        LinkedList<GameObject> jags = handler.object;
        for (GameObject player : jags) {
            if (mouseOver(e.getX(), e.getY(), player.getX(), player.getY(), 24, 24)) {
                if (defense.contains(player.getID())) {
                    //TODO put presaved coverages here
                    if (SwingUtilities.isRightMouseButton(e)) {
                        String[] choices = {"1", "2", "3", "4", "6"};
                        String input = (String) JOptionPane.showInputDialog(null, null,
                                "Choose Coverage", JOptionPane.QUESTION_MESSAGE, null, // Use
                                choices, // Array of choices
                                choices[0]); // Initial choice
                        if (input.equals("1")) {
                            preSavedCoverages("1", new Point(player.getX() + 12, player.getY()), player.getID());
                        }
                        if (input.equals("2")) {
                            preSavedCoverages("2", new Point(player.getX() + 12, player.getY()), player.getID());
                        }
                        if (input.equals("3")) {
                            preSavedCoverages("3", new Point(player.getX() + 12, player.getY()), player.getID());
                        }
                        if (input.equals("4")) {
                            preSavedCoverages("4", new Point(player.getX() + 12, player.getY()), player.getID());
                        }
                        if (input.equals("6")) {
                            preSavedCoverages("6", new Point(player.getX() + 12, player.getY()), player.getID());
                        }
                    }
                } else if (offensiveLine.contains(player.getID())) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        String[] choices = {"Counter", "Inside Zone", "Outside Zone", "Power", "Trap"};
                        String input = (String) JOptionPane.showInputDialog(null, null,
                                "Choose Run", JOptionPane.QUESTION_MESSAGE, null, // Use
                                choices, // Array of choices
                                choices[0]); // Initial choice
                        System.out.println(input);
                        if (input.equals("Counter")) {
                            preSavedRuns("Counter", new Point(player.getX() + 12, player.getY()), player.getID());
                        }
                        if (input.equals("Inside Zone")) {
                            preSavedRuns("Inside Zone", new Point(player.getX() + 12, player.getY()), player.getID());
                        }
                        if (input.equals("Outside Zone")) {
                            preSavedRuns("Outside Zone", new Point(player.getX() + 12, player.getY()), player.getID());
                        }
                        if (input.equals("Power")) {
                            preSavedRuns("Power", new Point(player.getX() + 12, player.getY()), player.getID());
                        }
                        if (input.equals("Trap")) {
                            preSavedRuns("Trap", new Point(player.getX() + 12, player.getY()), player.getID());
                        }
                    }
                } else {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        String[] choices = {"Bubble", "Comeback", "Corner", "Corner Post", "Curl", "Dig", "Flat", "Go", "Hitch", "Hitch and Go", "Post", "Post Corner", "Sail",
                                "Skinny Post/Bender", "Slant", "Slant and Go", "Swing", "Under/Drag", "Wheel", "Whip/Return/Pivot", "5 Yard In", "5 Yard Out", "10 Yard Out"};
                        String input = (String) JOptionPane.showInputDialog(null, null,
                                "Choose Route", JOptionPane.QUESTION_MESSAGE, null, // Use
                                choices, // Array of choices
                                choices[0]); // Initial choice
                        System.out.println(input);
                        if (input.equals("Bubble")) {
                            preSavedRoutes("Bubble", new Point(player.getX()+12, player.getY()), player.getID());
                        }
                        if (input.equals("Comeback")) {
                            preSavedRoutes("Comeback", new Point(player.getX()+12, player.getY()+12), player.getID());
                        }
                        if (input.equals("Corner")) {
                            preSavedRoutes("Corner", new Point(player.getX()+12, player.getY()+12), player.getID());
                        }
                        if (input.equals("Corner Post")) {
                            preSavedRoutes("Corner Post", new Point(player.getX()+12, player.getY()+12), player.getID());
                        }
                        if (input.equals("Curl")) {
                            preSavedRoutes("Curl", new Point(player.getX()+12, player.getY()+12), player.getID());
                        }
                        if (input.equals("Dig")) {
                            preSavedRoutes("Dig", new Point(player.getX()+12, player.getY()+12), player.getID());
                        }
                        if (input.equals("Flat")) {
                            preSavedRoutes("Flat", new Point(player.getX()+12, player.getY()+12), player.getID());
                        }
                        if (input.equals("Go")) {
                            preSavedRoutes("Go", new Point(player.getX()+12, player.getY()+12), player.getID());
                        }
                        if (input.equals("Hitch")) {
                            preSavedRoutes("Hitch", new Point(player.getX()+12, player.getY()+12), player.getID());
                        }
                        if (input.equals("Hitch and Go")) {
                            preSavedRoutes("Hitch and Go", new Point(player.getX()+12, player.getY()+12), player.getID());
                        }
                        if (input.equals("Post")) {
                            preSavedRoutes("Post", new Point(player.getX()+12, player.getY()+12), player.getID());
                        }
                        if (input.equals("Post Corner")) {
                            preSavedRoutes("Post Corner", new Point(player.getX()+12, player.getY()+12), player.getID());
                        }
                        if (input.equals("Sail")) {
                            preSavedRoutes("Sail", new Point(player.getX()+12, player.getY()+12), player.getID());
                        }
                        if (input.equals("Skinny Post/Bender")) {
                            preSavedRoutes("Skinny Post/Bender", new Point(player.getX()+12, player.getY()+12), player.getID());
                        }
                        if (input.equals("Slant")) {
                            preSavedRoutes("Slant", new Point(player.getX()+12, player.getY()+12), player.getID());
                        }
                        if (input.equals("Slant and Go")) {
                            preSavedRoutes("Slant and Go", new Point(player.getX()+12, player.getY()+12), player.getID());
                        }
                        if (input.equals("Swing")) {
                            preSavedRoutes("Swing", new Point(player.getX()+12, player.getY()+12), player.getID());
                        }
                        if (input.equals("Under/Drag")) {
                            preSavedRoutes("Under/Drag", new Point(player.getX()+12, player.getY()+12), player.getID());
                        }
                        if (input.equals("Wheel")) {
                            preSavedRoutes("Wheel", new Point(player.getX()+12, player.getY()+12), player.getID());
                        }
                        if (input.equals("Whip/Return/Pivot")) {
                            preSavedRoutes("Whip/Return/Pivot", new Point(player.getX()+12, player.getY()+12), player.getID());
                        }
                        if (input.equals("5 Yard In")) {
                            preSavedRoutes("5 Yard In", new Point(player.getX()+12, player.getY()+12), player.getID());
                        }
                        if (input.equals("5 Yard Out")) {
                            preSavedRoutes("5 Yard Out", new Point(player.getX()+12, player.getY()+12), player.getID());
                        }
                        if (input.equals("10 Yard Out")) {
                            preSavedRoutes("10 Yard Out", new Point(player.getX()+12, player.getY()+12), player.getID());
                        }
                    }
                }
            }
        }
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
                        if (motionList.size() > 0) {
                            for (int i = 0; i < motionList.size(); i++) {
                                Line currLine;
                                currLine = (Line) (motionList.get(i));
                                if (player.getID().equals(currLine.getId()) && currLine.getP2().getX() - line.getP1().getX() <= 10 || currLine.getP2().getX() - line.getP1().getX() <= -10) {
                                    clicks++;
                                    line.setId(player.getID());
                                    return;
                                }
                            }
                        } else if (routeList.size() > 0) {
                            for (int i = 0; i < routeList.size(); i++) {
                                Line currLine;
                                currLine = (Line) (routeList.get(i));
                                if (player.getID().equals(currLine.getId()) && currLine.getP3().getX() - line.getP1().getX() <= 10 || currLine.getP3().getX() - line.getP1().getX() <= -10) {
                                    clicks++;
                                    line.setId(player.getID());
                                    return;
                                }
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
            } else if (clicks == 1) {
                line.setP2(new Point(x, y));
                clicks++;
            } else {
                line.setP3(new Point(x, y));
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
            } else if (clicks == 1) {
                line.setP2(new Point(x, y));
                clicks++;
            } else {
                line.setP3(new Point(x, y));
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
            if (!bubbleList.isEmpty()) {
                int size = bubbleList.size() - 1;
                bubbleList.remove(size);
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
            for (int z = 0; z < bubbleList.size(); z++) {
                Line currLine;
                currLine = (Line) (bubbleList.get(z));
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
        Timer timer = new Timer(5, new ActionListener() {
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
        Timer timer2 = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<GameObject> jags = handler.object;
                for (GameObject player : jags) {
                    for (int l = 0; l < routeList.size(); l++) {
                        if (mouseOver(((Line) routeList.get(l)).getP1().getX(), ((Line) routeList.get(l)).getP1().getY(), player.getX(), player.getY(), 24, 24)) {
                           if (((Line) routeList.get(l)).getP5() != null) {

                               int p5x = ((Line) routeList.get(l)).getP5().getX() - 12;
                               int p5y = ((Line) routeList.get(l)).getP5().getY() - 12;
                               player.setX(p5x);
                               player.setY(p5y);
                           } else if (((Line) routeList.get(l)).getP4() != null) {
                               int p4x = ((Line) routeList.get(l)).getP4().getX() - 12;
                               int p4y = ((Line) routeList.get(l)).getP4().getY() - 12;
                               player.setX(p4x);
                               player.setY(p4y);
                           } else {
                               int p3x = ((Line) routeList.get(l)).getP3().getX() - 12;
                               int p3y = ((Line) routeList.get(l)).getP3().getY() - 12;
                               player.setX(p3x);
                               player.setY(p3y);
                           }

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
                   for (int l = 0; l < bubbleList.size(); l++) {
                       if (mouseOver(((Line) bubbleList.get(l)).getP1().getX(), ((Line) bubbleList.get(l)).getP1().getY(), player.getX(), player.getY(), 24, 24)) {
                           int p2x = ((Line) bubbleList.get(l)).getP2().getX() - 12;
                           int p2y = ((Line) bubbleList.get(l)).getP2().getY() - 12;
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
        if (!bubbleList.isEmpty()) {
            int size = bubbleList.size() - 1;
            bubbleList.remove(size);
        }

    }

    public void render(Graphics g) {

        g.setColor(Color.red);
        Graphics2D g2 = (Graphics2D) g;
        Line currLine;
        for (int i = 0; i < routeList.size(); i++) {
            currLine = (Line) (routeList.get(i));
            if (currLine.getP4() == null) {
                g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 10.0f, null, 0.0f));
                g2.drawPolyline(new int[]{currLine.getP1().getX(), currLine.getP2().getX(), currLine.getP3().getX()},
                new int[]{currLine.getP1().getY(), currLine.getP2().getY(), currLine.getP3().getY()}, 3);
            } else if (currLine.getP5() == null) {
                g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 10.0f, null, 0.0f));
                g2.drawPolyline(new int[]{currLine.getP1().getX(), currLine.getP2().getX(), currLine.getP3().getX(), currLine.getP4().getX()},
                new int[]{currLine.getP1().getY(), currLine.getP2().getY(), currLine.getP3().getY(), currLine.getP4().getY()}, 4);
            } else {
                g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 10.0f, null, 0.0f));
                g2.drawPolyline(new int[]{currLine.getP1().getX(), currLine.getP2().getX(), currLine.getP3().getX(), currLine.getP4().getX(), currLine.getP5().getX()},
                new int[]{currLine.getP1().getY(), currLine.getP2().getY(), currLine.getP3().getY(), currLine.getP4().getY(), currLine.getP5().getY()}, 5);
            }
        }
        for (int m = 0; m < motionList.size(); m++) {
            currLine = (Line) (motionList.get(m));
            g2.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
            g2.drawPolyline(new int[]{currLine.getP1().getX(), currLine.getP2().getX(), currLine.getP3().getX()},
                    new int[]{currLine.getP1().getY(), currLine.getP2().getY(), currLine.getP3().getY()}, 3);
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
                    if (currLine.getId().toString().equals("WCB") || currLine.getId().toString().equals("SS") || currLine.getId().toString().equals("FS")) {
                        g2.drawRect(currLine.getP2().getX() - 100, currLine.getP2().getY() - 75, 200, 150);
                    } else {
                        g2.drawRect(currLine.getP2().getX() - 50, currLine.getP2().getY() - 50, 100, 100);
                    }
                } else {
                g2.drawRect(currLine.getP2().getX() - 50, currLine.getP2().getY() - 50, 100, 100);
            }
        }

        for (int m = 0; m < blockList.size(); m++) {
            currLine = (Line) (blockList.get(m));
            g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 10.0f, null, 0.0f));
            g2.drawPolyline(new int[]{currLine.getP1().getX(), currLine.getP2().getX(), currLine.getP3().getX()},
                    new int[]{currLine.getP1().getY(), currLine.getP2().getY(), currLine.getP3().getY()}, 3);
            g2.drawPolyline(new int[] {currLine.getP3().getX()-10, currLine.getP3().getX(), currLine.getP3().getX()+10},
                        new int[] {currLine.getP3().getY(), currLine.getP3().getY(), currLine.getP3().getY()}, 3);
        }

        for (int m = 0; m < bubbleList.size(); m++) {
            currLine = (Line) (bubbleList.get(m));
            g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 10.0f, null, 0.0f));
            if (currLine.getP1().getX() <= game.frame.getWidth()/2 && currLine.getP2() == null) {
                g2.drawArc(currLine.getP1().x -125, currLine.getP1().y-12, 125, 40, 180, 180);
            } else if (currLine.getP1().getX() >= game.frame.getWidth()/2 && currLine.getP2() == null) {
                g2.drawArc(currLine.getP1().x, currLine.getP1().y-12, 125, 40, 180, 180);
            } else if (currLine.getP1().getX() <= game.frame.getWidth()/2 && currLine.getP2().equals(currLine.getP1())) {
                g2.drawArc(currLine.getP1().x - 325, currLine.getP1().y - 24, 325, 40, 180, 180);
            } else if (currLine.getP1().getX() >= game.frame.getWidth()/2 && currLine.getP2().equals(currLine.getP1())) {
                g2.drawArc(currLine.getP1().x, currLine.getP1().y - 24, 325, 40, 180, 180);
            }
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

    protected boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
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

    public void preSavedRoutes(String string, Point p1, ID id) {
        if (string.isEmpty()) {
            return;
        } else if (string.equals("Bubble")) {
            savedRoutes = new Line();
            savedRoutes.setP1(p1);
            savedRoutes.setId(id);
            bubbleList.add(savedRoutes);
        } else if (string.equals("Comeback")) {
            if (p1.getX() <= game.frame.getWidth() / 2) {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x - 20, p1.y - 225);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x - 45, p2.y + 45));
                savedRoutes.setId(id);
            } else {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x + 20, p1.y - 225);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x + 45, p2.y + 45));
                savedRoutes.setId(id);
            }
            routeList.add(savedRoutes);

        } else if (string.equals("Corner")) {
            if (p1.getX() <= game.frame.getWidth() / 2) {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x + 20, p1.y - 170);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x - 85, p2.y - 75));
                savedRoutes.setId(id);
            } else {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x - 20, p1.y - 170);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x + 85, p2.y - 75));
                savedRoutes.setId(id);
            }
            routeList.add(savedRoutes);

        } else if (string.equals("Corner Post")) {
            if (p1.getX() <= game.frame.getWidth() / 2) {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x + 20, p1.y - 170);
                savedRoutes.setP2(p2);
                Point p3 = new Point(p2.x - 30, p2.y - 25);
                savedRoutes.setP3(p3);
                savedRoutes.setP4(new Point(p3.x + 150, p3.y - 75));
                savedRoutes.setId(id);
            } else {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x - 20, p1.y - 170);
                savedRoutes.setP2(p2);
                Point p3 = new Point(p2.x + 30, p2.y - 25);
                savedRoutes.setP3(p3);
                savedRoutes.setP4(new Point(p3.x - 150, p3.y - 75));
                savedRoutes.setId(id);
            }
            routeList.add(savedRoutes);

        } else if (string.equals("Curl")) {
            if (p1.getX() <= game.frame.getWidth()/2) {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x+20, p1.y-170);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x+10, p2.y+10));
                savedRoutes.setId(id);
            } else {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x-20, p1.y-170);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x-10, p2.y+10));
                savedRoutes.setId(id);
            }
            routeList.add(savedRoutes);
        } else if (string.equals("Dig")) {
            if (p1.getX() <= game.frame.getWidth() / 2) {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x + 20, p1.y - 170);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x + 100, p2.y));
                savedRoutes.setId(id);
            } else {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x - 20, p1.y - 170);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x - 100, p2.y));
                savedRoutes.setId(id);
            }
            routeList.add(savedRoutes);
        } else if (string.equals("Flat")) {
            if (p1.getX() <= game.frame.getWidth() / 2) {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x - 300, p1.y - 50);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(p2);
                savedRoutes.setId(id);
            } else {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x + 300, p1.y -50);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(p2);
                savedRoutes.setId(id);
            }
            routeList.add(savedRoutes);

        } else if (string.equals("Go")) {
            if (p1.getX() <= game.frame.getWidth() / 2) {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x + 20, p1.y - 250);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(p2);
                savedRoutes.setId(id);
            } else {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x - 20, p1.y - 250);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(p2);
                savedRoutes.setId(id);
            }
            routeList.add(savedRoutes);

        } else if (string.equals("Hitch")) {
            if (p1.getX() <= game.frame.getWidth() / 2) {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x + 20, p1.y - 85);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x + 10, p2.y + 10));
                savedRoutes.setId(id);
            } else {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x - 20, p1.y - 85);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x - 10, p2.y + 10));
                savedRoutes.setId(id);
            }
            routeList.add(savedRoutes);
        } else if (string.equals("Hitch and Go")) {
            if (p1.getX() <= game.frame.getWidth() / 2) {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x + 20, p1.y - 85);
                savedRoutes.setP2(p2);
                Point p3 = new Point(p2.x + 10, p2.y + 10);
                savedRoutes.setP3(p3);
                savedRoutes.setP4(new Point(p3.getX() +20, p3.y-150));
                savedRoutes.setId(id);
            } else {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x - 20, p1.y - 85);
                savedRoutes.setP2(p2);
                Point p3 = new Point(p2.x - 10, p2.y + 10);
                savedRoutes.setP3(p3);
                savedRoutes.setP4(new Point(p3.getX()-20, p3.y-150));
                savedRoutes.setId(id);
            }
            routeList.add(savedRoutes);

        } else if (string.equals("Post")) {
            if (p1.getX() <= game.frame.getWidth() / 2) {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x + 20, p1.y - 170);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x + 85, p2.y - 75));
                savedRoutes.setId(id);
            } else {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x - 20, p1.y - 170);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x - 85, p2.y - 75));
                savedRoutes.setId(id);
            }
            routeList.add(savedRoutes);

        } else if (string.equals("Post Corner")) {
            if (p1.getX() <= game.frame.getWidth() / 2) {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x + 20, p1.y - 170);
                savedRoutes.setP2(p2);
                Point p3 = new Point(p2.x + 30, p2.y - 25);
                savedRoutes.setP3(p3);
                savedRoutes.setP4(new Point(p3.x - 150, p3.y - 75));
                savedRoutes.setId(id);
            } else {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x - 20, p1.y - 170);
                savedRoutes.setP2(p2);
                Point p3 = new Point(p2.x - 30, p2.y - 25);
                savedRoutes.setP3(p3);
                savedRoutes.setP4(new Point(p3.x + 150, p3.y - 75));
                savedRoutes.setId(id);
            }
            routeList.add(savedRoutes);

        } else if (string.equals("Sail")) {
            if (p1.getX() <= game.frame.getWidth() / 2) {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x + 50, p1.y - 100);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x + 300, p2.y - 100));
                savedRoutes.setId(id);
            } else {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x - 50, p1.y - 100);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x - 300, p2.y - 100));
                savedRoutes.setId(id);
            }
            routeList.add(savedRoutes);

        } else if (string.equals("Skinny Post/Bender")) {
            if (p1.getX() <= game.frame.getWidth() / 2) {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x + 20, p1.y - 170);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x + 75, p2.y - 125));
                savedRoutes.setId(id);
            } else {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x - 20, p1.y - 170);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x - 75, p2.y - 125));
                savedRoutes.setId(id);
            }
            routeList.add(savedRoutes);

        } else if (string.equals("Slant")) {
            if (p1.getX() <= game.frame.getWidth() / 2) {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x + 20, p1.y - 70);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x + 95, p2.y - 65));
                savedRoutes.setId(id);
            } else {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x - 20, p1.y - 70);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x - 95, p2.y - 65));
                savedRoutes.setId(id);
            }
            routeList.add(savedRoutes);

        } else if (string.equals("Slant and Go")) {
            if (p1.getX() <= game.frame.getWidth() / 2) {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x + 20, p1.y - 70);
                savedRoutes.setP2(p2);
                Point p3 = new Point(p2.x + 95, p2.y - 65);
                savedRoutes.setP3(p3);
                savedRoutes.setP4(new Point(p3.x+10, p3.y-100));
                savedRoutes.setId(id);
            } else {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x - 20, p1.y - 70);
                savedRoutes.setP2(p2);
                Point p3 = new Point(p2.x - 95, p2.y - 65);
                savedRoutes.setP3(p3);
                savedRoutes.setP4(new Point(p3.x-10, p3.y-100));
                savedRoutes.setId(id);
            }
            routeList.add(savedRoutes);

        } else if (string.equals("Swing")) {
            savedRoutes = new Line();
            savedRoutes.setP1(p1);
            savedRoutes.setP2(p1);
            savedRoutes.setId(id);
            bubbleList.add(savedRoutes);

        } else if (string.equals("Under/Drag")) {
            if (p1.getX() <= game.frame.getWidth() / 2) {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x + 50, p1.y - 90);
                savedRoutes.setP2(p2);
                Point p3 = new Point(p2.x + 400, p2.y);
                savedRoutes.setP3(p3);
                savedRoutes.setId(id);
            } else {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x - 50, p1.y - 90);
                savedRoutes.setP2(p2);
                Point p3 = new Point(p2.x - 400, p2.y);
                savedRoutes.setP3(p3);
                savedRoutes.setId(id);
            }
            routeList.add(savedRoutes);
        } else if (string.equals("Wheel")) {
            if (p1.getX() <= game.frame.getWidth() / 2) {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x - 20 , p1.y - 50);
                savedRoutes.setP2(p2);
                Point p3 = new Point(p2.x - 200, p2.y);
                savedRoutes.setP3(p3);
                Point p4 = new Point(p3.x - 50, p3.y - 30);
                savedRoutes.setP4(p4);
                Point p5 = new Point(p4.x + 50, p4.y - 200);
                savedRoutes.setP5(p5);
                savedRoutes.setId(id);
            } else {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x + 20 , p1.y - 50);
                savedRoutes.setP2(p2);
                Point p3 = new Point(p2.x + 200, p2.y);
                savedRoutes.setP3(p3);
                Point p4 = new Point(p3.x - 50, p3.y - 30);
                savedRoutes.setP4(p4);
                Point p5 = new Point(p4.x - 50, p4.y - 200);
                savedRoutes.setP5(p5);
                savedRoutes.setId(id);
            }
            routeList.add(savedRoutes);

        } else if (string.equals("Whip/Return/Pivot")) {
            if (p1.getX() <= game.frame.getWidth() / 2) {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x + 100 , p1.y - 100);
                savedRoutes.setP2(p2);
                Point p3 = new Point(p2.x, p2.y + 10);
                savedRoutes.setP3(p3);
                Point p4 = new Point(p3.x - 150, p3.y);
                savedRoutes.setP4(p4);
                savedRoutes.setId(id);
            } else {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x - 100 , p1.y - 100);
                savedRoutes.setP2(p2);
                Point p3 = new Point(p2.x, p2.y + 10);
                savedRoutes.setP3(p3);
                Point p4 = new Point(p3.x +150, p3.y);
                savedRoutes.setP4(p4);
                savedRoutes.setId(id);
            }
            routeList.add(savedRoutes);

        } else if (string.equals("5 Yard In")) {
            if (p1.getX() <= game.frame.getWidth() / 2) {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x + 20, p1.y - 80);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x + 100, p2.y));
                savedRoutes.setId(id);
            } else {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x - 20, p1.y - 80);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x - 100, p2.y));
                savedRoutes.setId(id);
            }
            routeList.add(savedRoutes);

        } else if (string.equals("5 Yard Out")) {
            if (p1.getX() <= game.frame.getWidth() / 2) {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x + 20, p1.y - 80);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x - 100, p2.y));
                savedRoutes.setId(id);
            } else {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x - 20, p1.y - 80);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x + 100, p2.y));
                savedRoutes.setId(id);
            }
            routeList.add(savedRoutes);

        }else if (string.equals("10 Yard Out")) {
            if (p1.getX() <= game.frame.getWidth() / 2) {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x + 20, p1.y - 170);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x - 100, p2.y));
                savedRoutes.setId(id);
            } else {
                savedRoutes = new Line();
                savedRoutes.setP1(p1);
                Point p2 = new Point(p1.x - 20, p1.y - 170);
                savedRoutes.setP2(p2);
                savedRoutes.setP3(new Point(p2.x + 100, p2.y));
                savedRoutes.setId(id);
            }
            routeList.add(savedRoutes);
        }
    }

    public void preSavedRuns(String string, Point p1, ID id) {
        LinkedList<GameObject> jags = handler.object;
        for (GameObject player : jags) {
            if (string.isEmpty()) {
                return;
            } else if (string.equals("Counter")) {
                if (player.getID().toString().equals("SOSLB")) {
                    player.setX(430);
                    player.setY(371);
                }

                if (player.getID().toString().equals("TE")) {
                    player.setX(445);
                    player.setY(411);
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX()+12, player.getY()+12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x-3, playerPoint.y-20));
                    savedRoutes.setP3(savedRoutes.getP2());
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("C")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX()+12, player.getY()+12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x-10, playerPoint.y-15));
                    savedRoutes.setP3(savedRoutes.getP2());
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("RG")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX()+12, player.getY()+12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x+10, playerPoint.y-15));
                    savedRoutes.setP3(savedRoutes.getP2());
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("RT")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX()+12, player.getY()+12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x-110, playerPoint.y-60));
                    savedRoutes.setP3(savedRoutes.getP2());
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("LG")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX()+12, player.getY()+12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x+7, playerPoint.y+20));
                    savedRoutes.setP3(new Point(playerPoint.x+140, playerPoint.y-20));
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("LT")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX()+12, player.getY()+12);
                    savedRoutes.setP1(playerPoint);
                    Point p2 = new Point(playerPoint.x+70, playerPoint.y+30);
                    savedRoutes.setP2(p2);
                    savedRoutes.setP3(new Point(p2.x+25, p2.y-90));
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                }
            } else if (string.equals("Inside Zone")) {
                if (player.getID().toString().equals("C")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 25, playerPoint.y - 25));
                    savedRoutes.setP3(savedRoutes.getP2());
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("RG")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 25, playerPoint.y - 25));
                    savedRoutes.setP3(savedRoutes.getP2());
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("RT")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 25, playerPoint.y - 25));
                    savedRoutes.setP3(savedRoutes.getP2());
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("LG")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 25, playerPoint.y - 25));
                    savedRoutes.setP3(savedRoutes.getP2());
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("LT")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 25, playerPoint.y - 25));
                    savedRoutes.setP3(savedRoutes.getP2());
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                }
            } else if (string.equals("Outside Zone")) {
                if (player.getID().toString().equals("C")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX()+12, player.getY()+12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x+20, playerPoint.y-15));
                    savedRoutes.setP3(savedRoutes.getP2());
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("RG")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX()+12, player.getY()+12);
                    savedRoutes.setP1(playerPoint);
                    Point p2 = new Point(playerPoint.x+10, playerPoint.y-20);
                    savedRoutes.setP2(p2);
                    savedRoutes.setP3(new Point(p2.x-10, p2.y-50));
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("RT")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX()+12, player.getY()+12);
                    savedRoutes.setP1(playerPoint);
                    Point p2 = new Point(playerPoint.x+30, playerPoint.y-15);
                    savedRoutes.setP2(p2);
                    savedRoutes.setP3(new Point(p2.x-10, p2.y-50));
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("LG")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX()+12, player.getY()+12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x+10, playerPoint.y-15));
                    savedRoutes.setP3(savedRoutes.getP2());
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("LT")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX()+12, player.getY()+12);
                    savedRoutes.setP1(playerPoint);
                    Point p2 = new Point(playerPoint.x+30, playerPoint.y-10);
                    savedRoutes.setP2(p2);
                    savedRoutes.setP3(new Point(p2.x+10, p2.y-30));
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("TE")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX()+12, player.getY()+12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x+20, playerPoint.y-25));
                    savedRoutes.setP3(savedRoutes.getP2());
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                }
            } else if (string.equals("Power")) {
                if (player.getID().toString().equals("C")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x - 5, playerPoint.y - 20));
                    savedRoutes.setP3(savedRoutes.getP2());
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("RG")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 5, playerPoint.y - 20));
                    savedRoutes.setP3(savedRoutes.getP2());
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("RT")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    Point p2 = new Point(playerPoint.x - 15, playerPoint.y - 25);
                    savedRoutes.setP2(p2);
                    savedRoutes.setP3(new Point(p2.x - 25, p2.y - 25));
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("LG")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    Point p2 = new Point(playerPoint.x + 50, playerPoint.y + 25);
                    savedRoutes.setP2(p2);
                    savedRoutes.setP3(new Point(p2.x + 70, p2.y - 90));
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("LT")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x - 5, playerPoint.y - 20));
                    savedRoutes.setP3(savedRoutes.getP2());
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("TE")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x, playerPoint.y - 20));
                    savedRoutes.setP3(savedRoutes.getP2());
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                }
            } else if (string.equals("Trap")) {
                if (player.getID().toString().equals("C")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x - 5, playerPoint.y - 15));
                    savedRoutes.setP3(savedRoutes.getP2());
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("RG")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x - 5, playerPoint.y - 60));
                    savedRoutes.setP3(savedRoutes.getP2());
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("RT")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 10, playerPoint.y - 60));
                    savedRoutes.setP3(savedRoutes.getP2());
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("LG")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    Point p2 = new Point(playerPoint.x + 15, playerPoint.y + 15);
                    savedRoutes.setP2(p2);
                    savedRoutes.setP3(new Point(p2.x + 60, p2.y - 30));
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("LT")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    Point p2 = new Point(playerPoint.x + 20, playerPoint.y - 15);
                    savedRoutes.setP2(p2);
                    savedRoutes.setP3(new Point(p2.x + 10, p2.y - 25));
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                } else if (player.getID().toString().equals("TE")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x, playerPoint.y - 20));
                    savedRoutes.setP3(savedRoutes.getP2());
                    savedRoutes.setId(player.getID());
                    blockList.add(savedRoutes);
                }
            }
        }
    }

    public void preSavedCoverages(String string, Point p1, ID id) {
        LinkedList<GameObject> jags = handler.object;
        for (GameObject player : jags) {
            if (string.isEmpty()) {
                return;
            } else if (string.equals("1")) {
                if (player.getID().toString().equals("FS")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX()+12, player.getY()+12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x, playerPoint.y-100));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                }
                game.defensivePlay.setText("cover 1");
            } else if (string.equals("2")) {
                if (player.getID().toString().equals("FS")) {
                    player.setX(732);
                    player.setY(274);
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 50, playerPoint.y - 100));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                } else if (player.getID().toString().equals("SS")) {
                    player.setX(360);
                    player.setY(266);
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x - 50, playerPoint.y - 100));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                } else if (player.getID().toString().equals("WCB")) {
                    player.setX(110);
                    player.setY(360);
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 20, playerPoint.y - 35));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                }  else if (player.getID().toString().equals("SCB")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x - 20, playerPoint.y - 35));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                } else if (player.getID().toString().equals("WOSLB")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x - 120, playerPoint.y - 35));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                } else if (player.getID().toString().equals("SISLB")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x - 60, playerPoint.y - 35));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                } else if (player.getID().toString().equals("SOSLB")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 50, playerPoint.y -20));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                }
                game.defensivePlay.setText("cover 2");
            } else if (string.equals("3")) {
                if (player.getID().toString().equals("FS")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 50, playerPoint.y - 100));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                } else if (player.getID().toString().equals("SS")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x - 50, playerPoint.y - 30));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                } else if (player.getID().toString().equals("WCB")) {
                    player.setX(110);
                    player.setY(360);
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 20, playerPoint.y - 135));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                } else if (player.getID().toString().equals("SCB")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x - 20, playerPoint.y - 135));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                } else if (player.getID().toString().equals("WOSLB")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x - 50, playerPoint.y - 35));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                } else if (player.getID().toString().equals("SISLB")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 20, playerPoint.y - 35));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                } else if (player.getID().toString().equals("SOSLB")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 120, playerPoint.y - 35));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                }
                game.defensivePlay.setText("cover 3");
            } else if (string.equals("4")) {
                if (player.getID().toString().equals("FS")) {
                    player.setX(732);
                    player.setY(274);
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 10, playerPoint.y - 75));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                } else if (player.getID().toString().equals("SS")) {
                    player.setX(360);
                    player.setY(266);
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 10, playerPoint.y - 75));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                } else if (player.getID().toString().equals("WCB")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 20, playerPoint.y - 115));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                } else if (player.getID().toString().equals("SCB")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x - 20, playerPoint.y - 115));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                } else if (player.getID().toString().equals("WOSLB")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x - 120, playerPoint.y - 35));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                } else if (player.getID().toString().equals("SISLB")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 20, playerPoint.y - 35));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                } else if (player.getID().toString().equals("SOSLB")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 120, playerPoint.y - 35));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                }
                game.defensivePlay.setText("cover 4");
            } else if (string.equals("6")) {
                if (player.getID().toString().equals("FS")) {
                    player.setX(732);
                    player.setY(274);
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 45, playerPoint.y - 75));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                } else if (player.getID().toString().equals("SS")) {
                    player.setX(360);
                    player.setY(266);
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 10, playerPoint.y - 75));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                } else if (player.getID().toString().equals("WCB")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 20, playerPoint.y - 115));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                } else if (player.getID().toString().equals("SCB")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x - 20, playerPoint.y - 35));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                } else if (player.getID().toString().equals("WOSLB")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x - 120, playerPoint.y - 35));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                } else if (player.getID().toString().equals("SISLB")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 20, playerPoint.y - 35));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                } else if (player.getID().toString().equals("SOSLB")) {
                    savedRoutes = new Line();
                    Point playerPoint = new Point(player.getX() + 12, player.getY() + 12);
                    savedRoutes.setP1(playerPoint);
                    savedRoutes.setP2(new Point(playerPoint.x + 120, playerPoint.y - 35));
                    savedRoutes.setId(player.getID());
                    zoneList.add(savedRoutes);
                }
                game.defensivePlay.setText("cover 6");
            }
        }
    }

}

class Line {

    private Point p1;
    private Point p2;
    private Point p3;
    private Point p4;
    private Point p5;
    private Point p6;
    private Point p7;
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

    public Point getP4(){
        return p4;
    }

    public Point getP5(){
        return p5;
    }

    public Point getP6(){
        return p6;
    }

    public Point getP7(){
        return p7;
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

    public void setP4(Point p4) {
        this.p4 = p4;
    }

    public void setP5(Point p5) {
        this.p5 = p5;
    }

    public void setP6(Point p6) {
        this.p6 = p6;
    }

    public void setP7(Point p7) {
        this.p7 = p7;
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