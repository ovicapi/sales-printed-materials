import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;


public class SPM_frame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private static final int FRAME_WIDTH = 1200;
	private static final int FRAME_HEIGHT = 800;
	
	private JSplitPane splitPane;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JComboBox<String> selectYear;
	
	private JLabel p2_buc;
	private JLabel p2_eur;
	private JLabel p2l1;
	private JLabel p2_an;
	private JLabel p2l2;
	private JLabel p2l3;
	private JLabel p2l4;
	private JLabel p2l5;
	private JLabel p2l6;
	private JLabel p2l7;
	private JLabel p2l8;
	private JLabel p2l9;
	private JLabel p2l10;
	private JLabel p2l11;
	private JLabel p2l12;
	private JLabel p2l13;
	private JLabel p2l14;
	private JLabel p2l15;
	private JLabel p2l16;
	private JLabel p2l17;
	private JLabel p2l18;
	private JLabel p2l19;
	private JLabel p2_2016buc;
	private JLabel p2_2017buc;
	private JLabel p2_2018buc;
	private JLabel p2_2019buc;
	private JLabel p2_2020buc;
	private JLabel p2_2021buc;
	private JLabel p2_2016eur;
	private JLabel p2_2017eur;
	private JLabel p2_2018eur;
	private JLabel p2_2019eur;
	private JLabel p2_2020eur;
	private JLabel p2_2021eur;
	private JLabel p2_ianbuc;
	private JLabel p2_febbuc;
	private JLabel p2_marbuc;
	private JLabel p2_aprbuc;
	private JLabel p2_maibuc;
	private JLabel p2_iunbuc;
	private JLabel p2_iulbuc;
	private JLabel p2_augbuc;
	private JLabel p2_septbuc;
	private JLabel p2_octbuc;
	private JLabel p2_novbuc;
	private JLabel p2_decbuc;
	
	static String first = new String("Raport pe ani");
    static String second = new String("Raport pe luni");
    
    private static DecimalFormat df2 = new DecimalFormat ("###,###.00");
	
	public SPM_frame() throws InvalidFormatException, FileNotFoundException {

		setTitle("Sales of Printed Materials");
		createComponents();			
		setSize(FRAME_WIDTH, FRAME_HEIGHT);	
	}
	public void createComponents() throws InvalidFormatException, FileNotFoundException {

		splitPane = new JSplitPane();										// Construct a splitPane that will hold the top and he bottom panels
		setPreferredSize(new Dimension(1200, 800));
		getContentPane().setLayout(new GridLayout());
		getContentPane().add(splitPane);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerLocation(120);
		splitPane.setTopComponent(topPanel);
		splitPane.setBottomComponent(bottomPanel);
		
		topPanel = new JPanel();											// Construct the topPanel that will hold the selection area
		topPanel.setLayout(null);
		
		JLabel p1l1 = new JLabel("");										// Construct a label to catch the selected product
		p1l1.setPreferredSize(new Dimension(750, 40));
		Dimension size1 = p1l1.getPreferredSize();
		p1l1.setBounds(20, 55, size1.width, size1.height);
		p1l1.setHorizontalAlignment(SwingConstants.CENTER);
		p1l1.setFont(new Font("Verdana", Font.BOLD, 22));
		p1l1.setOpaque(true);
		p1l1.setBackground(new Color(224, 224, 224));
		p1l1.setForeground(Color.RED);
		
		JRadioButton byYears = new JRadioButton(first, true);				// Construct a radioButton to select the report by years
		byYears.setBounds(830, 25, 150, 30);
		byYears.setActionCommand(first);

		JRadioButton byMonths = new JRadioButton(second);					// Construct a radioButton to select the report by months
		byMonths.setBounds(830, 65, 150, 30);
		byMonths.setActionCommand(second);
		
		ButtonGroup group = new ButtonGroup();								// Group the above radioButtons
		group.add(byYears);
		group.add(byMonths);
		
		RadioListener myListener = new RadioListener();						// Register a listener for the radio buttons
	    byYears.addActionListener(myListener);
	    byMonths.addActionListener(myListener);

		//Prepare the list of years to feed the comboBox
		
		ArrayList<String> years = new ArrayList<String>();	//Construct an array list of string containing products
			years.add("Alege anul");
			years.add("2016");
			years.add("2017");
			years.add("2018");
			years.add("2019");
			years.add("2020");
			years.add("2021");
			
		//Convert the array list "years" into an array, to construct JComboBox based on this array		

		String[] years_array = years.toArray(new String[years.size()]);	
		
		selectYear = new JComboBox<>(years_array);
		selectYear.setBounds(1020, 65, 110, 30);
		selectYear.setBackground(Color.WHITE);
		selectYear.setVisible(false);
		
		//Add a Listener for the JComboBox "selectYear"

		selectYear.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e_year) {
				p2_an.setText((String) e_year.getItem());
				if (e_year.getStateChange() == ItemEvent.SELECTED) {
					ArrayList<String> luni = new ArrayList<String>();	//Construct an array list of string containing months
					luni.add("ian");
					luni.add("feb");
					luni.add("mar");
					luni.add("apr");
					luni.add("mai");
					luni.add("iun");
					luni.add("iul");
					luni.add("aug");
					luni.add("sept");
					luni.add("oct");
					luni.add("nov");
					luni.add("dec");

					ArrayList<Integer> buc_luni = null;

					/*
				Identific randurile ce trebuie adunate dupa urmatoarele criterii:
				- produsul selectat in panelul de top sa fie gasit pe coloana H (8)
				- anul selectat in panelul de top sa fie gasit pe coloana I (9)
				- luna selectata sa fie gasita in coloana D (4)
				Adun bucatile gasite pe coloana F (6)
					 */
					try {
						buc_luni = SumMonth.sumQuantityMonth(p1l1.getText(), p2_an.getText(), luni);
					} catch (InvalidFormatException | FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					buc_luni.get(0).toString();
					p2_ianbuc.setText(String.format("%,d", buc_luni.get(0)));
					buc_luni.get(1).toString();
					p2_febbuc.setText(String.format("%,d", buc_luni.get(1)));
					buc_luni.get(2).toString();
					p2_marbuc.setText(String.format("%,d", buc_luni.get(2)));
					buc_luni.get(3).toString();
					p2_aprbuc.setText(String.format("%,d", buc_luni.get(3)));				
					buc_luni.get(4).toString();
					p2_maibuc.setText(String.format("%,d", buc_luni.get(4)));
					buc_luni.get(5).toString();
					p2_iunbuc.setText(String.format("%,d", buc_luni.get(5)));
					buc_luni.get(6).toString();
					p2_iulbuc.setText(String.format("%,d", buc_luni.get(6)));
					buc_luni.get(7).toString();
					p2_augbuc.setText(String.format("%,d", buc_luni.get(7)));
					buc_luni.get(8).toString();
					p2_septbuc.setText(String.format("%,d", buc_luni.get(8)));
					buc_luni.get(9).toString();
					p2_octbuc.setText(String.format("%,d", buc_luni.get(9)));
					buc_luni.get(10).toString();
					p2_novbuc.setText(String.format("%,d", buc_luni.get(10)));
					buc_luni.get(11).toString();
					p2_decbuc.setText(String.format("%,d", buc_luni.get(11)));
				}
			}
		});
		
		//Prepare the list of products to feed the comboBox

		List<String> products;										//Construct an array list of string containing products
		Boolean debug = false;
		ArrayList<Sheet> mySheet = new ArrayList<Sheet>(SelectSheets.selectSheets());
		Sheet sheet_ListaProduse = mySheet.get(2);
		products = ArrayFromExcelToFeedCombo.GetExcelTableIntoArrayListString(sheet_ListaProduse, debug);
		
		//Convert the array list "products" into an array, to construct JComboBox based on this array		

		String[] products_array = products.toArray(new String[products.size()]);

		//Construct a JComboBox named "selectProduct", to select an item and view information about this item
		
		JComboBox<String> selectProduct = new JComboBox<>(products_array);
		selectProduct.setBounds(20,10,400,25);
		selectProduct.setPreferredSize(new Dimension(400, 25));
		selectProduct.setBackground(Color.WHITE);

		//Add a Listener for the JComboBox "SelectProduct"

		selectProduct.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				p1l1.setText((String) e.getItem());

				ArrayList<String> ani = new ArrayList<String>();	//Construct an array list of string containing years
				ani.add("2016");
				ani.add("2017");
				ani.add("2018");
				ani.add("2019");
				ani.add("2020");
				ani.add("2021");
				
				ArrayList<Integer> buc_ani = null;
