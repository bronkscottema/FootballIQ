//import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//public class menu extends MouseAdapter {
//
//    private Game game;
//    private Handler handler;
//    final int WIDTH = 720, HEIGHT = WIDTH / 12 * 9;
//
//
//    public menu(Game game, Handler handler) {
//        this.game = game;
//        this.handler = handler;
//    }
//
//    public void mousePressed(MouseEvent e) {
//        int mx = e.getX();
//        int my = e.getY();
//
//        if (mouseOver(mx, my, 185,195,355,50 )) {
//            game.gameSTATE = Game.STATE.game;
//
//        }
//
//    }
//
//    public void mouseReleased(MouseEvent e) {
//
//    }
//
//    public void tick() {
//
//    }
//
//    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
//        if (mx > x && mx < x + width) {
//            if(my > y && my < y + height) {
//                return true;
//            } else return false;
//        } else return false;
//    }
//
//    public void render(Graphics g) {
//        g.setColor(Color.BLACK);
//        Font font = new Font("SansSerif", Font.PLAIN, 56);
//        FontMetrics metrics = g.getFontMetrics(font);
//        Image img;
//        img = Toolkit.getDefaultToolkit().getImage("src/images/field.png");
//        g.drawImage(img,0, 0, WIDTH, HEIGHT, game);
//        // Determine the X coordinate for the text
//        int x = (720 - metrics.stringWidth("FOOTBALL IQ")) / 2;
//        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
//        int y = (540 - metrics.getHeight()) / 2;
//        // Set the font
//        g.setFont(font);
//        // Draw the String
//        g.drawString("FOOTBALL IQ", x, y);
//    }
//}
