import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.*;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 720, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private Movement movement;
    private Route route;


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
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooseDirec = new JFileChooser();
                chooseDirec.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooseDirec.showSaveDialog(frame.getContentPane());
                File file = chooseDirec.getSelectedFile();
                file = new File(file+".gif");
                BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB_PRE);
                Graphics2D g2=(Graphics2D)image.getGraphics();
                g2.setColor(Color.WHITE);
                g2.fillRect(0,0,WIDTH,HEIGHT);
                g2.setStroke(new BasicStroke(1));
                g2.setColor(Color.black);
                g2.drawLine(0,20,WIDTH,20);
                g2.drawLine((WIDTH/2)-155,30,(WIDTH/2)-145,30);
                g2.drawLine((WIDTH/2)-155,40,(WIDTH/2)-145,40);
                g2.drawLine((WIDTH/2)-155,50,(WIDTH/2)-145,50);
                g2.drawLine((WIDTH/2)-155,60,(WIDTH/2)-145,60);
                g2.drawLine((WIDTH/2)+155,60,(WIDTH/2)+145,60);
                g2.drawLine((WIDTH/2)+155,50,(WIDTH/2)+145,50);
                g2.drawLine((WIDTH/2)+155,40,(WIDTH/2)+145,40);
                g2.drawLine((WIDTH/2)+155,30,(WIDTH/2)+145,30);
                g2.drawLine(0,70,WIDTH,70);
                g2.drawLine((WIDTH/2)-155,80,(WIDTH/2)-145,80);
                g2.drawLine((WIDTH/2)-155,90,(WIDTH/2)-145,90);
                g2.drawLine((WIDTH/2)-155,100,(WIDTH/2)-145,100);
                g2.drawLine((WIDTH/2)-155,110,(WIDTH/2)-145,110);
                g2.drawLine((WIDTH/2)+155,110,(WIDTH/2)+145,110);
                g2.drawLine((WIDTH/2)+155,100,(WIDTH/2)+145,100);
                g2.drawLine((WIDTH/2)+155,90,(WIDTH/2)+145,90);
                g2.drawLine((WIDTH/2)+155,80,(WIDTH/2)+145,80);
                g2.drawLine(0,120,WIDTH,120);
                g2.drawLine((WIDTH/2)-155,130,(WIDTH/2)-145,130);
                g2.drawLine((WIDTH/2)-155,140,(WIDTH/2)-145,140);
                g2.drawLine((WIDTH/2)-155,150,(WIDTH/2)-145,150);
                g2.drawLine((WIDTH/2)-155,160,(WIDTH/2)-145,160);
                g2.drawLine((WIDTH/2)+155,160,(WIDTH/2)+145,160);
                g2.drawLine((WIDTH/2)+155,150,(WIDTH/2)+145,150);
                g2.drawLine((WIDTH/2)+155,140,(WIDTH/2)+145,140);
                g2.drawLine((WIDTH/2)+155,130,(WIDTH/2)+145,130);
                g2.drawLine(0,170,WIDTH,170);
                g2.drawLine((WIDTH/2)-155,180,(WIDTH/2)-145,180);
                g2.drawLine((WIDTH/2)-155,190,(WIDTH/2)-145,190);
                g2.drawLine((WIDTH/2)-155,200,(WIDTH/2)-145,200);
                g2.drawLine((WIDTH/2)-155,210,(WIDTH/2)-145,210);
                g2.drawLine((WIDTH/2)+155,210,(WIDTH/2)+145,210);
                g2.drawLine((WIDTH/2)+155,200,(WIDTH/2)+145,200);
                g2.drawLine((WIDTH/2)+155,190,(WIDTH/2)+145,190);
                g2.drawLine((WIDTH/2)+155,180,(WIDTH/2)+145,180);
                g2.drawLine(0,220,WIDTH,220);
                g2.drawLine((WIDTH/2)-155,230,(WIDTH/2)-145,230);
                g2.drawLine((WIDTH/2)-155,240,(WIDTH/2)-145,240);
                g2.drawLine((WIDTH/2)-155,250,(WIDTH/2)-145,250);
                g2.drawLine((WIDTH/2)-155,260,(WIDTH/2)-145,260);
                g2.drawLine((WIDTH/2)+155,260,(WIDTH/2)+145,260);
                g2.drawLine((WIDTH/2)+155,250,(WIDTH/2)+145,250);
                g2.drawLine((WIDTH/2)+155,240,(WIDTH/2)+145,240);
                g2.drawLine((WIDTH/2)+155,230,(WIDTH/2)+145,230);
                g2.drawLine(0,HEIGHT/2,WIDTH,HEIGHT/2);
                g2.drawLine((WIDTH/2)-155,280,(WIDTH/2)-145,280);
                g2.drawLine((WIDTH/2)-155,290,(WIDTH/2)-145,290);
                g2.drawLine((WIDTH/2)-155,300,(WIDTH/2)-145,300);
                g2.drawLine((WIDTH/2)-155,310,(WIDTH/2)-145,310);
                g2.drawLine((WIDTH/2)+155,310,(WIDTH/2)+145,310);
                g2.drawLine((WIDTH/2)+155,300,(WIDTH/2)+145,300);
                g2.drawLine((WIDTH/2)+155,290,(WIDTH/2)+145,290);
                g2.drawLine((WIDTH/2)+155,280,(WIDTH/2)+145,280);
                g2.drawLine(0,320,WIDTH,320);
                g2.drawLine((WIDTH/2)-155,330,(WIDTH/2)-145,330);
                g2.drawLine((WIDTH/2)-155,340,(WIDTH/2)-145,340);
                g2.drawLine((WIDTH/2)-155,350,(WIDTH/2)-145,350);
                g2.drawLine((WIDTH/2)-155,360,(WIDTH/2)-145,360);
                g2.drawLine((WIDTH/2)+155,360,(WIDTH/2)+145,360);
                g2.drawLine((WIDTH/2)+155,350,(WIDTH/2)+145,350);
                g2.drawLine((WIDTH/2)+155,340,(WIDTH/2)+145,340);
                g2.drawLine((WIDTH/2)+155,330,(WIDTH/2)+145,330);
                g2.drawLine(0,370,WIDTH,370);
                g2.drawLine((WIDTH/2)-155,380,(WIDTH/2)-145,380);
                g2.drawLine((WIDTH/2)-155,390,(WIDTH/2)-145,390);
                g2.drawLine((WIDTH/2)-155,400,(WIDTH/2)-145,400);
                g2.drawLine((WIDTH/2)-155,410,(WIDTH/2)-145,410);
                g2.drawLine((WIDTH/2)+155,410,(WIDTH/2)+145,410);
                g2.drawLine((WIDTH/2)+155,400,(WIDTH/2)+145,400);
                g2.drawLine((WIDTH/2)+155,390,(WIDTH/2)+145,390);
                g2.drawLine((WIDTH/2)+155,380,(WIDTH/2)+145,380);
                g2.drawLine(0,420,WIDTH,420);
                g2.drawLine((WIDTH/2)-155,430,(WIDTH/2)-145,430);
                g2.drawLine((WIDTH/2)-155,440,(WIDTH/2)-145,440);
                g2.drawLine((WIDTH/2)-155,450,(WIDTH/2)-145,450);
                g2.drawLine((WIDTH/2)-155,460,(WIDTH/2)-145,460);
                g2.drawLine((WIDTH/2)+155,460,(WIDTH/2)+145,460);
                g2.drawLine((WIDTH/2)+155,450,(WIDTH/2)+145,450);
                g2.drawLine((WIDTH/2)+155,440,(WIDTH/2)+145,440);
                g2.drawLine((WIDTH/2)+155,430,(WIDTH/2)+145,430);
                g2.drawLine(0,470,WIDTH,470);
                g2.drawLine((WIDTH/2)-155,480,(WIDTH/2)-145,480);
                g2.drawLine((WIDTH/2)-155,490,(WIDTH/2)-145,490);
                g2.drawLine((WIDTH/2)-155,500,(WIDTH/2)-145,500);
                g2.drawLine((WIDTH/2)-155,510,(WIDTH/2)-145,510);
                g2.drawLine((WIDTH/2)+155,510,(WIDTH/2)+145,510);
                g2.drawLine((WIDTH/2)+155,500,(WIDTH/2)+145,500);
                g2.drawLine((WIDTH/2)+155,490,(WIDTH/2)+145,490);
                g2.drawLine((WIDTH/2)+155,480,(WIDTH/2)+145,480);
                g2.setFont(new Font("SansSerif", Font.PLAIN, 12));
                g2.drawString(offense.getText(), 0, 495-25);
                g2.drawString(offensivePlay.getText(), 0, 495);
                g2.drawString(defense.getText(), 520, 495-25);
                g2.drawString(defensivePlay.getText(), 520, 495);
                handler.render(g2);
                route.render(g2);
                try
                {
                    ImageIO.write(image, "gif", new File("/"+file));
                }
                catch (Exception ev) {
                ev.printStackTrace();
                }
            }
        });
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

            if(System.currentTimeMillis() - timer > 1000000000) {
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