package restaurant;

import java.awt.CardLayout;
import java.awt.Font;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

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

	private FileManager fileManager;

	private Font mainFont;

	// Home panel
	private JPanel panelHome;

	// Ingredients panel
	private JPanel panelIngredient;

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
		// TODO construct Home panel
	}

	public void initIngredient() {
		// TODO construct Ingredient panel
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
