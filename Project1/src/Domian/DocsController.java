package Domian;

import database.DatabaseInterface;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import SharedObjects.*;

public class DocsController {
	private DatabaseInterface DBhelper;
	private FileSystemInterface FileHelper;

	public DocsController(DatabaseInterface DBhelper) {
		this.DBhelper = DBhelper;
		FileHelper = new FileSystemInterface();
	}

	public ArrayList<Document> getAllDocuments() {
		return DBhelper.getAllDocuments();
	}

	public Document getDocument(int id) {
		return DBhelper.getDocument(id);
	}
	public Document getDocumentWithContent(int id) {
		Document toreturn = DBhelper.getDocument(id);
		toreturn.setBytes(FileHelper.createByteArray(new File("C:////Users////Documents////GitHub////ENSF480_Project_I////Project1////SavedDocuments////" 
					+ toreturn.getTitle() + "." + FileSystemInterface.getExtension(toreturn.getFilePath()))));
		return toreturn;
	}

	public void updateDocument(Document doc) {
		deleteDocument(doc.getISBN());
		addDocument(doc);
	}

	public void deleteDocument(int id) {
		FileHelper.deleteDocument(new File("C:////Users////Documents////GitHub////ENSF480_Project_I////Project1////SavedDocuments////" 
										+ DBhelper.getDocument(id).getTitle() + "." + FileSystemInterface.getExtension(DBhelper.getDocument(id).getFilePath())));
		DBhelper.deleteDocument(id);
	}

	public Document addDocument(Document doc) {
		FileHelper.uploadDocument(doc);
		return DBhelper.addDocument(doc);
	}

	public ArrayList<Document> getPromotions() {
		return DBhelper.getAllPromotions();
	}

	public void addPromotion(int id) {
		DBhelper.addDocPromotion(id);
		NotifySingleton.getInstance().updateList(this);
	}

	public void removePromotion(int id) {
		DBhelper.removeDocPromotion(id);
		NotifySingleton.getInstance().updateList(this);
	}
}