/*
				Identific randurile ce trebuie adunate dupa urmatoarele criterii:
					- produsul selectat in panelul de top sa fie gasit pe coloana H (8)
					- anul selectat in panelul de top sa fie gasit pe coloana I (9)
				Adun bucatile gasite pe coloana F (6)

*/
				try {
					 buc_ani = SumYear.sumQuantityYear((String)e.getItem(), ani);
				} catch (InvalidFormatException | FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				buc_ani.get(0).toString();
				p2_2016buc.setText(String.format("%,d", buc_ani.get(0)) + " BUC");
				buc_ani.get(1).toString();
				p2_2017buc.setText(String.format("%,d", buc_ani.get(1)) + " BUC");
				buc_ani.get(2).toString();
				p2_2018buc.setText(String.format("%,d", buc_ani.get(2)) + " BUC");
				buc_ani.get(3).toString();
				p2_2019buc.setText(String.format("%,d", buc_ani.get(3)) + " BUC");				
				buc_ani.get(4).toString();
				p2_2020buc.setText(String.format("%,d", buc_ani.get(4)) + " BUC");
				buc_ani.get(5).toString();
				p2_2021buc.setText(String.format("%,d", buc_ani.get(5)) + " BUC");
				
				ArrayList<Double> eur_ani = null;
/*
				Identific randurile ce trebuie adunate dupa urmatoarele criterii:
					- produsul selectat in panelul de top sa fie gasit pe coloana H (8)
					- anul selectat in panelul de top sa fie gasit pe coloana I (9)
				Adun valorile gasite pe coloana E (5)

*/
				try {
					 eur_ani = SumYear.sumValueYear((String)e.getItem(), ani);
				} catch (InvalidFormatException | FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				p2_2016eur.setText(df2.format(eur_ani.get(0)) + " EUR");
				p2_2017eur.setText(df2.format(eur_ani.get(1)) + " EUR");
				p2_2018eur.setText(df2.format(eur_ani.get(2)) + " EUR");
				p2_2019eur.setText(df2.format(eur_ani.get(3)) + " EUR");
				p2_2020eur.setText(df2.format(eur_ani.get(4)) + " EUR");
				p2_2021eur.setText(df2.format(eur_ani.get(5)) + " EUR");
			}	
		});
		
		//Add the label and the comboBox to the topPanel
		
		topPanel.add(selectProduct);
		topPanel.add(p1l1);
		topPanel.add(byYears);
		topPanel.add(byMonths);
		topPanel.add(selectYear);
		
		//Add topPanel to the splitPane
		
		splitPane.add(topPanel);

		//Create a panel for the bottom of the frame. This panel will hold the informations by months and respectively by years of the products
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout((LayoutManager) new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
		bottomPanel.setBackground(Color.BLUE);
		bottomPanel.setLayout(null);

		Font rowTitleFont = new Font("Verdana", Font.BOLD, 14);
		Dimension rowTitleDimension = new Dimension(50,30);
		
		p2_buc = new JLabel ("Vanzari (BUC)");
		p2_buc.setBounds(30, 60, 120, 30);
		p2_buc.setHorizontalAlignment(SwingConstants.CENTER);
		p2_buc.setFont(rowTitleFont);
		p2_buc.setForeground(Color.BLACK);	

		p2_eur = new JLabel ("Venituri (EUR)");
		p2_eur.setBounds(30, 90, 120, 30);
		p2_eur.setHorizontalAlignment(SwingConstants.CENTER);
		p2_eur.setFont(rowTitleFont);
		p2_eur.setForeground(Color.BLACK);			

		p2l1 = new JLabel("TOTAL an");											// Column title "TOTAL an" for the report by months
		p2l1.setPreferredSize(rowTitleDimension);
		p2l1.setBounds(200, 10, 80, 30);
		p2l1.setHorizontalAlignment(SwingConstants.CENTER);
		p2l1.setFont(rowTitleFont);
		p2l1.setForeground(Color.BLACK);
		p2l1.setVisible(false);
		
		p2_an = new JLabel();													// Column title selected year for the report by months
		p2_an.setPreferredSize(rowTitleDimension);
		p2_an.setBounds(200, 30, 80, 30);
		p2_an.setHorizontalAlignment(SwingConstants.CENTER);
		p2_an.setFont(rowTitleFont);
		p2_an.setForeground(Color.BLACK);
		p2_an.setVisible(false);
		
		p2l2 = new JLabel("ian");												// Column title "ian" for the report by months
		p2l2.setPreferredSize(rowTitleDimension);
		p2l2.setBounds(320, 10, rowTitleDimension.width, rowTitleDimension.height);
		p2l2.setHorizontalAlignment(SwingConstants.CENTER);
		p2l2.setFont(rowTitleFont);
		p2l2.setForeground(Color.BLACK);
		p2l2.setVisible(false);

		p2l3 = new JLabel("feb");												// Column title "feb" for the report by months
		p2l3.setPreferredSize(rowTitleDimension);
		p2l3.setBounds(390, 10, rowTitleDimension.width, rowTitleDimension.height);
		p2l3.setHorizontalAlignment(SwingConstants.CENTER);
		p2l3.setFont(rowTitleFont);
		p2l3.setForeground(Color.BLACK);
		p2l3.setVisible(false);
		
		p2l4 = new JLabel("mar");												// Column title "mar" for the report by months
		p2l4.setPreferredSize(rowTitleDimension);
		p2l4.setBounds(460, 10, rowTitleDimension.width, rowTitleDimension.height);
		p2l4.setHorizontalAlignment(SwingConstants.CENTER);
		p2l4.setFont(rowTitleFont);
		p2l4.setForeground(Color.BLACK);	
		p2l4.setVisible(false);
		
		p2l5 = new JLabel("apr");												// Column title "apr" for the report by months
		p2l5.setPreferredSize(rowTitleDimension);
		p2l5.setBounds(530, 10, rowTitleDimension.width, rowTitleDimension.height);
		p2l5.setHorizontalAlignment(SwingConstants.CENTER);
		p2l5.setFont(rowTitleFont);
		p2l5.setForeground(Color.BLACK);			
		p2l5.setVisible(false);
		
		p2l6 = new JLabel("mai");												// Column title "mai" for the report by months
		p2l6.setPreferredSize(rowTitleDimension);
		p2l6.setBounds(600, 10, rowTitleDimension.width, rowTitleDimension.height);
		p2l6.setHorizontalAlignment(SwingConstants.CENTER);
		p2l6.setFont(rowTitleFont);
		p2l6.setForeground(Color.BLACK);		
		p2l6.setVisible(false);
		
		p2l7 = new JLabel("iun");												// Column title "iun" for the report by months
		p2l7.setPreferredSize(rowTitleDimension);
		p2l7.setBounds(670, 10, rowTitleDimension.width, rowTitleDimension.height);
		p2l7.setHorizontalAlignment(SwingConstants.CENTER);
		p2l7.setFont(rowTitleFont);
		p2l7.setForeground(Color.BLACK);
		p2l7.setVisible(false);

		p2l8 = new JLabel("iul");												// Column title "iul" for the report by months
		p2l8.setPreferredSize(rowTitleDimension);
		p2l8.setBounds(740, 10, rowTitleDimension.width, rowTitleDimension.height);
		p2l8.setHorizontalAlignment(SwingConstants.CENTER);
		p2l8.setFont(rowTitleFont);
		p2l8.setForeground(Color.BLACK);	
		p2l8.setVisible(false);
		
		p2l9 = new JLabel("aug");												// Column title "aug" for the report by months
		p2l9.setPreferredSize(rowTitleDimension);
		p2l9.setBounds(810, 10, rowTitleDimension.width, rowTitleDimension.height);
		p2l9.setHorizontalAlignment(SwingConstants.CENTER);
		p2l9.setFont(rowTitleFont);
		p2l9.setForeground(Color.BLACK);	
		p2l9.setVisible(false);
		
		p2l10 = new JLabel("sept");												// Column title "sept" for the report by months
		p2l10.setPreferredSize(rowTitleDimension);
		p2l10.setBounds(880, 10, rowTitleDimension.width, rowTitleDimension.height);
		p2l10.setHorizontalAlignment(SwingConstants.CENTER);
		p2l10.setFont(rowTitleFont);
		p2l10.setForeground(Color.BLACK);	
		p2l10.setVisible(false);
		
		p2l11 = new JLabel("oct");												// Column title "oct" for the report by months
		p2l11.setPreferredSize(rowTitleDimension);
		p2l11.setBounds(950, 10, rowTitleDimension.width, rowTitleDimension.height);
		p2l11.setHorizontalAlignment(SwingConstants.CENTER);
		p2l11.setFont(rowTitleFont);
		p2l11.setForeground(Color.BLACK);	
		p2l11.setVisible(false);
		
		p2l12 = new JLabel("nov");												// Column title "nov" for the report by months
		p2l12.setPreferredSize(rowTitleDimension);
		p2l12.setBounds(1020, 10, rowTitleDimension.width, rowTitleDimension.height);
		p2l12.setHorizontalAlignment(SwingConstants.CENTER);
		p2l12.setFont(rowTitleFont);
		p2l12.setForeground(Color.BLACK);	
		p2l12.setVisible(false);
		
		p2l13 = new JLabel("dec");												// Column title "dec" for the report by months
		p2l13.setPreferredSize(rowTitleDimension);
		p2l13.setBounds(1090, 10, rowTitleDimension.width, rowTitleDimension.height);
		p2l13.setHorizontalAlignment(SwingConstants.CENTER);
		p2l13.setFont(rowTitleFont);
		p2l13.setForeground(Color.BLACK);	
		p2l13.setVisible(false);
		
		p2l14 = new JLabel("TOTAL an 2016");									// Column title "TOTAL an 2016" for the report by years
		p2l14.setPreferredSize(rowTitleDimension);
		p2l14.setBounds(200, 10, 150, 30);
		p2l14.setHorizontalAlignment(SwingConstants.CENTER);
		p2l14.setFont(rowTitleFont);
		p2l14.setForeground(Color.BLACK);	
		p2l14.setVisible(true);
		
		p2l15 = new JLabel("TOTAL an 2017");									// Column title "TOTAL an 2017" for the report by years
		p2l15.setPreferredSize(rowTitleDimension);
		p2l15.setBounds(350, 10, 150, 30);
		p2l15.setHorizontalAlignment(SwingConstants.CENTER);
		p2l15.setFont(rowTitleFont);
		p2l15.setForeground(Color.BLACK);	
		p2l15.setVisible(true);		
		
		p2l16 = new JLabel("TOTAL an 2018");									// Column title "TOTAL an 2018" for the report by years
		p2l16.setPreferredSize(rowTitleDimension);
		p2l16.setBounds(500, 10, 150, 30);
		p2l16.setHorizontalAlignment(SwingConstants.CENTER);
		p2l16.setFont(rowTitleFont);
		p2l16.setForeground(Color.BLACK);	
		p2l16.setVisible(true);		
		
		p2l17 = new JLabel("TOTAL an 2019");									// Column title "TOTAL an 2019" for the report by years
		p2l17.setPreferredSize(rowTitleDimension);
		p2l17.setBounds(650, 10, 150, 30);
		p2l17.setHorizontalAlignment(SwingConstants.CENTER);
		p2l17.setFont(rowTitleFont);
		p2l17.setForeground(Color.BLACK);	
		p2l17.setVisible(true);		
		
		p2l18 = new JLabel("TOTAL an 2020");									// Column title "TOTAL an 2020" for the report by years
		p2l18.setPreferredSize(rowTitleDimension);
		p2l18.setBounds(800, 10, 150, 30);
		p2l18.setHorizontalAlignment(SwingConstants.CENTER);
		p2l18.setFont(rowTitleFont);
		p2l18.setForeground(Color.BLACK);	
		p2l18.setVisible(true);
		
		p2l19 = new JLabel("TOTAL an 2021");									// Column title "TOTAL an 2021" for the report by years
		p2l19.setPreferredSize(rowTitleDimension);
		p2l19.setBounds(950, 10, 150, 30);
		p2l19.setHorizontalAlignment(SwingConstants.CENTER);
		p2l19.setFont(rowTitleFont);
		p2l19.setForeground(Color.BLACK);	
		p2l19.setVisible(true);

		p2_2016buc = new JLabel();												// Quantity of selected product sales for 2016
		p2_2016buc.setBounds(200, 60, 150, 30);
		p2_2016buc.setHorizontalAlignment(SwingConstants.CENTER);
		p2_2016buc.setFont(rowTitleFont);
		p2_2016buc.setForeground(Color.BLACK);	
		p2_2016buc.setVisible(true);
		
		p2_2017buc = new JLabel();												// Quantity of selected product sales for 2017
		p2_2017buc.setBounds(350, 60, 150, 30);
		p2_2017buc.setHorizontalAlignment(SwingConstants.CENTER);
		p2_2017buc.setFont(rowTitleFont);
		p2_2017buc.setForeground(Color.BLACK);	
		
		p2_2018buc = new JLabel();												// Quantity of selected product sales for 2018
		p2_2018buc.setBounds(500, 60, 150, 30);
		p2_2018buc.setHorizontalAlignment(SwingConstants.CENTER);
		p2_2018buc.setFont(rowTitleFont);
		p2_2018buc.setForeground(Color.BLACK);	
		
		p2_2019buc = new JLabel();												// Quantity of selected product sales for 2019
		p2_2019buc.setBounds(650, 60, 150, 30);
		p2_2019buc.setHorizontalAlignment(SwingConstants.CENTER);
		p2_2019buc.setFont(rowTitleFont);
		p2_2019buc.setForeground(Color.BLACK);	
		
		p2_2020buc = new JLabel();												// Quantity of selected product sales for 2020
		p2_2020buc.setBounds(800, 60, 150, 30);
		p2_2020buc.setHorizontalAlignment(SwingConstants.CENTER);
		p2_2020buc.setFont(rowTitleFont);
		p2_2020buc.setForeground(Color.BLACK);	
		
		p2_2021buc = new JLabel();												// Quantity of selected product sales for 2021
		p2_2021buc.setBounds(950, 60, 150, 30);
		p2_2021buc.setHorizontalAlignment(SwingConstants.CENTER);
		p2_2021buc.setFont(rowTitleFont);
		p2_2021buc.setForeground(Color.BLACK);	
		p2_2021buc.setVisible(true);
		
		p2_2016eur = new JLabel();												// Value of selected product sales for 2016
		p2_2016eur.setBounds(200, 90, 150, 30);
		p2_2016eur.setHorizontalAlignment(SwingConstants.CENTER);
		p2_2016eur.setFont(rowTitleFont);
		p2_2016eur.setForeground(Color.BLACK);	
		p2_2016eur.setVisible(true);

		p2_2017eur = new JLabel();												// Value of selected product sales for 2017
		p2_2017eur.setBounds(350, 90, 150, 30);
		p2_2017eur.setHorizontalAlignment(SwingConstants.CENTER);
		p2_2017eur.setFont(rowTitleFont);
		p2_2017eur.setForeground(Color.BLACK);	
		p2_2017eur.setVisible(true);

		p2_2018eur = new JLabel();												// Value of selected product sales for 2018
		p2_2018eur.setBounds(500, 90, 150, 30);
		p2_2018eur.setHorizontalAlignment(SwingConstants.CENTER);
		p2_2018eur.setFont(rowTitleFont);
		p2_2018eur.setForeground(Color.BLACK);	
		p2_2018eur.setVisible(true);

		p2_2019eur = new JLabel();												// Value of selected product sales for 2019
		p2_2019eur.setBounds(650, 90, 150, 30);
		p2_2019eur.setHorizontalAlignment(SwingConstants.CENTER);
		p2_2019eur.setFont(rowTitleFont);
		p2_2019eur.setForeground(Color.BLACK);	
		p2_2019eur.setVisible(true);

		p2_2020eur = new JLabel();												// Value of selected product sales for 2020
		p2_2020eur.setBounds(800, 90, 150, 30);
		p2_2020eur.setHorizontalAlignment(SwingConstants.CENTER);
		p2_2020eur.setFont(rowTitleFont);
		p2_2020eur.setForeground(Color.BLACK);	
		p2_2020eur.setVisible(true);

		p2_2021eur = new JLabel();												// Value of selected product sales for 2021
		p2_2021eur.setBounds(950, 90, 150, 30);
		p2_2021eur.setHorizontalAlignment(SwingConstants.CENTER);
		p2_2021eur.setFont(rowTitleFont);
		p2_2021eur.setForeground(Color.BLACK);	
		p2_2021eur.setVisible(true);

		p2_ianbuc = new JLabel();												// Quantity of selected product sales for selected year in January
		p2_ianbuc.setPreferredSize(rowTitleDimension);
		p2_ianbuc.setBounds(320, 60, rowTitleDimension.width, rowTitleDimension.height);
		p2_ianbuc.setHorizontalAlignment(SwingConstants.CENTER);
		p2_ianbuc.setFont(rowTitleFont);
		p2_ianbuc.setForeground(Color.BLACK);	
		p2_ianbuc.setVisible(false);
		
		p2_febbuc = new JLabel();												// Quantity of selected product sales for selected year in February
		p2_febbuc.setPreferredSize(rowTitleDimension);
		p2_febbuc.setBounds(390, 60, rowTitleDimension.width, rowTitleDimension.height);
		p2_febbuc.setHorizontalAlignment(SwingConstants.CENTER);
		p2_febbuc.setFont(rowTitleFont);
		p2_febbuc.setForeground(Color.BLACK);	
		p2_febbuc.setVisible(false);

		p2_marbuc = new JLabel();												// Quantity of selected product sales for selected year in March
		p2_marbuc.setPreferredSize(rowTitleDimension);
		p2_marbuc.setBounds(460, 60, rowTitleDimension.width, rowTitleDimension.height);
		p2_marbuc.setHorizontalAlignment(SwingConstants.CENTER);
		p2_marbuc.setFont(rowTitleFont);
		p2_marbuc.setForeground(Color.BLACK);	
		p2_marbuc.setVisible(false);

		p2_aprbuc = new JLabel();												// Quantity of selected product sales for selected year in April
		p2_aprbuc.setPreferredSize(rowTitleDimension);
		p2_aprbuc.setBounds(530, 60, rowTitleDimension.width, rowTitleDimension.height);
		p2_aprbuc.setHorizontalAlignment(SwingConstants.CENTER);
		p2_aprbuc.setFont(rowTitleFont);
		p2_aprbuc.setForeground(Color.BLACK);	
		p2_aprbuc.setVisible(false);
		
		p2_maibuc = new JLabel();												// Quantity of selected product sales for selected year in May
		p2_maibuc.setPreferredSize(rowTitleDimension);
		p2_maibuc.setBounds(600, 60, rowTitleDimension.width, rowTitleDimension.height);
		p2_maibuc.setHorizontalAlignment(SwingConstants.CENTER);
		p2_maibuc.setFont(rowTitleFont);
		p2_maibuc.setForeground(Color.BLACK);	
		p2_maibuc.setVisible(false);

		p2_iunbuc = new JLabel();												// Quantity of selected product sales for selected year in June
		p2_iunbuc.setPreferredSize(rowTitleDimension);
		p2_iunbuc.setBounds(670, 60, rowTitleDimension.width, rowTitleDimension.height);
		p2_iunbuc.setHorizontalAlignment(SwingConstants.CENTER);
		p2_iunbuc.setFont(rowTitleFont);
		p2_iunbuc.setForeground(Color.BLACK);	
		p2_iunbuc.setVisible(false);

		p2_iulbuc = new JLabel();												// Quantity of selected product sales for selected year in July
		p2_iulbuc.setPreferredSize(rowTitleDimension);
		p2_iulbuc.setBounds(740, 60, rowTitleDimension.width, rowTitleDimension.height);
		p2_iulbuc.setHorizontalAlignment(SwingConstants.CENTER);
		p2_iulbuc.setFont(rowTitleFont);
		p2_iulbuc.setForeground(Color.BLACK);	
		p2_iulbuc.setVisible(false);

		p2_augbuc = new JLabel();												// Quantity of selected product sales for selected year in August
		p2_augbuc.setPreferredSize(rowTitleDimension);
		p2_augbuc.setBounds(810, 60, rowTitleDimension.width, rowTitleDimension.height);
		p2_augbuc.setHorizontalAlignment(SwingConstants.CENTER);
		p2_augbuc.setFont(rowTitleFont);
		p2_augbuc.setForeground(Color.BLACK);	
		p2_augbuc.setVisible(false);

		p2_septbuc = new JLabel();												// Quantity of selected product sales for selected year in September
		p2_septbuc.setPreferredSize(rowTitleDimension);
		p2_septbuc.setBounds(880, 60, rowTitleDimension.width, rowTitleDimension.height);
		p2_septbuc.setHorizontalAlignment(SwingConstants.CENTER);
		p2_septbuc.setFont(rowTitleFont);
		p2_septbuc.setForeground(Color.BLACK);	
		p2_septbuc.setVisible(false);

		p2_octbuc = new JLabel();												// Quantity of selected product sales for selected year in October
		p2_octbuc.setPreferredSize(rowTitleDimension);
		p2_octbuc.setBounds(950, 60, rowTitleDimension.width, rowTitleDimension.height);
		p2_octbuc.setHorizontalAlignment(SwingConstants.CENTER);
		p2_octbuc.setFont(rowTitleFont);
		p2_octbuc.setForeground(Color.BLACK);	
		p2_octbuc.setVisible(false);
		
		p2_novbuc = new JLabel();												// Quantity of selected product sales for selected year in November
		p2_novbuc.setPreferredSize(rowTitleDimension);
		p2_novbuc.setBounds(1020, 60, rowTitleDimension.width, rowTitleDimension.height);
		p2_novbuc.setHorizontalAlignment(SwingConstants.CENTER);
		p2_novbuc.setFont(rowTitleFont);
		p2_novbuc.setForeground(Color.BLACK);	
		p2_novbuc.setVisible(false);

		p2_decbuc = new JLabel();												// Quantity of selected product sales for selected year in December
		p2_decbuc.setPreferredSize(rowTitleDimension);
		p2_decbuc.setBounds(1090, 60, rowTitleDimension.width, rowTitleDimension.height);
		p2_decbuc.setHorizontalAlignment(SwingConstants.CENTER);
		p2_decbuc.setFont(rowTitleFont);
		p2_decbuc.setForeground(Color.BLACK);	
		p2_decbuc.setVisible(false);

		//Add labels to bottomPanel
		
		bottomPanel.add(p2_buc);
		bottomPanel.add(p2_eur);
		bottomPanel.add(p2l1);
		bottomPanel.add(p2_an);
		bottomPanel.add(p2l2);
		bottomPanel.add(p2l3);
		bottomPanel.add(p2l4);
		bottomPanel.add(p2l5);
		bottomPanel.add(p2l6);
		bottomPanel.add(p2l7);
		bottomPanel.add(p2l8);
		bottomPanel.add(p2l9);
		bottomPanel.add(p2l10);
		bottomPanel.add(p2l11);
		bottomPanel.add(p2l12);
		bottomPanel.add(p2l13);
		bottomPanel.add(p2l14);
		bottomPanel.add(p2l15);
		bottomPanel.add(p2l16);
		bottomPanel.add(p2l17);
		bottomPanel.add(p2l18);
		bottomPanel.add(p2l19);
		bottomPanel.add(p2_2016buc);
		bottomPanel.add(p2_2017buc);
		bottomPanel.add(p2_2018buc);
		bottomPanel.add(p2_2019buc);
		bottomPanel.add(p2_2020buc);
		bottomPanel.add(p2_2021buc);
		bottomPanel.add(p2_2016eur);
		bottomPanel.add(p2_2017eur);
		bottomPanel.add(p2_2018eur);
		bottomPanel.add(p2_2019eur);
		bottomPanel.add(p2_2020eur);
		bottomPanel.add(p2_2021eur);
		bottomPanel.add(p2_ianbuc);
		bottomPanel.add(p2_febbuc);
		bottomPanel.add(p2_marbuc);
		bottomPanel.add(p2_aprbuc);
		bottomPanel.add(p2_maibuc);
		bottomPanel.add(p2_iunbuc);
		bottomPanel.add(p2_iulbuc);
		bottomPanel.add(p2_augbuc);
		bottomPanel.add(p2_septbuc);
		bottomPanel.add(p2_octbuc);
		bottomPanel.add(p2_novbuc);
		bottomPanel.add(p2_decbuc);
		
		// Add the bottomPanel to the splitPane
		
		splitPane.add(bottomPanel);
		
		pack();
	}
	class RadioListener implements ActionListener { //only one event type needed
		
		public void actionPerformed (ActionEvent e) {
			if (e.getActionCommand() == first) {
				selectYear.setVisible(false);
				p2l1.setVisible(false);
				p2_an.setVisible(false);
				p2l2.setVisible(false);
				p2l3.setVisible(false);
				p2l4.setVisible(false);
				p2l5.setVisible(false);
				p2l6.setVisible(false);
				p2l7.setVisible(false);
				p2l8.setVisible(false);
				p2l9.setVisible(false);
				p2l10.setVisible(false);
				p2l11.setVisible(false);
				p2l12.setVisible(false);
				p2l13.setVisible(false);
				p2l14.setVisible(true);
				p2l15.setVisible(true);
				p2l16.setVisible(true);
				p2l17.setVisible(true);
				p2l18.setVisible(true);
				p2l19.setVisible(true);
				p2_2016buc.setVisible(true);
				p2_2017buc.setVisible(true);
				p2_2018buc.setVisible(true);
				p2_2019buc.setVisible(true);
				p2_2020buc.setVisible(true);
				p2_2021buc.setVisible(true);
				p2_2016eur.setVisible(true);
				p2_2017eur.setVisible(true);
				p2_2018eur.setVisible(true);
				p2_2019eur.setVisible(true);
				p2_2020eur.setVisible(true);
				p2_2021eur.setVisible(true);
				p2_ianbuc.setVisible(false);
				p2_febbuc.setVisible(false);
				p2_marbuc.setVisible(false);
				p2_aprbuc.setVisible(false);
				p2_maibuc.setVisible(false);
				p2_iunbuc.setVisible(false);
				p2_iulbuc.setVisible(false);
				p2_augbuc.setVisible(false);
				p2_septbuc.setVisible(false);
				p2_octbuc.setVisible(false);
				p2_novbuc.setVisible(false);
				p2_decbuc.setVisible(false);
				
			}
			else if (e.getActionCommand() == second){
//				System.out.println("Raport pe LUNI a fost selectat");
				selectYear.setVisible(true);
				p2l1.setVisible(true);
				p2_an.setVisible(true);
				p2l2.setVisible(true);
				p2l3.setVisible(true);
				p2l4.setVisible(true);
				p2l5.setVisible(true);
				p2l6.setVisible(true);
				p2l7.setVisible(true);
				p2l8.setVisible(true);
				p2l9.setVisible(true);
				p2l10.setVisible(true);
				p2l11.setVisible(true);
				p2l12.setVisible(true);
				p2l13.setVisible(true);
				p2l14.setVisible(false);
				p2l15.setVisible(false);
				p2l16.setVisible(false);
				p2l17.setVisible(false);
				p2l18.setVisible(false);
				p2l19.setVisible(false);
				p2_2016buc.setVisible(false);
				p2_2017buc.setVisible(false);
				p2_2018buc.setVisible(false);
				p2_2019buc.setVisible(false);
				p2_2020buc.setVisible(false);
				p2_2021buc.setVisible(false);
				p2_2016eur.setVisible(false);
				p2_2017eur.setVisible(false);
				p2_2018eur.setVisible(false);
				p2_2019eur.setVisible(false);
				p2_2020eur.setVisible(false);
				p2_2021eur.setVisible(false);
				p2_ianbuc.setVisible(true);
				p2_febbuc.setVisible(true);
				p2_marbuc.setVisible(true);
				p2_aprbuc.setVisible(true);
				p2_maibuc.setVisible(true);
				p2_iunbuc.setVisible(true);
				p2_iulbuc.setVisible(true);
				p2_augbuc.setVisible(true);
				p2_septbuc.setVisible(true);
				p2_octbuc.setVisible(true);
				p2_novbuc.setVisible(true);
				p2_decbuc.setVisible(true);
			}
		}
	}
}

