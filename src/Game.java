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
    JMenuItem playBook, insideRun;
    JButton motion, routeline, zone, block;
    JTextArea defensivePlay, offensivePlay, header1, header2,
            f1a, f2a, f3a, f4a, f5a, f6a, f7a, f8a, f9a, f10a, f11a,
            f1b, f2b, f3b, f4b, f5b, f6b, f7b, f8b, f9b, f10b, f11b;
    JFrame frame = new JFrame("FootballIQ");
    Font font = new Font("Courrier New", Font.PLAIN, 16);


    public Game() {
        Font font = new Font("Courrier New", Font.PLAIN, 16);
        handler = new Handler();
        frame.setSize(new Dimension(WIDTH,HEIGHT));
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
                playBook.setSelected(false);
                handler.startGame();
                newOffDef();
            }
        });
        newMenu.setFont(font);

        JMenuItem saveMenu = new JMenuItem("Save");
        saveMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!playBook.isSelected() && !insideRun.isSelected()) {
                    JFileChooser chooseDirec = new JFileChooser();
                    chooseDirec.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    chooseDirec.showSaveDialog(frame.getContentPane());
                    File file = chooseDirec.getSelectedFile();
                    file = new File(file + ".gif");
                    BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB_PRE);
                    Graphics2D g2 = (Graphics2D) image.getGraphics();
                    g2.setColor(Color.WHITE);
                    g2.fillRect(0, 0, WIDTH, HEIGHT);
                    g2.setStroke(new BasicStroke(1));
                    g2.setColor(Color.black);
                    g2.drawLine((WIDTH / 2) - 210, 20, (WIDTH / 2) - 200, 20);
                    g2.drawLine((WIDTH / 2) + 210, 20, (WIDTH / 2) + 200, 20);
                    g2.drawLine((WIDTH / 2) - 210, 40, (WIDTH / 2) - 200, 40);
                    g2.drawLine((WIDTH / 2) + 210, 40, (WIDTH / 2) + 200, 40);
                    g2.drawLine((WIDTH / 2) - 210, 60, (WIDTH / 2) - 200, 60);
                    g2.drawLine((WIDTH / 2) + 210, 60, (WIDTH / 2) + 200, 60);
                    g2.drawLine((WIDTH / 2) - 210, 80, (WIDTH / 2) - 200, 80);
                    g2.drawLine((WIDTH / 2) + 210, 80, (WIDTH / 2) + 200, 80);
                    g2.drawString("##", (WIDTH / 2)-450, 95);
                    g2.drawString("##", (WIDTH / 2)+450, 95);
                    g2.drawLine(0, 100, WIDTH, 100);
                    g2.drawString("##", (WIDTH / 2)-450, 115);
                    g2.drawString("##", (WIDTH / 2)+450, 115);
                    g2.drawLine((WIDTH / 2) - 210, 120, (WIDTH / 2) - 200, 120);
                    g2.drawLine((WIDTH / 2) + 210, 120, (WIDTH / 2) + 200, 120);
                    g2.drawLine((WIDTH / 2) - 210, 140, (WIDTH / 2) - 200, 140);
                    g2.drawLine((WIDTH / 2) + 210, 140, (WIDTH / 2) + 200, 140);
                    g2.drawLine((WIDTH / 2) - 210, 160, (WIDTH / 2) - 200, 160);
                    g2.drawLine((WIDTH / 2) + 210, 160, (WIDTH / 2) + 200, 160);
                    g2.drawLine((WIDTH / 2) - 210, 180, (WIDTH / 2) - 200, 180);
                    g2.drawLine((WIDTH / 2) + 210, 180, (WIDTH / 2) + 200, 180);
                    g2.drawString("##", (WIDTH / 2)-450, 195);
                    g2.drawString("##", (WIDTH / 2)+450, 195);
                    g2.drawLine(0, 200, WIDTH, 200);
                    g2.drawString("##", (WIDTH / 2)-450, 215);
                    g2.drawString("##", (WIDTH / 2)+450, 215);
                    g2.drawLine((WIDTH / 2) - 210, 220, (WIDTH / 2) - 200, 220);
                    g2.drawLine((WIDTH / 2) + 210, 220, (WIDTH / 2) + 200, 220);
                    g2.drawLine((WIDTH / 2) - 210, 240, (WIDTH / 2) - 200, 240);
                    g2.drawLine((WIDTH / 2) + 210, 240, (WIDTH / 2) + 200, 240);
                    g2.drawLine((WIDTH / 2) - 210, 260, (WIDTH / 2) - 200, 260);
                    g2.drawLine((WIDTH / 2) + 210, 260, (WIDTH / 2) + 200, 260);
                    g2.drawLine((WIDTH / 2) - 210, 280, (WIDTH / 2) - 200, 280);
                    g2.drawLine((WIDTH / 2) + 210, 280, (WIDTH / 2) + 200, 280);
                    g2.drawString("##", (WIDTH / 2)-450, 295);
                    g2.drawString("##", (WIDTH / 2)+450, 295);
                    g2.drawLine(0, 300, WIDTH, 300);
                    g2.drawString("##", (WIDTH / 2)-450, 315);
                    g2.drawString("##", (WIDTH / 2)+450, 315);
                    g2.drawLine((WIDTH / 2) - 210, 320, (WIDTH / 2) - 200, 320);
                    g2.drawLine((WIDTH / 2) + 210, 320, (WIDTH / 2) + 200, 320);
                    g2.drawLine((WIDTH / 2) - 210, 340, (WIDTH / 2) - 200, 340);
                    g2.drawLine((WIDTH / 2) + 210, 340, (WIDTH / 2) + 200, 340);
                    g2.drawLine((WIDTH / 2) - 210, 360, (WIDTH / 2) - 200, 360);
                    g2.drawLine((WIDTH / 2) + 210, 360, (WIDTH / 2) + 200, 360);
                    g2.drawLine((WIDTH / 2) + 210, 380, (WIDTH / 2) + 200, 380);
                    g2.drawLine((WIDTH / 2) - 210, 380, (WIDTH / 2) - 200, 380);
                    g2.drawString("##", (WIDTH / 2)-450, 400);
                    g2.drawString("##", (WIDTH / 2)+450, 400);
                    g2.drawLine(0, HEIGHT / 2, WIDTH, HEIGHT / 2);
                    g2.drawString("##", (WIDTH / 2)-450, 425);
                    g2.drawString("##", (WIDTH / 2)+450, 425);
                    g2.drawLine((WIDTH / 2) - 210, 420, (WIDTH / 2) - 200, 420);
                    g2.drawLine((WIDTH / 2) + 210, 420, (WIDTH / 2) + 200, 420);
                    g2.drawLine((WIDTH / 2) - 210, 440, (WIDTH / 2) - 200, 440);
                    g2.drawLine((WIDTH / 2) + 210, 440, (WIDTH / 2) + 200, 440);
                    g2.drawLine((WIDTH / 2) - 210, 460, (WIDTH / 2) - 200, 460);
                    g2.drawLine((WIDTH / 2) + 210, 460, (WIDTH / 2) + 200, 460);
                    g2.drawLine((WIDTH / 2) - 210, 480, (WIDTH / 2) - 200, 480);
                    g2.drawLine((WIDTH / 2) + 210, 480, (WIDTH / 2) + 200, 480);
                    g2.drawString("##", (WIDTH / 2)-450, 495);
                    g2.drawString("##", (WIDTH / 2)+450, 495);
                    g2.drawLine(0, 500, WIDTH, 500);
                    g2.drawString("##", (WIDTH / 2)-450, 515);
                    g2.drawString("##", (WIDTH / 2)+450, 515);
                    g2.drawLine((WIDTH / 2) - 210, 520, (WIDTH / 2) - 200, 520);
                    g2.drawLine((WIDTH / 2) + 210, 520, (WIDTH / 2) + 200, 520);
                    g2.drawLine((WIDTH / 2) - 210, 540, (WIDTH / 2) - 200, 540);
                    g2.drawLine((WIDTH / 2) + 210, 540, (WIDTH / 2) + 200, 540);
                    g2.drawLine((WIDTH / 2) - 210, 560, (WIDTH / 2) - 200, 560);
                    g2.drawLine((WIDTH / 2) + 210, 560, (WIDTH / 2) + 200, 560);
                    g2.drawLine((WIDTH / 2) - 210, 580, (WIDTH / 2) - 200, 580);
                    g2.drawLine((WIDTH / 2) + 210, 580, (WIDTH / 2) + 200, 580);
                    g2.drawString("##", (WIDTH / 2)-450, 595);
                    g2.drawString("##", (WIDTH / 2)+450, 595);
                    g2.drawLine(0, 600, WIDTH, 600);
                    g2.drawString("##", (WIDTH / 2)-450, 615);
                    g2.drawString("##", (WIDTH / 2)+450, 615);
                    g2.drawLine((WIDTH / 2) - 210, 620, (WIDTH / 2) - 200, 620);
                    g2.drawLine((WIDTH / 2) + 210, 620, (WIDTH / 2) + 200, 620);
                    g2.drawLine((WIDTH / 2) - 210, 640, (WIDTH / 2) - 200, 640);
                    g2.drawLine((WIDTH / 2) + 210, 640, (WIDTH / 2) + 200, 640);
                    g2.drawLine((WIDTH / 2) - 210, 660, (WIDTH / 2) - 200, 660);
                    g2.drawLine((WIDTH / 2) + 210, 660, (WIDTH / 2) + 200, 660);
                    g2.drawLine((WIDTH / 2) - 210, 680, (WIDTH / 2) - 200, 680);
                    g2.drawLine((WIDTH / 2) + 210, 680, (WIDTH / 2) + 200, 680);
                    g2.drawString("##", (WIDTH / 2)-450, 695);
                    g2.drawString("##", (WIDTH / 2)+450, 695);
                    g2.drawLine(0, 700, WIDTH, 700);

                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    handler.render(g2);
                    route.render(g2);
                    try {
                        ImageIO.write(image, "gif", new File("/" + file));
                    } catch (Exception ev) {
                        ev.printStackTrace();
                    }
                } else if (playBook.isSelected()) {
                    JFileChooser chooseDirec = new JFileChooser();
                    chooseDirec.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    chooseDirec.showSaveDialog(frame.getContentPane());
                    File file = chooseDirec.getSelectedFile();
                    file = new File(file + ".gif");
                    BufferedImage image = new BufferedImage(HEIGHT, WIDTH-150, BufferedImage.TYPE_INT_ARGB_PRE);
                    Graphics2D g2 = (Graphics2D) image.getGraphics();
                    g2.setColor(Color.WHITE);
                    g2.fillRect(0, 0, HEIGHT, WIDTH-150);
                    g2.setStroke(new BasicStroke(1));
                    g2.setStroke(new BasicStroke(1));
                    g2.setColor(Color.black);
                    g2.drawLine(0, 370, HEIGHT, 370);
                    g2.drawString("##", 65, 350);
                    g2.drawString("##", 745, 350);
                    g2.drawLine(265, 350, 275, 350);
                    g2.drawLine(535, 350, 545, 350);
                    g2.drawLine(265, 330, 275, 330);
                    g2.drawLine(535, 330, 545, 330);
                    g2.drawLine(265, 310, 275, 310);
                    g2.drawLine(535, 310, 545, 310);
                    g2.drawLine(265, 290, 275, 290);
                    g2.drawLine(535, 290, 545, 290);
                    g2.drawString("##", 65, 290);
                    g2.drawString("##", 745, 290);
                    g2.drawLine(0, 270, HEIGHT, 270);
                    g2.drawString("##", 65, 250);
                    g2.drawString("##", 745, 250);
                    g2.drawLine(265, 250, 275, 250);
                    g2.drawLine(535, 250, 545, 250);
                    g2.drawLine(265, 230, 275, 230);
                    g2.drawLine(535, 230, 545, 230);
                    g2.drawLine(265, 210, 275, 210);
                    g2.drawLine(535, 210, 545, 210);
                    g2.drawLine(265, 190, 275, 190);
                    g2.drawLine(535, 190, 545, 190);
                    g2.drawString("##", 65, 190);
                    g2.drawString("##", 745, 190);
                    g2.drawLine(0, 170, HEIGHT, 170);
                    g2.drawString("##", 65, 150);
                    g2.drawString("##", 745, 150);
                    g2.drawLine(265, 150, 275, 150);
                    g2.drawLine(535, 150, 545, 150);
                    g2.drawLine(265, 130, 275, 130);
                    g2.drawLine(535, 130, 545, 130);
                    g2.drawLine(265, 110, 275, 110);
                    g2.drawLine(535, 110, 545, 110);
                    g2.drawLine(265, 90, 275, 90);
                    g2.drawLine(535, 90, 545, 90);
                    g2.drawString("##", 65, 90);
                    g2.drawString("##", 745, 90);
                    g2.drawLine(0, 70, HEIGHT, 70);
                    g2.drawString("##", 65, 50);
                    g2.drawString("##", 745, 50);
                    g2.drawLine(265, 50, 275, 50);
                    g2.drawLine(535, 50, 545, 50);
                    g2.drawLine(265, 30, 275, 30);
                    g2.drawLine(535, 30, 545, 30);
                    g2.drawLine(265, 10, 275, 10);
                    g2.drawLine(535, 10, 545, 10);
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setFont(font);
                    g2.drawString(offensivePlay.getText(), offensivePlay.getX(), offensivePlay.getY());
                    g2.drawString(defensivePlay.getText(), defensivePlay.getX(), defensivePlay.getY());
                    g2.drawString(header1.getText(), header1.getX(), header1.getY());
                    g2.drawString(header2.getText(), header2.getX(), header2.getY());
                    g2.drawString(f1a.getText(), f1a.getX(), f1a.getY());
                    g2.drawString(f2a.getText(), f2a.getX(), f2a.getY());
                    g2.drawString(f3a.getText(), f3a.getX(), f3a.getY());
                    g2.drawString(f4a.getText(), f4a.getX(), f4a.getY());
                    g2.drawString(f5a.getText(), f5a.getX(), f5a.getY());
                    g2.drawString(f6a.getText(), f6a.getX(), f6a.getY());
                    g2.drawString(f7a.getText(), f7a.getX(), f7a.getY());
                    g2.drawString(f8a.getText(), f8a.getX(), f8a.getY());
                    g2.drawString(f9a.getText(), f9a.getX(), f9a.getY());
                    g2.drawString(f10a.getText(), f10a.getX(), f10a.getY());
                    g2.drawString(f11a.getText(), f11a.getX(), f11a.getY());
                    g2.drawString(f1b.getText(), f1b.getX(), f1b.getY());
                    g2.drawString(f2b.getText(), f2b.getX(), f2b.getY());
                    g2.drawString(f3b.getText(), f3b.getX(), f3b.getY());
                    g2.drawString(f4b.getText(), f4b.getX(), f4b.getY());
                    g2.drawString(f5b.getText(), f5b.getX(), f5b.getY());
                    g2.drawString(f6b.getText(), f6b.getX(), f6b.getY());
                    g2.drawString(f7b.getText(), f7b.getX(), f7b.getY());
                    g2.drawString(f8b.getText(), f8b.getX(), f8b.getY());
                    g2.drawString(f9b.getText(), f9b.getX(), f9b.getY());
                    g2.drawString(f10b.getText(), f10b.getX(), f10b.getY());
                    g2.drawString(f11b.getText(), f11b.getX(), f11b.getY());
                    handler.render(g2);
                    route.render(g2);
                    try {
                        ImageIO.write(image, "gif", new File("/" + file));
                    } catch (Exception ev) {
                        ev.printStackTrace();
                    }
                } else if (insideRun.isSelected()) {
                    JFileChooser chooseDirec = new JFileChooser();
                    chooseDirec.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    chooseDirec.showSaveDialog(frame.getContentPane());
                    File file = chooseDirec.getSelectedFile();
                    file = new File(file + ".gif");
                    BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB_PRE);
                    Graphics2D g2 = (Graphics2D) image.getGraphics();
                    g2.setColor(Color.WHITE);
                    g2.fillRect(0, 0, WIDTH, HEIGHT);
                    g2.setStroke(new BasicStroke(1));
                    g2.setColor(Color.black);
                    g2.drawLine((WIDTH / 2) - 310, 40, (WIDTH / 2) - 300, 40);
                    g2.drawLine((WIDTH / 2) + 310, 40, (WIDTH / 2) + 300, 40);
                    g2.drawLine((WIDTH / 2) - 310, 80, (WIDTH / 2) - 300, 80);
                    g2.drawLine((WIDTH / 2) + 310, 80, (WIDTH / 2) + 300, 80);
                    g2.drawLine((WIDTH / 2) - 310, 120, (WIDTH / 2) - 300, 120);
                    g2.drawLine((WIDTH / 2) + 310, 120, (WIDTH / 2) + 300, 120);
                    g2.drawLine((WIDTH / 2) - 310, 160, (WIDTH / 2) - 300, 160);
                    g2.drawLine((WIDTH / 2) + 310, 160, (WIDTH / 2) + 300, 160);
                    g2.drawLine(0, 200, WIDTH, 200);
                    g2.drawLine((WIDTH / 2) - 310, 240, (WIDTH / 2) - 300, 240);
                    g2.drawLine((WIDTH / 2) + 310, 240, (WIDTH / 2) + 300, 240);
                    g2.drawLine((WIDTH / 2) - 310, 280, (WIDTH / 2) - 300, 280);
                    g2.drawLine((WIDTH / 2) + 310, 280, (WIDTH / 2) + 300, 280);
                    g2.drawLine((WIDTH / 2) - 310, 320, (WIDTH / 2) - 300, 320);
                    g2.drawLine((WIDTH / 2) + 310, 320, (WIDTH / 2) + 300, 320);
                    g2.drawLine((WIDTH / 2) - 310, 360, (WIDTH / 2) - 300, 360);
                    g2.drawLine((WIDTH / 2) + 310, 360, (WIDTH / 2) + 300, 360);
                    g2.drawLine(0, HEIGHT / 2, WIDTH, HEIGHT / 2);
                    g2.drawLine((WIDTH / 2) - 310, 440, (WIDTH / 2) - 300, 440);
                    g2.drawLine((WIDTH / 2) + 310, 440, (WIDTH / 2) + 300, 440);
                    g2.drawLine((WIDTH / 2) - 310, 480, (WIDTH / 2) - 300, 480);
                    g2.drawLine((WIDTH / 2) + 310, 480, (WIDTH / 2) + 300, 480);
                    g2.drawLine((WIDTH / 2) - 310, 520, (WIDTH / 2) - 300, 520);
                    g2.drawLine((WIDTH / 2) + 310, 520, (WIDTH / 2) + 300, 520);
                    g2.drawLine((WIDTH / 2) - 310, 560, (WIDTH / 2) - 300, 560);
                    g2.drawLine((WIDTH / 2) + 310, 560, (WIDTH / 2) + 300, 560);
                    g2.drawLine(0, 600, WIDTH, 600);
                    g2.drawLine((WIDTH / 2) - 310, 640, (WIDTH / 2) - 300, 640);
                    g2.drawLine((WIDTH / 2) + 310, 640, (WIDTH / 2) + 300, 640);
                    g2.drawLine((WIDTH / 2) - 310, 680, (WIDTH / 2) - 300, 680);
                    g2.drawLine((WIDTH / 2) + 310, 680, (WIDTH / 2) + 300, 680);
                    g2.drawLine((WIDTH / 2) - 310, 720, (WIDTH / 2) - 300, 720);
                    g2.drawLine((WIDTH / 2) + 310, 720, (WIDTH / 2) + 300, 720);
                    g2.drawString(offensivePlay.getText(), offensivePlay.getX(), offensivePlay.getY());
                    g2.drawString(defensivePlay.getText(), defensivePlay.getX(), defensivePlay.getY());
                    handler.render(g2);
                    route.render(g2);
                    try {
                        ImageIO.write(image, "gif", new File("/" + file));
                    } catch (Exception ev) {
                        ev.printStackTrace();
                    }
                }
            }
        });
        saveMenu.setFont(font);

        playBook = new JMenuItem("Playbook");
        playBook.setFont(font);
        playBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playBook.setSelected(true);
                insideRun.setSelected(false);
                handler.playbook();
                playbookTemplate();
            }
        });
        insideRun = new JMenuItem("Inside Run");
        insideRun.setFont(font);
        insideRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playBook.setSelected(false);
                insideRun.setSelected(true);
                handler.nineOnSeven();
                route.insideRun();
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
                    if (!playBook.isSelected()) {
                        handler.punt();
                    }
                }
            });
            punt.setFont(font);
            JMenuItem FG = new JMenuItem("Field Goal");
            FG.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!playBook.isSelected()) {
                        handler.FG();
                    }
                }
            });
            FG.setFont(font);
            JMenuItem KO = new JMenuItem("Kick Off/Kick Off Return");
            KO.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!playBook.isSelected()) {
                        handler.KO();
                    }
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
                if (playBook.isSelected()) {
                    handler.playbook();
                }
            }

        });
        offDef.setFont(font);

        JMenuItem sixman = new JMenuItem("6 man");
        sixman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.sixMan();
            }
        });
        sixman.setFont(font);

        JMenuItem eightman = new JMenuItem("8 man");
        eightman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.eightMan();
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

        JMenuItem twelveMan = new JMenuItem("12 man");
        twelveMan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.twelveMan();
            }
        });
        twelveMan.setFont(font);

        jMenuBar.add(jMenuFile);
        jMenuBar.add(jMenuEdit);
        jMenuBar.add(jMenuType);
        jMenuFile.add(newMenu);
        jMenuFile.add(saveMenu);
        jMenuFile.add(playBook);
        jMenuFile.add(insideRun);
        jMenuFile.add(animate);
        jMenuFile.add(jMenuSub);
        jMenuSub.add(punt);
        jMenuSub.add(FG);
        jMenuSub.add(KO);
        jMenuFile.add(offDef);
        jMenuType.add(sixman);
        jMenuType.add(eightman);
        jMenuType.add(elevenman);
        jMenuType.add(twelveMan);
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
        offensivePlay = new JTextArea("insert play name");
        offensivePlay.setFont(font);
        offensivePlay.setBounds(0, 725, 300, 50);
        frame.add(offensivePlay);
        //top left defensive play
        defensivePlay = new JTextArea("insert play name");
        defensivePlay.setFont(font);
        defensivePlay.setBounds(1100-300, 725, 300, 50);
        frame.add(defensivePlay);

        //playbook
        header1 = new JTextArea("Position");
        header1.setFont(font);
        header1.setBounds(0,430, 75,40);
        header1.setVisible(false);
        header1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(header1);
        header2 = new JTextArea("Description");
        header2.setFont(font);
        header2.setBounds(75,430, HEIGHT-75,40);
        header2.setVisible(false);
        header2.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(header2);
        f1a = new JTextArea();
        f1a.setFont(font);
        f1a.setBounds(0,470, 75,40);
        f1a.setVisible(false);
        f1a.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(f1a);
        f2a = new JTextArea();
        f2a.setFont(font);
        f2a.setBounds(0,510, 75,40);
        f2a.setVisible(false);
        f2a.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(f2a);
        f3a = new JTextArea();
        f3a.setFont(font);
        f3a.setBounds(0,550, 75,40);
        f3a.setVisible(false);
        f3a.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(f3a);
        f4a = new JTextArea();
        f4a.setFont(font);
        f4a.setBounds(0,590, 75,40);
        f4a.setVisible(false);
        f4a.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(f4a);
        f5a = new JTextArea();
        f5a.setFont(font);
        f5a.setBounds(0,630, 75,40);
        f5a.setVisible(false);
        f5a.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(f5a);
        f6a = new JTextArea();
        f6a.setFont(font);
        f6a.setBounds(0,670, 75,40);
        f6a.setVisible(false);
        f6a.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(f6a);
        f7a = new JTextArea();
        f7a.setFont(font);
        f7a.setBounds(0,710, 75,40);
        f7a.setVisible(false);
        f7a.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(f7a);
        f8a = new JTextArea();
        f8a.setFont(font);
        f8a.setBounds(0, 750, 75,40);
        f8a.setVisible(false);
        f8a.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(f8a);
        f9a = new JTextArea();
        f9a.setFont(font);
        f9a.setBounds(0,790, 75,40);
        f9a.setVisible(false);
        f9a.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(f9a);
        f10a = new JTextArea();
        f10a.setFont(font);
        f10a.setBounds(0,830, 75,40);
        f10a.setVisible(false);
        f10a.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(f10a);
        f11a = new JTextArea();
        f11a.setFont(font);
        f11a.setBounds(0,870, 75,40);
        f11a.setVisible(false);
        f11a.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(f11a);
        f1b = new JTextArea();
        f1b.setFont(font);
        f1b.setBounds(75,470, HEIGHT-75,40);
        f1b.setVisible(false);
        f1b.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(f1b);
        f2b = new JTextArea();
        f2b.setFont(font);
        f2b.setBounds(75,510, HEIGHT-75,40);
        f2b.setVisible(false);
        f2b.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(f2b);
        f3b = new JTextArea();
        f3b.setFont(font);
        f3b.setBounds(75,550, HEIGHT-75,40);
        f3b.setVisible(false);
        f3b.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(f3b);
        f4b = new JTextArea();
        f4b.setFont(font);
        f4b.setBounds(75,590, HEIGHT-75,40);
        f4b.setVisible(false);
        f4b.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(f4b);
        f5b = new JTextArea();
        f5b.setFont(font);
        f5b.setBounds(75,630, HEIGHT-75,40);
        f5b.setVisible(false);
        f5b.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(f5b);
        f6b = new JTextArea();
        f6b.setFont(font);
        f6b.setBounds(75,670, HEIGHT-75,40);
        f6b.setVisible(false);
        f6b.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(f6b);
        f7b = new JTextArea();
        f7b.setFont(font);
        f7b.setBounds(75,710, HEIGHT-75,40);
        f7b.setVisible(false);
        f7b.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(f7b);
        f8b = new JTextArea();
        f8b.setFont(font);
        f8b.setBounds(75,750, HEIGHT-75,40);
        f8b.setVisible(false);
        f8b.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(f8b);
        f9b = new JTextArea();
        f9b.setFont(font);
        f9b.setBounds(75,790, HEIGHT-75,40);
        f9b.setVisible(false);
        f9b.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(f9b);
        f10b = new JTextArea();
        f10b.setFont(font);
        f10b.setBounds(75,830, HEIGHT-75,40);
        f10b.setVisible(false);
        f10b.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(f10b);
        f11b = new JTextArea();
        f11b.setFont(font);
        f11b.setBounds(75,870, HEIGHT-75,40);
        f11b.setVisible(false);
        f11b.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        frame.add(f11b);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        Image imgInsideRun;
        imgInsideRun = Toolkit.getDefaultToolkit().getImage((getClass().getResource("/images/insideRun.png")));
        img = Toolkit.getDefaultToolkit().getImage((getClass().getResource("/images/field.png")));
        g.drawImage(img,0, 0, WIDTH, HEIGHT, this);
        if (playBook.isSelected()) {
            g.drawImage(img,0, 0, HEIGHT, WIDTH, this);
        } else if (insideRun.isSelected()) {
            g.drawImage(imgInsideRun,0, 0, WIDTH, HEIGHT, this);
        } else {
            img = Toolkit.getDefaultToolkit().getImage((getClass().getResource("/images/field.png")));
            g.drawImage(img,0, 0, WIDTH, HEIGHT, this);
        }
        route.render(g);
        handler.render(g);
        g.dispose();
        bs.show();
    }

    public static int clamp(int var, int min, int max) {
        if (var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;

    }

    public void playbookTemplate() {
        frame.setSize(new Dimension(HEIGHT,WIDTH));
        frame.setResizable(false);
        header1.setVisible(true);
        header2.setVisible(true);
        f1b.setVisible(true);
        f2b.setVisible(true);
        f3b.setVisible(true);
        f4b.setVisible(true);
        f5b.setVisible(true);
        f6b.setVisible(true);
        f7b.setVisible(true);
        f8b.setVisible(true);
        f9b.setVisible(true);
        f10b.setVisible(true);
        f11b.setVisible(true);
        f1a.setVisible(true);
        f2a.setVisible(true);
        f3a.setVisible(true);
        f4a.setVisible(true);
        f5a.setVisible(true);
        f6a.setVisible(true);
        f7a.setVisible(true);
        f8a.setVisible(true);
        f9a.setVisible(true);
        f10a.setVisible(true);
        f11a.setVisible(true);
        //resize the other buttons
        offensivePlay.setBounds(0,910,275, 75 );
        defensivePlay.setBounds(HEIGHT-275,910,275, 75 );
        motion.setBounds(275, 910, 68, 75);
        routeline.setBounds(343, 910, 68, 75);
        zone.setBounds(411, 910, 67, 75);
        block.setBounds(478, 910, 67, 75);

    }

    public void newOffDef() {
        frame.setSize(new Dimension(WIDTH,HEIGHT));
        frame.setResizable(false);
        header1.setVisible(false);
        header2.setVisible(false);
        f1b.setVisible(false);
        f2b.setVisible(false);
        f3b.setVisible(false);
        f4b.setVisible(false);
        f5b.setVisible(false);
        f6b.setVisible(false);
        f7b.setVisible(false);
        f8b.setVisible(false);
        f9b.setVisible(false);
        f10b.setVisible(false);
        f11b.setVisible(false);
        f1a.setVisible(false);
        f2a.setVisible(false);
        f3a.setVisible(false);
        f4a.setVisible(false);
        f5a.setVisible(false);
        f6a.setVisible(false);
        f7a.setVisible(false);
        f8a.setVisible(false);
        f9a.setVisible(false);
        f10a.setVisible(false);
        f11a.setVisible(false);
        motion.setBounds(400, 725, 75, 50);
        routeline.setBounds(475, 725, 75, 50);
        zone.setBounds(550, 725, 75, 50);
        block.setBounds(625, 725, 75, 50);
        offensivePlay.setBounds(0, 725, 300, 50);
        defensivePlay.setBounds(1100-300, 725, 300, 50);

    }

    public static void main(String[] args) {
        new Game();
    }
}