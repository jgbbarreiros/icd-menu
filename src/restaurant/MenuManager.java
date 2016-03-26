package restaurant;

import java.util.UUID;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Menu manager
 * 
 * The user interface will call these methods 
 * to manipulate the DOM and update it self.
 */
public class MenuManager {
	
	private Document doc;
	private Element rootElement;
	private Element ingredients;
	private Element items;
	private Element menu;

	public MenuManager(Document doc) {
		this.doc = doc;
		
		// DOM base structure
		rootElement = doc.createElement("restaurant");
		doc.appendChild(rootElement);
		
		ingredients = doc.createElement("ingredients");
		rootElement.appendChild(ingredients);
		
		items = doc.createElement("items");
		rootElement.appendChild(items);
		
		menu = doc.createElement("menu");
		rootElement.appendChild(menu);
	}
	
	public boolean addIngredient(String name) {
		NodeList ingredientsList = ingredients.getChildNodes();
		for (int i = 0; i < ingredientsList.getLength(); i++) {
			if (ingredientsList.item(i).getTextContent().equals(name))
				return false;
		}
		Element ingredient = doc.createElement("ingredient");
		ingredient.appendChild(doc.createTextNode(name));
		ingredient.setAttribute("ingrID", UUID.randomUUID().toString());
		ingredients.appendChild(ingredient);
		return true;
	}
	
	public boolean removeIngredient(String name) {
		NodeList ingredientsList = ingredients.getChildNodes();
		for (int i = 0; i < ingredientsList.getLength(); i++) {
			if (ingredientsList.item(i).getTextContent().equals(name)) {
				ingredients.removeChild(ingredientsList.item(i));
				return true;
			}
		}
		return false;
	}

}
