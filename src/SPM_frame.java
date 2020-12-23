import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;

public class SPM_frame extends JFrame {

private static final long serialVersionUID = 1L;
	
	private static final int FRAME_WIDTH = 1200;
	private static final int FRAME_HEIGHT = 800;
	
	public SPM_frame() throws InvalidFormatException, FileNotFoundException {
		setTitle("Sales of Printed Materials");
		createComponents();			
		setSize(FRAME_WIDTH, FRAME_HEIGHT);	
	}
	public void createComponents() throws InvalidFormatException, FileNotFoundException {

		JPanel panelFrame1 = new JPanel();									// Construct the panel for select the product
		panelFrame1.setLayout(new FlowLayout(FlowLayout.LEADING));
		panelFrame1.setBounds(0, 0, 1200, 200);
		panelFrame1.setBackground(Color.RED);
		panelFrame1.setLayout(null);
		
		
		JLabel p1l1 = new JLabel("");											// See the results of changing ComboBox value
		p1l1.setPreferredSize(new Dimension(750, 50));
		Dimension size1 = p1l1.getPreferredSize();
		p1l1.setBounds(375, 45, size1.width, size1.height);
		p1l1.setHorizontalAlignment(SwingConstants.CENTER);
		p1l1.setFont(new Font("Verdana", Font.BOLD, 22));
		p1l1.setOpaque(true);
		p1l1.setBackground(new Color(224, 224, 224));
		p1l1.setForeground(Color.RED);
		
		//Construct an array list of string containing products

		List<String> products;
		Boolean debug = false;
		ArrayList<Sheet> mySheet = new ArrayList<Sheet>(SelectSheets.selectSheets());
		Sheet sheet_ListaProduse = mySheet.get(2);
		products = ArrayFromExcelToFeedCombo.GetExcelTableIntoArrayListString(sheet_ListaProduse, debug);
		
//Convert the array list "products" into an array, to construct JComboBox based on this array		

		String[] products_array = products.toArray(new String[products.size()]);

//Construct a JComboBox named "selectProduct", to select an item and view information about this item
		
		JComboBox<String> selectProduct = new JComboBox<>(products_array);
		selectProduct.setBounds(20,10,400,25);
		selectProduct.setPreferredSize(new Dimension(400, 40));

//Add a Listener for the JComboBox "SelectProduct"

		selectProduct.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				p1l1.setText((String) e.getItem());
			}
		});
		panelFrame1.add(selectProduct);
		panelFrame1.add(p1l1);
		
		//Add panel to the frame
		
		add(panelFrame1);
	}
}
