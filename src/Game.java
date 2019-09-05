import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.Buffer;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1100, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private Movement movement;
    private Route route;


    public Game() {
        Font font = new Font("Courrier New", Font.PLAIN, 16);
        handler = new Handler();
        JFrame frame = new JFrame("FootballIQ");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setIconImage(new ImageIcon(getClass().getResource("/images/field.png")).getImage());
        //offense
        JTextArea offense = new JTextArea("offense");
        offense.setFont(font);
        offense.setBounds(0, 750-50, 300, 50);
        frame.add(offense);
        //top left offense play
        JTextArea offensivePlay = new JTextArea("insert play name");
        offensivePlay.setFont(font);
        offensivePlay.setBounds(0, 750, 300, 50);
        frame.add(offensivePlay);
        //top right defense
        JTextArea defense = new JTextArea("defense");
        defense.setFont(font);
        defense.setBounds(1100-300, 750-50, 300, 50);
        frame.add(defense);
        //top left defensive play
        JTextArea defensivePlay = new JTextArea("insert play name");
        defensivePlay.setFont(font);
        defensivePlay.setBounds(1100-300, 750, 300, 50);
        frame.add(defensivePlay);

        //buttons
        Button undo = new Button("undo");
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                route.undo();
            }
        });
        undo.setBounds(350,700,80,100);
        undo.setFont(font);
        frame.add(undo);
        Button play = new Button("play");
        play.setBounds(430,700,80,100);
        play.setFont(font);
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
                g2.drawLine((WIDTH/2)-210,20,(WIDTH/2)-200,20);
                g2.drawLine((WIDTH/2)+210,20,(WIDTH/2)+200,20);
                g2.drawLine((WIDTH/2)-210,40,(WIDTH/2)-200,40);
                g2.drawLine((WIDTH/2)+210,40,(WIDTH/2)+200,40);
                g2.drawLine((WIDTH/2)-210,60,(WIDTH/2)-200,60);
                g2.drawLine((WIDTH/2)+210,60,(WIDTH/2)+200,60);
                g2.drawLine((WIDTH/2)-210,80,(WIDTH/2)-200,80);
                g2.drawLine((WIDTH/2)+210,80,(WIDTH/2)+200,80);
                g2.drawLine(0,100,WIDTH,100);
                g2.drawLine((WIDTH/2)-210,120,(WIDTH/2)-200,120);
                g2.drawLine((WIDTH/2)+210,120,(WIDTH/2)+200,120);
                g2.drawLine((WIDTH/2)-210,140,(WIDTH/2)-200,140);
                g2.drawLine((WIDTH/2)+210,140,(WIDTH/2)+200,140);
                g2.drawLine((WIDTH/2)-210,160,(WIDTH/2)-200,160);
                g2.drawLine((WIDTH/2)+210,160,(WIDTH/2)+200,160);
                g2.drawLine((WIDTH/2)-210,180,(WIDTH/2)-200,180);
                g2.drawLine((WIDTH/2)+210,180,(WIDTH/2)+200,180);
                g2.drawLine(0,200,WIDTH,200);
                g2.drawLine((WIDTH/2)-210,220,(WIDTH/2)-200,220);
                g2.drawLine((WIDTH/2)+210,220,(WIDTH/2)+200,220);
                g2.drawLine((WIDTH/2)-210,240,(WIDTH/2)-200,240);
                g2.drawLine((WIDTH/2)+210,240,(WIDTH/2)+200,240);
                g2.drawLine((WIDTH/2)-210,260,(WIDTH/2)-200,260);
                g2.drawLine((WIDTH/2)+210,260,(WIDTH/2)+200,260);
                g2.drawLine((WIDTH/2)-210,280,(WIDTH/2)-200,280);
                g2.drawLine((WIDTH/2)+210,280,(WIDTH/2)+200,280);
                g2.drawLine(0,300,WIDTH,300);
                g2.drawLine((WIDTH/2)-210,320,(WIDTH/2)-200,320);
                g2.drawLine((WIDTH/2)+210,320,(WIDTH/2)+200,320);
                g2.drawLine((WIDTH/2)-210,340,(WIDTH/2)-200,340);
                g2.drawLine((WIDTH/2)+210,340,(WIDTH/2)+200,340);
                g2.drawLine((WIDTH/2)-210,360,(WIDTH/2)-200,360);
                g2.drawLine((WIDTH/2)+210,360,(WIDTH/2)+200,360);
                g2.drawLine((WIDTH/2)+210,380,(WIDTH/2)+200,380);
                g2.drawLine((WIDTH/2)-210,380,(WIDTH/2)-200,380);
                g2.drawLine(0,HEIGHT/2,WIDTH,HEIGHT/2);
                g2.drawLine((WIDTH/2)-210,420,(WIDTH/2)-200,420);
                g2.drawLine((WIDTH/2)+210,420,(WIDTH/2)+200,420);
                g2.drawLine((WIDTH/2)-210,440,(WIDTH/2)-200,440);
                g2.drawLine((WIDTH/2)+210,440,(WIDTH/2)+200,440);
                g2.drawLine((WIDTH/2)-210,460,(WIDTH/2)-200,460);
                g2.drawLine((WIDTH/2)+210,460,(WIDTH/2)+200,460);
                g2.drawLine((WIDTH/2)-210,480,(WIDTH/2)-200,480);
                g2.drawLine((WIDTH/2)+210,480,(WIDTH/2)+200,480);
                g2.drawLine(0,500,WIDTH,500);
                g2.drawLine((WIDTH/2)-210,520,(WIDTH/2)-200,520);
                g2.drawLine((WIDTH/2)+210,520,(WIDTH/2)+200,520);
                g2.drawLine((WIDTH/2)-210,540,(WIDTH/2)-200,540);
                g2.drawLine((WIDTH/2)+210,540,(WIDTH/2)+200,540);
                g2.drawLine((WIDTH/2)-210,560,(WIDTH/2)-200,560);
                g2.drawLine((WIDTH/2)+210,560,(WIDTH/2)+200,560);
                g2.drawLine((WIDTH/2)-210,580,(WIDTH/2)-200,580);
                g2.drawLine((WIDTH/2)+210,580,(WIDTH/2)+200,580);
                g2.drawLine(0,600,WIDTH,600);
                g2.drawLine((WIDTH/2)-210,620,(WIDTH/2)-200,620);
                g2.drawLine((WIDTH/2)+210,620,(WIDTH/2)+200,620);
                g2.drawLine((WIDTH/2)-210,640,(WIDTH/2)-200,640);
                g2.drawLine((WIDTH/2)+210,640,(WIDTH/2)+200,640);
                g2.drawLine((WIDTH/2)-210,660,(WIDTH/2)-200,660);
                g2.drawLine((WIDTH/2)+210,660,(WIDTH/2)+200,660);
                g2.drawLine((WIDTH/2)-210,680,(WIDTH/2)-200,680);
                g2.drawLine((WIDTH/2)+210,680,(WIDTH/2)+200,680);

                g2.setFont(font);
                g2.drawString(offense.getText(), 0, 700);
                g2.drawString(offensivePlay.getText(), 0, 750);
                g2.drawString(defense.getText(), 800, 700);
                g2.drawString(defensivePlay.getText(), 800, 750);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
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
        save.setFont(font);
        save.setBounds(510,700,80,100);
        frame.add(save);
        Button reset = new Button("reset");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                route.reset();
            }
        });
        reset.setBounds(590,700,80,100);
        reset.setFont(font);
        frame.add(reset);
        Button reload = new Button("reload");
        reload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                route.reload();
            }
        });
        reload.setFont(font);
        reload.setBounds(670,700,80,100);
        frame.add(reload);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
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
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000000000) {
                timer += 100000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
            try {
                Thread.sleep((now - System.nanoTime() + 1000000000 /60) / 1000000);
            } catch (Exception ev) {
                System.out.print("it's fine trust me, just need to put the thread to sleep or computers get hot");
            }
        }
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
        img = Toolkit.getDefaultToolkit().getImage((getClass().getResource("/images/field.png")));
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