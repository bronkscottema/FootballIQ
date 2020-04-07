import java.awt.*;
import java.util.ArrayList;

public class Player extends GameObject {

    Handler handler;
    ArrayList<String> playerChangeString = new ArrayList<String>();
    ArrayList<ID> playerChangeId = new ArrayList<ID>();

    public Player(int x, int y, ID id) {

        super(x, y, id);
    }

    ArrayList<ID> offense = new ArrayList<ID>();

    {
        offense.add(ID.TE);
        offense.add(ID.LT);
        offense.add(ID.LG);
        offense.add(ID.C);
        offense.add(ID.RG);
        offense.add(ID.RT);
        offense.add(ID.QB);
        offense.add(ID.RB);
        offense.add(ID.FB);
        offense.add(ID.WRH);
        offense.add(ID.WRX);
        offense.add(ID.WRZ);
        offense.add(ID.A);
        offense.add(ID.B);
        offense.add(ID.U);
        offense.add(ID.F);
        offense.add(ID.T);
        offense.add(ID.W);
        offense.add(ID.O);
        offense.add(ID.O1);
        offense.add(ID.O2);
        offense.add(ID.O3);
        offense.add(ID.O4);
        offense.add(ID.O5);
        offense.add(ID.O6);
    }

    ArrayList<ID> defense;

    {
        defense = new ArrayList<ID>();
        defense.add(ID.SDE);
        defense.add(ID.SDT);
        defense.add(ID.WDT);
        defense.add(ID.WDE);
        defense.add(ID.SOSLB);
        defense.add(ID.SISLB);
        defense.add(ID.WOSLB);
        defense.add(ID.WISLB);
        defense.add(ID.FS);
        defense.add(ID.SS);
        defense.add(ID.WCB);
        defense.add(ID.SCB);
        defense.add(ID.D);
        defense.add(ID.D1);
        defense.add(ID.D2);
        defense.add(ID.D3);
        defense.add(ID.D4);
        defense.add(ID.D5);
        defense.add(ID.D6);
    }
    ArrayList<ID> kickOff;

    {
        kickOff = new ArrayList<ID>();
        kickOff.add(ID.K);
        kickOff.add(ID.P);
        kickOff.add(ID.L1);
        kickOff.add(ID.L2);
        kickOff.add(ID.L3);
        kickOff.add(ID.L4);
        kickOff.add(ID.L5);
        kickOff.add(ID.R5);
        kickOff.add(ID.R4);
        kickOff.add(ID.R3);
        kickOff.add(ID.R2);
        kickOff.add(ID.R1);

    }

    public void changePlayer(String userInput, ID id) {
        if (userInput != null) {
            playerChangeString.add(userInput);
            playerChangeId.add(id);
        }
    }

    public void tick() {
        //route methods go here
        x += velX;
        y += velY;

        //stop stuff from going past the Game screen
        x = Game.clamp(x, 0, Game.WIDTH - 16);
        y = Game.clamp(y, 0, Game.HEIGHT - 37);

    }

