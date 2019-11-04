import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1100, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private Movement movement;
    private Route route;
    JButton motion, routeline, zone, block;
    JTextArea defensivePlay, offensivePlay;


    public Game() {
        Font font = new Font("Courrier New", Font.PLAIN, 16);
        handler = new Handler();
        JFrame frame = new JFrame("FootballIQ");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setIconImage(new ImageIcon(getClass().getResource("/images/field.png")).getImage());

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == motion) {
                    motion.setSelected(true);
                    routeline.setSelected(false);
                    zone.setSelected(false);
                    block.setSelected(false);
                } else if (e.getSource() == routeline) {
                    motion.setSelected(false);
                    routeline.setSelected(true);
                    zone.setSelected(false);
                    block.setSelected(false);
                } else if (e.getSource() == zone) {
                    motion.setSelected(false);
                    routeline.setSelected(false);
                    zone.setSelected(true);
                    block.setSelected(false);
                } else if (e.getSource() == block) {
                    motion.setSelected(false);
                    routeline.setSelected(false);
                    zone.setSelected(false);
                    block.setSelected(true);
                }
            }
        };

        motion = new JButton("Motion");
        motion.addActionListener(actionListener);
        motion.setBounds(400, 725, 75, 50);
        frame.add(motion);
        routeline = new JButton("Line");
        routeline.addActionListener(actionListener);
        routeline.setBounds(475, 725, 75, 50);
        frame.add(routeline);
        zone = new JButton("Zone");
        zone.addActionListener(actionListener);
        zone.setBounds(550, 725, 75, 50);
        frame.add(zone);
        block = new JButton("Block");
        block.addActionListener(actionListener);
        block.setBounds(625, 725, 75, 50);
        frame.add(block);

        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenuFile = new JMenu("File");
        JMenu jMenuEdit = new JMenu("Edit");
        JMenu jMenuType = new JMenu("Type");

        JMenuItem newMenu = new JMenuItem("New");
        newMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.startGame();
            }
        });
        newMenu.setFont(font);

        JMenuItem saveMenu = new JMenuItem("Save");
        saveMenu.addActionListener(new ActionListener() {
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
                g2.drawString(offensivePlay.getText(), 0, 750);
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
        saveMenu.setFont(font);

        JMenuItem playBook = new JMenuItem("Playbook");
        playBook.setFont(font);
        playBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playBook.setSelected(true);
                handler.playbook();
            }
        });

        JMenuItem animate = new JMenuItem("Animate");
        animate.setFont(font);
        animate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                route.play();
            }
        });
        animate.setFont(font);

        JMenu jMenuSub = new JMenu("Special Teams");
        jMenuSub.setFont(font);
        JMenuItem punt = new JMenuItem("Punt/Punt Return");
        punt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.punt();
            }
        });
        punt.setFont(font);
        JMenuItem FG = new JMenuItem("Field Goal");
        FG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.FG();
            }
        });
        FG.setFont(font);
        JMenuItem KO = new JMenuItem("Kick Off/Kick Off Return");
        KO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.KO();
            }
        });
        KO.setFont(font);
        JMenuItem reload = new JMenuItem("reload");
        reload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                route.reload();
            }
        });
        reload.setFont(font);
        JMenuItem reset = new JMenuItem("reset");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                route.reset();
            }
        });
        reset.setFont(font);
        JMenuItem undo = new JMenuItem("undo");
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                route.undo();
            }
        });
        undo.setFont(font);
        JMenuItem offDef = new JMenuItem("Offense/Defense");
        offDef.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.startGame();
            }
        });
        offDef.setFont(font);

        JMenuItem sixman = new JMenuItem("6 man");
        sixman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.startGame();
            }
        });
        sixman.setFont(font);

        JMenuItem eightman = new JMenuItem("8 man");
        eightman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.startGame();
            }
        });
        eightman.setFont(font);

        JMenuItem elevenman = new JMenuItem("11 man");
        elevenman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.startGame();
            }
        });
        elevenman.setFont(font);



        jMenuBar.add(jMenuFile);
        jMenuBar.add(jMenuEdit);
        jMenuBar.add(jMenuType);
        jMenuFile.add(newMenu);
        jMenuFile.add(saveMenu);
        jMenuFile.add(playBook);
        jMenuFile.add(animate);
        jMenuFile.add(jMenuSub);
        jMenuSub.add(punt);
        jMenuSub.add(FG);
        jMenuSub.add(KO);
        jMenuFile.add(offDef);
        jMenuType.add(sixman);
        jMenuType.add(eightman);
        jMenuType.add(elevenman);
        jMenuEdit.add(undo);
        jMenuEdit.add(reset);
        jMenuEdit.add(reload);
        frame.setJMenuBar(jMenuBar);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {
                    //undo with ctrl + z
                    route.undo();
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    route.play();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        //top left offense play
        JTextArea offensivePlay = new JTextArea("insert play name");
        offensivePlay.setFont(font);
        offensivePlay.setBounds(0, 725, 300, 50);
        frame.add(offensivePlay);
        //top left defensive play
        defensivePlay = new JTextArea("insert play name");
        defensivePlay.setFont(font);
        defensivePlay.setBounds(1100-300, 725, 300, 50);
        frame.add(defensivePlay);


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