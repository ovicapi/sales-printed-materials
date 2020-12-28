import java.io.IOException;
import javax.swing.JFrame;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class SPM_main {
	protected static final int EXIT_ON_CLOSE = 0;

	public static void main(String[] args) throws IOException, InvalidFormatException, InterruptedException {

		JFrame frameLogo = new Logo();
		frameLogo.setLocationRelativeTo(null);
		frameLogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLogo.setAlwaysOnTop (true);
		frameLogo.setUndecorated(true);
		frameLogo.setVisible(true);

		int delay = 3000; // Delay in milliseconds
		Thread.sleep(delay);
		frameLogo.dispose();
		try {
			MainFrame.mainFrame();
		} catch (InvalidFormatException e1) {
			e1.printStackTrace();
		}
	}
}