    public void render(Graphics g) {
        if (!isInsideRun) {
            Font font = new Font("Courrier New", Font.PLAIN, 16);
            Font fontOL = new Font("Courrier New", Font.PLAIN, 14);
            Graphics2D g2 = (Graphics2D) g;
            if (offense.contains(getID()) || kickOff.contains(getID())) {
                g2.drawOval(x, y, 24, 24);
                g2.setStroke(new BasicStroke(3));
                g2.setColor(Color.black);
                if (getID() == ID.A) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("A", x + 7, y + 15);
                }
                if (getID() == ID.B) {
                    g.setColor(Color.black);
                    g2.setFont(font);
                    g.drawString("B", x + 7, y + 17);
                }
                if (getID() == ID.F) {
                    g.setColor(Color.black);
                    g2.setFont(font);
                    g.drawString("F", x + 7, y + 17);
                }
                if (getID() == ID.WRH) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("H", x + 6, y + 17);
                }
                if (getID() == ID.QB) {
                    g.setColor(Color.black);
                    g2.setFont(font);
                    g.drawString("Q", x + 6, y + 17);
                }
                if (getID() == ID.RB) {
                    g.setColor(Color.black);
                    g2.setFont(font);
                    g.drawString("F", x + 7, y + 17);
                }
                if (getID() == ID.T) {
                    g.setColor(Color.black);
                    g2.setFont(font);
                    g.drawString("T", x + 7, y + 17);
                }
                if (getID() == ID.TE) {
                    g.setColor(Color.black);
                    g2.setFont(font);
                    g.drawString("Y", x + 7, y + 17);
                }
                if (getID() == ID.U) {
                    g.setColor(Color.black);
                    g2.setFont(font);
                    g.drawString("U", x + 7, y + 17);
                }
                if (getID() == ID.WRX) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("X", x + 7, y + 17);
                }
                if (getID() == ID.W) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("W", x + 7, y + 17);
                }
                if (getID() == ID.WRZ) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("Z", x + 7, y + 17);
                }
                if (getID() == ID.LT) {
                    g2.setColor(Color.black);
                    g2.setFont(fontOL);
                    g2.drawString("LT", x + 3, y + 17);
                }
                if (getID() == ID.LG) {
                    g2.setColor(Color.black);
                    g2.setFont(fontOL);
                    g2.drawString("LG", x + 3, y + 17);
                }
                if (getID() == ID.C) {
                    g2.setColor(Color.black);
                    g2.setFont(fontOL);
                    g2.drawString("C", x + 7, y + 17);
                }
                if (getID() == ID.RG) {
                    g2.setColor(Color.black);
                    g2.setFont(fontOL);
                    g2.drawString("RG", x +3, y + 17);
                }
                if (getID() == ID.RT) {
                    g2.setColor(Color.black);
                    g2.setFont(fontOL);
                    g2.drawString("RT", x + 3, y + 17);
                }
            } else if (defense.contains(getID())) {
                g.setColor(Color.black);
                g.fillRect(x, y, 24, 24);
                g.setColor(Color.white);
                g.fillRect(x + 2, y + 2, 20, 20);

            }
        } else {
            Font font = new Font("Courrier New", Font.PLAIN, 24);
            Graphics2D g2 = (Graphics2D) g;
            if (offense.contains(getID()) || kickOff.contains(getID())) {
                g2.drawOval(x, y, 50, 50);
                g2.setStroke(new BasicStroke(3));
                g2.setColor(Color.black);
                if (getID() == ID.WRH) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("H", x + 16, y + 30);
                }
                if (getID() == ID.QB) {
                    g.setColor(Color.black);
                    g2.setFont(font);
                    g.drawString("Q", x + 16, y + 30);
                }
                if (getID() == ID.RB) {
                    g.setColor(Color.black);
                    g2.setFont(font);
                    g.drawString("F", x + 17, y + 30);
                }
                if (getID() == ID.TE) {
                    g.setColor(Color.black);
                    g2.setFont(font);
                    g.drawString("Y", x + 17, y + 30);
                }
                if (getID() == ID.WRX) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("X", x + 17, y + 30);
                }
                if (getID() == ID.WRZ) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("Z", x + 17, y + 30);
                }
                if (getID() == ID.LT) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("LT", x + 10, y + 30);
                }
                if (getID() == ID.LG) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("LG", x + 10, y + 30);
                }
                if (getID() == ID.C) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("C", x + 10, y + 30);
                }
                if (getID() == ID.RG) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("RG", x + 10, y + 30);
                }
                if (getID() == ID.RT) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("RT", x + 10, y + 30);
                }
            } else if (defense.contains(getID())) {
                g.setColor(Color.black);
                g.fillRect(x, y, 50, 50);
                g.setColor(Color.white);
                g.fillRect(x + 2, y + 2, 46, 46);
            }
        }
        for (int p = 0; p < playerChangeId.size(); p++) {
            Font font = new Font("Courrier New", Font.PLAIN, 16);
            Font fontOL = new Font("Courrier New", Font.PLAIN, 14);
            Font fontInRun = new Font("Courrier New", Font.PLAIN, 24);
            Graphics2D g2 = (Graphics2D) g;
            if (playerChangeId.get(p).equals(getID())) {
                for (int i = 0; i < playerChangeString.size(); i++) {
                    if (!isInsideRun) {
                        if (offense.contains(getID())) {
                            if (playerChangeString.get(i).length() == 0) {
                                break;
                            } else if (playerChangeString.get(i).length() == 1) {
                                g2.setColor(Color.white);
                                g2.fillOval(x, y, 24, 24);
                                g2.setColor(Color.black);
                                g2.drawOval(x, y, 24, 24);
                                g2.setFont(font);
                                g2.drawString(playerChangeString.get(i), x + 7, y + 17);
                            } else if (playerChangeString.get(i).length() == 2) {
                                g2.setColor(Color.white);
                                g2.fillOval(x, y, 24, 24);
                                g2.setColor(Color.black);
                                g2.drawOval(x, y, 24, 24);
                                g2.setFont(fontOL);
                                g2.drawString(playerChangeString.get(i), x + 3, y + 17);
                            }
                        } else {
                            if (playerChangeString.get(i).length() == 0) {
                                break;
                            } else if (playerChangeString.get(i).length() == 1) {
                                g2.setColor(Color.white);
                                g2.fillRect(x, y, 24, 24);
                                g2.setColor(Color.black);
                                g2.drawRect(x, y, 24, 24);
                                g2.setFont(font);
                                g2.drawString(playerChangeString.get(i), x + 7, y + 17);
                            } else if (playerChangeString.get(i).length() == 2) {
                                g2.setColor(Color.white);
                                g2.fillRect(x, y, 24, 24);
                                g2.setColor(Color.black);
                                g2.drawRect(x, y, 24, 24);
                                g2.setFont(fontOL);
                                g2.drawString(playerChangeString.get(i), x + 3, y + 17);
                            }
                        }
                    } else {
                        if (offense.contains(getID())) {
                            if (playerChangeString.get(i).length() == 0) {
                                break;
                            } else if (playerChangeString.get(i).length() == 1) {
                                g2.setColor(Color.white);
                                g2.fillOval(x, y, 50, 50);
                                g2.setColor(Color.black);
                                g2.drawOval(x, y, 50, 50);
                                g2.setFont(fontInRun);
                                g2.drawString(playerChangeString.get(i), x + 17, y + 30);
                            } else if (playerChangeString.get(i).length() == 2) {
                                g2.setColor(Color.white);
                                g2.fillOval(x, y, 50, 50);
                                g2.setColor(Color.black);
                                g2.drawOval(x, y, 50, 50);
                                g2.setFont(fontInRun);
                                g2.drawString(playerChangeString.get(i), x + 10, y + 30);
                            }
                        } else {
                            if (playerChangeString.get(i).length() == 0) {
                                break;
                            } else if (playerChangeString.get(i).length() == 1) {
                                g2.setColor(Color.white);
                                g2.fillRect(x, y, 50, 50);
                                g2.setColor(Color.black);
                                g2.drawRect(x, y, 50, 50);
                                g2.setFont(fontInRun);
                                g2.drawString(playerChangeString.get(i), x + 17, y + 30);
                            } else if (playerChangeString.get(i).length() == 2) {
                                g2.setColor(Color.white);
                                g2.fillRect(x, y, 50, 50);
                                g2.setColor(Color.black);
                                g2.drawRect(x, y, 50, 50);
                                g2.setFont(fontInRun);
                                g2.drawString(playerChangeString.get(i), x + 10, y + 30);
                            }
                        }
                    }
                }
            }
        }
    }
}