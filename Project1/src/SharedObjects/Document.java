package SharedObjects;

import java.io.Serializable;

public class Document implements Serializable{

	private static final long serialVersionUID = 1L;
	private int ISBN;
	private String title;
	private String author;
	private String filePath;
	private String description;
	private DocumentType type;
	private Double price;
	private byte[] bytearray;
	public Document() {setISBN(-1); setTitle(null); setAuthor(null);setFilePath(null);setPrice(null);}
	
	public Document(int ISBN, String title, String author, String filePath, String description, DocumentType type, Double price) {
		this.setISBN(ISBN);
		this.setTitle(title);
		this.setAuthor(author);
		this.setFilePath(filePath);
		this.setDescription(description);
		this.setType(type);
		this.setPrice(price);
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	public byte[] getBytes() {
		return bytearray;
	}
	public void setBytes(byte[] b) {
		bytearray = b;
	}
	
	public String simpleString() {
		return title + " By: "+ author + "\n" + description;
	}

	public DocumentType getType() {
		return type;
	}

	public void setType(DocumentType type) {
		this.type = type;
	}
	
	public String toString() {
		String typeString = new String();
		switch(type) {
		case Book:
			typeString = "Book";
			break;
		case Magazine:
			typeString = "Magazine";
			break;
		case Journal:
			typeString = "Journal";
			break;
		}
		return "ID:"+ISBN+" "+"Title:"+title+" "+"Author:"+author+" "+"Type:"+typeString +" "+"Price:"+price;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	
}
