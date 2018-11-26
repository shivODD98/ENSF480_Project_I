package Domian;
import database.*;
import SharedObjects.*;


public class OrderController {
	private DatabaseInterface dbHelper;

	
	public OrderController(DatabaseInterface dbHelper) {
		setDbHelper(dbHelper);
	}
	
	
	public void placeOrder(PaymentInfo info) {
		dbHelper.placePayment(info, getDocPrice(info.getDoc().getISBN()));
	}
	
//	public PaymentInfo getOrder() {
//		
//	}
	
	public void deleteOrder(PaymentInfo info) {
		dbHelper.deletePayment(info.getId());
	}
	
//	public double getOrderSum() {
//		
//	}
	
	public double getDocPrice(int ISBN) {
		return dbHelper.getDocPrice(ISBN);
	}
	
	public DatabaseInterface getDbHelper() {
		return dbHelper;
	}

	public void setDbHelper(DatabaseInterface dbHelper) {
		this.dbHelper = dbHelper;
	}
}
