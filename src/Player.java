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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends GameObject {

    Handler handler;
    ArrayList<String> playerChangeString = new ArrayList<String>();
    ArrayList<ID> playerChangeId = new ArrayList<ID>();
    ArrayList<String> xmlPlayerString = new ArrayList<String>();
    private ArrayList<String> rolev;


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
    //TODO xml shit

    public void readXML(String xml) {
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


            for (int o = 0; o < offense.size(); o++) {
                if (dom.getElementsByTagName("ID") != null) {
                    if (dom.getElementsByTagName("PlayerName") != null) {
                        rolev.add("ID");
                        rolev.add("PlayerName");
                    }
                }
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

                // employee element

                for (int i = 0; i < offense.size(); i++) {
                    if (xmlPlayerString.size() > 0) {
                        for (int p = 0; p < xmlPlayerString.size(); p++) {
                            if (offense.get(i).toString().equals(xmlPlayerString.get(p))) {
                                System.out.print("something");

                            }
                        }
                    } else {
                        System.out.print("something");
                    }
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

                for (int i = 0; i < defense.size(); i++) {
                    //if (playerString > 0) {
                    //  for (int p = 0; p < playerString.size(); p++;) {
                    //  TODO Do stuff
                    //  }
                    // } else if {
                    // }
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

                System.out.println("Done creating XML File");

            } catch (ParserConfigurationException pce) {
                pce.printStackTrace();
            } catch (TransformerException tfe) {
                tfe.printStackTrace();
            }


    }
    private String getTextValue(String def, Element doc, String tag) {
        String value = def;
        NodeList nl;
        nl = doc.getElementsByTagName(tag);
        if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
            value = nl.item(0).getFirstChild().getNodeValue();
        }
        return value;
    }
}