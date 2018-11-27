package Domian;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import SharedObjects.Document;

public class FileSystemInterface {

	public FileSystemInterface() {
		// TODO Auto-generated constructor stub
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
	 * 
	 * @param sourceFile is the file to be copied
	 * @param destFile   is the file to be copied to
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

	public void uploadDocument(Document doc) {
		try {
			File file = new File(doc.getTitle());
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(doc.getBytes());
			fileOutputStream.close();

			File copy = new File("/Documents/" + doc.getAuthor() + "/" + doc.getTitle()
					+ "." + getExtension(doc.getFilePath()));
			doc.setFilePath(copy.getAbsolutePath());
			copy.mkdirs();
			copy.createNewFile();
			copyFile(file, copy);
			file.delete();
;		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void deleteDocument(File file) {
		if (file.delete()) {
			System.out.println("File Deleted");
		}
	}
}
