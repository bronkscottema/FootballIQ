import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class DetectFace {
 
	static JFrame frame;
	static JLabel lbl;
	static ImageIcon icon;
	
 
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		int x,y;

	    CascadeClassifier cascadeBodyClassifier = new CascadeClassifier("src/cascade.xml");

		VideoCapture videoDevice = new VideoCapture();
		videoDevice.open(0);
		if (videoDevice.isOpened()) {
			while (true) {		
				Mat frameCapture = new Mat();
				videoDevice.read(frameCapture);
				
				MatOfRect fullBody = new MatOfRect();
				cascadeBodyClassifier.detectMultiScale(frameCapture, fullBody);
				for (Rect rect : fullBody.toArray()) {
					Imgproc.rectangle(frameCapture, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(255));

					
				}

				PushImage(ConvertMat2Image(frameCapture));
//				System.out.println(String.format("%s body(FACES).", upperBody.toArray().length));
			}
		} else {
			return;
		}
	}
	private static BufferedImage ConvertMat2Image(Mat kameraVerisi) {
	
		
		MatOfByte byteMatVerisi = new MatOfByte();
		Imgcodecs.imencode(".jpg", kameraVerisi, byteMatVerisi);
		byte[] byteArray = byteMatVerisi.toArray();
		BufferedImage buffImage = null;
		try {
			InputStream in = new ByteArrayInputStream(byteArray);
			buffImage = ImageIO.read(in);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return buffImage;
	}
  	
	public static void MyFrame() {
		frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(700, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void PushImage(Image img2) {
		if (frame == null)
			MyFrame();
		if (lbl != null)
			frame.remove(lbl);
		icon = new ImageIcon(img2);
		lbl = new JLabel();
		lbl.setIcon(icon);
		frame.add(lbl);
		frame.revalidate();
	}
	
}