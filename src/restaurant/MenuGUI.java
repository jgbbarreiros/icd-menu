package restaurant;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MenuGUI {
	
	static final String DOCNAME = "restaurant";
	private FileManager fileManager;
	private Document doc;
	private Element rootElement;
	private Element ingredients;
	private Element items;
	private Element menu;

	private JFrame frame;
	
	private JPanel panelHome;
	private JPanel panelIngredient;
	private JPanel panelItem;
	private JPanel panelMenu;
	
	private JTextField txtNewIngredient;
	private JTextField txtNewItem;
	private JTextField txtPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuGUI window = new MenuGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuGUI() {
		initializeVariables();
		initialize();
	}
	
	/**
	 * Initialize variables.
	 */
	
	private void initializeVariables() {
		fileManager = new FileManager();
		doc = fileManager.blank();
		rootElement = doc.createElement("restaurant");
		doc.appendChild(rootElement);
		ingredients = doc.createElement("ingredients");
		rootElement.appendChild(ingredients);
		items = doc.createElement("items");
		rootElement.appendChild(items);
		menu = doc.createElement("menu");
		rootElement.appendChild(menu);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		/**
		 * Panel Home
		 */
		panelHome = new JPanel();
		panelHome.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panelHome, "name_22351689436633");
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
		btnIngredients.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
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
		btnItems.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
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
		btnMenu.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnMenu.setBounds(111, 293, 492, 80);
		panelHome.add(btnMenu);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileManager.saveAs(DOCNAME);
			}
		});
		btnSave.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnSave.setBackground(Color.LIGHT_GRAY);
		btnSave.setBounds(577, 11, 117, 58);
		panelHome.add(btnSave);
		
		/**
		 * Panel Ingredient
		 */
		panelIngredient = new JPanel();
		panelIngredient.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panelIngredient, "name_22353020617238");
		panelIngredient.setLayout(null);
		panelIngredient.setVisible(false);
		
		JList<String> listIngredients = new JList<String>();
		listIngredients.setBounds(429, 11, 265, 419);
		panelIngredient.add(listIngredients);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Element ingredient = doc.createElement("ingredient");
				ingredients.appendChild(ingredient);
				ingredient.setAttribute("ingrID", UUID.randomUUID().toString());
				ingredient.setNodeValue(txtNewIngredient.getText());
