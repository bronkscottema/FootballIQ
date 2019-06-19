import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 720, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private Movement movement;
    private Route route;
//    private ResourceManager rM;


    public Game() {
        handler = new Handler();
        JFrame frame = new JFrame("FootballIQ");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        //offense
        JTextArea offense = new JTextArea("offense");
        offense.setBounds(0, 495-25, 200, 25);
        frame.add(offense);
        //top left offense play
        JTextArea offensivePlay = new JTextArea("insert play name");
        offensivePlay.setBounds(0, 495, 200, 25);
        frame.add(offensivePlay);
        //top right defense
        JTextArea defense = new JTextArea("defense");
        defense.setBounds(520, 495-25, 200, 25);
        frame.add(defense);
        //top left defensive play
        JTextArea defensivePlay = new JTextArea("insert play name");
        defensivePlay.setBounds(520, 495, 200, 25);
        frame.add(defensivePlay);

        //buttons
        Button undo = new Button("undo");
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                route.undo();
            }
        });
        undo.setBounds(260,468,50,50);
        frame.add(undo);
        Button play = new Button("play");
        play.setBounds(310,468,50,50);
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                route.play();
            }
        });
        frame.add(play);
        Button save = new Button("save");
//        undo.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                route.undo();
//            }
//        });
        save.setBounds(360,468,50,50);
        frame.add(save);
        Button reset = new Button("reset");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                route.reset();
            }
        });
        reset.setBounds(410,468,50,50);
        frame.add(reset);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);
        movement = new Movement(handler, this);
        route = new Route(handler,this);
        handler.startGame();
        this.addMouseMotionListener(movement);
        this.addMouseMotionListener(route);
        this.addMouseListener(route);
        this.start();
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
        double amountOfTicks = 10;
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

            if(System.currentTimeMillis() - timer > 100000) {
                timer += 100000;
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
        movement.tick();
        route.tick();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        Image img;
        img = Toolkit.getDefaultToolkit().getImage("src/images/field.png");
        g.drawImage(img,0, 0, WIDTH, HEIGHT, this);
        route.render(g);
        handler.render(g);
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