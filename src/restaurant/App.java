package restaurant;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.w3c.dom.Document;

/**
 * Main class
 * 
 * The bridge between all other classes.
 * 
 * User interface that lets us create and edit the restaurant menu.
 * 
 */
public class App extends javax.swing.JFrame implements Runnable {

	private static final long serialVersionUID = 2102223258041677152L;
	
	static final String DOCNAME = "restaurant";
	
	private FileManager fileManager;
	private MenuManager menuManager;
	private Document doc;

	private Font mainFont;

	// Home panel
	private JPanel panelHome;

	// Ingredients panel
	private JPanel panelIngredient;
//	private 
	
	// Item panel
	private JPanel panelItem;

	// Menu Panel
	private JPanel panelMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Thread app = new Thread(new App());
		app.start();
	}

	/**
	 * Create the application.
	 */
	public App() {
		initVar();
		initGui();
	}

	/**
	 * Initialize variables.
	 */
	private void initVar() {
		fileManager = new FileManager();
		doc = fileManager.blank();
		menuManager = new MenuManager(doc);
		mainFont = new Font("Segoe UI Light", Font.PLAIN, 18);
		setUIFont(new FontUIResource(mainFont));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initGui() {
		initFrame();
		initHome();
		initIngredient();
		initItem();
		initMenu();
		setVisible(true);
	}

	/**
	 * Set default font
	 */
	public static void setUIFont(FontUIResource f) {
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value != null && value instanceof FontUIResource)
				UIManager.put(key, f);
		}
	}

	/**
	 * GUI components
	 */

	public void initFrame() {
		setBounds(100, 100, 720, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new CardLayout(0, 0));
	}

	public void initHome() {
		panelHome = new JPanel();
		panelHome.setBackground(Color.DARK_GRAY);
		getContentPane().add(panelHome, "name_22351689436633");
		panelHome.setLayout(null);
		panelHome.setVisible(true);
		
		
		JButton btnIngredients = new JButton("Ingredients");
		btnIngredients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelHome.setVisible(false);
				panelIngredient.setVisible(true);
			}
		});
		
		btnIngredients.setBackground(Color.LIGHT_GRAY);
		btnIngredients.setBounds(187, 116, 120, 80);
		panelHome.add(btnIngredients);
		
		JButton btnItems = new JButton("Items");
		btnItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelHome.setVisible(false);
				panelItem.setVisible(true);
			}
		});
		btnItems.setBackground(Color.LIGHT_GRAY);
		btnItems.setBounds(406, 116, 120, 80);
		panelHome.add(btnItems);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelHome.setVisible(false);
				panelMenu.setVisible(true);
			}
		});
		btnMenu.setBackground(Color.LIGHT_GRAY);
		btnMenu.setBounds(111, 293, 492, 80);
		panelHome.add(btnMenu);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileManager.saveAs(DOCNAME);
			}
		});
		btnSave.setBackground(Color.LIGHT_GRAY);
		btnSave.setBounds(577, 11, 117, 58);
		panelHome.add(btnSave);
	}

	public void initIngredient() {
		panelIngredient = new JPanel();
		panelIngredient.setBackground(Color.DARK_GRAY);
		getContentPane().add(panelIngredient, "name_22353020617238");
		panelIngredient.setLayout(null);
		
		DefaultListModel<String> listMIngredients = new DefaultListModel<String>();
		JList<String> listIngredients = new JList<String>(listMIngredients);
		JScrollPane scroll = new JScrollPane(listIngredients);
		scroll.setBounds(429, 11, 265, 419);
		panelIngredient.add(scroll);
		
		JTextField txtNewIngredient = new JTextField();
		ActionListener addIngredientAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNewIngredient.selectAll();
				String ingredientName = txtNewIngredient.getText();
				if(menuManager.addIngredient(ingredientName))
					listMIngredients.addElement(ingredientName);
			}
		};
		txtNewIngredient.addActionListener(addIngredientAction);
		txtNewIngredient.setFont(new Font("Consolas", Font.PLAIN, 14));
		txtNewIngredient.setText("New Ingredient");
		txtNewIngredient.setBounds(10, 76, 261, 29);
		panelIngredient.add(txtNewIngredient);
		txtNewIngredient.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(addIngredientAction);
		btnAdd.setBackground(Color.LIGHT_GRAY);
		btnAdd.setBounds(295, 65, 113, 43);
		panelIngredient.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ingredientName = listIngredients.getSelectedValue();
				if (menuManager.removeIngredient(ingredientName))
					listMIngredients.removeElement(ingredientName);
			}
		});
		btnRemove.setBackground(Color.LIGHT_GRAY);
		btnRemove.setBounds(295, 154, 113, 43);
		panelIngredient.add(btnRemove);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelIngredient.setVisible(false);
				panelHome.setVisible(true);
			}
		});
		btnBack.setBackground(Color.LIGHT_GRAY);
		btnBack.setBounds(10, 366, 113, 64);
		panelIngredient.add(btnBack);
	}

	public void initItem() {
		// TODO construct Item panel
	}

	public void initMenu() {
		// TODO construct Menu panel
	}

	@Override
	public void run() {
		// TODO do nothing ?
	}

}
