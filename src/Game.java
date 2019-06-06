
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 720, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private Movement movement;
    private Route route;
    private conceptsVsConcepts conceptsVsConcepts;
    private menu menu;

    public enum STATE {
        menu,
        help,
        login,
        game;
    }

    public STATE gameSTATE = STATE.menu;

    public Game() {
        handler = new Handler();
        movement = new Movement(handler, this);
        menu = new menu(this, handler);
        route = new Route(handler,this);
        new Window(WIDTH, HEIGHT, "FootballIQ", this);
        this.addMouseListener(menu);
        handler.startGame();
        conceptsVsConcepts = new conceptsVsConcepts();
        this.addMouseMotionListener(movement);
        this.addMouseMotionListener(route);
        this.addMouseListener(route);

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
        this.requestFocus();
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
        if (gameSTATE == STATE.game) {
            conceptsVsConcepts.tick();
            movement.tick();
            route.tick();
        }
        else if (gameSTATE == STATE.menu) {
            menu.tick();
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        if (gameSTATE == STATE.game) {
            Image img;
            img = Toolkit.getDefaultToolkit().getImage("src/images/field.png");
            g.drawImage(img,0, 0, WIDTH, HEIGHT, this);
            route.render(g);
            handler.render(g);
            conceptsVsConcepts.render(g);
        } else if (gameSTATE == STATE.menu) {
            menu.render(g);
        }
        g.dispose();
        bs.show();
    }

    public static int clamp (int var, int min, int max) {
        if (var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;

    }

    public static void main(String[] args) {
        new Game();
    }
}