import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.LinkedList;

public class Route extends MouseAdapter {

    private int x,y;

    private Handler handler;
    private Player player;
    private Game game;
//    public Game.STATE gameSTATE;
    private Point p1 = new Point();
    private Point p2 = new Point();
    private ArrayList list = new ArrayList();
    private Line line;
    private int clicks = 0;

    public Route( Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }



    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e){
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
        game.repaint();
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


    public void render(Graphics g) {
        g.setColor(Color.red);
        Line currLine;
        for (int i = 0; i < list.size(); i++) {
            currLine = (Line) (list.get(i));
            g.drawLine(currLine.getP1().getX(), currLine.getP1().getY(),currLine.getP2().getX(), currLine.getP2().getY());
        }
    }

}
class Line {

    private Point p1;
    private Point p2;

    public Line() {
    }

    public Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
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