import java.awt.Graphics;
import java.awt.event.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

import javax.imageio.*;
import javax.swing.*;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import org.opencv.objdetect.CascadeClassifier;


public class FaceDetection extends JFrame {
	//For Object Serialization Warning (although serialization isnt happening)
	private static final long serialVersionUID = 1L;
	
	private JButton startButton;
    private JButton pauseButton;
    private JPanel panel;
    private JFrame jframe;
 
    //background thread
    private DaemonThread myThread = null;
    
    int count = 0;
    
    VideoCapture webSource = null;
    Mat frame = new Mat();
    MatOfByte mem = new MatOfByte();
    CascadeClassifier faceDetector = new CascadeClassifier("src/cascade.xml");
    MatOfRect faceDetections = new MatOfRect();

    class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        public void run() {
            synchronized (this) {
                while (runnable) {
                    if (webSource.grab()) {
                        try {
                            webSource.retrieve(frame);
                            Graphics g = panel.getGraphics();
                            faceDetector.detectMultiScale(frame, faceDetections);
                            for (Rect rect : faceDetections.toArray()) {
            					Imgproc.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(255));

                            }
                            Imgcodecs.imencode(".jpg", frame, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
                            BufferedImage buff = (BufferedImage) im;
                            if (g.drawImage(buff, 0, 0, getWidth(), getHeight()-150 , 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                if (runnable == false) {
                                    System.out.println("Paused ..... ");
                                    this.wait();
                                }
                            }
                        } catch (Exception ex) {
                            System.out.println("Error");
                        }
                    }
                }
            }
        }
    }

    public FaceDetection() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        initComponents();
    }
    

    private void initComponents() {
    	jframe = new JFrame();
        panel = new JPanel();
        startButton = new JButton();
        pauseButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GroupLayout panelLayout = new GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 376, Short.MAX_VALUE)
        );

        startButton.setText("Start");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        pauseButton.setText("Pause");
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                pauseButtonActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(255, 255, 255)
                .addComponent(startButton)
                .addGap(86, 86, 86)
                .addComponent(pauseButton)
                .addContainerGap(258, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(startButton)
                    .addComponent(pauseButton))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        jframe.add(startButton);
        jframe.add(pauseButton);
        jframe.add(panel);
        jframe.setSize(panel.getWidth()+100,panel.getHeight()+100);
        jframe.setResizable(false);
        jframe.setVisible(true);
        
    }

    //Pause Button ActionListener
    private void pauseButtonActionPerformed(ActionEvent evt) {
        myThread.runnable = false;            
        pauseButton.setEnabled(false);   
        startButton.setEnabled(true);    
        webSource.release();  
    }
    
    //Start Button ActionListener
    private void startButtonActionPerformed(ActionEvent evt) {
    	String url = null;
    	if (webSource == null) {
    		 JFileChooser fileChooser = new JFileChooser();
    		    int returnValue = fileChooser.showOpenDialog(null);
    		    if (returnValue == JFileChooser.APPROVE_OPTION) {
    		      File selectedFile = fileChooser.getSelectedFile();
    		      url = selectedFile.toString();
    	}
        webSource = new VideoCapture(url); 
        myThread = new DaemonThread(); 
        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();               
        startButton.setEnabled(false);  
        pauseButton.setEnabled(true); 
	    }

    }
}
