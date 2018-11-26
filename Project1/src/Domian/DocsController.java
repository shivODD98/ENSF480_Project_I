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

	public DocsController(DatabaseInterface DBhelper) {
		this.DBhelper = DBhelper;
	}

	public ArrayList<Document> getAllDocuments() {
		return DBhelper.getAllDocuments();
	}

	public Document getDocument(int id) {
		return DBhelper.getDocument(id);
	}
	public Document getDocumentWithContent(int id) {
		Document toreturn = DBhelper.getDocument(id);
		toreturn.setBytes(createByteArray(new File(toreturn.getFilePath())));
		return toreturn;
	}

	public void updateDocument(Document doc) {
		deleteDocument(doc.getISBN());
		addDocument(doc);
	}

	public void deleteDocument(int id) {
		File file = new File(DBhelper.getDocument(id).getFilePath());
		if(file.delete()) {
			System.out.println("File Deleted");
		}
		DBhelper.deleteDocument(id);
	}

	public void addDocument(Document doc) {
		try {
			File file = new File(doc.getTitle());
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(doc.getBytes());
			fileOutputStream.close();

			 File copy = new File("/Users/danielheyns/Desktop/Server/Documents/" +
			 doc.getAuthor() + "/" + doc.getTitle() + "."
			 + getExtension(doc.getFilePath()));
			doc.setFilePath(copy.getAbsolutePath());
			copy.mkdirs();
			copy.createNewFile();
			copyFile(file, copy);
		} catch (IOException e) {
			e.printStackTrace();
		}
		DBhelper.addDocument(doc);
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
	
	/**
	 * copies the contents of one file to another, overwriting all previous data
	 * @param sourceFile is the file to be copied
	 * @param destFile is the file to be copied to
	 * @throws IOException when the source or destfiles could not be found
	 */
	@SuppressWarnings("resource")
	private static void copyFile(File sourceFile, File destFile) throws IOException {
		FileChannel source = null;
		FileChannel destination = null;

		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

}
