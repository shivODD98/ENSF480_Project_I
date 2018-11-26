package Domian;

import database.DatabaseInterface;

import java.util.ArrayList;

import SharedObjects.*;

public class DocsController {
	private DatabaseInterface DBhelper;

	public DocsController(DatabaseInterface DBhelper) {
		this.DBhelper = DBhelper;
	}

	public ArrayList<Document> getAllDocuments() {
		return DBhelper.getAllDocuments();
	}

	public Document getDocument(int id) {
		return DBhelper.getDocument(id);
	}

	public void updateDocument(Document doc) {
		DBhelper.deleteDocument(doc.getISBN());
		DBhelper.addDocument(doc);
	}

	public void deleteDocument(int id) {
		DBhelper.deleteDocument(id);
	}

	public void addDocument(Document doc) {
		DBhelper.addDocument(doc);
	}

	public ArrayList<Document> getPromotions() {
		return DBhelper.getAllPromotions();
	}

	public void addPromotion(int id) {
		DBhelper.addDocPromotion(id);
	}

	public void removePromotion(int id) {
		DBhelper.removeDocPromotion(id);
	}

}
