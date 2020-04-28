import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Player extends GameObject {

    Handler handler;
    ArrayList<String> playerChangeString = new ArrayList<String>();
    ArrayList<ID> playerChangeId = new ArrayList<ID>();
    ArrayList<String> xmlPlayerString = new ArrayList<String>();
    ArrayList<String> xmlPlayerId = new ArrayList<String>();
    Object object;
    int playerNum = 0;
    private ArrayList<String> rolev;
    boolean readXMLSelected = false;



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
        offense.add(ID.W);
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
    }
    ArrayList<ID> kickOff;

    {
        kickOff = new ArrayList<ID>();
        kickOff.add(ID.K);
        kickOff.add(ID.P);
        kickOff.add(ID.D);
        kickOff.add(ID.D1);
        kickOff.add(ID.D2);
        kickOff.add(ID.D3);
        kickOff.add(ID.D4);
        kickOff.add(ID.D5);
        kickOff.add(ID.D6);
        kickOff.add(ID.D7);
        kickOff.add(ID.D8);
        kickOff.add(ID.D9);
        kickOff.add(ID.D10);
        kickOff.add(ID.O);
        kickOff.add(ID.O1);
        kickOff.add(ID.O2);
        kickOff.add(ID.O3);
        kickOff.add(ID.O4);
        kickOff.add(ID.O5);
        kickOff.add(ID.O6);
        kickOff.add(ID.O7);
        kickOff.add(ID.O8);
        kickOff.add(ID.O9);
        kickOff.add(ID.O10);

    }

    public void changePlayer(String userInput, ID id) {
        if (userInput != null) {
            playerChangeString.add(userInput);
            playerChangeId.add(id);
            try {
                FileOutputStream fileOut = new FileOutputStream("card.ser");//creates a card serial file in output stream
                ObjectOutputStream out = new ObjectOutputStream(fileOut);//routs an object into the output stream.
                out.writeObject(userInput);// we designate our array of cards to be routed
                out.writeObject(id);// we designate our array of cards to be routed
                out.close();// closes the data paths
                fileOut.close();// closes the data paths
            } catch(IOException i) {
                i.printStackTrace();
            }
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
                if (getID() == ID.TE) {
                    g.setColor(Color.black);
                    g2.setFont(font);
                    g.drawString("Y", x + 7, y + 17);
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
                if (getID() == ID.SDE) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("DE", x + 1, y + 17);
                }
                if (getID() == ID.SDT) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("DT", x + 1, y + 17);
                }
                if (getID() == ID.WDT) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("DT", x + 1, y + 17);
                }
                if (getID() == ID.WDE) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("DE", x + 1, y + 17);
                }
                if (getID() == ID.SOSLB) {
                    g.setColor(Color.black);
                    g2.setFont(font);
                    g.drawString("S", x + 7, y + 17);
                }
                if (getID() == ID.SISLB) {
                    g.setColor(Color.black);
                    g2.setFont(font);
                    g.drawString("M", x + 5, y + 17);
                }
                if (getID() == ID.WOSLB) {
                    g.setColor(Color.black);
                    g2.setFont(font);
                    g.drawString("W", x + 5, y + 17);
                }
                if (getID() == ID.SCB) {
                    g.setColor(Color.black);
                    g2.setFont(font);
                    g.drawString("CB", x + 2, y + 17);
                }
                if (getID() == ID.FS) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("FS", x + 3, y + 17);
                }
                if (getID() == ID.SS) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("SS", x + 3, y + 17);
                }
                if (getID() == ID.WCB) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("CB", x + 2, y + 17);
                }
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
                if (getID() == ID.SDE) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("DE", x + 10, y + 30);
                }
                if (getID() == ID.SDT) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("DT", x + 10, y + 30);
                }
                if (getID() == ID.WDT) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("DT", x + 10, y + 30);
                }
                if (getID() == ID.WDE) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("DE", x + 10, y + 30);
                }
                if (getID() == ID.SOSLB) {
                    g.setColor(Color.black);
                    g2.setFont(font);
                    g.drawString("S", x + 17, y + 30);
                }
                if (getID() == ID.SISLB) {
                    g.setColor(Color.black);
                    g2.setFont(font);
                    g.drawString("M", x + 15, y + 30);
                }
                if (getID() == ID.WOSLB) {
                    g.setColor(Color.black);
                    g2.setFont(font);
                    g.drawString("W", x + 15, y + 30);
                }
                if (getID() == ID.SCB) {
                    g.setColor(Color.black);
                    g2.setFont(font);
                    g.drawString("CB", x + 10, y + 30);
                }
                if (getID() == ID.FS) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("FS", x + 10, y + 30);
                }
                if (getID() == ID.SS) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("SS", x + 10, y + 30);
                }
                if (getID() == ID.WCB) {
                    g2.setColor(Color.black);
                    g2.setFont(font);
                    g2.drawString("CB", x + 10, y + 30);
                }
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
                                g2.fillRect(x, y, 23, 23);
                                g2.setColor(Color.black);
                                g2.drawRect(x, y, 23, 23);
                                g2.setFont(font);
                                g2.drawString(playerChangeString.get(i), x + 7, y + 17);
                            } else if (playerChangeString.get(i).length() == 2) {
                                g2.setColor(Color.white);
                                g2.fillRect(x, y, 23, 23);
                                g2.setColor(Color.black);
                                g2.drawRect(x, y, 23, 23);
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
        if (readXMLSelected) {
            try {
                FileInputStream fileIn = new FileInputStream("readXml.ser");// Read serial file.
                ObjectInputStream in = new ObjectInputStream(fileIn);// input the read file.
                String object = (String) in.readObject().toString();
                in.close();//closes the input stream.
                fileIn.close();//closes the file data stream.


                    for (int p = 0; p < xmlPlayerString.size(); p++) {
                        for (int id = 0; id < xmlPlayerId.size(); id++) {
//                        if (offense.get(i).toString().equals(xmlPlayerId.get(id))) {
//
//                        } else {
//
//                        }
                        }
                    }

            } catch (IOException io) {
                System.out.print("handling file exception/maybe the file isn't found");
            } catch (ClassNotFoundException c) {
                System.out.println("Error");
            }
            readXMLSelected = false;
        }
    }

    public void readXML(String xml) {
        readXMLSelected = true;
        playerChangeString = new ArrayList<String>();
        Document dom;
        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // parse using the builder to get the DOM mapping of the
            // XML file
            dom = db.parse(xml);

            Element doc = dom.getDocumentElement();
            NodeList nl;

            nl = doc.getElementsByTagName("PlayerName");
            if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
               for (int i = 0; i < nl.getLength(); i++) {
                   xmlPlayerString.add(nl.item(i).getFirstChild().getNodeValue());
               }
            }
            NodeList nlId;
            nlId = doc.getElementsByTagName("ID");
            if (nlId.getLength() > 0 && nlId.item(0).hasChildNodes()) {
                for (int i = 0; i < nlId.getLength(); i++) {
                    xmlPlayerId.add(nlId.item(i).getFirstChild().getNodeValue());
                }
            }

            try {
                FileOutputStream fileOut = new FileOutputStream("readXml.ser");//creates a card serial file in output stream
                ObjectOutputStream out = new ObjectOutputStream(fileOut);//routs an object into the output stream.
                out.writeObject(xmlPlayerId);// we designate our array of cards to be routed
                out.writeObject(xmlPlayerString);// we designate our array of cards to be routed
                out.close();// closes the data paths
                fileOut.close();// closes the data paths
            } catch(IOException i) {
                i.printStackTrace();
            }

        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

    }

    public void saveToXML(String xml) {

        try {

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("Players");
            document.appendChild(root);

            for (int i = 0; i < offense.size(); i++) {
                try {
                    FileInputStream fileIn = new FileInputStream("card.ser");// Read serial file.
                    ObjectInputStream in = new ObjectInputStream(fileIn);// input the read file.
                    String object = (String) in.readObject();
                    ID object2 = (ID) in.readObject();// allocate it to the object file already instanciated.
                    in.close();//closes the input stream.
                    fileIn.close();//closes the file data stream.
                    xmlPlayerString.add(object);
                    xmlPlayerId.add(object2.toString());
                    if (xmlPlayerString.size() > 0) {
                        for (int p = 0; p < xmlPlayerString.size(); p++) {
                            for (int id = 0; id < xmlPlayerId.size(); id++) {
                                if (offense.get(i).toString().equals(xmlPlayerId.get(id))) {
                                    if (playerNum == 0) {
                                        String playerId = xmlPlayerId.get(id);
                                        String playerName = xmlPlayerString.get(p);
                                        Element player = document.createElement("Players");
                                        root.appendChild(player);

                                        // set an attribute to ID element
                                        Element elementId = document.createElement("ID");
                                        elementId.appendChild(document.createTextNode(playerId));
                                        player.appendChild(elementId);

                                        // elementPlayerName element
                                        Element elementPlayerName = document.createElement("PlayerName");
                                        elementPlayerName.appendChild(document.createTextNode(playerName));
                                        player.appendChild(elementPlayerName);
                                        playerNum++;
                                        break;
                                    }
                                } else {
                                    String playerId = offense.get(i).toString();
                                    Element player = document.createElement("Players");
                                    root.appendChild(player);

                                    // set an attribute to ID element
                                    Element elementId = document.createElement("ID");
                                    elementId.appendChild(document.createTextNode(playerId));
                                    player.appendChild(elementId);

                                    // elementPlayerName element
                                    Element elementPlayerName = document.createElement("PlayerName");
                                    elementPlayerName.appendChild(document.createTextNode(playerId));
                                    player.appendChild(elementPlayerName);
                                    break;
                                }
                                break;
                            }
                            break;
                        }
                    } else {
                       return;
                    }
                } catch(IOException io) {
                    System.out.print("handling file exception/maybe the file isn't found");
                } catch(ClassNotFoundException c) {
                    System.out.println("Error");
                } finally {
                    String playerId = offense.get(i).toString();
                    Element player = document.createElement("Players");
                    root.appendChild(player);

                    // set an attribute to ID element
                    Element elementId = document.createElement("ID");
                    elementId.appendChild(document.createTextNode(playerId));
                    player.appendChild(elementId);

                    // elementPlayerName element
                    Element elementPlayerName = document.createElement("PlayerName");
                    elementPlayerName.appendChild(document.createTextNode(playerId));
                    player.appendChild(elementPlayerName);
                }
            }

            for (int i = 0; i < defense.size(); i++) {
                try {
                    FileInputStream fileIn = new FileInputStream("card.ser");// Read serial file.
                    ObjectInputStream in = new ObjectInputStream(fileIn);// input the read file.
                    String object = (String) in.readObject();
                    ID object2 = (ID) in.readObject();// allocate it to the object file already instanciated.
                    in.close();//closes the input stream.
                    fileIn.close();//closes the file data stream.
                    xmlPlayerString.add(object);
                    xmlPlayerId.add(object2.toString());
                    if (xmlPlayerString.size() > 0) {
                        for (int p = 0; p < xmlPlayerString.size(); p++) {
                            for (int id = 0; id < xmlPlayerId.size(); id++) {
                                if (defense.get(i).toString().equals(xmlPlayerId.get(id))) {
                                    if (playerNum == 0) {
                                        String playerId = xmlPlayerId.get(id);
                                        String playerName = xmlPlayerString.get(p);
                                        Element player = document.createElement("Players");
                                        root.appendChild(player);

                                        // set an attribute to ID element
                                        Element elementId = document.createElement("ID");
                                        elementId.appendChild(document.createTextNode(playerId));
                                        player.appendChild(elementId);

                                        // elementPlayerName element
                                        Element elementPlayerName = document.createElement("PlayerName");
                                        elementPlayerName.appendChild(document.createTextNode(playerName));
                                        player.appendChild(elementPlayerName);
                                        playerNum++;
                                        break;
                                    }
                                } else {
                                    String playerId = defense.get(i).toString();
                                    Element player = document.createElement("Players");
                                    root.appendChild(player);

                                    // set an attribute to ID element
                                    Element elementId = document.createElement("ID");
                                    elementId.appendChild(document.createTextNode(playerId));
                                    player.appendChild(elementId);

                                    // elementPlayerName element
                                    Element elementPlayerName = document.createElement("PlayerName");
                                    elementPlayerName.appendChild(document.createTextNode(playerId));
                                    player.appendChild(elementPlayerName);
                                    break;
                                }
                                break;
                            }
                            break;
                        }
                    } else {
                        return;
                    }
                } catch(IOException io) {
                    System.out.println("File not found");
                } catch(ClassNotFoundException c) {
                    System.out.println("Error");
                }  finally {
                    String playerId = defense.get(i).toString();
                    Element player = document.createElement("Players");
                    root.appendChild(player);

                    // set an attribute to ID element
                    Element elementId = document.createElement("ID");
                    elementId.appendChild(document.createTextNode(playerId));
                    player.appendChild(elementId);

                    // elementPlayerName element
                    Element elementPlayerName = document.createElement("PlayerName");
                    elementPlayerName.appendChild(document.createTextNode(playerId));
                    player.appendChild(elementPlayerName);
                }
            }

            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult streamResult = new StreamResult(new File(xml));

            // If you use
            // StreamResult result = new StreamResult(System.out);
            // the output will be pushed to the standard output ...
            // You can use that for debugging

            transformer.transform(domSource, streamResult);
            //Remove .ser file because its no longer needed
            System.out.println("Done creating XML File");
            try {
                Files.deleteIfExists(Paths.get("card.ser"));
            }
            catch(IOException e) {
                System.out.println("No such file/directory exists");
            }

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}