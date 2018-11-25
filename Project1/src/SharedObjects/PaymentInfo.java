package SharedObjects;

import java.util.Date;

public class PaymentInfo {
	private PaymentType type;
	private int id;
	private Date date;
	private Document doc;
	
	public PaymentInfo(int id, PaymentType type, Date date, Document doc) {
		setId(id);
		setType(type);
		setDate(date);
		setDoc(doc);
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}
}
