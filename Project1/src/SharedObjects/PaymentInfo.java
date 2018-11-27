package SharedObjects;

import java.io.Serializable;
import java.util.Date;

public class PaymentInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	private int receiptId;
	private PaymentType type;
	private int Userid;
	private Date date;
	private Document doc;
//	private double sum;
	
	public PaymentInfo(int Userid, PaymentType type, Date date, Document doc) {
		setId(Userid);
		setType(type);
		setDate(date);
		setDoc(doc);
//		setSum(sum);
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	public int getId() {
		return Userid;
	}

	public void setId(int id) {
		this.Userid = id;
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

	public int getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}

//	public double getSum() {
//		return sum;
//	}
//
//	public void setSum(double sum) {
//		this.sum = sum;
//	}
}