//				listIngredients.setListData();
			}
		});
		btnAdd.setBackground(Color.LIGHT_GRAY);
		btnAdd.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnAdd.setBounds(295, 65, 113, 43);
		panelIngredient.add(btnAdd);
		
		txtNewIngredient = new JTextField();
		txtNewIngredient.setFont(new Font("Consolas", Font.PLAIN, 14));
		txtNewIngredient.setText("New Ingredient");
		txtNewIngredient.setBounds(10, 76, 261, 29);
		panelIngredient.add(txtNewIngredient);
		txtNewIngredient.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelIngredient.setVisible(false);
				panelHome.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnBack.setBackground(Color.LIGHT_GRAY);
		btnBack.setBounds(10, 366, 113, 64);
		panelIngredient.add(btnBack);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBackground(Color.LIGHT_GRAY);
		btnRemove.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnRemove.setBounds(295, 154, 113, 43);
		panelIngredient.add(btnRemove);
		
		/**
		 * Panel Item
		 */
		panelItem = new JPanel();
		panelItem.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panelItem, "name_22354259651295");
		panelItem.setLayout(null);
		panelItem.setVisible(false);
		
		JList list_1 = new JList();
		list_1.setBounds(448, 11, 246, 419);
		panelItem.add(list_1);
		
		JButton btnBack_1 = new JButton("Back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelItem.setVisible(false);
				panelHome.setVisible(true);
			}
		});
		
		ActionListener a = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelItem.setVisible(false);
				panelHome.setVisible(true);
			}
		};
		btnBack_1.setBackground(Color.LIGHT_GRAY);
		btnBack_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnBack_1.setBounds(10, 378, 110, 52);
		panelItem.add(btnBack_1);
		
		JButton button = new JButton(">");
		button.setBackground(Color.LIGHT_GRAY);
		button.setFont(new Font("Segoe UI Light", Font.PLAIN, 10));
		button.setBounds(205, 203, 39, 39);
		panelItem.add(button);
		
		JButton button_1 = new JButton("<");
		button_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 10));
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setBounds(205, 315, 39, 39);
		panelItem.add(button_1);
		
		JList list_2 = new JList();
		list_2.setBounds(10, 191, 201, 176);
		panelItem.add(list_2);
		
		JList list_3 = new JList();
		list_3.setBounds(237, 191, 201, 176);
		panelItem.add(list_3);
		
		JButton btnAdd_1 = new JButton("Add");
		btnAdd_1.setBackground(Color.LIGHT_GRAY);
		btnAdd_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnAdd_1.setBounds(29, 101, 110, 45);
		panelItem.add(btnAdd_1);
		
		txtNewItem = new JTextField();
		txtNewItem.setFont(new Font("Consolas", Font.PLAIN, 14));
		txtNewItem.setText("New Item");
		txtNewItem.setBounds(29, 52, 248, 31);
		panelItem.add(txtNewItem);
		txtNewItem.setColumns(10);
		
		JButton btnRemove_1 = new JButton("Remove");
		btnRemove_1.setBackground(Color.LIGHT_GRAY);
		btnRemove_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnRemove_1.setBounds(166, 101, 110, 45);
		panelItem.add(btnRemove_1);
		
		JRadioButton radioButton = new JRadioButton("Starter");
		radioButton.setForeground(Color.WHITE);
		radioButton.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		radioButton.setBackground(Color.DARK_GRAY);
		radioButton.setBounds(314, 29, 109, 23);
		panelItem.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("Meat");
		radioButton_1.setForeground(Color.WHITE);
		radioButton_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		radioButton_1.setBackground(Color.DARK_GRAY);
		radioButton_1.setBounds(314, 55, 109, 23);
		panelItem.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("Fish");
		radioButton_2.setForeground(Color.WHITE);
		radioButton_2.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		radioButton_2.setBackground(Color.DARK_GRAY);
		radioButton_2.setBounds(314, 83, 109, 23);
		panelItem.add(radioButton_2);
		
		JRadioButton radioButton_3 = new JRadioButton("Sweet");
		radioButton_3.setForeground(Color.WHITE);
		radioButton_3.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		radioButton_3.setBackground(Color.DARK_GRAY);
		radioButton_3.setBounds(314, 111, 109, 23);
		panelItem.add(radioButton_3);
		
		JRadioButton radioButton_4 = new JRadioButton("Fruit");
		radioButton_4.setForeground(Color.WHITE);
		radioButton_4.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		radioButton_4.setBackground(Color.DARK_GRAY);
		radioButton_4.setBounds(314, 135, 109, 23);
		panelItem.add(radioButton_4);
		
		/**
		 * Panel Menu
		 */
		panelMenu = new JPanel();
		panelMenu.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panelMenu, "name_22402817351008");
		panelMenu.setLayout(null);
		panelMenu.setVisible(false);
		
		JButton btnBack_2 = new JButton("Back");
		btnBack_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMenu.setVisible(false);
				panelHome.setVisible(true);
			}
		});
		btnBack_2.setBackground(Color.LIGHT_GRAY);
		btnBack_2.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnBack_2.setBounds(10, 375, 122, 55);
		panelMenu.add(btnBack_2);
		
		JLabel lblLunch = new JLabel("Lunch");
		lblLunch.setHorizontalAlignment(SwingConstants.CENTER);
		lblLunch.setFont(new Font("Segoe UI Light", Font.PLAIN, 32));
		lblLunch.setForeground(Color.WHITE);
		lblLunch.setBounds(185, 11, 122, 49);
		panelMenu.add(lblLunch);
		
		JLabel lblDinner = new JLabel("Dinner");
		lblDinner.setHorizontalAlignment(SwingConstants.CENTER);
		lblDinner.setForeground(Color.WHITE);
		lblDinner.setFont(new Font("Segoe UI Light", Font.PLAIN, 32));
		lblDinner.setBounds(371, 3, 137, 64);
		panelMenu.add(lblDinner);
		
		JList list_5 = new JList();
		list_5.setBounds(529, 11, 165, 419);
		panelMenu.add(list_5);
		
		JList list_6 = new JList();
		list_6.setBounds(165, 58, 165, 296);
		panelMenu.add(list_6);
		
		JList list_4 = new JList();
		list_4.setBounds(354, 58, 165, 296);
		panelMenu.add(list_4);
		
		JRadioButton rdbtnWorkday = new JRadioButton("Workday");
		rdbtnWorkday.setBackground(Color.DARK_GRAY);
		rdbtnWorkday.setForeground(Color.WHITE);
		rdbtnWorkday.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		rdbtnWorkday.setBounds(25, 88, 109, 23);
		panelMenu.add(rdbtnWorkday);
		
		JRadioButton rdbtnHoliday = new JRadioButton("Holiday");
		rdbtnHoliday.setForeground(Color.WHITE);
		rdbtnHoliday.setBackground(Color.DARK_GRAY);
		rdbtnHoliday.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		rdbtnHoliday.setBounds(25, 126, 109, 23);
		panelMenu.add(rdbtnHoliday);
		
		JButton btnAdd_2 = new JButton("Add");
		btnAdd_2.setBackground(Color.LIGHT_GRAY);
		btnAdd_2.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnAdd_2.setBounds(198, 375, 109, 23);
		panelMenu.add(btnAdd_2);
		
		JButton btnRemove_2 = new JButton("Remove");
		btnRemove_2.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnRemove_2.setBackground(Color.LIGHT_GRAY);
		btnRemove_2.setBounds(198, 409, 109, 23);
		panelMenu.add(btnRemove_2);
		
		JButton button_2 = new JButton("Add");
		button_2.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		button_2.setBackground(Color.LIGHT_GRAY);
		button_2.setBounds(391, 373, 109, 23);
		panelMenu.add(button_2);
		
		JButton button_3 = new JButton("Remove");
		button_3.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		button_3.setBackground(Color.LIGHT_GRAY);
		button_3.setBounds(391, 407, 109, 23);
		panelMenu.add(button_3);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Consolas", Font.PLAIN, 14));
		txtPrice.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrice.setText("10");
		txtPrice.setBounds(334, 389, 36, 36);
		panelMenu.add(txtPrice);
		txtPrice.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		lblPrice.setBackground(Color.DARK_GRAY);
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setBounds(324, 352, 49, 32);
		panelMenu.add(lblPrice);
		
		JLabel label = new JLabel("$");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBackground(Color.DARK_GRAY);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		label.setBounds(306, 391, 36, 27);
		panelMenu.add(label);
	}
}
