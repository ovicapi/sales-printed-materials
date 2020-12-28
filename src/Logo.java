import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Logo extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final int FRAME_WIDTH = 600;
	public static final int FRAME_HEIGHT = 400;
	
	public Logo() throws IOException, InvalidFormatException {
		createComponents();			
		setSize(FRAME_WIDTH, FRAME_HEIGHT);	
	}

	public void createComponents() throws IOException  {
		
		JPanel panelLogo = new JPanel();
		panelLogo.setLayout(new FlowLayout(FlowLayout.LEADING));
		panelLogo.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
		panelLogo.setBackground(Color.WHITE);
		panelLogo.setLayout(null);
		
		JLabel title = new JLabel();
		title.setText("<html><div style='text-align: center;'>Sales of Printed Materials</div></html>");
		title.setPreferredSize(new Dimension(400, 100));
		Dimension size = title.getPreferredSize();
		title.setBounds(100, 70, size.width, size.height);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Verdana", Font.BOLD, 30));
		title.setForeground(Color.RED);

		
		JLabel by = new JLabel();
		by.setText("<html><div style='text-align: center;'>by Capitanu</div></html>");
		by.setPreferredSize(new Dimension(200, 50));
		Dimension size1 = by.getPreferredSize();
		by.setBounds(410, 160, size1.width, size1.height);
		by.setHorizontalAlignment(SwingConstants.RIGHT);
		by.setFont(new Font("Courier", Font.PLAIN, 22));
		title.setForeground(Color.BLACK);

		ImageIcon imageIcon = new ImageIcon("resources/logo_intuitext.png");
		
		JLabel picLabel = new JLabel(imageIcon);
		picLabel.setPreferredSize(new Dimension(180, 52));
		Dimension size2 = picLabel.getPreferredSize();
		picLabel.setBounds(30, 300, size2.width, size2.height);
		by.setHorizontalAlignment(SwingConstants.LEFT);

		panelLogo.add(title);
		panelLogo.add(by);
		panelLogo.add(picLabel);
	
		add(panelLogo);
	}
}
