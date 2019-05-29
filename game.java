
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.applet.*;

public class game extends Canvas implements Runnable {

    public static final int WIDTH = 720, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;

    private Handler handler;

    public game() {
        handler = new Handler();

        new Window(WIDTH, HEIGHT, "FootballIQ", this);

        handler.addObject(new Player(WIDTH/2, HEIGHT/2, ID.C));
        handler.addObject(new Player((WIDTH/2)+25, HEIGHT/2, ID.LG));
        handler.addObject(new Player((WIDTH/2)+50, HEIGHT/2, ID.LT));
        handler.addObject(new Player((WIDTH/2)-25, HEIGHT/2, ID.RG));
        handler.addObject(new Player((WIDTH/2)-50, HEIGHT/2, ID.RT));
        handler.addObject(new Player(WIDTH/2, (HEIGHT/2)+50, ID.QB));
        handler.addObject(new Player((WIDTH/2)-25, (HEIGHT/2)+55, ID.RB));
        handler.addObject(new Player((WIDTH/2)+75, HEIGHT/2, ID.TE));
        handler.addObject(new Player((WIDTH/2)+300, (HEIGHT/2)+15, ID.WR));
        handler.addObject(new Player((WIDTH/2)-300, (HEIGHT/2), ID.WR));
        handler.addObject(new Player((WIDTH/2)-175, (HEIGHT/2)+15, ID.WR));
//        handler.addObject(new Player(375, 80, ID.widereceiver));
//        handler.addObject(new Player(100, 80, ID.widereceiver));
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try{
            thread.join();
            running = false;
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer= System.currentTimeMillis();
        int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
    }

    private void render () {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        Image img;
        img = Toolkit.getDefaultToolkit().getImage("images/field.png");
        g.drawImage(img,0, 0, WIDTH, HEIGHT, this);
        handler.render(g);
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new game();
    }
}