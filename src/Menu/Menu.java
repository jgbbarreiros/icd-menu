package Menu;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Menu {
	
	static final String DOCNAME = "menu";
	private DocManager docManager = null;
	private Document doc = null;
	
	public Menu() {
		docManager = new DocManager();
		doc = docManager.blankDocument();
		Element rootElement = doc.createElement("menu");
		doc.appendChild(rootElement);
		docManager.saveAs(DOCNAME);
		docManager.load(DOCNAME);
	}

	public static void main(String[] args) {
		Menu menu = new Menu();
	}
}
