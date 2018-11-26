package Domian;

import database.DatabaseInterface;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
		deleteDocument(doc.getISBN());
		addDocument(doc);
	}

	public void deleteDocument(int id) {
		DBhelper.deleteDocument(id);
	}

	public void addDocument(Document doc) {
		DBhelper.addDocument(doc);
		try {
			File file = new File(doc.getTitle());
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			// fileOutputStream.write(doc.getByte());
			fileOutputStream.close();

			// File copy = new File("/Users/danielheyns/Desktop/Server/Assignments/" +
			// doc.getCourseID() + "/" + .getId() + "."
			// + getExtension(assign.getPath())); This is fucked up cuz im on mac and you
			// guys are on windows, from my project btw
//		File copy = new File("C:\\Assigns\\" + assign.getCourseID() + "\\" + assign.getId() + "."
//					+ getExtension(file.getAbsolutePath()));
//			doc.setFilePath(copy.getAbsolutePath());
//			copy.getParentFile().mkdir();
//			copy.createNewFile();
//			copyFile(file, copy);
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public byte[] createByteArray(File file) {
		long length = file.length();
		byte[] content = new byte[(int) length];
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bos = new BufferedInputStream(fis);
			bos.read(content, 0, (int) length);
			bos.close();
			fis.close();
			return content;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	private static String getExtension(String path) {

		String ext[] = path.split("\\.");
		return ext[ext.length - 1];
	}

}
