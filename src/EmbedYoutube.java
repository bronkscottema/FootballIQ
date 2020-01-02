import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class EmbedYoutube {
	public static void main(String[] args) {
		NativeInterface.open();
		SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			JFrame frame = new JFrame("Youtube Video");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().add(getBrowswer(), BorderLayout.CENTER);
			frame.setVisible(true);
		}
		});
		NativeInterface.runEventPump();
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable () {
			public void run() {
				NativeInterface.close();
			}
		}));
	}
	public static JPanel  getBrowswer() {
		JPanel panel = new JPanel(new BorderLayout());
		JWebBrowser wb = new JWebBrowser();
		panel.add(wb, BorderLayout.CENTER);
		wb.setBarsVisible(false);
		wb.navigate("https://www.youtube.com/watch?v=GKiHB5AzihE");
		return panel;
	}

}
